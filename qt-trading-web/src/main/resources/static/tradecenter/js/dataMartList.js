var type,priceStyle,dataFormat,dataSource,dataSize,sortStyle;
//���������ݴ����
$(".data_sort>li>a").unbind("click").click(function(){
    $(".data_sort>li>a").removeClass("active");
    $(this).addClass("active");
    $(".sub_sort a").removeClass('active');
    $(".sub_sort .a1").addClass("active");
    type=$(this).attr("data-id");
    priceStyle=0;dataFormat=0;dataSource=0;dataSize=0;sortStyle=0;
});
//�Ƽ۷�ʽ
$(".priceStyle>li>a").unbind("click").click(function(){
    $(".priceStyle>li>a").removeClass("active");
    $(this).addClass("active");
    priceStyle=$(this).attr("data-id");
});
//���ݸ�ʽ
$(".dataFormat>li>a").unbind("click").click(function(){
    $(".dataFormat>li>a").removeClass("active");
    $(this).addClass("active");
    dataFormat=$(this).attr("data-id");
});
//������Դ
$(".dataSource>li>a").unbind("click").click(function(){
    $(".dataSource>li>a").removeClass("active");
    $(this).addClass("active");
    dataSource=$(this).attr("data-id");
});
//���ݴ�С
$(".dataSize>li>a").unbind("click").click(function(){
    $(".dataSize>li>a").removeClass("active");
    $(this).addClass("active");
    dataSize=$(this).attr("data-id");
});
//����ʽ
$(".sortStyle>li>a").unbind("click").click(function(){
    $(".sortStyle>li>a").removeClass("active");
    $(this).addClass("active");
    sortStyle=$(this).attr("data-id");
});
//��ȡ�б�����
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
//��ҳ��   ���ݵ���������count
$('#pageTool').Paging({pagesize:12,count:100,toolbar:true,callback:function(page,size,count){
    //console.log(page);//��ǰҳ
    //console.log(size);//ÿҳ����
    //console.log(count);//��ҳ��
}});