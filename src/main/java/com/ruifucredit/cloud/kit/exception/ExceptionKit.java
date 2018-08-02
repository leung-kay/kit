package com.ruifucredit.cloud.kit.exception;

import com.ruifucredit.cloud.kit.dto.Outgoing;
import com.ruifucredit.cloud.kit.dto.StatusCode;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.Optional;

public class ExceptionKit {

    private static final StatusCode[] STATUS_CODES = StatusCode.class.getEnumConstants();

    public static void except(StatusCode statusCode) {

        throw new Rexception(statusCode.toString());

    }

    public static Outgoing<?> badGoing(Throwable t) {

        // 如果不是通过except方法抛出的异常在这里统一处理
        if(!(t instanceof Rexception)) {
            return Outgoing.fail(t.toString());
        }

        Integer code = null;
        String message = null;

        try {
            code = Integer.valueOf(t.getLocalizedMessage());
        } catch (NumberFormatException e) {
            message = e.getLocalizedMessage();
        }

        if(code!=null) {
            int messageCode = code;
            Optional<StatusCode> optionalCode = Arrays.stream(STATUS_CODES).filter(statusCode -> statusCode.code==messageCode).findFirst();
            return optionalCode.<Outgoing<?>>map(Outgoing::fail).orElseGet(() -> Outgoing.fail(StatusCode.INTERNAL_SERVER_ERROR));
        } else if(StringUtils.hasText(message)) {
            return Outgoing.fail(message);
        } else {
            return Outgoing.fail(StatusCode.INTERNAL_SERVER_ERROR);
        }

    }

}
