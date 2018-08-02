package com.ruifucredit.cloud.kit.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Outgoing<T> {

    private Integer code;
    private T result;
    private String message;

    private Outgoing(T result) {
        this(StatusCode.OK.code, result, StatusCode.OK.message);
    }

    private Outgoing(StatusCode statusCode) {
        this(statusCode.code, null, statusCode.message);
    }

    private Outgoing(StatusCode statusCode, T result) {
        this(statusCode.code, result, statusCode.message);
    }

    private Outgoing() {
        this(StatusCode.OK.code, null, StatusCode.OK.message);
    }

    public boolean isSuccess() {
        return new Integer(StatusCode.OK.code).equals(code);
    }

    public static Outgoing<?> ok() {
        return new Outgoing<>();
    }

    public static <R> Outgoing<R> ok(R result) {
        return new Outgoing<>(result);
    }

    public static <R> Outgoing<R> ok(StatusCode statusCode, R result) {
        return new Outgoing<>(statusCode, result);
    }

    public static Outgoing<?> fail(StatusCode statusCode) {
        return new Outgoing<>(statusCode);
    }

    public static Outgoing<?> fail(String message) {
        return new Outgoing<>(StatusCode.INTERNAL_SERVER_ERROR.code, null, message);
    }

}
