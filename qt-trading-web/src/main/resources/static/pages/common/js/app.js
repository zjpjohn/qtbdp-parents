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
                }
            }).appendTo(options._container);

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
        var formatedate=new Date(date);
        formatedate=formatedate.getFullYear()+"-"+(parseInt(formatedate.getMonth())+1)+"-"+formatedate.getDate()+" "+formatedate.getHours()+":"+formatedate.getMinutes()+":"+formatedate.getSeconds();
        return formatedate;
    }

    /**
     * 字符串处理
     * @param str
     * @private
     */
    var _tmp_substr = function (str,n) {
        if(str.length>n){
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
            initDatas() ;
        },
        // 爬虫规则市场数据加载
        initCrawlers: function () {
            nav(3);
            options._url = "/api/crawlers/role" ; // 重置请求地址
            initType();
            initOrderByCond() ;
            initFilter() ;
            initDatas() ;
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
        initInstitutionHome: function (id) {
            nav(6);

            // 加载服务商明细
            LoadingData.request({url: "/api/institutionV2/"+id}, function(data){
                $("#tmpl_institution").tmpl(data.pageInfo,{
                    _date : function (date) {
                        return _tmp_formatedate(date)
                    },
                    _num : function (num) {
                        return _tmp_formatnum(num) ;
                    },
                    _def : function (v,t) {
                        return _tmp_defaultvalue(v,t) ;
                    }
                }).appendTo("#institution-container");
            });

            // 加载数据包产品
            options._url = "/api/institutionV2" ; // 重置请求地址
            initDatas() ;
        }
    };
}() ;