/**
 * Created by zd on 2017/5/4.
 */
$(function(){
    if(!$(".system-item").hasClass("open")){
        $(".system-item>a").trigger("click");
    }
    $(".system-item").addClass("active").siblings(".active").removeClass("active");
    $(".sub-menu li.nav-item.active").removeClass("active");
    $(".sysSetting-item").addClass("active");

    //返回菜单列表
    $(".returnList").click(function(){
        $(".page-content-wrapper").load("/sys/menu",{});
    });
});