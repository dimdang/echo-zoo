package org.cool.zoo.util;

import org.cool.zoo.entities.response.JResponseEntity;
import org.springframework.http.HttpStatus;

/**
 * Created by Dang Dim
 * Date     : 20-Jan-18, 5:11 PM
 * Email    : d.dim@gl-f.com
 */

public final class ResponseFactory {

    public static <T> JResponseEntity<T> build() {
        return new JResponseEntity();
    }

    public static <T> JResponseEntity<T> build(String message) {
        return ResponseFactory.build(message, null);
    }

    public static <T> JResponseEntity<T> build(String message, HttpStatus httpStatus) {
        return ResponseFactory.build(message, httpStatus, null);
    }

    public static <T> JResponseEntity<T> build(String message, HttpStatus status, T body) {
        JResponseEntity<T> responseEntity = build();
        responseEntity.setMessage(message);
        responseEntity.setStatus(status);
        responseEntity.setData(body);
//        responseEntity.addBody(body);
        return responseEntity;
    }

    public static <T> JResponseEntity<T> build(String message, HttpStatus status, String key, T body) {
        JResponseEntity<T> responseEntity = build(message, status);
//        responseEntity.addBody(key, body);
        responseEntity.setData(body);
        return responseEntity;
    }
}