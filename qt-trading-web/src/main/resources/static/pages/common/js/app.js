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

            $(options._tmpl).tmpl(data.pageInfo,{
                _date : function (date) {
                    return _tmp_formatedate(date) ;
                },
                _substr : function (str,n) {
                    return _tmp_substr(str,n) ;
                },
                _num : function (num) {
                    return _tmp_formatnum(num) ;
                },
                _timeChange : function (time) {
                    return _calculateTime(time);
                },
                _def : function (v,t) {
                    return _tmp_defaultvalue(v,t) ;
                }
            }).appendTo(options._container);

            // 查询条件变更，重新生成分页组件，并清空容器
            var _count = data.pageInfo.total ;
            if(_count != options._total) {
                options._total = _count ;

                if(!$(options._pager)) return ;

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

    /**
     * 绑定下载文件方法
     * @param id 产品ID
     */
    var bindingDownload = function (id) {
        $("#download-container").click(function () {
            LoadingData.request({url: "/api/download/file/check/"+id}, function(data) {

                if (data.code === 100) {
                    LoadingData.toastr({
                        _type: 'error',
                        _title: '请求数据错误，地址：' + data.url,
                        _msg: data.message
                    });
                    return;
                }

                if(data.result.success){
                    window.location.href="/download/file/"+id;
                } else {

                    if(data.result.errorCode == 2000) {
                        // 未登录
                        loginShow();
                    } else {
                        LoadingData.toastr({
                            _type: 'error',
                            _title: '下载数据失败',
                            _msg: data.result.message
                        });
                    }
                }
            }) ;
        })
    }

    /**
     * 绑定购买数据产品
     * 检查通过后购买，否则提示相应操作
     * @param id    产品ID
     * @param type  产品类型 1数据条目 2数据包
     */
    var bindingBuy = function (id, type) {
        type = type ? type : 2 ;

        $("#buy-container").click(function () {
            LoadingData.request({url: '/api/product/check/'+id+'/'+type }, function(data) {
                if (data.code === 100) {
                    LoadingData.toastr({
                        _type: 'error',
                        _title: '请求数据错误，地址：' + data.url,
                        _msg: data.message
                    });
                    return;
                }

                if(data.result.success){
                    location.href="/getOrderInfoAndGoto/"+id+"/"+type+"?order=4" ;
                } else {
                    if(data.result.errorCode == 2000) {
                        // 未登录
                        loginShow();
                    } else if (data.result.errorCode == 1000) {
                        // 已购买
                        LoadingData.toastr({
                            _type: 'warning',
                            // _title: data.result.title,
                            _msg: data.result.message
                        });

                        setTimeout(function () {
                            location.href="/usercenter?order=3" ;
                        }, 2000) ;
                    }
                }

            }) ;
        }) ;
    }
    
    /**
     * 时间格式转换
     * @param date
     * @private
     */
    var _tmp_formatedate = function (date) {
        if(!date) return ;
        var time = new Date(date);
        var b = 24*60; //分钟数
        time.setMinutes(time.getMinutes() + b, time.getSeconds(), -1);

        return time.format("yyyy-MM-dd hh:mm:ss") ;
    }

    /**
     * 字符串处理
     * @param str
     * @private
     */
    var _tmp_substr = function (str,n) {
        if(str && str.length>n){
            str=str.substring(0,n)+"...";
        }
        return str;
    }

    /**
     * 保留小数点后2位
     * @param num
     * @returns {*}
     * @private
     */
    var _tmp_formatnum = function (num) {
        return num.toFixed(2) ;
    }

    //计算数据众包截至时间
    var _calculateTime = function (time){
        var calculateTime=getDateDiff(time);
        return calculateTime;
    }
    /**
     * 默认值
     * @param v
     * @param type 1产品类型 2数据来源
     * @returns {*}
     * @private
     */
    var _tmp_defaultvalue = function (v, type) {

        if(typeof v == undefined || v == null) v = "-" ;

        if(type == 1) {
            switch (v) {
                case 0:
                    v = "数据包";
                    break ;
                case 1:
                    v = "API接口";
                    break ;
                case 2:
                    v = "数据报告";
                    break ;
            }
        } else if(type == 2) {
            switch (v) {
                case 0:
                    v = "个人";
                    break ;
                case 1:
                    v = "企业";
                    break ;
                case 2:
                    v = "政府";
                    break ;
                case 3:
                    v = "组织机构";
                    break ;
                case 4:
                    v = "互联网";
                    break ;
            }
        }

        return v;
    }

    return {
        // 数据市场数据加载
        initDatamart: function () {
            nav(2);
            initType();
            initOrderByCond() ;
            initFilter() ;
            initDatas({isUsed:1,auditStatus:1}) ;
        },
        // 爬虫规则市场数据加载
        initCrawlers: function () {
            nav(3);
            options._url = "/api/crawlers/role" ; // 重置请求地址
            initType();
            initOrderByCond() ;
            initFilter() ;
            initDatas({isUsed:1,auditStatus:1}) ;
        },
        // 数据市场详情页
        initDatamartDetail: function (id) {
            nav(2);

            // 加载产品明细
            LoadingData.request({url: "/api/product/"+id}, function(data){
                $("#tmpl_product").tmpl(data.pageInfo,{
                    _date : function (date) {
                        return _tmp_formatedate(date)
                    },
                    _num : function (num) {
                        return _tmp_formatnum(num) ;
                    },
                    _def : function (v,t) {
                        return _tmp_defaultvalue(v,t) ;
                    }
                }).appendTo("#product-container");

                if(data.pageInfo.price == 0)
                    bindingDownload(id) ;// 绑定免费下载操作
                else
                    bindingBuy(id) ;// 绑定购买操作
            });

            // 加载数据项明细
            options._url = "/api/product/item" ; // 重置请求地址
            options._tmpl = "#tmpl_items" ;
            options._rows = 20 ; //每页20条记录
            initDatas({productId:id}) ;
        },
        // 数据定制数据加载
        initCustom: function (type) {

            options._url = "/api/customized" ; // 重置请求地址
            initType();
            initOrderByCond() ;
            initFilter() ;
            initDatas({serviceType:type,auditStatus:1,isUsed:1}) ;
        },
        // 服务商数据加载
        initInstitution: function () {
            nav(6);

            options._url = "/api/institutionV2" ; // 重置请求地址
            options._rows = 20 ; //每页20条记录
            initType();
            initOrderByCond() ;
            initDatas() ;
        },
        // 服务商主页
        initInstitutionHome: function (id,type) {
            nav(6);

            // 加载服务商明细
            LoadingData.request({url: "/api/institutionV2/"+id}, function(data) {
                $("#tmpl_institution").tmpl(data.pageInfo, {
                    _date: function (date) {
                        return _tmp_formatedate(date)
                    },
                    _num: function (num) {
                        return _tmp_formatnum(num);
                    },
                    _def: function (v, t) {
                        return _tmp_defaultvalue(v, t);
                    }
                }).appendTo("#institution-container");

                switch (type) {
                    case 0:
                        // 加载数据包产品
                        initDatas({userId: data.pageInfo.createId,auditStatus:1,isUsed:1}) ;
                        break ;
                    case 1:
                        // 加载数据规则定制
                        options._url = "/api/customized" ;
                        options._tmpl = "#tmpl_data_custom" ;
                        initDatas({createId: data.pageInfo.createId,serviceType: 2,auditStatus:1,isUsed:1}) ;
                        break ;
                }

            });

            var _id = $("#createId").val() ;

            $(".tab-sub-container > a").each(function () {

                $(this).click(function () {

                    $(".tab-sub-container > a").removeClass("active") ;
                    $(this).addClass("active") ;

                    var _container = $(this).attr("data-id") ;
                    var _type = $(this).attr("data-type") ;
                    switch (_type) {
                        case "1":
                            // 加载数据包产品
                            if(_container == "data") {
                                options._url= "/api/product";
                                options._tmpl= "#tmpl_products";
                                initDatas({userId:_id,auditStatus:1,isUsed:1}) ;
                            } else {
                                options._url = "/api/customized" ;
                                options._tmpl = "#tmpl_data_custom" ;
                                initDatas({createId:_id,serviceType: 2,auditStatus:1,isUsed:1}) ;
                            }
                            break ;
                        case "2":
                            // 加载爬虫规则
                            if(_container == "data") {
                                options._url = "/api/crawlers/role" ;
                                options._tmpl = "#tmpl_crawlersRules" ;
                                initDatas({createId:_id,auditStatus:1,isUsed:1}) ;
                            } else {
                                options._url = "/api/customized" ;
                                options._tmpl = "#tmpl_role_custom" ;
                                initDatas({createId:_id, serviceType: 1,auditStatus:1,isUsed:1}) ;
                            }
                            break ;
                    }

                });

            }) ;
        },

        /****************个人中心*****************/
        //概览 最新订单、最新发布
        initUserCenter: function (id) {
            // 最新前5条订单
            LoadingData.request({url: "/api/trade/orders/5"}, function(data){
                $("#tmpl_orders").tmpl(data.pageInfo,{
                    _date : function (date) {
                        return _tmp_formatedate(date)
                    },
                    _num : function (num) {
                        return _tmp_formatnum(num) ;
                    },
                    _substr : function (str,n) {
                        return _tmp_substr(str,n) ;
                    }
                }).appendTo("#order-container");
            });

            // 最新前5条定制
            LoadingData.request({url: "/api/customized",data:{createId:id,rows:5},loadding:"#loadding1"}, function(data){
                $("#tmpl_customized").tmpl(data.pageInfo,{
                    _date : function (date) {
                        return _tmp_formatedate(date)
                    },
                    _num : function (num) {
                        return _tmp_formatnum(num) ;
                    },
                    _substr : function (str,n) {
                        return _tmp_substr(str,n) ;
                    }
                }).appendTo("#customized-container");
            });

        },
        // 已购买的商品
        initUserBuy:function(id,type){
            switch (type) {
                case 0:
                    // 数据订单
                    options._url = "/api/trade/orders" ;
                    options._tmpl = "#tmpl_dataorder" ;
                    options._container = "#data-container" ;
                    initDatas({userId:id,productType:2}) ;
                    break ;
                case 1:
                    // 爬虫规则订单
                    options._url = "/api/trade/orders" ;
                    options._tmpl = "#tmpl_ruleorder" ;
                    options._container = "#rule-container" ;
                    initDatas({userId:id,productType:3}) ;
                    break ;
            }
        },
        // 个人信息
        initPersonInfo:function (id) {

            // 加载个人信息
            LoadingData.request({url: "/api/user/"+id}, function (data) {

                $("#tmpl_userinfo").tmpl(data.pageInfo).appendTo("#data-container");

                var _options = {
                    _form: "#perdetail",
                    _rules: {
                        head: {
                            url:true
                        },
                        nick: {
                            required: true
                        }
                    }
                }

                FormValidationMd.init(_options);
            });

            // 点击上传头像
            $(document).on("change","#upheadimg",function() {
                var formData = new FormData();
                formData.append("img",$("#upheadimg")[0].files[0]);

                LoadingData.request({
                    url: "/api/upload/img",
                    type: "POST",
                    file: true, // 文件上传标识
                    data: formData
                }, function (data) {
                    $(".peronimg").attr("src", data.img);
                    $("#realHead").val(data.img);
                });
            });


        },
        // 我的定制
        initUserCustomized:function (id, type) {

            switch (type) {
                case 0:
                    // 数据定制
                    options._url = "/api/customized" ;
                    options._tmpl = "#tmpl_customized_data" ;
                    options._container = "#customized-data-container" ;
                    initDatas({createId:id,serviceType:2}) ;
                    break ;
                case 1:
                    // 爬虫规则定制
                    options._url = "/api/customized" ;
                    options._tmpl = "#tmpl_customized_rule" ;
                    options._container = "#customized-rule-container" ;
                    initDatas({createId:id,serviceType:1}) ;
                    break ;
            }

        },
        // 我的发布
        initUserPublish:function (id,type) {
            switch (type) {
                case 0:
                    // 数据产品
                    options._url = "/api/product" ;
                    options._tmpl = "#tmpl_data" ;
                    options._container = "#data-container" ;
                    initDatas({userId:id}) ;
                    break ;
                case 1:
                    // 爬虫规则定制
                    options._url = "/api/crawlers/role" ;
                    options._tmpl = "#tmpl_rule" ;
                    options._container = "#rule-container" ;
                    initDatas({createId:id}) ;
                    break ;
            }
        }
    };
}() ;



//订单支付
function pay(no,amount,subject) {
    var orderData = {"orderNo":no,"amount":amount,"subject":subject};
    $.ajax({
        url: '/api/trade/alipayapi',
        type: 'post',
        data: JSON.stringify(orderData),//$.parseJSON( jsonstr );
        contentType: "application/json; charset=utf-8",
        dataType: 'json',
        success: function (data) {
            if(data != null){
                $("#aliBack").html(data.pageInfo.sHtmlText);//这里content是一个普通的String
            }
        },
        error: function () {

        }
    });
}

//下载函数封装
function orderDownload(orderId){
    $.ajax({
        url: "/api/upload/file/exist?orderNo="+orderId,
        type: "get",
        xhrFields:{withCredentials:true},
        success: function (data) {
            if(data.success){
                window.location.href="/download/"+orderId;
            }else{
                layer.msg("当前数据已失效",{icon:5});
            }
        }
    })
}






