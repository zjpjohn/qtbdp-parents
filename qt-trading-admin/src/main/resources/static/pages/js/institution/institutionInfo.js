var id = Common.QueryString().id;

$(function () {

    Common.info({
        url:"/api/institutionV2/"+ id
    },function (data) {
        var institutionType,
            name,
            number,
            fullFace,
            negativeSide,
            person,
            location,
            tel,
            $name = $("#nameLabel"),
            $IDLabel = $('#IDLabel'),
            $IDImgLabel = $('#IDImgLabel');

        if(data.institutionType == 2){

            $name.html("姓名");
            $IDLabel.html("身份证号");
            $IDImgLabel.html("身份证");

            institutionType = "个人";
            if(!data.personalInfoModel){
                layer.msg("没有相关服务商个人信息",{icon: 5});
            }else {
                name = data.personalInfoModel.realName;
                number = data.personalInfoModel.idNumber;
                fullFace = data.personalInfoModel.fullFacePhoto;
                negativeSide = data.personalInfoModel.negativeSidePhoto;
                person = data.personalInfoModel.createId;
                tel = data.personalInfoModel.phone;
                location = data.personalInfoModel.address
            }
        }else {

            $name.html("企业名");
            $IDLabel.html("营业执照号");
            $IDImgLabel.html("营业执照");

            institutionType = "企业";

            if(!data.companyInfoModel){
                layer.msg("没有相关服务商企业信息",{icon: 5});
            }else {
                name = data.companyInfoModel.companyName;
                number = data.companyInfoModel.licenseNumber;
                fullFace = data.companyInfoModel.license;
                negativeSide = '';
                person = data.companyInfoModel.createId;
                tel = data.companyInfoModel.phone;
                location = data.companyInfoModel.address
            }
        }


        if(person == 0){
            person = "系统管理员";
        }

        $('#identity').val(institutionType);//身份
        $('#institutionName').val(data.name);//服务商名称
        $('#domain').val(data.designation);//服务领域
        $('#logo').attr("src",data.logo);//logo


        $('#name').val(name);//姓名or企业名
        $('#ID').val(number);//身份证号or营业执照
        $('#IDImg1').attr("src",fullFace);//正面
        $('#IDImg2').attr("src",negativeSide);//反面



        $('#location').val(location);//所在地
        $('#person').val(person);//申请人
        $('#tel').val(tel);//电话
    });


});
