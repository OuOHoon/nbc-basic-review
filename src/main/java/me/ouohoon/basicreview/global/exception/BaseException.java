package me.ouohoon.basicreview.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BaseException extends RuntimeException {

    private ErrorCode errorCode;
}
