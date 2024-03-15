package com.boot.shareBook.validator;

import com.boot.shareBook.model.Review;
import com.boot.shareBook.model.User;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

// https://docs.spring.io/spring-framework/reference/core/validation/validator.html
@Component
public class UserValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object obj, Errors e) {
        User u = (User) obj;
        validateField(u.getUsername(), "username", "아이디(이메일)를 입력해주세요.", e);
        validateField(u.getPassword(), "password", "비밀번호를 입력해주세요.", e);
        validateField(u.getName(), "name", "이름을 입력해주세요.", e);
        validateField(u.getNickname(), "nickname", "닉네임을 입력해주세요.", e);
        validateField(u.getBirthday(), "birthday", "생년월일을 입력해주세요.", e);
    }

    private void validateField(Object fieldValue, String fieldName, String errorMessage, Errors e) {
        if (fieldValue == null || (fieldValue instanceof String && StringUtils.isEmpty((String) fieldValue))) {
            e.rejectValue(fieldName, "key", errorMessage);
        }
    }
}