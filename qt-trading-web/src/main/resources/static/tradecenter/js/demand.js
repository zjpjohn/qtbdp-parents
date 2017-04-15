//获取列表数据
function getData(){
    $.ajax({
        type:"GET",
        dataType:"json",
        url:"",
        ansync:true,
        data:{

        },
        xhrFields:{
            withCredentials:true
        },
        success:function(data){
            console.log(data);
        },
        error:function(data){
            console.log(data);
        }
    });
}
//分页条   数据的总条数：count
$('#pageTool').Paging({pagesize:6,count:100,toolbar:true,callback:function(page,size,count){
    //console.log(page);//当前页
    //console.log(size);//每页条数
    //console.log(count);//总页数
}});

//数据类型筛选
$(".chose_menu>a").unbind("click").click(function(){
    $(this).addClass("active").siblings(".active").removeClass("active");
    dataType=$(this).attr("data-id");//数据类型
    type=$(".filters>.active").attr("data-id");//数据众包、方案召集
});
//数据众包、方案召集
$(".filters>a").unbind("click").click(function(){
    $(this).addClass("active").siblings(".active").removeClass("active");
    type=$(this).attr("data-id");//数据众包、方案召集
    dataType=$(".chose_menu>.active").attr("data-id");//数据类型
});