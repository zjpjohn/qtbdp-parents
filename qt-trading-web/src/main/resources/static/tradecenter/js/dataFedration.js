//获取联盟数据
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
			var html = "";
			for(var i=0;i<data.length;i++){
				html +='<li><a href="javascript:;"></a><p>广东叁玖捌大数据科技有限公司</p><div class="prolist_fot"><span></span><em>物联网&nbsp;,&nbsp;其他</em></div></li>'
			}
			$(".prolist").html(html);
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
//点击分类时
$(".chose_menu>a").unbind("click").click(function(){
	$(this).addClass("active").siblings(".active").removeClass("active");
	var type=$(this).attr("data-id");
	console.log(type);
});


