package com.ruifucredit.cloud.kit.aop;

import com.ruifucredit.cloud.kit.dto.Outgoing;
import com.ruifucredit.cloud.kit.exception.ExceptionKit;
import com.ruifucredit.cloud.kit.exception.Rexception;
import com.ruifucredit.cloud.kit.json.GsonKit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.stream.Collectors;

@ControllerAdvice
@Order(1000)
@Slf4j
public class Catcher {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Outgoing<?> throwing(MethodArgumentNotValidException ex) {

        String info = String.format("[CatchingException] %s", ex.toString());

        log.warn(info);

        BindingResult bs = ex.getBindingResult();
        String message = bs.getFieldErrors().stream().map(e -> e.getDefaultMessage()).collect(Collectors.joining(" ", "[", "]"));

        Outgoing<?> outgoing = Outgoing.fail(message);

        info += String.format(" [Outgoing] %s", GsonKit.toJson(outgoing));

        log.warn(info);

        return outgoing;
    }

    @ExceptionHandler(Rexception.class)
    public Outgoing<?> throwing(Rexception ex) {

        String info = String.format("[CatchingException] %s", ex.toString());

        log.error(info);

        Outgoing<?> outgoing = ExceptionKit.badGoing(ex);

        info += String.format(" [Outgoing] %s", GsonKit.toJson(outgoing));

        log.error(info);

        return outgoing;
    }

}
