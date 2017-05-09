/**
 * Created by zd on 2017/5/3.
 */
$(function(){
    if(!$("#system-item").hasClass("open")){
        $("#system-item>a").trigger("click");
    }
    $("#system-item").addClass("active").siblings(".active").removeClass("active");
    $("#sub-menu li.nav-item.active").removeClass("active");
    $("#sysSetting-item").addClass("active");

    $(".menu_td").each(function(){
        var pidsArr=$(this).attr("pids").split(",");
        var length=pidsArr.length-2;
        $(this).css("padding-left",20*length);
    });

    //进入菜单添加页
    $(".addMenuLink").click(function(){
        $(".page-content-wrapper").load("/sys/menu/form",{});
    });

});