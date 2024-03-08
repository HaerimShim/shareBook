package com.boot.shareBook.validator;

import com.boot.shareBook.model.Review;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

// https://docs.spring.io/spring-framework/reference/core/validation/validator.html
@Component
public class ReviewValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return Review.class.equals(clazz);
    }

    @Override
    public void validate(Object obj, Errors e) {
        Review r = (Review) obj;
        validateField(r.getContent(), "content", "내용을 입력해주세요.", e);
        validateField(r.getComment(), "comment", "한줄평을 입력해주세요.", e);
        validateField(r.getWriter(), "writer", "저자를 입력해주세요.", e);
        validateField(r.getTitle(), "title", "책 제목을 입력해주세요.", e);
        validateField(r.getRating(), "rating", "평점을 입력해주세요.", e);
    }

    private void validateField(Object fieldValue, String fieldName, String errorMessage, Errors e) {
        if (fieldValue == null || (fieldValue instanceof String && StringUtils.isEmpty((String) fieldValue))) {
            e.rejectValue(fieldName, "key", errorMessage);
        }
    }
}