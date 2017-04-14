var type,priceStyle,dataFormat,dataSource,dataSize,sortStyle;
//点击左侧数据大分类
$(".data_sort>li>a").unbind("click").click(function(){
    $(".data_sort>li>a").removeClass("active");
    $(this).addClass("active");
    $(".sub_sort a").removeClass('active');
    $(".sub_sort .a1").addClass("active");
    type=$(this).attr("data-id");
    priceStyle=0;dataFormat=0;dataSource=0;dataSize=0;sortStyle=0;
});
//计价方式
$(".priceStyle>li>a").unbind("click").click(function(){
    $(".priceStyle>li>a").removeClass("active");
    $(this).addClass("active");
    priceStyle=$(this).attr("data-id");
});
//数据格式
$(".dataFormat>li>a").unbind("click").click(function(){
    $(".dataFormat>li>a").removeClass("active");
    $(this).addClass("active");
    dataFormat=$(this).attr("data-id");
});
//数据来源
$(".dataSource>li>a").unbind("click").click(function(){
    $(".dataSource>li>a").removeClass("active");
    $(this).addClass("active");
    dataSource=$(this).attr("data-id");
});
//数据大小
$(".dataSize>li>a").unbind("click").click(function(){
    $(".dataSize>li>a").removeClass("active");
    $(this).addClass("active");
    dataSize=$(this).attr("data-id");
});
//排序方式
$(".sortStyle>li>a").unbind("click").click(function(){
    $(".sortStyle>li>a").removeClass("active");
    $(this).addClass("active");
    sortStyle=$(this).attr("data-id");
});
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
$('#pageTool').Paging({pagesize:12,count:100,toolbar:true,callback:function(page,size,count){
    //console.log(page);//当前页
    //console.log(size);//每页条数
    //console.log(count);//总页数
}});