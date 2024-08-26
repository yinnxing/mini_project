package yin.com.stores.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
public enum ErrorCode {
    INVALID_KEY(1001, "Uncategorized error", HttpStatus.BAD_REQUEST),
    UNCATEGORIZED_EXCEPTION(9999, "Uncategorized exception", HttpStatus.INTERNAL_SERVER_ERROR),
    USER_EXISTED(1002, "User existed",HttpStatus.BAD_REQUEST ),
    PRODUCT_EXISTED(1010, "Product existed",HttpStatus.BAD_REQUEST ),
    PRODUCT_NOT_FOUND(1009, "Product not found",HttpStatus.BAD_REQUEST ),
    USERNAME_INVALID(1003, "Username must be at least {min} characters", HttpStatus.BAD_REQUEST),
    PASSWORD_INVALID(1004, "Password must be at least {min} characters", HttpStatus.BAD_REQUEST),
    DOB_INVALID(1008, "Your age must be at least {min}", HttpStatus.BAD_REQUEST),
    USER_NOT_EXIST(1005, "User not exist", HttpStatus.NOT_FOUND),
    STORE_NOT_FOUND(1009, "Store not found", HttpStatus.NOT_FOUND),
    PRODUCT_NOT_FOUND_IN_STORE(1011, "Product not found in store", HttpStatus.NOT_FOUND),
    INSUFFICIENT_STOCK(1012, "Insufficient stock",  HttpStatus.BAD_REQUEST),
    UNAUTHENTICATED(1006, "Unauthenticated", HttpStatus.UNAUTHORIZED),
    UNAUTHORIZED(1007, "You do not have permission", HttpStatus.FORBIDDEN)
    ;
    private int code;
    private String message;
    private HttpStatusCode statusCode;


    ErrorCode(int code, String message, HttpStatusCode statusCode) {
        this.code = code;
        this.message = message;
        this.statusCode = statusCode;
    }



}
