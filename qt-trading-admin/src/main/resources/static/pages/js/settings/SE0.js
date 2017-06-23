$(function () {
    var id,
        $tbody = $('tbody'),
        $searchBtn = $('#searchBtn'),
        searchInp = $('#searchInp');


    var settings = {
        container:"#datatable_ajax",
        url: "/api/seo/settings/resources?moduleId=abcd00001234efgh",
        columns: [
            { "data": "resourceId" },
            { "data": "resourceName" },
            { "data": "resourcePath" },
            { "data": null }
        ],
        columnDefs:[{
            targets: 3,
            render: function (data, type, row, meta) {
                return '<button  class="btn btn-sm green btn-outline filter-submit change_btn" data-toggle="modal" data-target="#SEOModal" data-seoid="'+ row.seoId +'" data-value="'+ row.resourceId +'">设置SEO</button>';
            }
        },
            { "orderable": false, "targets": 3 }
        ]
    };

    Common.ajaxTable(settings);




    //修改获取值
    $tbody.on('click', '.change_btn', function() {
        var seoId = $(this).data('seoid');
        id = $(this).data('value');
        $('#keyWord').val('');
        $('#desc').val('');
        $('#Title').val('');


        if(seoId == null){
            return;
        }else {
            var data = {
                resourcesId:id
            };
            $.ajax({
                url:'/api/seo/settings/'+id,
                type:"get",
                data:data,
                dataType:'json',
                async:true,
                error: function () {
                    layer.msg("获取信息错误",{icon: 5});
                },
                success: function (ret) {
                    if(ret.pageInfo){
                        $('#keyWord').val(ret.pageInfo.seoKeywords);
                        $('#desc').val(ret.pageInfo.seoDescription);
                        $('#Title').val(ret.pageInfo.seoTitle);
                    }else {
                        layer.msg("获取信息失败",{icon: 5});
                    }
                }
            })
        }
    });


    //添加SE0
    $('#confirm_btn').on('click', function () {
        var keyWord = $('#keyWord').val(),
            desc = $('#desc').val(),
            title = $('#Title').val();
        var data = {
            resourcesId:id,
            seoKeywords:keyWord,
            seoDescription:desc,
            seoTitle:title
        };

        $.ajax({
            type:"post",
            data:JSON.stringify(data),
            dataType:'json',
            url:'/api/seo/settings',
            contentType:'application/json',
            async:true,
            error: function () {
                layer.msg("添加出现错误",{icon: 5});
            },
            success: function (ret) {
                if(ret.result.success == true){
                    $('#SEOModal').modal('hide');
                    layer.msg("添加成功",{icon: 1},function () {
                        window.location.href = "/seo";
                    });
                }else {
                    layer.msg("添加失败",{icon: 5});
                }
            }
        })
    });
































    // //删除
    // $tbody.on('click', '.del_btn', function() {
    //     var id = $(this).data('id');
    //
    //     Common.confirmModal(id,'删除', function () {
    //         $.ajax({
    //             type:'get',
    //             data:{
    //                 id:id
    //             },
    //             dataType:'json',
    //             url:'',
    //             async:true,
    //             error: function () {
    //                 modal('删除失败');
    //             },
    //             success: function () {
    //                 $('#SEOModal').modal('hide');
    //                 layer.msg("删除成功",{icon: 1},function () {
    //                     window.location.href = "/seo";
    //                 });
    //             }
    //         })
    //     });
    // });

});
