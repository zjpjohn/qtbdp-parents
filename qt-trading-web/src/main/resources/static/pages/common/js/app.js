/**
 * Created by dell on 2017/6/9.
 */

var App = function () {

    var options = {
        _url:"/api/product",
        _container: "#data-container",  // 数据容器
        _tmpl: "#tmpl_products",        // 内容模板
        _pager: "#pageTool",            // 分页插件ID
        _rows:12,                       // 默认每页条数
        _total: 0                       // 记录总数
    }

    // 请求数据
    var params = {
        page:1,    // 默认当前页
        rows:12    // 默认每页条数
    }

    /**
     * 加载类目数据
     * @param pid
     * @param container
     * @param tmplId
     */
    var initType = function (pid,container) {

        container = container ? container : "#type-container" ; // 类目容器
        pid = pid ? pid : 0 ; // 父类目ID

        var tmplId = $(container).attr("data-tmpl") ; // 内容模板
        var sub_container = $(container).attr("data-subtype") ; // 子类目容器

        LoadingData.request({url: "/api/type/"+pid}, function(data){

            // 有子节点
            if( data.pageInfo.length != 0) {
                // 清空类目数据
                $(container).html("").show() ;
                // 装载类目数据
                $(tmplId).tmpl(data).appendTo(container);

                // 类目绑定click事件
                $(container+" > a").click(function () {

                    // 选中效果
                    $(container+" > a").removeClass("active") ;
                    $(this).addClass("active") ;

                    pid = $(this).attr("data-id") ;

                    // 递归调用
                    if(sub_container) {
                        // 全部时
                        if(pid == 0)
                            $(sub_container).html("").hide() ;
                        else
                            initType(pid, sub_container) ;
                    }
                    // 加载数据
                    initDatas({dataType:pid}) ;
                })
            } else {
                $(container).html("").hide() ;
            }
        }) ;

    }

    /**
     * 加载产品数据
     * @param _params 请求参数
     * @param _options 方法固有参数
     */
    var initDatas = function (_params) {

        // json对象合并
        if(_params) $.extend(params, _params) ;

        LoadingData.request({url: options._url, data: params}, function(data){

            if(data.code === 100) {
                LoadingData.toastr({
                    _type: 'error',
                    _title: '请求数据错误，地址：'+data.url,
                    _msg: data.message
                }) ;
                return ;
            }

            // 清空容器
            $(options._container).html("") ;

            $(options._tmpl).tmpl(data.pageInfo).appendTo(options._container);

            // 查询条件变更，重新生成分页组件，并清空容器
            var _count = data.pageInfo.total ;
            if(_count != options._total) {
                options._total = _count ;

                $(options._pager).html("") ;
                // 给参数赋数据总数，分页方法paging()用到
                $(options._pager).Paging({
                    pagesize: options._rows,
                    count: options._total,
                    toolbar:true,
                    callback:function(page,size,count){

                        // 请求参数赋值
                        params.page = page;
                        params.rows = size;

                        initDatas(params) ;

                        // console.log(page);//当前页
                        // console.log(size);//每页条数
                        // console.log(count);//总页数
                    }
                });
            }
        });
    }

    /**
     * 过滤条件
     * 注意：页面中_filterId必须和model对象中属性名一致
     */
    var initFilter = function () {

        var container = "#filter-container" ; // 过滤容器

        $(container+" > span").each(function () {

            var _filterId = this.id ;

            // 绑定click事件
            $("#"+_filterId+" > a").click(function () {
                $("#"+_filterId+" > a").removeClass("active") ;
                $(this).addClass("active") ;

                // 封装过滤条件
                var _params = {} ;
                var _id = $(this).attr("data-id") ;

                if(_id == -1)
                    params[_filterId] = undefined ; // 删除不限条件的查询
                else
                    _params[_filterId] = _id ;

                // 传入过滤字段
                initDatas(_params) ;
            }) ;
        })

    }

    /**
     * 排序
     * 注意：页面中data-by必须和数据库对应表的字段名一致
     */
    var initOrderByCond = function () {

        var container = "#order-container" ; // 排序容器

        // 绑定click事件
        $(container+" > a").click(function () {
            $(container+" > a").removeClass("active") ;
            $(this).addClass("active") ;

            // 传入排序字段
            initDatas({ orderBy : $(this).attr("data-by") }) ;
        }) ;
    }

    return {
        // 数据市场数据加载
        initDatamart: function () {
            initType();
            initOrderByCond() ;
            initFilter() ;
            initDatas() ;
        },
        // 爬虫规则市场数据加载
        initCrawlers: function () {

            options._url = "/api/crawlers/role" ; // 重置请求地址
            initType();
            initOrderByCond() ;
            initFilter() ;
            initDatas() ;
        }
    };
}() ;