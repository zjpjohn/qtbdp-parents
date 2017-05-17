$(document).ready(function(){
    //表单信息
    var $form = $('#form_content'),//form表单
        $waresN = $('#waresName'),//产品名称
        $describe = $('#describe'),//产品描述
        $classA = $('#classA'),//一级分类
        $classB = $('#classB'),//二级分类
        $charge = $('#charge'),//收费方式
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
    $("#charge").on('click','input',function () {
        if(this.type != 'radio'){
            return;
        }else if($(this).is(':checked') === true){
            if($(this).attr("id") === 'mode1'){
                $('#Price').css('display','block');
            }else {
                $('#Price').css('display','none');
            }
        }
    });

    //收费价格校验
    $('#Price').on('blur','input',function () {
        if($('#Price').css('display') != 'none'){
            if($whole.val() == '' || $child.val() == ''){
                $('#warning').css('display','block');
                return;
            }else {
                $('#warning').css('display','none');
            }
        }
    })

    
    
    function upload(ele) {
        ele.on('change',function () {
            $.ajax({

            })
        })
    }
    



    $form.on('submit',function (e) {
        e.preventDefault(); //组织默认提交表单
       //获取表单信息
        var whole = 0,
            child = 0,
            wares = $waresN.val(),
            describe = $describe.html(),
            type = $('#waresType input:radio:checked ').val(),
            classA = $classA.val(),
            classB = $classB.val(),
            source = $('#source input:radio:checked ').val(),
            file = $file.val(),
            fileImg = $('.dropify-render>img').attr("src");


        console.log(wares);
        console.log(describe);
        console.log(type);
        console.log(classA);
        console.log(classB);
        console.log(source);
        console.log(file);
        console.log(fileImg);


        // $.ajax({
        //     url: '',
        //     type: "post",
        //     dataType:'json',
        //     data: {
        //
        //     },
        //     error: function () {
        //
        //     },
        //     success: function() {
        //
        //     }
        // });
    })


});
