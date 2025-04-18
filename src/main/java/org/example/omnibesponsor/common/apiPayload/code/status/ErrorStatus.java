package org.example.omnibesponsor.common.apiPayload.code.status;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.omnibesponsor.common.apiPayload.code.BaseErrorCode;
import org.example.omnibesponsor.common.apiPayload.code.ErrorReasonDto;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorStatus implements BaseErrorCode {

    // 일반 상태
    _INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR,"COMMON500","서버 에러"),
    _BAD_REQUEST(HttpStatus.BAD_REQUEST,"COMMON400","잘못된 요청입니다."),
    _UNAUTHORIZED(HttpStatus.UNAUTHORIZED,"COMMON401","인증이 필요합니다"),
    _FORBIDDEN(HttpStatus.FORBIDDEN,"COMMON402","금지된 요청입니다."),
    _NOT_FOUND(HttpStatus.NOT_FOUND,"COMMON403","데이터를 찾지 못했습니다."),

    // 카테고리 상태
    _ALREADY_EXIST_CATEGORY(HttpStatus.BAD_REQUEST,"CATEGORY4001","이미 존재하는 카테고리입니다."),
    _CATEGORY_NOT_FOUND(HttpStatus.NOT_FOUND,"CATEGORY4002","카테고리가 없습니다."),

    // 스폰서 상태
    _ALREADY_EXIST_SPONSOR(HttpStatus.BAD_REQUEST,"SPONSOR4001","이미 존재하는 스폰서입니다."),
    _NOT_FOUND_SPONSOR(HttpStatus.NOT_FOUND,"SPONSOR4002","없는 스폰서입니다."),
    ;

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    @Override
    public ErrorReasonDto getReason() {
        return ErrorReasonDto.builder()
                .code(code)
                .message(message)
                .isSuccess(false)
                .build();
    }

    @Override
    public ErrorReasonDto getReasonHttpStatus() {
        return ErrorReasonDto.builder()
                .code(code)
                .message(message)
                .isSuccess(false)
                .httpStatus(httpStatus)
                .build();
    }
}
