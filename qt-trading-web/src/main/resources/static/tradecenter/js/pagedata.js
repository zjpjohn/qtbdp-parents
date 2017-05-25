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
                console.log(data);
                if(data && data.pageInfo) {
                    //console.log(data.pageInfo);
                    $(_target).empty() ;
                    $(_tmpl).tmpl(data.pageInfo, {

                        _substr : function(str,n) {
                           return pageData._substr(str,n) ;
                        },
                        _date : function (date) {
                            return pageData._formatedate(date) ;
                        },
                        _surplusDays : function (toDate) {
                            return pageData._getSurplusDays(toDate);
                        },
                        _time : function(time){
                            return pageData._calculateTime(time);
                        },
                        _switchtype :function(data){
                            return pageData._dataType(data);
                        },
                        _switchlayout :function(format){
                            return pageData._dataFormat(format);
                        },
                        _demands :function(demand){
                            return pageData._demandType(demand);
                        },
                        _repstr :function(str2,i){
                            return pageData._replacestr(str2,i);
                        },
                        _dataTypeProp :function (props) {
                            return pageData._dataTypeProps(props);
                        },
                        _prices :function (price) {
                            return pageData._price(price);
                        }

                    }).appendTo(_target);


                    // 给参数赋数据总数，分页方法paging()用到
                    settings.count = data.pageInfo.total ;

                    // 第一次加载数据，条件变更需要重新加载分页插件
                    if (pageData._change) pageData.paging(settings);
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
                var orderBy = "";
                $(".priceStyle a.active").each(function () {
                    var _vid = $(this).attr("data-id") ;
                    if(_vid) _vids += _vid + "," ;
                }) ;
                $(".sortStyle a.active").each(function(){
                    var order = $(this).attr("data-id") ;
                    if(order && orderBy != order) orderBy = order;
                });
                if(_vids && _vids.endsWith(",")) _vids = _vids.substr(0,_vids.length-1) ;

                // 请求参数要http://localhost:8040/swagger-ui.html接口中的key一致
                var _hasVids = false ;
                var flag = false;
                if(settings.params) {
                    // 存在key=valIds替换原有值，否则push
                    $.each(settings.params, function (index, param) {
                        var _key = param.key ;
                        if(_key == "valIds") {
                            _hasVids = true ;
                            param.value = _vids ;
                        }
                        //替换原有的orderBy值
                        if(_key == "orderBy") {
                            flag = true;
                            param.value = orderBy;
                        }
                    }) ;
                } else {
                    settings.params = [] ;// params不存在新建空json数组
                }
                if(!_hasVids) settings.params.push({key:"valIds",value:_vids}) ;
                if(!flag) settings.params.push({key:"orderBy",value:orderBy});
                // console.log("valIds: "+_vids);//vids

                // 条件变更，数据重新查询
                pageData._change = true ;
                pageData.products(settings) ;

            }) ;
        })
    },
    /**
     * 一级筛选，点击选中并加载数据
     * @param settings
     */
    filter:function(settings){
        if(!settings.filter_elm) return ;
       $(settings.filter_elm).click(function(){
           console.log(1);
           $(this).addClass("active").siblings(".active").removeClass("active");
            var val= $(this).attr("data-id");
           settings.params[0].value=$(this).attr("data-id");
           settings.curr_page = 1;
           pageData._change = true ;
           pageData.products(settings) ;
       });
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
    },

    /**
     * 字符串处理
     * @param str
     * @private
     */

    _substr: function (str,n) {
        if(str.length>n){
            str=str.substring(0,n)+"...";
        }
        str.replace("<p>","").replace("</p>","");
        return str;
    },
    //数据内 p 标签截取
    _replacestr: function (str2,i) {
        var reg=/<[^>]*>/g;
        str2=str2.replace(reg,"");
        if(str2.length>i){
            str2=str2.substring(0,i)+"...";
        }

        return str2;
    },


    /**
     * 时间格式转换
     * @param date
     * @private
     */
    _formatedate: function (date) {
        var formatedate=new Date(date);
        formatedate=formatedate.getFullYear()+"-"+(parseInt(formatedate.getMonth())+1)+"-"+formatedate.getDate()+" "+formatedate.getHours()+":"+formatedate.getMinutes()+":"+formatedate.getSeconds();
       return formatedate;
    },

    /**
     * 计算数据众包的到期天数
     */
    _getSurplusDays: function (toDate) {
        var date = new Date().getTime();
        var days = parseInt((toDate-date)/(24*3600*1000));
        return days<0?"--":days;
    },
    //计算数据众包截至时间
    _calculateTime:function(time){
        var calculateTime=getDateDiff(time);
        return calculateTime;
    },

//需求大厅 数据类型
    _dataType: function(data){
        var datanum="";
        switch(data){
            case 1:
                datanum="知识/专利";
                break;
            case 2:
                datanum="智能制造";
                break;
            case 3:
                datanum="物联网";
                break;
            case 4:
                datanum="先进制造";
                break;
            case 5:
                datanum="工业参数";
                break;

            case 6:
                datanum="仪器/仪表 ";
                break;
            case 7:
                datanum="工业安全";
                break;
            case 8:
                datanum="政府监管 ";
                break;

            case 9:
                datanum="学术/教育";
                break;
            case 10:
                datanum="工业运行";
                break;
            case 11:
                datanum="物流数据";
                break;

            case 12:
                datanum="工业设计";
                break;
            case 13:
                datanum="行业综合";
                break;

            default :
                datanum="其它";
                break;

        }
         return datanum;
    },


//需求大厅 数据众包数据格式
    _dataFormat: function(format){
        var dataFor="";
        switch(format){
            case 1:
                dataFor="视频";
                break;
            case 2:
                dataFor="音频";
                break;
            case 3:
                dataFor="文本";
                break;
            case 4:
                dataFor="图像";
                break;
            default:
                dataFor="其它";
                break;

        }
        return dataFor;
    },
    //需求大厅  方案召集需求类型
    _demandType: function(demand){
        var demandtypes="";
        switch(demand){
            case 1:
                demandtypes="数据预处理";
                break;
            case 2:
                demandtypes="数据建模";
                break;


        }
        return demandtypes;
    },

    //数据商场 具体详情数据格式解析
    _dataTypeProps: function (data) {
        var returnData = [];
        if(data != null && data != ""){
            var dataArr = data.split(";");
            var jsonData = {};
            for(var i=0;i<dataArr.length;i++){
                var aa = new Array();
                aa = dataArr[i].split(":");
                jsonData[aa[0]] = aa[1];
            }
            returnData.push(jsonData.数据格式);
            returnData.push(jsonData.数据来源);
            returnData.push(jsonData.数据大小);
            return returnData.join(",");
        }else{
            return "";
        }

    },

    // 数据商场页面要显示的价格
    _price: function (price) {
        var returnData = "";
        if(price != null && price != ""){
            returnData = "￥"+price;
        } else{
            returnData = "￥"+0;
        }
        return returnData;
    }

};






