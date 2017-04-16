var Datamart = {

    /**
     * 数据商城获取产品数据
     *
     * @param _url 请求获取数据地址
     * @param _tmpl_id jquery template 模板元素，如：#div_id 或 .class_name
     * @param _target 替换html元素，如：#div_id 或 .class_name
     */
    products : function(_url, _tmpl_id, _target){

        var _loadding = $("#loadding") ;

        _loadding.hide() ;

        $.ajax({
            type:"GET",
            dataType:"json",
            url: _url,
            ansync:true,
            xhrFields:{
                withCredentials:true
            },
            beforeSend: function() {
                _loadding.show() ;
            },
            success:function(data){

                // console.log(data);

                if(data && data.pageInfo) {
                    $(_tmpl_id).tmpl(data.pageInfo).appendTo(_target);
                }
            },
            error:function(data){
                console.log(data);
            },
            complete : function () {
                _loadding.hide() ;
            }
        });
    },

    /**
     * 分页条   数据的总条数：count
     * @param _c 产品总记录数
     */
    paging : function (_c) {

        var _s = 12 ; // 固定每页12条数据

        $('#pageTool').Paging({
            pagesize: _s,
            count: _c,
            toolbar:true,
            callback:function(page,size,count){

                console.log(page);//当前页
                console.log(size);//每页条数
                console.log(count);//总页数
            }
        });
    }
};