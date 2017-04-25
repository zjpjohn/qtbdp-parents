/**
 * Created by dell on 2017/4/24.
 */

function checkData(){
    var designation = $("#designation").val();
    var description = $("#description").val();
    var uploadtext = $(".uploadtext").val();
    var dataScale = $("#dataScale").val();

    var dimension=$('input:radio[name="dimension"]:checked').val();
    var dataType=$('input:radio[name="dataType"]:checked').val();

    var demandType=$('input:radio[name="demandType"]:checked').val();
    var expFee = $("#expFee").val();
    var fromDate = $("#fromDate").val();
    var toDate = $("#toDate").val();
    var contactsName = $("#contactsName").val();
    var contactsPhone = $("#contactsPhone").val();
    var contactsEmail = $("#contactsEmail").val();


    if(designation==""){
        $(".scalerror").html("*请输入数据名称");
        $("#designation").addClass("err").focus();
        return false;
    }
    if(description.length<10 || description.length>1000){
        $(".scalerror").html("*请输入0-1000字的产品描述");
        $("#datadescribe").addClass("err").focus();
        return false;
    }
    if(uploadtext=="" ){
        $(".scalerror").html("*请上传正确文件格式！支持的格式：docx，doc，xls，xlsx，pdf，txt，sql");
        $(".uploadtext").addClass("err").focus();
        return false;
    }
    if(dataScale=="" ){
        $(".scalerror").html("*请输入数据规模");
        $("#dataScale").addClass("err").focus();
        return false;
    }
    if(!dimension){
        $(".scalerror").html("*请选择数据维度");
        return false;
    }

   if(!dataType){
        $(".scalerror").html("*请选择数据类型");
        return false;
    }
   if(!demandType){
        $(".scalerror").html("*请选择需求类型");
        return false;
    }

   if(expFee==""){
        $(".scalerror").html("*请输入预期费用");
        $("#expFee").addClass("err").focus();
        return false;
    }

    //起止时间
   if(fromDate==""){
        $(".scalerror").html("*请输入开始时间");
        $("#fromDate").addClass("err").focus();
        return false;
    }
   if(toDate==""){
        $(".scalerror").html("*请输入结束时间");
        $("#toDate").addClass("err").focus();
        return false;
    }

   if(contactsName==""){
        $(".scalerror").html("*请输入联系人姓名");
        $("#contactsName").addClass("err").focus();
        return false;
    }
   if(contactsPhone==""){
        $(".scalerror").html("*请输联系电话");
        $("#contactsPhone").addClass("err").focus();
        return false;
    }
   if(!(/^13[0-9]{9}$|14[0-9]{9}|17[0-9]{9}|15[0-9]{9}$|18[0-9]{9}$/.test(contactsPhone))){
        $(".scalerror").html("*请输入正确的联系电话");
        $("#contactsPhone").addClass("err").focus();
        return false;
    }
   if(contactsEmail==""){
        $(".scalerror").html("*请输入联系邮箱");
        $("#contactsEmail").addClass("err").focus();
        return false;
    }
   if(!(/^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/.test(contactsEmail))){
        $(".scalerror").html("*请输入正确邮箱");
        $("#contactsEmail").addClass("err").focus();
        return false;
   }
    $(".scalerror").html("");
   return true;


}
$(".abouttime").datetimepicker({
    lang:'ch',
    format:'Y-m-d h:i:s',
    timepicker:true,
    yearStart:1990,
    yearEnd:2027,
    todayButton:true
});
$.datetimepicker.setLocale('ch');

var consubmit={
    scheme:function(){
        $("#dataissue").unbind("click").click(function(){
            if(!checkData()){
                return false;
            }
            var _data = consubmit._formatparam($("#formsubmits").serialize()) ;
            console.log("_data："+_data);

            if($(this).attr("class").indexOf("disabled")==-1){
                var me=this;
                $.ajax({
                    dataType: "text",
                    url: "/api/demand/sosInfos",
                    type: "post",
                    contentType:"application/json",
                    data: _data,
                    success: function(data){
                        console.log(data);
                        alert("提交成功");
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
    consubmit.scheme();

    //导航选中
    nav(3);
});















































