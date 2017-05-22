var Common = {
    /**
     * 自定义表格
     * @param settings
     */
    ajaxTable : function(settings) {
        //提示信息
        var lang = {
            "sProcessing": "处理中...",
            "sLengthMenu": "每页 _MENU_ 项",
            "sZeroRecords": "没有匹配结果",
            "sInfo": "当前显示第 _START_ 至 _END_ 项，共 _TOTAL_ 项。",
            "sInfoEmpty": "当前显示第 0 至 0 项，共 0 项",
            "sInfoFiltered": "(由 _MAX_ 项结果过滤)",
            "sInfoPostFix": "",
            "sSearch": "搜索:",
            "sUrl": "",
            "sEmptyTable": "表中数据为空",
            "sLoadingRecords": "载入中...",
            "sInfoThousands": ",",
            "oPaginate": {
                "sFirst": "首页",
                "sPrevious": "上页",
                "sNext": "下页",
                "sLast": "末页",
                "sJump": "跳转"
            },
            "oAria": {
                "sSortAscending": ": 以升序排列此列",
                "sSortDescending": ": 以降序排列此列"
            }
        } ;

        var _grid = settings.container ;
        var _url = settings.url ;
        var _cols = settings.columns ;

        //初始化表格
        $(_grid).dataTable({
            language:lang,  //提示信息
            autoWidth: false,  //禁用自动调整列宽
            stripeClasses: ["odd", "even"],  //为奇偶行加上样式，兼容不支持CSS伪类的场合
            processing: true,  //隐藏加载提示,自行处理
            serverSide: true,  //启用服务器端分页
            searching: false,  //禁用原生搜索
            orderMulti: false,  //启用多列排序
            order: [],  //取消默认排序查询,否则复选框一列会出现小箭头
            renderer: "bootstrap",  //渲染样式：Bootstrap和jquery-ui
            pagingType: "full_numbers",  //分页样式：simple,simple_numbers,full,full_numbers
            columnDefs:[{
                targets: 6,
                render: function (data, type, row, meta) {
                    var isUsed,
                        color;
                    if(row.isUsed == 1){
                        isUsed = "下架";
                        color = "red";
                    }else {
                        isUsed = "上架";
                        color = "blue";
                    }
                    return '<a href="javascript:void(0)" class="btn btn-sm green btn-outline filter-submit revise" data-value="'+ row.id +'">修改</a>' +
                        '<button class="btn btn-sm '+ color + ' btn-outline filter-cancel isUsed" value=" '+ row.id +' ">'+ isUsed + '</button>';
                }
            },
                { "orderable": false, "targets": 4 }
            ],
            ajax: function (data, callback, settings) {

                console.log(data);
                //封装请求参数
                var param = {};
                param.rows = data.length;//页面显示记录条数，在页面显示每页显示多少项的时候
                param.page = (data.start / data.length)+1;//当前页码
                param.name = data.search;
                //console.log(param);
                //ajax请求数据
                $.ajax({
                    type: "GET",
                    url: _url,
                    cache: false,  //禁用缓存
                    data: param,  //传入组装的参数
                    dataType: "json",
                    success: function (result) {

                        var dataChage = result.pageInfo.list;
                        if(dataChage.length > 0){
                            for ( var i = 0; i<dataChage.length; i++){
                                var time = dataChage[i].addTime;
                                dataChage[i].addTime = Common._formatedate(time);
                                var props = dataChage[i].dataTypeProps;
                                dataChage[i].dataTypeProps = Common._dataTypeProps(props);
                                var typeId = dataChage[i].dataType;
                                dataChage[i].dataType = dataTypeJson[typeId];
                            }
                        }

                        //封装返回数据
                        var returnData = {};
                        returnData.draw = data.draw;//这里直接自行返回了draw计数器,应该由后台返回
                        returnData.recordsTotal = result.pageInfo.total;//返回数据全部记录
                        returnData.recordsFiltered = result.pageInfo.total;//后台不实现过滤功能，每次查询均视作全部结果
                        returnData.data = result.pageInfo.list;//返回的数据列表
                        //调用DataTables提供的callback方法，代表数据已封装完成并传回DataTables进行渲染
                        //此时的数据需确保正确无误，异常判断应在执行此回调前自行处理完毕
                        callback(returnData);
                    }
                });
            },
            //列表表头字段
            columns: _cols
        }).api();
        //此处需调用api()方法,否则返回的是JQuery对象而不是DataTables的API对象
    },
    createAttr:function (opt) {
        var obj = {};
        obj.attrId = opt.attrId;
        obj.attrName = opt.attrName;
        obj.valId = opt.valId;
        obj.valName = opt.valName;
        return obj;
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
            returnData.push(jsonData.计价方式);
            returnData.push(jsonData.数据格式);
            returnData.push(jsonData.数据来源);
            returnData.push(jsonData.数据大小);
            return returnData.join(",");
        }else{
            return "";
        }

    }

};