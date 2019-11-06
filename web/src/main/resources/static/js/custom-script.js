;
$(function () {

    /* 侧栏控制 */
    var pageName = $('.page').attr('pageName');
    $('.nav-list li[pageName="' + pageName + '"]').addClass('active');

    function leftNavControl() {
        var $leftNav = $('.page-body-left');

        if ($leftNav.hasClass('open')) {
            $leftNav.removeClass('open').addClass('close');
        } else {
            $leftNav.addClass('open').removeClass('close');
        }
        ;
    };
    $('#menuBtn').click(function () {
        leftNavControl();
    });
    $('.body-left-mask').click(function () {
        console.log(123)
        leftNavControl();
    });

    /* 提示框配置 */
    var timeOut = 2000;
    toastr.options = {
        positionClass: "toast-top-center"
    };
    window.tips = {
        success: function (text, time) {
            var t = time == undefined ? t = timeOut : t = time;
            toastr.options.timeOut = t;
            toastr.remove();
            toastr.success(text);
        },
        warning: function (text, time) {
            var t = time == undefined ? t = timeOut : t = time;
            toastr.options.timeOut = t;
            toastr.remove();
            toastr.warning(text);
        },
        error: function (text, time) {
            var t = time == undefined ? t = timeOut : t = time;
            toastr.options.timeOut = t;
            toastr.remove();
            toastr.error(text);
        }
    };

    /* 单个网址清除按钮控制 */
    $('.oneShortInput').keyup(function () {
        if ($(this).val() == '') {
            $('.one-clear-btn').hide();
        } else {
            $('.one-clear-btn').show();
        }
        ;
    });
    $('.one-clear-btn').click(function () {
        $('.oneShortInput').val("").focus();
        $('.one-clear-btn').hide();
    });

    /* 单个生成展示 */
    function oneCreatedShow(shortUrl) {
        if (shortUrl) {
            var canvas = $('#onshortQRCodeWrap').qrcode(shortUrl).find('canvas').hide()[0];
            var src = canvas.toDataURL('image/jpg');
            var index = shortUrl.lastIndexOf('/') + 1;

            $('#oneshortShowtrtext').text(shortUrl);
            $('#onshortQRCodeWrap').html('<img src="' + src + '">');
            $('#dowdloadQRCodeBtn').attr('href', src).attr('download', 'QRCode-' + shortUrl.substring(index) + '.png');
        }
        ;

        $('#oneShortCreated').show();
        tips.success('网址缩短成功');
    };
    $.oneCreatedShow = oneCreatedShow;

    /* 单个缩短复制按钮 */
    var clipboard = new ClipboardJS('#oneshortCopybtn');
    clipboard.on('success', function (e) {
        tips.success('链接已复制到剪切板');
    });
    clipboard.on('error', function (e) {
        tips.error('自动复制失败，请重新复制或手动复制');
    });

    /* 批量缩短清除按钮控制 */
    $('.batchShortInput').keyup(function () {
        if ($(this).val() == '') {
            $('.batch-clear-btn').hide();
        } else {
            $('.batch-clear-btn').show();
        }
        ;
    });
    $('.batch-clear-btn').click(function () {
        $('.batchShortInput').val("").focus();
        $('.batch-clear-btn').hide();
    });

    /* 管理短网址复制按钮配置 */
    var clipboard = new ClipboardJS('#shorturl-copy-btn');
    clipboard.on('success', function (e) {
        tips.success('链接已复制到剪切板');
    });
    clipboard.on('error', function (e) {
        tips.error('自动复制失败，请重新复制或手动复制');
    });

    /* 管理短网址全选按钮控制 */
    function allCheckedComputed() {
        var inputs = $('.selector:not(#selectall-btn) input').length;
        var inputsChecked = $('.selector:not(#selectall-btn) input:checked').length;

        if (inputs == inputsChecked) {
            $('#selectall-btn input')[0].checked = true;
        } else {
            $('#selectall-btn input')[0].checked = false;
        }
        ;
    };
    $('#selectall-btn input').click(function () {
        var inputs = $('.selector:not(#selectall-btn) input');

        if (this.checked) {
            for (let i = 0; i < inputs.length; i++) {
                inputs[i].checked = true;
            }
            ;
        } else {
            for (let i = 0; i < inputs.length; i++) {
                inputs[i].checked = false;
            }
            ;
        }
        ;
        allCheckedComputed();
    });
    $('.selector input').click(function () {
        allCheckedComputed();
    });

    /* 套餐对比客服弹窗 */
    $('#goContactVipBtn').click(function () {
        $('#vipContactMask').show();
    });
    $('#vipContactMask .close-btn').click(function () {
        $('#vipContactMask').hide();
    });

    /* 右侧工具栏控制 */
    $(".toolItem").hover(function () {
        var box = $(this).find('.contactBox'),
            initRange = box.outerWidth() + 25,
            showRange = box.outerWidth() + 10;

        box.css('left', '-' + initRange + 'px').stop().show().animate({
            left: "-" + showRange + "px",
            opacity: 1,
            display: "block"
        });
    }, function () {
        var box = $(this).find('.contactBox'),
            initRange = box.outerWidth() + 25;

        box.stop().animate({left: "-" + initRange + "px", opacity: 0, "display": "none"}, function () {
            box.hide()
        });
    });


    /* 显示数据分析tab的内容 */
    $.showTabContent = function (index) {
        $('.tab-switch-bar li:eq(' + index + ')').addClass('active').siblings('li').removeClass('active');
        $('#tabBox .tab-content:eq(' + index + ')').show().siblings('.tab-content').hide();
    };
    $.showTabContent(0);

    /* 切换数据分析tab的内容 */
    $('.tab-switch-bar li').click(function (e) {
        e.stopPropagation();
        e.preventDefault();
        var _this = this;

        if (!$(_this).hasClass('active')) {
            var index = $(_this).index();

            if ($.tabSwitchBar(e)) {
                $.showTabContent(index);
            }
            ;
        }
    });
    $.tabSwitchBar = function (e) {
        return true;
    };

    /* 日期选择插件配置 */
    var dateSelects = $('.query-criteria .select-date');    //获取要添加日期选择的元素(此处为父级元素)
    var dateSelectList = {};    //各个绑定日期选择插件的插件对象集合

    dateSelects.each(function () {
        var inputName = $(this).find('input').attr('name');   //获取绑定日期选择input标签的name值

        dateSelectList[inputName] = laydate.render({    //用name值作为对象名保存当前插件对象，方便后期对某个单独修改
            elem: $(this).find('input')[0],
            type: 'date',
            range: '-',
            format: 'yyyy.MM.dd',
            theme: '#4A6BCE',
            btns: ['confirm'],
            zIndex: 99,
            done: function (value) {
                if (value == '') {
                    $(this.elem).next('span').text('选择时间段');
                } else {
                    $(this.elem).next('span').text(value);
                }
            }
        });
    });
    $.dateSelectList = dateSelectList;


});