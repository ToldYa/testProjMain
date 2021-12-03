package com.vesa.testprojmain;

import com.vesa.testprojmain.exception.BadRequestException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

@Slf4j
public class AbstractController {

    protected void execute(final Runnable executeProcedure) {
        executeProcedure.run();
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<String> handleBadRequestException(Exception e) {
        log.debug(String.format("BadRequestException handled: %s", e.getCause().getMessage()));
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotImplementedException.class)
    public ResponseEntity<String> handleNotImplementedException(Exception e) {
        log.debug(String.format("NotImplemented handled"));
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }
}
