package com.mx.utils.Validators;/*
@author 郭子雄
@description 用户表单检验
*/

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserValidator {
    public  static boolean checkError(BindingResult result){
        if(result.hasErrors()) {

            //校验失败，返回失败,显示校验失败的错误信息
            Map<String, Object> map = new HashMap<>();
            List<FieldError> errors = result.getFieldErrors();
            for(FieldError fieldError : errors) {
                map.put(fieldError.getField(), fieldError.getDefaultMessage());
            }
            System.out.println(map);
            return true;
        }else {
            return false;
        }
    }
}
