package com.walter.o21jwtproject.aopexceptions;

import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.View;

import java.security.SignatureException;

@RestControllerAdvice
public class GlobalExceptionHandlerAspect {

    private final View error;

    public GlobalExceptionHandlerAspect(View error) {
        this.error = error;
    }

    @ExceptionHandler(Exception.class)
    public ProblemDetail handleSecurityException(Exception e) {
        ProblemDetail errorDetail= null;

        if(e instanceof BadCredentialsException) {
            errorDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(401), e.getMessage());
            errorDetail.setProperty("access_denied_reason", "Bad Credentials");
        }

        if(e instanceof UsernameNotFoundException) {
            errorDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(401), e.getMessage());
            errorDetail.setProperty("access_denied_reason", "Bad Credentials");
        }

        if(e instanceof SignatureException) {
            errorDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(401), e.getMessage());
            errorDetail.setProperty("access_denied_reason", "JWT Signature not valid (Token not valid)");
        }

        if(e instanceof ExpiredJwtException) {
            errorDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(401), e.getMessage());
            errorDetail.setProperty("access_denied_reason", "JWT Token Expired");
        }

        return errorDetail;
    }
}
