$(document).ready(function(){
	
	    <!-- 前端表单验证 -->
        // 注册表单验证
        function ValidatorRegisterInfo(){
            $('#registerValidate').bootstrapValidator({
                message: '你的输入有误',
                feedbackIcons: {
                    valid: 'glyphicon glyphicon-ok',
                    invalid: 'glyphicon glyphicon-remove',
                    validating: 'glyphicon glyphicon-refresh'
                },
                fields: {
                    uName: {
                        message: '用户昵称验证失败',
                        validators: {
                            notEmpty: {
                                message: '用户昵称不能为空'
                            },
                            stringLength: {
                                min: 1,
                                max: 10,
                                message: '昵称长度为1-10个字符'
                            }
                        }
                    },
                    password:{
                        message:'密码不和规范',
                        validators:{
                            notEmpty:{
                                message:'用户新密码不能为空'
                            },
                            regexp: {
                                regexp: /^[^ ]+$/,
                                message: '密码不能有空格'
                            },
                            stringLength: {
                                min: 6,
                                max: 20,
                                message: '密码长度为6-20个字符'
                            },
                            identical: {
                                field: 'repassword',
                                message: '两次密码不一致！'
                            }
                        }
                    },
                    repassword:{
                        message:'密码不和规范',
                        validators:{
                            notEmpty:{
                                message:'用户新密码不能为空'
                            },
                            regexp: {
                                regexp: /^[^ ]+$/,
                                message: '密码不能有空格'
                            },
                            stringLength: {
                                min: 6,
                                max: 20,
                                message: '密码长度为6-20个字符'
                            },
                            identical: {
                                field: 'password',
                                message: '两次密码不一致！'
                            }
                        }
                    },
                    email:{
                        validators: {
                            notEmpty: {
                                message: '邮箱地址不能为空'
                            },
                            emailAddress: {
                                message: '邮箱地址格式有误'
                            }
                        }
                    }



                }
            });
        }
        ValidatorRegisterInfo();


        // 登录表单验证
        function ValidatorLoginInfo(){
            $('#login-form').bootstrapValidator({
                message: '你的输入有误',
                feedbackIcons: {
                    valid: 'glyphicon glyphicon-ok',
                    invalid: 'glyphicon glyphicon-remove',
                    validating: 'glyphicon glyphicon-refresh'
                },
                fields: {
                    name: {
                        message: 'ID验证失败',
                        validators: {
                            notEmpty: {
                                message: 'ID不能为空'
                            },
                            stringLength: {
                                min: 6,
                                max: 10,
                                message: 'ID长度为6-10个字符'
                            }
                        }
                    },
                    password:{
                        message:'密码不和规范',
                        validators:{
                            notEmpty:{
                                message:'用户新密码不能为空'
                            },
                            regexp: {
                                regexp: /^[^ ]+$/,
                                message: '密码不能有空格'
                            },
                            stringLength: {
                                min: 6,
                                max: 20,
                                message: '密码长度为6-20个字符'
                            }
                        }
                    }
                }
            });
        }
        ValidatorLoginInfo();

	

});