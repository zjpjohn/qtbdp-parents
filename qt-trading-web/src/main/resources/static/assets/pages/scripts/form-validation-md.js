var FormValidationMd = function() {


    /**
     * 表单校验
     * @param options
     * {
     *     _form: "#form_id",   // 表单元素ID
     *     _messages: json,     // 表单校验不通过提示信息
     *     _rules: json         // 表单校验规则
     * }
     */
    var handleValidation = function(options,callback) {
        // for more info visit the official plugin documentation: 
        // http://docs.jquery.com/Plugins/Validation
        var form = $(options._form);
        var error = $('.alert-danger', form);
        var success = $('.alert-success', form);

        form.validate({
            errorElement: 'span', //default input error message container
            errorClass: 'help-block help-block-error', // default input error message class
            focusInvalid: true, // do not focus the last invalid input
            ignore: "", // validate all fields including form hidden input
            messages: options._messages,
            rules: options._rules,

            invalidHandler: function(event, validator) { //display error alert on form submit              
                success.hide();
                error.show();
                scrollTo(error, -200);
            },

            errorPlacement: function(error, element) {
                if (element.is(':checkbox')) {
                    error.insertAfter(element.closest(".md-checkbox-list, .md-checkbox-inline, .checkbox-list, .checkbox-inline"));
                } else if (element.is(':radio')) {
                    error.insertAfter(element.closest(".md-radio-list, .md-radio-inline, .radio-list,.radio-inline"));
                } else {
                    error.insertAfter(element); // for other inputs, just perform default behavior
                }
            },

            highlight: function(element) { // hightlight error inputs
                $(element)
                    .closest('.form-group').addClass('has-error'); // set error class to the control group
            },

            unhighlight: function(element) { // revert the change done by hightlight
                $(element)
                    .closest('.form-group').removeClass('has-error'); // set error class to the control group
            },

            success: function(label) {
                label.closest('.form-group').removeClass('has-error'); // set success class to the control group
            },

            submitHandler: function(form) {
                success.show();
                error.hide();

                // 屏蔽提交按钮，防止页面重复提交操作
                var btn = Ladda.create( form.querySelector( 'button[type=submit]' ) );
                $.ajax({
                    url: $(form)[0].action,
                    type: "post",
                    contentType:"application/json; charset=utf-8",
                    data: $(form).serialize(),
                    dataType:"json",
                    beforeSend : function () {
                        btn.start() ;
                    },
                    success:function( data ){

                        // 调用回调函数
                        if($.isFunction(callback)) callback(data) ;

                        // 实际开发中需要删除setTimeout
                        setTimeout(function () {
                            btn.stop();

                            LoadingData.toastr({
                                _type: 'success',
                                _title: '表单提交',
                                _msg: '数据提交成功'
                            }) ;

                        },500);
                    },
                    error : function ( data ) {
                        btn.stop();

                        LoadingData.toastr({
                            _type: 'error',
                            _title: '表单提交',
                            _msg: '网络超时，请重试或者联系管理员'
                        }) ;
                    }
                });

                /*$(form).ajaxSubmit({
                    dataType:"json",
                    contentType:"application/json; charset=utf-8",
                    data: $(form).serializeArray(),
                    beforeSubmit : function () {
                        btn.start() ;
                    },
                    success:function( data ){

                        // 调用回调函数
                        if($.isFunction(callback)) callback(data) ;

                        // 实际开发中需要删除setTimeout
                        setTimeout(function () {
                            btn.stop();

                            LoadingData.toastr({
                                _type: 'success',
                                _title: '表单提交',
                                _msg: '数据提交成功'
                            }) ;

                        },500);
                    },
                    error : function ( data ) {
                        btn.stop();

                        LoadingData.toastr({
                            _type: 'error',
                            _title: '表单提交',
                            _msg: '网络超时，请重试或者联系管理员'
                        }) ;
                    }
                });*/
            }
        });
    }

    var scrollTo = function(el, offeset) {
        var pos = (el && el.size() > 0) ? el.offset().top : 0;

        if (el) {
            if ($('body').hasClass('page-header-fixed')) {
                pos = pos - $('.page-header').height();
            } else if ($('body').hasClass('page-header-top-fixed')) {
                pos = pos - $('.page-header-top').height();
            } else if ($('body').hasClass('page-header-menu-fixed')) {
                pos = pos - $('.page-header-menu').height();
            }
            pos = pos + (offeset ? offeset : -1 * el.height());
        }

        $('html,body').animate({
            scrollTop: pos
        }, 'slow');
    };

    return {
        //main function to initiate the module
        init: function(options,callback) {
            handleValidation(options,callback);
        }
    };
}();

