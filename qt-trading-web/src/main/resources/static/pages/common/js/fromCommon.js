var fromCommon = {
    //图片上传预览
    RenderImg : function (ele,imgID,url,fileID) {
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
                        fileID.val(data.img);
                    }
                }
            })
        })
    },
    //选择框
    typeSelect:function ($ele,url,value,string) {
        $ele.empty();
        var html = '';
        if(value == ''){
            html = '<option value="">'+ string + '</option>';
            $ele.append(html);
            return;
        }else {
            $.ajax({
                url:url,
                type:"get",
                dataType:"json",
                error:function () {
                    layer.msg("获取数据类别失败",{icon:5});
                },
                success:function(ret){
                    if(ret){
                        var data = ret.pageInfo;
                        if(data.length == 0){
                            html = '<option value="0">无</option>';
                        }else {
                            html = '<option value="">'+ string + '</option>';
                            $.each(data, function(i, ele) {
                                var option = '<option data-pid="'+ ele.pid +'"  value="' + ele.id + '">' + ele.name + '</option>';
                                html += option;
                            });
                        }
                        $ele.append(html);
                    }
                }
            })
        }
    },

    inputBind:function (showInp,hideInp) {
        showInp.on('change',function () {
            var val = $(this).val();
            hideInp.val(val);
        })
    },
    QueryString:function () {
        var query_string = {};
        var query = window.location.search.substring(1);
        var vars = query.split("&");
        for (var i = 0; i < vars.length; i++) {
            var pair = vars[i].split("=");
            // If first entry with this name
            if (typeof query_string[pair[0]] === "undefined") {
                query_string[pair[0]] = pair[1];
                // If second entry with this name
            } else if (typeof query_string[pair[0]] === "string") {
                var arr = [query_string[pair[0]], pair[1]];
                query_string[pair[0]] = arr;
                // If third or later entry with this name
            } else {
                query_string[pair[0]].push(pair[1]);
            }
        }
        return query_string;
    }
};
