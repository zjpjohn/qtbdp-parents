var Datamart = {

    /**
     * 数据商城获取产品数据
     *
     * @param settings
     * {
     *  url: "/api/product",        // 请求地址
     *  tmpl_id: #tmpl_products,    // jquery template 模板元素，如：#div_id 或 .class_name
     *  target: #product_list,      // 替换html元素，如：#div_id 或 .class_name
     *  curr_page: 1,               // 当前页数
     *  count: 100,                 // 总记录数
     *  params:[{key:"page",value:"1"}] // 已数组的方式传参数，key 参数名，value 参数值
     * }
     */
    products : function(settings){

        var _tmpl = settings.tmpl_id ;
        var _target = settings.target ;
        if(!settings.curr_page) settings.curr_page = 1 ; // 默认1
        var _u = Datamart.join(settings) ; // 拼装URL

        var _loadding = $("#loadding") ;

        _loadding.hide() ;

        $.ajax({
            type:"GET",
            dataType:"json",
            url: _u ,
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

                    // 给参数赋数据总数，分页方法paging()用到
                    settings.count = data.pageInfo.total ;
                }
            },
            error:function(data){
                // console.log(data);
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
        if(!_c) return ;
        var _s = settings.size ? settings.size : 12; // 默认每页12条
        var _pager = settings.pager_id ;

        $(_pager).Paging({
            pagesize: _s,
            count: _c,
            toolbar:true,
            callback:function(page,size,count){

                settings.curr_page = page ; // 当前页
                Datamart.products(settings) ;
                // console.log(page);//当前页
                // console.log(size);//每页条数
                // console.log(count);//总页数
            }
        });
    },

    /**
     * 绑定页面属性刷选信息，点击选中并加载产品数据
     * @param settings
     * {
     *  url: "/api/product?page=",  // 请求地址
     *  count: 100,                 // 产品总记录数
     *  size: 12,                   // 每页记录数
     *  tmpl_id: #tmpl_products,    // jquery template 模板元素，如：#div_id 或 .class_name
     *  target: #product_list       // 替换html元素，如：#div_id 或 .class_name
     *  pager_id: #pageTool         // 分页html元素标签
     *  params:[{key:"page",value:"1"}] // 已数组的方式传参数，key 参数名，value 参数值
     * }
     */
    active : function (settings) {

        if(!settings) return ;

        // 获取已选值
        var _vids = "";
        $("ul.sub_sort li a").each(function () {
            _vids += $(this).attr("data-id")+"," ;
        }) ;

        $("ul.sub_sort li").each(function () {

            var $_a = $(this).find("a") ;

            $_a.click(function () {

                $_a.removeClass("active");
                $(this).addClass("active");

                // 组装属性值ID参数
                _vids += $(this).attr("data-id");
                if(!_vids) return ;

                // 请求参数要http://localhost:8040/swagger-ui.html接口中的key一致
                settings.params = [{key:"valIds",value:_vids}]

                // console.log("valIds: "+_vids);//vids

                Datamart.products(settings) ;
            }) ;
        })
    },

    /**
     * 拼接url请求参数
     * @param settings
     * @returns {*}
     */
    join : function (settings) {

        if(!settings) return ; // 参数为空

        var result = settings.url +"?page="+settings.curr_page ;

        if(settings.params && settings.params.length > 0) {
            var _params_str = "" ;
            settings.params.forEach(function(param,index,array){

                var _key = param.key ;
                var _val = param.value ;

                // if(index+1 != array.length) _params_str += "&" ;

                _params_str += "&"+_key+"="+_val ;

            }) ;

            result += _params_str ;
        }

        return  result ;
    }

};