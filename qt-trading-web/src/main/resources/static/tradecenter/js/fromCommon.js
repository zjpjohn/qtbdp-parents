var fromCommon = {
    //图片上传预览
    RenderImg : function (ele,imgID,url) {
        ele.on('change',function (e) {
            var file = e.target.files[0],
                reader = new FileReader();
            $(this).prev().hide();
            $(this).next().show();
            reader.onload = function(e){
                imgID.attr('src',e.target.result);
            };
            /*渲染图片*/
            reader.readAsDataURL(file);

            var formData = new FormData();
            formData.append("img", file);
            $.ajax({
                url:url,
                type:"POST",
                dataType:"json",
                contentType: false,
                processData: false,
                data:formData,
                beforeSend:function () {
                    layer.msg("图片正在上传",{icon:1});
                },
                error:function () {
                    layer.msg("图片上传失败",{icon:5});
                },
                success:function(data){
                    if(data.success == true){
                        layer.msg("图片上传成功",{icon:1});
                    }
                }
            })
        })
    }
};
