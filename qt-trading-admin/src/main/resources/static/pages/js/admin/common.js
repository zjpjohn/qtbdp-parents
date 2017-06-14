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
        var _columnDefs = settings.columnDefs;

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
            pagingType: "simple_numbers",  //分页样式：simple,simple_numbers,full,full_numbers
            columnDefs:_columnDefs,
            ajax: function (data, callback, settings) {

                //封装请求参数
                var param = {};
                param.rows = data.length;//页面显示记录条数，在页面显示每页显示多少项的时候
                param.page = (data.start / data.length)+1;//当前页码
                // param.name = data.search;
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
                                var time = dataChage[i].addtime,
                                    createTime = dataChage[i].createTime;
                                dataChage[i].addtime = Common._formatedate(time);
                                var props = dataChage[i].dataTypeProps;
                                dataChage[i].dataTypeProps = Common._dataTypeProps(props);
                                var typeId = dataChage[i].dataType;

                                dataChage[i].createTime = Common._formatedate(createTime);

                                if(dataChage[i].auditStatus == 1){
                                    dataChage[i].auditStatus = "审核已通过"
                                }else if(dataChage[i].auditStatus == 0){
                                    dataChage[i].auditStatus = "未审核"
                                }else {
                                    dataChage[i].auditStatus = "审核未通过"
                                }

                                if(dataChage[i].createId == 0){
                                    dataChage[i].createId = "系统管理员"
                                }

                                if(dataChage[i].institutionType == 1){
                                    dataChage[i].institutionType = "服务商"
                                }else {
                                    dataChage[i].institutionType = "个人"
                                }

                                if(typeId == 0){
                                    typeId = "其他";
                                }
                                dataChage[i].dataType = dataTypeJson[typeId]?dataTypeJson[typeId]:typeId;
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


    //创建属性对象数据
    createAttr:function (opt) {
        var obj = {};
        obj.attrName = opt.attrName;
        obj.valName = opt.valName;
        obj.attrId = opt.attrId;
        obj.valId = opt.valId;
        return obj;
    },
    //获取修改时页面id值
    QueryString:function () {
        var query_string = {};
        var query = window.location.search.substring(1);
        var vars = query.split("&");
        for (var i = 0; i < vars.length; i++) {
            var pair = vars[i].split("=");
            // If first entry with this name
            if (typeof query_string[pair[0]] === "undefined") {
                query_string[pair[0]] = pair[1];
                // If second entry with this name
            } else if (typeof query_string[pair[0]] === "string") {
                var arr = [query_string[pair[0]], pair[1]];
                query_string[pair[0]] = arr;
                // If third or later entry with this name
            } else {
                query_string[pair[0]].push(pair[1]);
            }
        }
        return query_string;
    },
    attrSelect:function (str,symbols) {
        if(typeof str != 'string' || str == ""){
            console.log("参数错误")
        }else {
            var newStr = str.split(symbols[0]),
                arr = [];
            newStr.pop();

            for(var i = 0;i < newStr.length;i++){
                var newArr = newStr[i].split(symbols[1]),
                    obj = {};
                obj.attrName = newArr[0];
                obj.valName = newArr[1];
                obj.attrId = newArr[2];
                obj.valId = newArr[3];
                arr.push(obj);
            }
            return arr;
        }
    },
    //渲染Select
    typeList:function (opt) {
        var ele = opt.ele,
            id = opt.id,
            tmpl = opt.tmpl,
            url = opt.url,
            CId = opt.CId,
            selected = opt.selected,
            attrID = opt.attrId,
            marketPrice = opt.marketPrice,
            price = opt.price,
            PMarket = opt.PMarket,
            PWhole = opt.PWhole,
            PChild = opt.PChild,
            itemPrice = opt.itemPrice,
            priceName = opt.priceName,
            attrContent = opt.attrContent;


        ele.empty();
        $.ajax({
            url: url,
            dataType: "json",
            type:"get",
            async:false,
            data:{
                isUsed:1,
                id:id
            },
            error:function () {
                layer.msg("获取列表数据失败",{icon:5});
            },
            success: function (ret) {
                var data = ret.pageInfo;
                tmpl.tmpl(data).appendTo(ele);


                if(CId){
                    Common.optionSelect({
                        ele:ele,
                        id:CId
                    });

                    attrID.css("display","block");
                    Common.attrList({
                        attrContent:attrContent,
                        id:CId,
                        selected:selected,
                        priceName:priceName,
                        marketPrice:marketPrice,
                        price:price,
                        itemPrice:itemPrice,
                        PMarket:PMarket,
                        PWhole:PWhole,
                        PChild:PChild
                    });
                }
            }
        });
    },
    //渲染已选择的option
    optionSelect:function (obj) {
        var ele = obj.ele,
            opt = ele.children(),
            id = obj.id;

        for(var i = 0;i < opt.length;i++){

            if($(opt[i]).val() == id){

                ele.val($(opt[i]).val());
                break;
            }else {
                continue;
            }
        }
    },
    //渲染单选框
    attrList:function (opt) {
        var attrContent = opt.attrContent,
            attrId = opt.attrId,
            id = opt.id,
            marketPrice = opt.marketPrice,
            price = opt.price,
            itemPrice = opt.itemPrice,
            PMarket = opt.PMarket,
            PWhole = opt.PWhole,
            PChild = opt.PChild,
            priceName = opt.priceName,
            selected = opt.selected;

        $.ajax({
            url: "/api/dataType/findTypeAttr",
            dataType: "json",
            type:"get",
            data:{
                id:id
            },
            error:function () {
                alert("加载错误")
            },
            success: function (ret) {
                var data = ret.pageInfo;
                if(attrId){
                    attrId.css("display","block");
                }

                $.each(attrContent,function (i,val) {
                    var tmpl = val.tmpl,
                        index = val.index,
                        idName = val.idName,
                        ele = val.attrN;
                    ele.empty();
                    tmpl.tmpl(data[index],{
                        setId:function (i) {
                            return idName + i;
                        }
                    }).appendTo(ele);


                    if(selected){
                        $.each(selected,function (s,v) {
                            if(ele.find("input").data("attrid") == v.attrId){
                                for(var j = 0;j < ele.find("input").length;j++){
                                    if($(ele.find("input")[j]).val() == v.valId){
                                        $(ele.find("input")[j]).attr("checked",'checked');
                                        if(v.attrId == 4 && v.valId == 101){
                                            priceName.css("display","block");
                                            PWhole.val(price);
                                            PChild.val(itemPrice);
                                            PMarket.val(marketPrice);
                                        }
                                    }
                                }
                            }
                        });
                    }
                });

            }
        });
    },
    /**
     * 时间格式转换
     * @param date
     * @private
     */
    _formatedate: function (date) {
        var formatedate=new Date(date);
      //  formatedate=formatedate.getFullYear()+"-"+(parseInt(formatedate.getMonth())+1)+"-"+formatedate.getDate()+" "+formatedate.getHours()+":"+formatedate.getMinutes()+":"+formatedate.getSeconds();
        return formatedate.toLocaleString().replace(/年|月/g, "-").replace(/日/g, " ");
    },

    //数据商场 具体详情数据格式解析
    _dataTypeProps: function (data) {
        if(data != null && data != ""){
            var dataArr = data.split(";");
            var jsonData = {};
            for(var i=0;i<dataArr.length;i++){
                var aa = new Array();
                aa = dataArr[i].split(":");
                jsonData[aa[0]] = aa[1];
            }
            return jsonData.产品类型?jsonData.产品类型:"该类型已不存在";
        }else{
            return "该类型已不存在";
        }
    },

    //确认与否弹框
    Modal : function(title, id,url,type) {
        var smodal =
            '<div class="modal fade confirm-modal-sm" id="myConfirmModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">' +
            '    <div class="modal-dialog">' +
            '        <div class="modal-content">' +
            '            <div class="modal-header">' +
            '                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">关闭</span></button>' +
            '                <h4 class="modal-title" id="myModalLabel">' + title + '</h4>' +
            '            </div>' +
            '            <div class="modal-body">' +
                '              <div class="form-group form-md-radios" id="Auditing">'+
                '               <div class="md-radio-inline">'+
                '                   <div class="md-radio">'+
                    '                   <input type="radio" id="pass"   name="isUsed" value="1" class="md-radiobtn" checked="checked">'+
                    '                   <label for="pass">'+
                    '                       <span></span>'+
                    '                       <span class="check"></span>'+
                    '                       <span class="box"></span> 通过'+
                    '                   </label>'+
                '                   </div>'+
                    '               <div class="md-radio">'+
                    '                   <input type="radio" id="NoPass" name="isUsed" value="2" class="md-radiobtn">'+
                    '                   <label for="NoPass">'+
                    '                      <span></span>'+
                    '                      <span class="check"></span>'+
                    '                      <span class="box"></span> 不通过'+
                    '                   </label>'+
                    '               </div>'+
                '                </div>'+
                '               </div>'+
                '               <div class="form-group" id="reason" style="display: none"> '+
            '                        <label class="control-label">不通过原因：</label>'+
            '                        <div class=""> '+
            '                            <input  type="text"  class="form-control borderRadiusIE8" placeholder="请输入原因" id="reasonContent" >'+
            '                        </div> '+
            '                   </div>  '+
            '            </div>' +
            '            <div class="modal-footer">' +
            '                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>' +
            '                <button type="button" class="btn btn-primary" id="j_confirm_btn">确认</button>' +
            '            </div>' +
            '        </div>' +
            '    </div>' +
            '</div>';

        var bconfirm = false;

        $('body').append($(smodal));

        $('#myConfirmModal').modal('show');

        $('#myConfirmModal').on('hidden.bs.modal', function(e) {
            $('#myConfirmModal').remove();
        });

        var AuditStatus = $('#Auditing input:radio[name="isUsed"]:checked').val(),
            radio = $('#Auditing input');


        radio.on('change', function () {
            if($(this).is(':checked') === true){
                AuditStatus = $(this).val();
                if(AuditStatus == 2){
                    $("#reason").css('display','block');
                }else {
                    $("#reason").css('display','none');
                }
            }
        });


        $('#j_confirm_btn').on('click', function () {
            if(AuditStatus == 2){
                var reason = $('#reasonContent').val();
            }else {
                reason = "";
            }

            $.ajax({
                url: url,
                dataType: "json",
                type:type,
                data:{
                    id:id,
                    status:AuditStatus,
                    reason:reason
                },
                error:function () {
                    layer.msg("审核失败",{icon: 5});
                },
                success: function (ret) {
                    if(ret.success === true){
                        $('#myConfirmModal').modal('hide');
                        layer.msg("审核成功",{icon: 1},function(){
                            window.location.reload();
                        });
                    }
                }
            })
        });

    },
    info : function (obj,fn) {
        var url = obj.url;

        $.ajax({
            type:'get',
            data:{
                id:id
            },
            dataType: "json",
            url:url,
            async:true,
            error:function () {
                layer.msg("获取详情失败",{icon: 5});
            },
            success: function (ret) {
                var data = ret.pageInfo;
                if(data){
                   fn(data);
                }else {
                    layer.msg("页面没有数据",{icon: 5});
                }
            }
        });
    },
    downloadFile:function (url,data,downloadUrl) {
        $.ajax({
            type:'get',
            data: data,
            dataType: "json",
            url:url,
            async:true,
            error:function () {
                layer.msg("下载出现错误",{icon: 5});
            },
            success: function (ret) {
                if(ret.success == true){
                    layer.msg("准备开始下载",{icon: 1},function () {
                        window.location.href = downloadUrl
                    });
                }else {
                    layer.msg("下载文件失败",{icon: 1});
                }
            }
        })
    },
    typeSelect:function (dataTypeJson,typeId) {
        var id = typeId;
        for(var k in dataTypeJson){
            if(typeId == k){
                typeId = dataTypeJson[k];

            }else {
                id = "其他";
            }
        }
        return id;
    }
};

