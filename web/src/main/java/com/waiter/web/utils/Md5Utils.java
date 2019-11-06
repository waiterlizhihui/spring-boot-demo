package com.waiter.web.utils;

import java.security.MessageDigest;


public class Md5Utils {


    public static void main(String[] args) {

        //System.out.println(Md5Crypt.("https://m.jr.jd.com/flowh5/team-bag/dist/activity.html?pin=9d98bd01762988705af93e5ee5074610&actCode=81CA43DFAF&markId=77e3044c803850156f17ea3dbfabe8deb35592d364a861d3bc94f926e4793b8a55862397b9e0e6aa83294185e6a098e0&source=zf001&utm_source=Android&utm_term=wxfriends&from=singlemessage"));


    }

    public static String stringToMd5(String inputStr) {

        String result = "";
        MessageDigest md5;
        byte[] byt = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
            md5.update((inputStr).getBytes("UTF-8"));
            byt = md5.digest();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


        int i;
        StringBuffer buf = new StringBuffer("");

        for (int offset = 0; offset < byt.length; offset++) {
            i = byt[offset];
            if (i < 0) {
                i += 256;
            }
            if (i < 16) {
                buf.append("0");
            }
            buf.append(Integer.toHexString(i));
        }

        result = buf.toString();
        return result;

    }


}
