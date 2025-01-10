package com.example.test_project.util;

import com.example.test_project.dto.ResponseDTO;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class ResponseUtil {
    public static <T> ResponseDTO<T> handleOkResponse(ResponseDTO<T> responseDTO, Object data, String message){
        responseDTO.setData(data);
        responseDTO.setMessage(message);
        responseDTO.setStatus(HttpStatus.OK.value());
        responseDTO.setTimestamp(LocalDateTime.now());
        return responseDTO;
    }
    public static <T> ResponseDTO<T> handleCreateResponse(ResponseDTO<T> responseDTO, Object data, String message){
        responseDTO.setData(data);
        responseDTO.setMessage(message);
        responseDTO.setStatus(HttpStatus.CREATED.value());
        responseDTO.setTimestamp(LocalDateTime.now());
        return responseDTO;
    }
    public static <T> ResponseDTO<T> handleConflictResponse(ResponseDTO<T> responseDTO, String message){
        responseDTO.setData(null);
        responseDTO.setMessage(message);
        responseDTO.setStatus(HttpStatus.CONFLICT.value());
        responseDTO.setTimestamp(LocalDateTime.now());
        return responseDTO;
    }

    public static ResponseDTO handleErrorResponse(ResponseDTO responseDTO, String message) {
        responseDTO.setData(null);
        responseDTO.setMessage(message);
        responseDTO.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        responseDTO.setTimestamp(LocalDateTime.now());
        return responseDTO;
    }

    public static ResponseDTO handleNotFoundResponse(ResponseDTO responseDTO, String message) {
        responseDTO.setData(null);
        responseDTO.setMessage(message);
        responseDTO.setStatus(HttpStatus.NOT_FOUND.value());
        responseDTO.setTimestamp(LocalDateTime.now());
        return responseDTO;
    }
}
