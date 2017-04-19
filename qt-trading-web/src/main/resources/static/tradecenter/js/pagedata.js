var pageData = {

    _change : true , // 条件是否变更，需要重新查询数据

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
     *  size: 12,                   // 每页记录数
     *  params:[{key:"page",value:"1"}] // 已数组的方式传参数，key 参数名，value 参数值
     * }
     */
    products : function(settings){

        var _tmpl = settings.tmpl_id ;
        var _target = settings.target ;
        if(!settings.curr_page) settings.curr_page = 1 ;    // 默认1
        if(!settings.size) settings.size = 12 ;             // 默认12
        var _u = pageData.join(settings) ; // 拼装URL

        // console.log(_u);

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
                    // console.log(data.pageInfo);
                    //标题与内容剪切
                    for(var i=0;i<data.pageInfo.list.length;i++){
                        if(data.pageInfo.list[i].designation && data.pageInfo.list[i].designation.length>12){
                            data.pageInfo.list[i].designation=data.pageInfo.list[i].designation.substring(0,14)+"...";
                        }
                        if(data.pageInfo.list[i].introduce && data.pageInfo.list[i].introduce.length>40){
                            data.pageInfo.list[i].introduce=data.pageInfo.list[i].introduce.substring(0,40)+"...";
                        }
                        if(data.pageInfo.list[i].itemName && data.pageInfo.list[i].itemName.length>10){
                            data.pageInfo.list[i].itemName=data.pageInfo.list[i].itemName.substring(0,10)+"...";
                        }
                        if(data.pageInfo.list[i].editorTime){
                            var editorTime=new Date(data.pageInfo.list[i].editorTime);
                            data.pageInfo.list[i].editorTime=editorTime.getFullYear()+"/"+editorTime.getMonth()+"/"+editorTime.getDate()+" "+editorTime.getHours()+":"+editorTime.getMinutes()+":"+editorTime.getSeconds();
                        }
                    }
                    $(_target).empty() ;
                    $(_tmpl).tmpl(data.pageInfo).appendTo(_target);


                    // 给参数赋数据总数，分页方法paging()用到
                    settings.count = data.pageInfo.total ;

                    // 第一次加载数据，条件变更需要重新加载分页插件
                    if(settings.pager_id){
                        if(pageData._change) pageData.paging(settings) ;
                    }

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
     *  url: "/api/product",        // 请求地址
     *  tmpl_id: #tmpl_products,    // jquery template 模板元素，如：#div_id 或 .class_name
     *  target: #product_list,      // 替换html元素，如：#div_id 或 .class_name
     *  pager_id: #pageTool         // 分页html元素标签
     *  curr_page: 1,               // 当前页数
     *  count: 100,                 // 总记录数
     *  size: 12,                   // 每页记录数，默认12
     *  params:[{key:"page",value:"1"}] // 已数组的方式传参数，key 参数名，value 参数值
     * }
     */
    paging : function (settings) {

        var _c = settings.count ;
        if(!_c) return ;
        var _s = settings.size ? settings.size : 12; // 默认每页12条
        var _pager = settings.pager_id ;

        $(_pager).empty() ; // 清空原有分页插件

        $(_pager).Paging({
            pagesize: _s,
            count: _c,
            toolbar:true,
            callback:function(page,size,count){

                settings.curr_page = page ; // 当前页

                // 条件不变，无需重新查询数据
                // if(!pageData._change)
                pageData.products(settings) ;

                pageData._change = false ;
                // console.log(page);//当前页
                // console.log(size);//每页条数
                // console.log(count);//总页数
            }
        });
    },

    /**
     * 绑定页面属性刷选信息，点击选中并加载产品数据
     * @param settings
     *
     * {
     *  url: "/api/product",        // 请求地址
     *  tmpl_id: #tmpl_products,    // jquery template 模板元素，如：#div_id 或 .class_name
     *  target: #product_list,      // 替换html元素，如：#div_id 或 .class_name
     *  pager_id: #pageTool         // 分页html元素标签
     *  curr_page: 1,               // 当前页数
     *  count: 100,                 // 总记录数
     *  size: 12,                   // 每页记录数
     *  params:[{key:"page",value:"1"}] // 已数组的方式传参数，key 参数名，value 参数值
     * }
     */
    active : function (settings) {

        if(!settings) return ;

        $("ul.sub_sort li").each(function () {

            var $_a = $(this).find("a") ;

            $_a.click(function () {

                $_a.removeClass("active");
                $(this).addClass("active");

                // 获取已选值
                var _vids = "";
                $("ul.sub_sort li a.active").each(function () {
                    var _vid = $(this).attr("data-id") ;
                    if(_vid) _vids += _vid + "," ;
                }) ;
                if(_vids && _vids.endsWith(",")) _vids = _vids.substr(0,_vids.length-1) ;

                // 请求参数要http://localhost:8040/swagger-ui.html接口中的key一致
                var _hasVids = false ;
                if(settings.params) {
                    // 存在key=valIds替换原有值，否则push
                    $.each(settings.params, function (index, param) {
                        var _key = param.key ;
                        if(_key == "valIds") {
                            _hasVids = true ;
                            param.value = _vids ;
                        }
                    }) ;
                } else {
                    settings.params = [] ;// params不存在新建空json数组
                }
                if(!_hasVids) settings.params.push({key:"valIds",value:_vids}) ;

                // console.log("valIds: "+_vids);//vids

                // 条件变更，数据重新查询
                pageData._change = true ;
                pageData.products(settings) ;

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

};