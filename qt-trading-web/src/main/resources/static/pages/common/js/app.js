/**
 * Created by dell on 2017/6/9.
 */

var App = function () {

    var options = {
        _url:"/api/product",
        _container: "#data-container",// 数据容器
        _tmpl: "#tmpl_products", // 内容模板
        _rows: 12 , // 默认每页显示12条
        _pager: "#pageTool" // 分页插件ID
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
                    // 加载产品数据
                    initProducts(pid) ;
                })
            } else {
                $(container).html("").hide() ;
            }
        }) ;

    }

    /**
     * 加载产品数据
     * @param _page 当前页数
     * @param _rows 每页记录数
     * @param tid   类目ID
     */
    var initProducts = function (_tid,_page,_rows) {

        if(!_tid) _tid = 0 ;
        if(!_page) _page = 1 ;
        if(!_rows) _rows = options._rows ;

        LoadingData.request({url: options._url, data:{page:_page,rows:_rows,dataType:_tid}}, function(data){

            // 清空容器
            $(options._container).html("") ;
            $(options._pager).html("") ;

            $(options._tmpl).tmpl(data.pageInfo).appendTo(options._container);

            // 给参数赋数据总数，分页方法paging()用到
            $(options._pager).Paging({
                pagesize: options._rows,
                count: data.pageInfo.total,
                toolbar:true,
                callback:function(page,size,count){

                    initProducts(_tid, page, size) ;

                    // console.log(page);//当前页
                    // console.log(size);//每页条数
                    // console.log(count);//总页数
                }
            });

        });
    }

    /**
     *
     */
    var initRules = function () {
        
    }

    return {
        // 处理数据商城页面数据加载
        initDatamart: function () {
            initType();
            initProducts() ;
        },

        initCrawlers:function () {
            initType();
            initRules() ;
        }
    };
}() ;