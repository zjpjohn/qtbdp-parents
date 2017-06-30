var id = Common.QueryString().id;

$(function () {

    Common.info({
        url:"/api/feedback/"+ id
    },function (data) {

        if(data.feedbackType == 1){
            data.feedbackType = "数据需求"
        }else if(data.feedbackType == 2){
            data.feedbackType = "商务合作"
        }else {
            data.feedbackType = "无此类型"
        }

        if(data.mark == 1){
            data.mark = "已查看"
        }else {
            data.mark = "未查看"
        }

        $('#type').val(data.feedbackType);//反馈类型
        $('#email').val(data.email);//邮箱
        $('#content').val(data.content);//反馈内容
        $('#IP').val(data.ip);//IP地址
        $('#isCheck').val(data.mark);//是否查看
        $('#time').val(Common._formatedate(data.addtime));//时间
        $('#phone').val(data.phone);//电话
    });

    $.ajax({
        type: "put",
        url: "/api/feedback/changeMark",
        data: {'id' :  id},
        dataType: "json",
        success: function (result) {
            if (result.success) {
            }else {
                layer.msg("修改用户反馈查看状态失败",{icon: 5});
            }
        },
        error: function () {
            layer.msg("修改用户反馈查看状态失败",{icon: 5});
        }
    });

});

