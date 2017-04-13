/**
 * Created by dell on 2017/3/1.
 */
$(function(){
    //获取轮播图
    /*$.ajax({
        type:"get",
        async:true,
        url:INTERFACE_URL+"/adpicture",
        data:{
            typeAttrId:"ad_index_top"
        },
        dataType:"jsonp",
        jsonp:"callback",
        success:function(obj){
            console.log(obj.data);
            var picHTML="",tipHtml="";
            for(var i=0;i<obj.data.length;i++){
                if(i==0){
                    picHTML+='<li style="opacity:1">';
                }else{
                    picHTML+='<li>';
                }
                picHTML+='<a href="'+obj.data[i].url+'" style="background:url('+obj.data[i].previewImagePath+') no-repeat top center">'+
                            '</a></li>'
            }
            for(var i=0;i<obj.data.length;i++){
                if(i==0){
                    tipHtml+='<a href="javascript:;" class="small_active"> <i></i> </a>';
                }else{
                    tipHtml+='<a href="javascript:;"> <i></i> </a>';
                }
            }

            $("#bannerpic").html(picHTML);
            $("#bannerbtn").html(tipHtml);
*/
            // 轮播图开始
            var left = $('.content_middle .btnLeft');//获取左点击
            var right = $('.content_middle .btnRight');//获取右点击
            var aSmall = $('.content_middle .table a');
            var aLi = $('.content_middle ul li');
            var iNow = 0;
            // 左点击
            left.click(function(){
                iNow--;
                // 判断回流
                if(iNow<0){
                    iNow=2;
                }
                aLi.eq(iNow).siblings().stop().animate({
                    opacity:0

                },800);
                aLi.eq(iNow).stop().animate({
                    opacity:1

                },800);
                aSmall.eq(iNow).addClass('small_active').siblings().removeClass('small_active');

            });
            // 右点击切换
            right.click(function(){
                iNow++;
                if(iNow>2){
                    iNow=0;
                }
                aLi.eq(iNow).siblings().stop().animate({
                    opacity:0

                },800);
                aLi.eq(iNow).stop().animate({
                    opacity:1

                },800);
                aSmall.eq(iNow).addClass('small_active').siblings().removeClass('small_active');
            });

            //手动切换
            aSmall.mouseover(function(){
                var n = $(this).index();
                var n2=$('.content_middle .table a.small_active').index();
                iNow = n;
                if(n==n2){
                    return;
                }
                aLi.eq(iNow).siblings().stop().animate({
                    opacity:0

                },800);
                aLi.eq(iNow).stop().animate({
                    opacity:1

                },800);
                aSmall.eq(iNow).addClass('small_active').siblings().removeClass('small_active');
            });
            // 封装函数体
            function move1(){
                aLi.eq(iNow).siblings().stop().animate({
                    opacity:0

                },800);
                aLi.eq(iNow).stop().animate({
                    opacity:1
                },800);
                aSmall.eq(iNow).addClass('small_active').siblings().removeClass('small_active');
            }

            // 定个定时器的初始值

            function run2(){
                iNow++;
                if(iNow>2){
                    iNow=0;
                }
                move1();
            }

// 定时器
            timer = setInterval(run2,2500);

//当鼠标划入，停止轮播图切换
            $(".content_middle").hover(function(){
                clearInterval(timer);
                $('.content_middle .btnLeft').stop(true, true).animate({ left:'30px'}, 300);
                $('.content_middle .btnRight').stop(true, true).animate({ right:'30px'}, 300);
            },function(){
                timer = setInterval(run2,2500);
                $('.content_middle .btnLeft').stop(true, true).animate({ left:'-41px'}, 300);
                $('.content_middle .btnRight').stop(true, true).animate({ right:'-41px'}, 300);
            })
        /*},
        error:function(data){
            console.log(data);
        }
    });*/
})