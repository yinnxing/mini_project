package yin.com.stores.exception;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import yin.com.stores.dto.response.ApiResponse;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(value = AuthorizationDeniedException.class)
    ResponseEntity<ApiResponse> handlingAccessDeniedException(AuthorizationDeniedException exception){
        log.error("Exception: ", exception);
        return ResponseEntity.status(ErrorCode.UNAUTHORIZED.getStatusCode())
                .body(ApiResponse.builder()
                        .code(ErrorCode.UNAUTHORIZED.getCode())
                        .message(ErrorCode.UNAUTHORIZED.getMessage())
                        .build());
    }

     @ExceptionHandler(value = Exception.class)
     ResponseEntity<ApiResponse> handlingRuntimeException(RuntimeException exception){
         log.error("Exception: ", exception);
         ApiResponse apiResponse = new ApiResponse();
         apiResponse.setCode(ErrorCode.UNCATEGORIZED_EXCEPTION.getCode());
         apiResponse.setMessage(ErrorCode.UNCATEGORIZED_EXCEPTION.getMessage());
         return ResponseEntity.badRequest().body(apiResponse);
     }

    @ExceptionHandler(value = AppException.class)
    ResponseEntity<ApiResponse> handlingRuntimeException(AppException exception){
        log.error("Exception: ", exception);
        ErrorCode errorCode = exception.getErrorCode();
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setCode(errorCode.getCode());
        apiResponse.setMessage(errorCode.getMessage());
        return ResponseEntity.badRequest().body(apiResponse);

    }



}
