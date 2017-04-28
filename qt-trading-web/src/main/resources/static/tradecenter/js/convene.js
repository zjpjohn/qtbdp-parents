
function dateForMat(myDate){
    var myDate=new Date(myDate);
    var nowY = myDate.getFullYear();
    var nowM = myDate.getMonth()+1;
    var nowD = myDate.getDate();
    var date = nowY+"-"+(nowM<10 ? "0" + nowM : nowM)+"-"+(nowD<10 ? "0"+ nowD : nowD);
    return date;
}
$("#fromDate").val(dateForMat(new Date()));//初始化开始时间

function checkData(){
    var designation = $("#designation").val();
    var description = $("#description").val();
    var filePath = $("#filePath").val();
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
    if(filePath=="" ){
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
        $(".scalerror").html("*请输入截至时间");
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
/*
$(".abouttime").datetimepicker({
    lang:'ch',
    format:'Y-m-d h:i:s',
    timepicker:true,
    yearStart:1990,
    yearEnd:2027,
    todayButton:true
});
$.datetimepicker.setLocale('ch');
*/
//上传文件

var headUrl="";
$("#filePath").change(function() {
   // var form = new FormData($("#formsubmits")[0]);
   // var _data = form.get("filePath") ;
    //console.log(form.get("file"));
    var form = new FormData();
    form.append("file",$("#filePath")[0].files[0]);

    $.ajax({
        url: "/api/upload/file",
        type: "post",
        xhrFields:{withCredentials:true},
        processData:false,
        contentType:false,
        data:form,
        success: function (data) {
            console.log(data);
            headUrl=data.file;
            console.log(headUrl);
            $("#filePath").attr("value",headUrl);
            $("#uploadtext").val(headUrl);



        }
    })
});


//点击发布

var consubmit={
    scheme:function(){
        $("#dataissue").unbind("click").click(function(){
            if(!checkData()){
                return false;
            }
            var _data = consubmit._formatparam($("#formsubmits").serializeArray()) ;
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
                        layer.confirm('您已成功提交发布信息，请耐心等待审核结果', {
                            btn: ['确定'] //按钮
                        }, function(){
                           history.go(-1);
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
        /*var _data = {} ;
        var _keys = param.split("&") ;
        _keys.forEach(function(val,index){
            var _attrs = val.split("=") ;
            _data[_attrs[0]] = _attrs[1] ;
        }) ;
        return JSON.stringify(_data);*/

        var _data = {} ;
        param.forEach(function(val,index){
            _data[val.name] = val.value ;
        }) ;

        return JSON.stringify(_data);
    }
}







$(function(){
    consubmit.scheme();
    //导航选中
    nav(3);
    fabuHover(2);
});
