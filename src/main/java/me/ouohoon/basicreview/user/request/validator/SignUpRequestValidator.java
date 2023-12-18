package me.ouohoon.basicreview.user.request.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import me.ouohoon.basicreview.user.request.SignUpRequest;

import java.util.regex.Pattern;

import static me.ouohoon.basicreview.global.dto.ValidationCode.*;


public class SignUpRequestValidator implements ConstraintValidator<ValidSignUpRequest, Object> {

    private static final Pattern NICKNAME_PATTERN = Pattern.compile("^[a-zA-Z0-9]{3,}$");

    @Override
    public void initialize(ValidSignUpRequest constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        SignUpRequest request = (SignUpRequest) value;
        boolean valid = true;

        if (!validateNickname(request.getNickname())) {
            setErrorMessage(SIGNUP_NICKNAME.getField(), SIGNUP_NICKNAME.getMessage(), context);
            valid = false;
        }

        if (!validatePassword(request.getNickname(), request.getPassword())) {
            setErrorMessage(SIGNUP_PASSWORD.getField(), SIGNUP_PASSWORD.getMessage(), context);
            valid = false;
        }

        if (!validatePasswordConfirm(request.getPassword(), request.getPasswordConfirm())) {
            setErrorMessage(SIGNUP_PASSWORD_CONFIRM.getField(), SIGNUP_PASSWORD_CONFIRM.getMessage(), context);
            valid = false;
        }

        return valid;
    }

    private boolean validateNickname(String nickname) {
        return nickname != null && NICKNAME_PATTERN.matcher(nickname).matches();
    }

    private boolean validatePassword(String nickname, String password) {
        return password != null && !password.contains(nickname) && password.length() >= 4;
    }

    private boolean validatePasswordConfirm(String password, String passwordConfirm) {
        return password.equals(passwordConfirm);
    }

    private void setErrorMessage(String fieldName, String message, ConstraintValidatorContext context) {
        context.buildConstraintViolationWithTemplate(message)
                .addPropertyNode(fieldName)
                .addConstraintViolation()
                .disableDefaultConstraintViolation();
    }
}