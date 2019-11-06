package com.waiter.utils.idgen;

import com.waiter.utils.MacUtils;
import com.waiter.utils.PropertiesLoaderUtils;
import com.waiter.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.management.ManagementFactory;

/**
 * @ClassName Snowflake
 * @Description 雪花算法生成主键ID
 * 雪花算法生成ID长度为64位，生成规则为：
 * 0---0000000000 0000000000 0000000000 0000000000 0 --- 00000 ---00000 ---000000000000
 * 第一位未使用；接下来的41位表示毫秒级的时间，41位的时间戳可以支持到2082年(timestamp)；接着的5位表示数据中心的标识位(dataCenterId)；再接下来的5位为机器ID(或线程ID,workerId)；最后12位为改毫秒内的ID序列(sequence)
 * 当然，这个规则可以根据实际业务需求进行调整
 * @Author lizhihui
 * @Date 2019/2/24 17:01
 * @Version 1.0
 */
public class Snowflake {
    private static final Logger logger = LoggerFactory.getLogger(Snowflake.class);

    /**
     * 时间起点
     */
    private static final long TIME_START;

    /**
     * 数据中心标识位长度
     */
    private static final long DATA_CENTER_ID_BITS;

    /**
     * 机器标识位长度
     */
    private static final long WORKER_ID_BITS;

    /**
     * 毫秒内自增位长度
     */
    private static final long SEQUENCE_BITS;

    /**
     * 最大数据中心ID
     */
    private static final long MAX_DATA_CENTER_ID;

    /**
     * 最大机器ID
     */
    private static final long MAX_WORKER_ID;

    /**
     * 最大的序列号
     */
    private static final long MAX_SEQUENCE;

    /**
     * 时间戳左偏移位
     */
    private static final long TIMESTAMP_SHIFT;

    /**
     * 数据中心左偏移位
     */
    private static final long DATA_CENTER_ID_SHIFT;

    /**
     * 机器ID的左偏移位
     */
    private static final long WORKER_ID_SHIFT;

    /**
     * 上次产生ID的时间戳
     */
    private long lastTimestamp = -1L;

    /**
     * 数据中心ID
     */
    private long dataCenterId;

    /**
     * 机器ID（实际是线程标识）
     */
    private long workerId;

    /**
     * 毫秒内序列
     */
    private long sequence = 0L;

    private static Snowflake snowflake;

    static {
        PropertiesLoaderUtils loaderUtils = new PropertiesLoaderUtils("config.properties");
        TIME_START = loaderUtils.getLong("snowflake.starTime");
        WORKER_ID_BITS = loaderUtils.getLong("snowflake.workIdBits");
        DATA_CENTER_ID_BITS = loaderUtils.getLong("snowflake.dataCenterIdBits");
        SEQUENCE_BITS = loaderUtils.getLong("snowflake.sequenceBit");

        //-1L用在java中用反码表示为64个1，-1L进行DATA_CENTER_ID_BITS位左移操作后，左边的DATA_CENTER_ID_BITS位都变为了0，然后和-1L再做一次异或操作，变成了左边DATA_CENTER_ID_BITS位都为1的正数
        MAX_DATA_CENTER_ID = -1L ^ (-1L << DATA_CENTER_ID_BITS);
        MAX_WORKER_ID = -1L ^ (-1L << WORKER_ID_BITS);
        MAX_SEQUENCE = -1L ^ (-1L << SEQUENCE_BITS);

        WORKER_ID_SHIFT = SEQUENCE_BITS;
        DATA_CENTER_ID_SHIFT = WORKER_ID_BITS + SEQUENCE_BITS;
        TIMESTAMP_SHIFT = DATA_CENTER_ID_SHIFT + SEQUENCE_BITS;
    }

    public static long getId(){
        if(snowflake == null){
            synchronized (Snowflake.class){
                if(snowflake == null){
                    snowflake = new Snowflake();
                }
            }
        }
        return snowflake.nextId();
    }

    /**
     * 构造函数
     */
    public Snowflake(){
        this.dataCenterId = getDataCenterId();
        this.workerId = getMaxWorkerId(dataCenterId);
    }

    /**
     * 构造一个Snowflake
     * 多个Snowflake之间的入参（机器ID和数据中心ID的组合），必须不同
     * @param workerId
     * @param dataCenterId
     */
    public Snowflake(long workerId, long dataCenterId){
        if(workerId > MAX_WORKER_ID || workerId < 0){
            throw new IllegalArgumentException(
                    String.format("worker Id can't be greater than %d or less than 0", MAX_WORKER_ID));
        }

        if(dataCenterId > MAX_DATA_CENTER_ID || dataCenterId < 0){
            throw new IllegalArgumentException(
                    String.format("datacenter Id can't be greater than %d or less than 0", MAX_DATA_CENTER_ID));
        }
        this.workerId = workerId;
        this.dataCenterId = dataCenterId;
        logger.info(String.format(
                "worker starting. timestamp left shift %d, datacenter id bits %d, worker id bits %d, sequence bits %d, workerid %d",
                TIMESTAMP_SHIFT, DATA_CENTER_ID_BITS, WORKER_ID_BITS, SEQUENCE_BITS, workerId));
    }

    /**
     * 生成下一个ID
     * @return
     */
    public synchronized long nextId(){
        long timestamp = timeGen();
        if(timestamp < lastTimestamp){
            logger.error(String.format("clock is moving backwards. Rejecting requests until %d.", lastTimestamp));
            throw new RuntimeException(String.format(
                    "Clock moved backwards. Refusing to generate id for %d milliseconds", lastTimestamp - timestamp));
        }

        if(lastTimestamp == timestamp){
            sequence = (sequence + 1) & MAX_SEQUENCE;
            if(sequence == 0){
                //当前毫秒的序列已经达到最大了，等待下一秒再生成
                timestamp = waitNextMillis(lastTimestamp);
            }
        } else{
            sequence = 0L;
        }
        lastTimestamp = timestamp;
        return ((timestamp - TIME_START) << TIMESTAMP_SHIFT) | (dataCenterId << DATA_CENTER_ID_SHIFT) | (workerId << WORKER_ID_SHIFT) | sequence;
    }

    /**
     * 等待下一个毫秒的到来
     * @param lastTimestamp
     * @return
     */
    private long waitNextMillis(long lastTimestamp){
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp){
            timestamp = timeGen();
        }
        return timestamp;
    }

    /**
     * 获取当前时间的毫秒数
     * @return
     */
    private long timeGen(){
        return System.currentTimeMillis();
    }

    /**
     * 生成数据中心ID
     * 根据MAC地址来生成数据中心ID,每台服务器的mac地址是不变的，所以生成的ID是固定的
     * @return
     */
    private static long getDataCenterId(){
        long id = 0L;
        try{
            String macAddr = MacUtils.getMac();
            long macLong = MacUtils.mac2String(macAddr);
            id = macLong % (MAX_DATA_CENTER_ID + 1);
        } catch (Exception e){
            logger.warn("获取数据中心ID出错:"+e.getMessage());
        }
        return id;
    }

    /**
     * 生成机器ID,根据java进行的PID生成
     * @param dataCenterId
     * @return
     */
    private static long getMaxWorkerId(long dataCenterId){
        StringBuffer mpid = new StringBuffer();
        mpid.append(dataCenterId);
        String name = ManagementFactory.getRuntimeMXBean().getName();
        if(StringUtils.isNotEmpty(name)){
            //获取JVM的PID
            mpid.append(name.split("@")[0]);
        }
        return (mpid.toString().hashCode() & 0xffff) % (MAX_WORKER_ID + 1);
    }

    public static void main(String[] args){
        for(int i=0;i<10;i++){
            System.out.println(getId());
        }
    }
}
