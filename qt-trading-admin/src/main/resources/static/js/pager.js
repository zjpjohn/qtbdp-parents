/*
* 功能: 分页（支持所有请求方式）
*/
(function ($) {
    $.fn.pager = function (options) {
        var opts = $.extend({}, $.fn.pager.defaults, options);
        if(opts && opts.renderTo === undefined || opts.renderTo == null){
        	opts.renderTo = this;
        }
        generatePageNavBar(opts);
    };
    $.fn.pager.defaults = {
    	renderTo: null,//导航条id
        currentPage: 0,//当前页
        totalPage: 0,//总共页数
        totalRow: 0,//总记录数
        navBarStyle: '',//导航条样式
        isAjax: false,//是否为ajax请求
        func: null//回调函数
    };
})(jQuery);

//生成分页导航条
var generatePageNavBar = function(options){
	if(options){
		options.currentPage = options.currentPage === undefined ? 1 : parseInt(options.currentPage);//默认第一页
		options.totalPage = options.totalPage === undefined ? 1 : parseInt(options.totalPage);
		options.totalRow = options.totalRow === undefined ? 1 : parseInt(options.totalRow);
	}
	flushNavBar(options);//刷新分页导航条
	executeEvent(options);//执行分页导航条的事件
};

//刷新分页导航条
var flushNavBar = function(opts){
	var navbar = $(opts.renderTo);
	//var first = $('<a class="first" data-current="1" href="javascript:;" >首页</a>');
	var totalRow = $('<span>(共'+opts.totalRow+'条记录)&nbsp;</span>');
	var prev = $('<a class="prev" data-current="'+(opts.currentPage-1)+'" href="javascript:;" title="使用方向键左键也可翻到上一页哦！"><<上一页</a>');
	var next = $('<a class="next" data-current="'+(opts.currentPage+1)+'" href="javascript:;" title="使用方向键右键也可翻到下一页哦！">下一页>></a>');
	//var last = $('<a class="last" data-current="'+opts.totalPage+'" href="javascript:;" >尾页</a>');
	var direct_input = $('<label>跳到&nbsp;<input class="direct_input" title="输入页码，按回车快速跳转" size="4" value="'+opts.currentPage+'" id="currentPage"/><span title="共 '+opts.totalPage+' 页"> / '+opts.totalPage+' 页</span></label>');
	var direct = $('<a class="direct" href="javascript:;" >确定</a>');
	var pages = [];
	
	if(opts.totalPage <= 7){
		loopAppend(pages,1,opts.totalPage,opts.currentPage);
	}else{
		if(opts.currentPage + 2 <= 7){
			loopAppend(pages,1,7,opts.currentPage);
			pages.push($('<span class="more">...</span>'));
		}else{
			loopAppend(pages,1,2,opts.currentPage);
			pages.push($('<span class="more">...</span>'));
			if(opts.currentPage + 2 >= opts.totalPage){
				loopAppend(pages,opts.totalPage-5+1,opts.totalPage,opts.currentPage);
			}else{
				loopAppend(pages,opts.currentPage-2,opts.currentPage+2,opts.currentPage);
				pages.push($('<span class="more">...</span>'));
			}
		}
	}
	
	navbar.html("");//先清空原来的
	navbar.append(totalRow);
	//navbar.append(first);
	if(opts.currentPage > 1){
		navbar.append(prev);
	}
	for(var i=0,len=pages.length; i<len; i++){
		navbar.append(pages[i]);
	}
	if(opts.currentPage < opts.totalPage){
		navbar.append(next);
	}
	//navbar.append(last);
	navbar.append(direct_input);
	navbar.append(direct);
};

//执行分页导航条的事件
var executeEvent = function(options){
	//为a绑定click事件
	$(options.renderTo).find("a").unbind("click");
	$(options.renderTo).find("a").bind("click",function(){
		var page = $(this).attr("data-current");//或者$(this).html()
		if($(this).hasClass("direct")){
			var inputVal = $(".direct_input").val();
			var result = testInput(inputVal,options.totalPage);
			if(result['isLegal']){
				page = inputVal;
			}else{
				alert(result['msg']);
				return;
			}
		}
		if(options.isAjax){
			options.currentPage = page;
			generatePageNavBar(options);
		}
		options.func(page);
	});
	//为input绑定keyup事件
	$(options.renderTo).find("input.direct_input").unbind("keyup");
	$(options.renderTo).find("input.direct_input").bind("keyup",function(e){
		e = e || event;
        if (e.keyCode == 13) {  //判断是否单击的enter按键(回车键)
        	var page;
        	var inputVal = $(this).val();
        	var result = testInput(inputVal,options.totalPage);
			if(result['isLegal']){
				page = inputVal;
			}else{
				alert(result['msg']);
				return;
			}
			if(options.isAjax){
				options.currentPage = page;
				generatePageNavBar(options);
			}
			options.func(page);
        }
	});
	//绑定键盘左右方向键
	/*$(document).unbind("keydown");
	$(document).bind("keydown",function(e){
		e = e || event;
		if(e.keyCode != 37 && e.keyCode != 39){
			return;
		}
		var current = $(options.renderTo).find(".current").html();
    	current = parseInt(current);
    	var page;
		if (e.keyCode == 37) {  //判断是否单击的右方向键
        	page = current-1;
        }
        if (e.keyCode == 39) {  //判断是否单击的右方向键
        	page = current+1;
        }
        if(page > options.totalPage || page < 1){
        	return;
        }
        if(options.isAjax){
			options.currentPage = page;
			generatePageNavBar(options);
		}
		options.func(page);
	});*/
};

//循环追加页码信息
var loopAppend = function(pages,begin,end,currentPage){
	for(var i=begin; i<=end; i++){
		if(i == currentPage){
			pages.push($('<span class="current" >'+i+'</span>'));
		}else{
			pages.push($('<a data-current="'+i+'" class="p" href="javascript:;" >'+i+'</a>'));
		}
	}
};

//验证输入页码是否合法
var testInput = function(inputVal,totalPage){
	var result = {};
	var bool = true;
	var msg = "";
	var pattern = eval("/^[0-9]*[1-9][0-9]*$/");
	console.log(inputVal);
	if(!pattern.test(inputVal)){
		msg = "请输入正确的页码";
		bool = false;
	}
	if(inputVal > totalPage){
		msg = "输入页码不能大于总页数";
		bool = false;
	}
	result.isLegal = bool;
	result.msg = msg;
	return result;
};
/*function pager() {
	$("#page").pager({
		// renderTo: null,
		currentPage : $("#page").attr("data-currentPage"),
		totalPage : $("#page").attr("data-totalPage"),
		totalRow : $("#page").attr("data-totalRow"),
		navBarStyle : '',
		func : function(current) {
			loadPagerData(current);
		}
	});
}*/
function pager(pagen) {
	var pageid="#"+pagen;
	$(pageid).pager({
		// renderTo: null,
		currentPage : $(pageid).attr("data-currentPage"),
		totalPage : $(pageid).attr("name"),
		totalRow : $(pageid).attr("value"),
		navBarStyle : '',
		func : function(current) {
			loadPagerData(current);
		}
	});
}
