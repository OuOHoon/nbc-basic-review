package me.ouohoon.basicreview.global.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BaseResponse<T> {

    private Boolean status; // 응답 코드로 행동하는 로직이 없으므로 요청의 성공, 실패만 쉽게 판단할 수 있도록 한다.
    private T payload;

    public static <P> BaseResponse<P> success(P payload) {
        return new BaseResponse<>(true, payload);
    }

    public static <P> BaseResponse<P> failure(P payload) {
        return new BaseResponse<>(false, payload);
    }
}
