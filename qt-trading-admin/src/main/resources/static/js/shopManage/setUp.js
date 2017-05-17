$(document).ready(function(){
    //表单元素
    var $form = $('#form_sample'),//form表单
        $waresN = $('#waresName'),//产品名称
        $describe = $('#describe'),//产品描述
        $classA = $('#classA'),//一级分类
        $classB = $('#classB'),//二级分类
        $optTmpl = $('#opt_tmpl'),//分类模板
        $charge = $('#charge'),//收费方式
        $price = $('#Price'),//价格
        $whole = $('#whole'),//整包价格
        $child = $('#child'),//子文件价格
        $file = $('#fileName');//上传文件


    $('#describe').trumbowyg();//文本编辑器实例化
    $('.dropify').dropify();//图片上传实例化

    //上传文件插件
    $("input[type=file]").change(function(){
        $(this).parents(".uploader").find(".filename").val($(this).val());
    });

    $("input[type=file]").each(function(){
        if($(this).val()==""){
            $(this).parents(".uploader").find(".filename").val("未选中文件...");
        }
    });

    //收费价格显示与否
    $charge.on('click','input',function () {
        if(this.type != 'radio'){
            return;
        }else if($(this).is(':checked') === true){
            if($(this).attr("id") === 'mode1'){
                $price.css('display','block');
            }else {
                $price.css('display','none');
            }
        }
    });

    //收费价格校验
    $price.on('blur','input',function () {
        if($price.css('display') != 'none'){
            if($whole.val() == '' || $child.val() == ''){
                $('#warning').css('display','block');
                return;
            }else {
                $('#warning').css('display','none');
            }
        }
    });


    var typeList = function (ele,id) {
        ele.empty();
        var url;
        if(ele.is('#classA') == true){
            url = "/api/dataType/findRootNode";
        }else if(ele.is('#classB') == true){
            url = "/api/dataType/findSecondNode";
        }
        $.ajax({
            url: url,
            dataType: "json",
            type:"get",
            data:{
                id:id
            },
            error:function () {
                alert("加载错误")
            },
            success: function (ret) {
                var data = ret.pageInfo;
                $optTmpl.tmpl(data).appendTo(ele);
            }
        });
    };
    

    typeList($classA);

    $classA.on('change',function () {
        var id = $(this).val();
       if(id == ""){
           return;
       }else {
           typeList($classB,id);
       }
    });











    $form.on('submit',function (e) {
        //e.preventDefault(); //组织默认提交表单
       //获取表单信息
        var whole = 0,
            child = 0,
            wares = $waresN.val(),
            describe = $describe.html(),
            type = $('#waresType input:radio:checked ').val(),
            classA = $classA.val(),
            classB = $classB.val(),
            source = $('#source input:radio:checked ').val();


        var fd = new FormData();
        fd.append("shopName", wares);
        fd.append("shopContact", describe);
        fd.append("shopMobile", type);
        fd.append("merchantBaseId", classA);
        fd.append("commission", classB);
        fd.append("shopIntro", source);

        // fd.forEach(function(i, j) {
        //     console.log(i, j);
        // });


        // $.ajax({
        //     url: '/api/product/add',
        //     type: "post",
        //     dataType:'json',
        //     data: fd,
        //     error: function () {
        //         alert("错误")
        //     },
        //     success: function() {
        //         alert("成功")
        //     }
        // });
    })


});
