;
/*  预设颜色列表 */
var colorList1 = ["#c23531", "#2f4554", "#61a0a8", "#d48265", "#91c7ae", "#749f83", "#ca8622", "#bda29a", "#6e7074", "#546570", "#c4ccd3"];
var colorList2 = ['#dd6b66', '#759aa0', '#e69d87', '#8dc1a9', '#ea7e53', '#eedd78', '#73a373', '#73b9bc', '#7289ab', '#91ca8c', '#f49f42'];
var colorList3 = ['#2ec7c9', '#b6a2de', '#5ab1ef', '#ffb980', '#d87a80', '#8d98b3', '#e5cf0d', '#97b552', '#95706d', '#dc69aa', '#07a2a4', '#9a7fd1', '#588dd5', '#f5994e', '#c05050', '#59678c', '#c9ab00', '#7eb00a', '#6f5553', '#c14089'];

/* ------------------------------ 访问趋势 ------------------------------ */
// 初始化
var trendChart = echarts.init(document.getElementById('trend-chart'));
// 配置
trendChart.setOption({
    title: {
        text: '访问趋势图',
        left: 'center'
    },
    color: colorList2,
    tooltip: {
        trigger: 'axis'
    },
    legend: {
        bottom: 3,
        itemGap: 20,
        itemWidth: 35,
        itemHeight: 15,
        textStyle: {
            fontSize: 14
        },
        data: []
    },
    grid: {
        top: 45,
        left: 5,
        right: 5,
        bottom: 40,
        containLabel: true
    },
    xAxis: {
        type: 'category',
        boundaryGap: true,
        axisTick: {
            alignWithLabel: true
        },
        data: []
    },
    yAxis: [{
        type: 'value'
    }],
    series: []
});

// 赋值方法
function setTrendChartVal(data) {
    var chartData = {
        legend: {data: []},
        xAxis: {data: []},
        series: []
    };
    // 设置X轴的值
    chartData.xAxis.data = data.time;
    for (let i in data.value) {
        // 设置图例的值
        chartData.legend.data.push(i);
        // 设置Y轴的值
        chartData.series.push({name: i, type: 'line', data: data.value[i]});
    }
    ;
    // 将值赋到chart
    trendChart.setOption(chartData);
};

/* ------------------------------ 地域分布-国内分布 ------------------------------ */
// 初始化
var mapChart = echarts.init(document.getElementById('map-chart'));
// 配置
mapChart.setOption({
    title: {
        text: '国内访问分布图',
        left: 'center'
    },
    tooltip: {
        trigger: 'item'
    },
    visualMap: {
        type: 'continuous',
        left: 3,
        bottom: 10,
        itemWidth: 18,
        itemHeight: 120,
        text: ['高', '低'],
        inRange: {
            color: ['lightskyblue', 'yellow', 'orangered']
        },
        calculable: true
    },
    roamController: {
        show: false,
        x: 'right',
        mapTypeControl: {
            'china': true
        }
    },
    series: [{
        name: '地区分布',
        type: 'map',
        mapType: 'china',
        roam: true,
        top: 50,
        zoom: 1.1,
        scaleLimit: {
            min: 1,
            max: 2
        },
        label: {
            normal: {
                show: true,
                fontSize: 11,
            },
            emphasis: {
                show: true
            }
        }
    }]
});

// 赋值方法
function setMapChartVal(data) {
    var chartData = {
        visualMap: {min: 0, max: 0},
        series: []
    };
    // 将数组从大到小排序
    var newData = data.sort(function (a, b) {
        var v1 = a['value'];
        var v2 = b['value'];
        return v2 - v1;
    });
    // 设置最大值
    chartData.visualMap.max = newData[0].value;
    // 设置地图的值
    chartData.series.push({data: newData});
    // 将值赋到chart
    mapChart.setOption(chartData);
};

/* ------------------------------ 地域分布-地区TOP10 ------------------------------ */
// 初始化
var areaChart = echarts.init(document.getElementById('area-chart'));
// 配置
areaChart.setOption({
    color: ['#5CC0DD'],
    title: {
        text: '访问地区TOP10',
        left: 'center'
    },
    grid: {
        top: '10%',
        left: '4%',
        right: '4%',
        bottom: '2%',
        containLabel: true
    },
    xAxis: {
        type: 'value'
    },
    yAxis: {
        type: 'category',
        inverse: true,
        data: []
    },
    series: [{
        name: '地区',
        type: 'bar',
        label: {normal: {position: 'right', show: true}},
        data: []
    }]
});

// 赋值方法
function setAreaChartVal(data) {
    var chartData = {
        yAxis: {data: []},
        series: [{data: []}]
    };
    // 将数组从大到小排序
    var newData = data.sort(function (a, b) {
        var v1 = a['value'];
        var v2 = b['value'];
        return v2 - v1;
    });
    // 截取前10个
    newData.splice(10);
    for (let i of newData) {
        // 设置Y轴的名
        chartData.yAxis.data.push(i.name);
        // 设置Y轴的值
        chartData.series[0].data.push(i.value);
    }
    ;
    // 将值赋到chart
    areaChart.setOption(chartData);
};

/* ------------------------------ 操作系统-设备统计 ------------------------------ */
// 初始化
var drivesChart = echarts.init(document.getElementById('device-chart'));
// 配置
drivesChart.setOption({
    color: colorList1,
    title: {
        text: '客户端设备统计',
        left: 'center'
    },
    tooltip: {
        trigger: 'item',
        formatter: "{a} <br/>{b} : {c} ({d}%)"
    },
    legend: {
        bottom: 10,
        left: 'center',
        data: []
    },
    series: [{
        name: '终端设备',
        type: 'pie',
        radius: '55%',
        center: ['50%', '50%'],
        selectedMode: 'single',
        itemStyle: {
            emphasis: {
                shadowBlur: 10,
                shadowOffsetX: 0,
                shadowColor: 'rgba(0, 0, 0, 0.5)'
            }
        },
        data: []
    }]
});

// 赋值方法
function setDrivesChartVal(data) {
    var chartData = {
        legend: {data: []},
        series: [{data: []}]
    };
    // 将数组从大到小排序
    var newData = data.sort(function (a, b) {
        var v1 = a['value'];
        var v2 = b['value'];
        return v2 - v1;
    });
    for (let v of newData) {
        // 设置图例的值
        chartData.legend.data.push(v.name);
    }
    ;
    // 设置饼图的值
    chartData.series[0].data = newData;
    // 将值赋到chart
    drivesChart.setOption(chartData);
};


/* 操作系统TOP */
// var charDrivesTop = echarts.init(document.getElementById('device-top-chart'));
// charDrivesTop.setOption({
//     color: colorList,
//     title: {
//         text: '设备系统TOP',
//         left: 'center'
//     },
//     tooltip: {
//         trigger: 'item',
//         formatter: "{a} <br/>{b} : {c} ({d}%)"
//     },
//     legend: {
//         bottom: 10,
//         left: 'center',
//         data: []
//     },

// });
// var titlename = ["Android","PC"];
// var baifenbi = ["0.5714","0.4286"];
var barchart = {
    title: {
        text: '设备系统TOP',
        left: 'center'
    },
    xAxis: [
        {show: false},
        {show: false}
    ],
    yAxis: {
        show: false,
        inverse: true,
        type: 'category',
        axisTick: {
            show: false,
        },
        axisLine: {
            show: false,
        },
    },
    series: [
        //背景色--------------------我是分割线君------------------------------//
        {
            show: true,
            type: 'bar',
            barGap: '-100%',
            barWidth: '50%',
            itemStyle: {
                normal: {
                    barBorderRadius: 5,
                    color: '#f5f5f5'
                },
                emphasis: {
                    color: '#f5f5f5'
                }
            },
            z: 1,
            data: [1, 1, 1, 1, 1],
        },
        //蓝条--------------------我是分割线君------------------------------//
        {
            show: true,
            type: 'bar',
            barGap: '-100%',
            barWidth: '50%', //统计条宽度
            itemStyle: {
                normal: {
                    barBorderRadius: 5, //统计条弧度
                }

            },
            max: 1,
            label: {

                normal: {
                    show: true,
                    textStyle: {
                        color: '#333', //百分比颜色
                    },
                    position: 'top',
                    //百分比格式
                    formatter: function (data) {
                        // console.log(data)
                        return (baifenbi[data.dataIndex] * 100).toFixed(2) + '%';
                    },
                }
            },
            labelLine: {
                show: false,
            },
            z: 2,
            data: baifenbi,
        },
        //数据条--------------------我是分割线君------------------------------//
        {
            show: false,
            type: 'bar',
            xAxisIndex: 1, //代表使用第二个X轴刻度!!!!!!!!!!!!!!!!!!!!!!!!
            barGap: '-100%',
            barWidth: '35%', //统计条宽度
            itemStyle: {
                normal: {
                    barBorderRadius: 5,
                    color: 'rgba(22,203,115,0.05)'
                },
            },
            label: {
                normal: {
                    show: true,
                    //label 的position位置可以是top bottom left,right,也可以是固定值
                    //在这里需要上下统一对齐,所以用固定值
                    position: [0, '-100%'],
                    rich: { //富文本
                        black: { //自定义颜色
                            color: '#000',
                        },
                        green: {
                            color: '#70DDA7',
                        },
                        yellow: {
                            color: '#FEC735',
                        },
                    },
                    formatter: function (data) {
                        //富文本固定格式{colorName|这里填你想要写的内容}
                        return '{black|' + titlename[data.dataIndex] + '}';

                    }
                }
            },
            data: [0, 0, 0, 0, 0]
        }

    ]
};

// var deviceTopChart =  echarts.init(document.getElementById('device-top-chart'));
var deviceTop = {
    setOption: function () {
    }
};
var titlename = ["Android", "PC"];
var baifenbi = ["0.5714", "0.4286"];
var colorLists = ['red', 'blue', 'green']
deviceTop.setOption({
    title: {
        text: '设备系统TOP',
        left: 'center'
    },
    xAxis: [
        {show: false},
        {show: false}
    ],
    yAxis: {
        show: false,
        inverse: true,
        type: 'category',
        axisTick: {show: false},
        axisLine: {show: false}
    },
    series: [
        {
            show: true,
            type: 'bar',
            barGap: '-100%',
            barWidth: '50%',
            itemStyle: {
                normal: {
                    barBorderRadius: 5,
                    color: '#f5f5f5'
                },
                emphasis: {
                    color: '#f5f5f5'
                }
            },
            z: 1,
            data: [1, 1, 1, 1, 1],
        },
        {
            show: true,
            type: 'bar',
            barGap: '-100%',
            barWidth: '50%',
            labelLine: {show: false},
            max: 1,
            itemStyle: {
                normal: {
                    color: 'red',
                    barBorderRadius: 5,
                }
            },
            z: 2,
            // label: {

            //     normal: {
            //         show: true,
            //         textStyle: {
            //             color: '#333',
            //         },
            //         position: 'top',

            //         formatter: function(data) {

            //             return (baifenbi[data.dataIndex] * 100).toFixed(2) + '%';
            //         },
            //     }
            // },
            data: baifenbi,
        },

        {
            show: false,
            type: 'bar',
            xAxisIndex: 1,
            barGap: '-100%',
            barWidth: '50%',
            itemStyle: {
                normal: {
                    barBorderRadius: 5,
                    color: 'rgba(255,255,255,0.0)'
                },
            },
            label: {
                normal: {
                    show: true,
                    position: [0, '-60%'],
                    color: '#000',
                    formatter: function (data) {
                        //富文本固定格式{colorName|这里填你想要写的内容}
                        return titlename[data.dataIndex] + '： ' + (baifenbi[data.dataIndex] * 100).toFixed(2) + '%';

                    }
                }
            },
            data: [0, 0, 0, 0, 0]
        }


    ]
});


/* ------------------------------ 操作系统-设备系统TOP ------------------------------ */
// 初始化
var deviceTopChart = echarts.init(document.getElementById('device-top-chart'));
// 配置
deviceTopChart.setOption({
    title: {
        text: '设备系统TOP',
        left: 'center'
    },
    grid: {
        top: '10%',
        left: '4%',
        right: '6%',
        bottom: '2%',
        containLabel: true
    },
    xAxis: {
        show: false,
        type: 'value'
    },
    yAxis: {
        show: false,
        type: 'category',
        inverse: true,
        data: []
    },
    series: [
        // 背景条
        {
            type: 'bar',
            barGap: '-100%',
            barCategoryGap: '60%',
            z: 1,
            itemStyle: {
                normal: {
                    barBorderRadius: 5,
                    color: '#f5f5f5'
                },
                emphasis: {
                    color: '#f5f5f5'
                }
            },
            data: []
        },
        // 进度条
        {
            type: 'bar',
            barGap: '-100%',
            barCategoryGap: '60%',
            z: 2,
            itemStyle: {
                normal: {
                    barBorderRadius: 5,
                    color: function (params) {
                        return colorList1[params.dataIndex]
                    }
                }
            },
            label: {
                normal: {
                    show: true,
                    position: ['5', '-65%'],
                    fontSize: 15,
                    color: '#333',
                    formatter: function (d) {
                        return d.name + '： ' + parseFloat((d.value * 100).toFixed(2)) + "%";
                    }
                }
            },
            data: []
        }
    ]
});

// 赋值方法
function setDeviceTopChartVal(data) {
    var chartData = {
        yAxis: {data: []},
        series: [
            {data: []},
            {data: []}
        ]
    };
    var total = 0;
    // 将数组从大到小排序
    var newData = data.sort(function (a, b) {
        var v1 = a['value'];
        var v2 = b['value'];
        return v2 - v1;
    });
    for (let v of newData) {
        // 计算总数
        total += v.value;
    }
    ;
    for (let i of newData) {
        // 设置Y轴的名
        chartData.yAxis.data.push(i.name);
        // 设置背景条的值
        chartData.series[0].data.push(1);
        // 设置进度条的值
        chartData.series[1].data.push(parseFloat((i.value / total).toFixed(4)));
    }
    ;
    // 将值赋到chart
    deviceTopChart.setOption(chartData);
};

deviceTopChartData = [
    {name: "Android", value: 778},
    {name: "PC", value: 696},
    {name: "iPhone", value: 586},
    {name: "iPad", value: 107},
    {name: "Wphone", value: 33}
]
setDeviceTopChartVal(deviceTopChartData)


/* ------------------------------ 访问环境-浏览器统计 ------------------------------ */
// 初始化
var browserChart = echarts.init(document.getElementById('browser-chart'));
// 配置
browserChart.setOption({
    color: colorList3,
    title: {
        text: '浏览器统计',
        left: 'center'
    },
    tooltip: {
        trigger: 'item',
        formatter: "{a} <br/>{b} : {c} ({d}%)"
    },
    legend: {
        show: false,
        bottom: 10,
        left: 'center',
        data: []
    },
    series: [{
        name: '浏览器',
        type: 'pie',
        radius: '55%',
        center: ['50%', '63%'],
        selectedMode: 'single',
        itemStyle: {
            emphasis: {
                shadowBlur: 10,
                shadowOffsetX: 0,
                shadowColor: 'rgba(0, 0, 0, 0.5)'
            }
        },
        data: []
    }]
});

// 赋值方法
function setBrowserChartVal(data) {
    var chartData = {
        legend: {data: []},
        series: [{data: []}]
    };
    // 将数组从大到小排序
    var newData = data.sort(function (a, b) {
        var v1 = a['value'];
        var v2 = b['value'];
        return v2 - v1;
    });
    /* for(let v of newData) {
      // 设置图例的值
      chartData.legend.data.push(v.name);
    };*/
    // 设置饼图的值
    chartData.series[0].data = newData;
    // 将值赋到chart
    browserChart.setOption(chartData);
};

/* ------------------------------ 访问环境-浏览器TOP ------------------------------ */
// 初始化
var browserTopChart = echarts.init(document.getElementById('browser-top-chart'));
// 配置
browserTopChart.setOption({
    title: {
        text: '浏览器TOP',
        left: 'center'
    },
    grid: {
        top: '10%',
        left: '4%',
        right: '6%',
        bottom: '2%',
        containLabel: true
    },
    xAxis: {
        type: 'value'
    },
    yAxis: {
        type: 'category',
        inverse: true,
        data: []
    },
    series: [{
        name: '浏览器',
        type: 'bar',
        label: {normal: {position: 'right', show: true}},
        itemStyle: {
            normal: {
                color: function (params) {
                    return colorList3[params.dataIndex]
                }
            }
        },
        data: []
    }]
});

// 赋值方法
function setBrowserTopChartVal(data) {
    var chartData = {
        yAxis: {data: []},
        series: [{data: []}]
    };
    // 将数组从大到小排序
    var newData = data.sort(function (a, b) {
        var v1 = a['value'];
        var v2 = b['value'];
        return v2 - v1;
    });
    for (let i of newData) {
        // 设置Y轴的名
        chartData.yAxis.data.push(i.name);
        // 设置Y轴的值
        chartData.series[0].data.push(i.value);
    }
    ;
    // 将值赋到chart
    browserTopChart.setOption(chartData);
};

