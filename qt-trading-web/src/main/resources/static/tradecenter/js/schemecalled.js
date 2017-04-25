//导航选中
nav(3);
fabuHover(3);
//时间显示格式
function dateForMat(myDate){
    var myDate=new Date(myDate);
    var nowY = myDate.getFullYear();
    var nowM = myDate.getMonth()+1;
    var nowD = myDate.getDate();
    var date = nowY+"-"+(nowM<10 ? "0" + nowM : nowM)+"-"+(nowD<10 ? "0"+ nowD : nowD);
    return date;
}
$("#fromDate").val(dateForMat(new Date()));//初始化开始时间

//表单提交前验证
function checkData(){
    var designation = $("#designation").val();
    var description = $("#description").val();
    var dataPattern=$('input:radio[name="dataPattern"]:checked').val();
    var dataScale = $("#dataScale").val();
    var dataType=$("input[name='dataType']:checked").val();
    var expFee = $("#expFee").val();
    var fromDate = $("#fromDate").val();
    var toDate = $("#toDate").val();
    var contactsName = $("#contactsName").val();
    var contactsPhone = $("#contactsPhone").val();
    var contactsEmail = $("#contactsEmail").val();
    if(""==designation){
        $(".scalerror").html("*请输入数据名称");
        $("#designation").addClass("err").focus();
        return false;
    }
    if(description=="" ){
        $(".scalerror").html("*请输入需求描述");
        $("#description").addClass("err").focus();
        return false;
    }
    if( description.length<10 || description.length>1000){
        $(".scalerror").html("*请输入10~1000个字的需求描述");
        $("#description").addClass("err").focus();
        return false;
    }
    if(!dataPattern){
        $(".scalerror").html("*请选择数据格式");
        return false;
    }
    if(dataScale==""){
        $(".scalerror").html("*请输入数据规模");
        $("#dataScale").addClass("err").focus();
        return false;
    }
    if(!(/^[0-9]+([.][0-9]{0,2}){0,1}$/.test(dataScale))){
        $(".scalerror").html("*请输入正确的数据规模");
        $("#dataScale").addClass("err").focus();
        return false;
    }
    if(!dataType){
        $(".scalerror").html("*请选择数据类型");
        return false;
    }
    if(expFee==""){
        $(".scalerror").html("*请输入预期费用");
        $("#expFee").addClass("err").focus();
        return false;
    }
    if(!(/^[0-9]+([.][0-9]{0,2}){0,1}$/.test(expFee))){
        $(".scalerror").html("*请输入正确的预期费用");
        $("#expFee").addClass("err").focus();
        return false;
    }
    if(toDate=="" ){
        $(".scalerror").html("*请输入截至日期");
        $("#toDate").addClass("err").focus();
        return false;
    }
    if(contactsName=="" ){
        $(".scalerror").html("*请输入联系人");
        $("#contacts_name").addClass("err").focus();
        return false;
    }
    if(contactsPhone=="" ){
        $(".scalerror").html("*请输入联系电话");
        $("#contactsPhone").addClass("err").focus();
        return false;
    }
    if(!(/^13[0-9]{9}$|14[0-9]{9}|17[0-9]{9}|15[0-9]{9}$|18[0-9]{9}$/.test(contactsPhone))){
        $(".scalerror").html("*请输入正确的联系电话");
        $("#contactsPhone").addClass("err").focus();
        return false;
    }
    if(contactsEmail=="" ){
        $(".scalerror").html("*请输入联系邮箱");
        $("#contactsEmail").addClass("err").focus();
        return false;
    }
    if(!(/^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/.test(contactsEmail))){
        $(".scalerror").html("*请输入正确的联系邮箱");
        $("#contactsEmail").addClass("err").focus();
        return false;
    }
    return true;
}


var crowsubmit={
    scheme:function(){
        $("#formSubmit").unbind("click").click(function(){
            if(!checkData()){
                return false;
            }
            var _data = crowsubmit._formatparam($("#schemeForm").serialize()) ;
            console.log("_data："+_data);

            if($(this).attr("class").indexOf("disabled")==-1){
                var me=this;
                $.ajax({
                    dataType: "text",
                    url: "/api/demand/buyInfos",
                    type: "post",
                    contentType:"application/json",
                    data: _data,
                    success: function(data){
                        console.log(data);
                        layer.confirm("您已成功提交发布信息，请耐心等待审核结果",
                            {title:"",btn:["确定"]},
                            function(index){
                                layer.close(index);
                                location.href="/demand";
                            });
                    },
                    error:function(data){
                        console.log("提交失败");
                    }
                });
            }

        });
    },
    _formatparam :function (param) {

        if(!param) return ;
        var _data = {} ;
        var _keys = param.split("&") ;
        _keys.forEach(function(val,index){
            var _attrs = val.split("=") ;
            _data[_attrs[0]] = _attrs[1] ;
        }) ;
        return JSON.stringify(_data);
    }
}


$(function(){
    crowsubmit.scheme();
});


