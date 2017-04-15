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
$('#pageTool').Paging({pagesize:20,count:100,toolbar:true,callback:function(page,size,count){
    //console.log(page);//当前页
    //console.log(size);//每页条数
    //console.log(count);//总页数
}});

//信息列表筛选
$(".filter>a").unbind("click").click(function(){
    $(this).addClass("active").siblings(".active").removeClass("active");
    type=$(this).attr("data-id");//筛选值
});