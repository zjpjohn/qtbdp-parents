nav(2);
//banner动画
var m=0,n=1,deg1=0;deg2=0,plus=true,arrow=205,arrowLeft=882,plus2=true;
function intervals(){
    m++;
    //中间的箭头收缩
    if(m%3==0){
        if(arrow==205){
            plus2=false;
        }else if( arrow==175) {
            plus2=true;
        }
        if(plus2){
            arrow++;
            arrowLeft-=0.5;
        }else {
            arrow--;
            arrowLeft+=0.5;
        }
        $(".arrow").css("width",arrow).css("left",arrowLeft);
    }
    //最左边箭头、右边的小方块显示与消失
    if(m%25==0){
        n++;
        if(n>3){
            n=1;
        }
        $(".arrows>i:nth-child("+n+")").addClass("blue").siblings(".blue").removeClass("blue");
        if($(".square").attr("class").indexOf("disappear")==-1){
            $(".square").addClass("disappear");
        }else{
            $(".square").removeClass("disappear");
        }
    }
    //钟摆与左右摇摆
    deg1++;
    if(deg1>360){
        deg1=0;
    }
    if(deg2==45){
        plus=false;
    }else if( deg2==-45) {
        plus=true;
    }
    if(plus){
        deg2++;
    }else {
        deg2--;
    }
    $(".hex").css("transform","rotate("+deg1+"deg)");
    $(".clock").css("transform","rotate("+deg2+"deg)");
    //最右边数据递增
    if(m%100==0){
        m=0;
        if(parseInt($(".exchange").html())<1000000000){
            $(".exchange").html(parseInt($(".exchange").html())+parseInt(Math.random()*400+100));
        }
        if(parseInt($(".capacity").html())<10000){
            $(".capacity").html(parseInt($(".capacity").html())+parseInt(Math.random()*10+1)+"G");
        }
    }

}
setInterval(intervals,20);