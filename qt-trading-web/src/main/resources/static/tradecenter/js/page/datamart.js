var Datamart = {

    /**
     * 数据商城获取产品数据
     *
     * @param settings
     * {
     *  url: "/api/product?page=",  // 请求地址
     *  tmpl_id: #tmpl_products,    // jquery template 模板元素，如：#div_id 或 .class_name
     *  target: #product_list,      // 替换html元素，如：#div_id 或 .class_name
     *  curr_page: 1                // 当前页数
     * }
     */
    products : function(settings){

        var _u = settings.url ;
        var _tmpl = settings.tmpl_id ;
        var _target = settings.target ;
        var _p = settings.curr_page ;

        var _loadding = $("#loadding") ;

        _loadding.hide() ;

        $.ajax({
            type:"GET",
            dataType:"json",
            url: _u+_p ,
            ansync:true,
            xhrFields:{
                withCredentials:true
            },
            beforeSend: function() {
                _loadding.show() ;
            },
            success:function(data){

                // console.log(data);

                if(data && data.pageInfo) {
                    $(_target).empty() ;
                    $(_tmpl).tmpl(data.pageInfo).appendTo(_target);
                }
            },
            error:function(data){
                console.log(data);
            },
            complete : function () {
                _loadding.hide() ;
            }
        });
    },

    /**
     * 分页条   数据的总条数：count
     * @param settings
     * {
     *  url: "/api/product?page=",  // 请求地址
     *  count: 100,                 // 产品总记录数
     *  size: 12,                   // 每页记录数
     *  tmpl_id: #tmpl_products,    // jquery template 模板元素，如：#div_id 或 .class_name
     *  target: #product_list       // 替换html元素，如：#div_id 或 .class_name
     *  pager_id: #pageTool         // 分页html元素标签
     * }
     */
    paging : function (settings) {

        var _c = settings.count ;
        var _s = settings.size ? settings.size : 12; // 默认每页12条
        var _pager = settings.pager_id ;

        $(_pager).Paging({
            pagesize: _s,
            count: _c,
            toolbar:true,
            callback:function(page,size,count){

                settings.curr_page = page ; // 当前页
                Datamart.products(settings) ;
                console.log(page);//当前页
                console.log(size);//每页条数
                console.log(count);//总页数
            }
        });
    }
};