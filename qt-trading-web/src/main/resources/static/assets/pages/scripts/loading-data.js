/**
 * Created by dell on 2017/6/8.
 */

var LoadingData = {

    /**
     * ajax请求数据
     * @param options
     * {
     *   url: '/xxx/xx',
     *   loadding: '#loadding',
     *   data: '',
     *   type:'POST'
     * }
     * @param callback 方法成功后回调函数
     */
    request : function (options, callback) { //

        if(!options.url) return ;

        var _options = {
            loadding: "#loadding",
            data: '',
            type: 'GET',
            file: false
        }
        $.extend(_options, options) ;

        if(_options.file) {
            // 文件上传
            $.ajax({
                url: _options.url,
                type: _options.type,
                processData:false,
                contentType:false,
                data: _options.data,
                async:true,
                beforeSend:function () {
                    $(_options.loadding).show() ;
                },
                success:function (data) {

                    // console.log(data);

                    // 调用回调函数
                    if($.isFunction(callback)) callback(data) ;

                    $(_options.loadding).hide() ;
                },
                error: function () {
                    LoadingData.toastr({
                        _type: 'error',
                        _title: '请求数据',
                        _msg: '网络超时，请重试或者联系管理员'
                    }) ;

                    $(_options.loadding).hide() ;
                }
            });
        } else {
            $.ajax({
                url: _options.url,
                type: _options.type,
                // dataType:"json",
                data: _options.data,
                async:true,
                beforeSend:function () {
                    $(_options.loadding).show() ;
                },
                success:function (data) {

                    // console.log(data);

                    // 调用回调函数
                    if($.isFunction(callback)) callback(data) ;

                    $(_options.loadding).hide() ;
                },
                error: function () {
                    LoadingData.toastr({
                        _type: 'error',
                        _title: '请求数据',
                        _msg: '网络超时，请重试或者联系管理员'
                    }) ;

                    $(_options.loadding).hide() ;
                }
            });
        }
    },

    /**
     * 提示
     * @param options
     * {
     *  _type: success/error,
     *  _title: '标题',
     *  _msg: '内容'
     * }
     */
    toastr : function (options) {

        toastr.options = {
            "closeButton": false, //是否显示关闭按钮
            "debug": false, //是否使用debug模式
            "positionClass": "toast-bottom-right",//弹出窗的位置
            "showDuration": "300",//显示的动画时间
            "hideDuration": "1000",//消失的动画时间
            "timeOut": "1000", //展现时间
            "extendedTimeOut": "1000",//加长展示时间
            "showEasing": "swing",//显示时的动画缓冲方式
            "hideEasing": "linear",//消失时的动画缓冲方式
            "showMethod": "fadeIn",//显示时的动画方式
            "hideMethod": "fadeOut" //消失时的动画方式
        };

        toastr[options._type](options._msg, options._title)
    },

    /**
     * 拼接url请求参数
     * @param settings
     * @returns {*}
     */
    join : function (settings) {

        if(!settings) return ; // 参数为空

        var result = settings.url +"?page="+settings.curr_page+"&rows="+settings.size ;

        if(settings.params && settings.params.length > 0) {
            var _params_str = "" ;
            settings.params.forEach(function(param,index,array){

                var _key = param.key ;
                var _val = param.value ;

                if(_val) _params_str += "&"+_key+"="+_val ;

                // if(index+1 != array.length) _params_str += "&" ;

            }) ;

            result += _params_str ;
        }

        return  result ;
    }

}