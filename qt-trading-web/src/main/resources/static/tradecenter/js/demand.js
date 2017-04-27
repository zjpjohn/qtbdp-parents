nav(3);
function getData(dataType){
    // 数据众包
    var settings = {
        url: "/api/demand/buyInfos",
        tmpl_id: "#tmpl_demand_buyInfos" ,
        target: "#data_list" ,
        pager_id: "#pageTool",
        size:6,
        params: [{key:"dataType",value:dataType },{key:"isUsed",value:1 }]
    }
    //方案召集
    var settings_sos = {
        url: "/api/demand/sosInfos",
        tmpl_id: "#tmpl_demand_sosInfos" ,
        target: "#data_list" ,
        pager_id: "#pageTool",
        size:6,
        params: [{key:"dataType",value:dataType },{key:"isUsed",value:1 }]
    }
    var flag = $("#buyInfo").is(".active");
    if(flag){
        pageData.products(settings);
    }else{
        pageData.products(settings_sos) ;
    }
}
getData(null);
//数据众包、方案召集切换
$(".filters>a").unbind("click").click(function(){
    $(".chose_menu>a").removeClass("active");
    $("#limit").addClass("active");
    $(this).addClass("active").siblings(".active").removeClass("active");
    pageData._change = true ;
    getData(null);
});
//服务范围点击
function getDataByDataType(data) {
    $(data).addClass("active").siblings(".active").removeClass("active");
    pageData._change = true ;
    var dataTypeNew= $(data).attr("data-id");
    if(dataTypeNew == 0){
        dataTypeNew = null;
    }
    getData(dataTypeNew);
}
function receiveOrder(){
    console.log(userId);
    if (!userId ) {
        location.href = "/login";
    } else {
        $.ajax({
            type: "GET",
            dataType: "json",
            url: "/api/institution/exist",
            ansync: true,
            xhrFields: {
                widthCredentials: true
            },
            data: {
                userId: userId
            },
            success: function (data) {
                if (data.isExist) {
                    layer.msg("对不起，订单业务暂时还未开放", {icon: 5});
                } else {
                    layer.confirm("对不起，你还不是服务商，请先成为数据服务商",
                        {title: "", btn: ["确定", "取消"]},
                        function (index) {
                            layer.close(index);
                            location.href = "/institution/add";
                        },
                        function (index) {
                            layer.close(index);
                        });
                }
            },
            error: function (data) {
                console.log(data);
            }
        });
    }
}