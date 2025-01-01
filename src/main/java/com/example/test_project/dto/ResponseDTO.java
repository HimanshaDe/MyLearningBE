package com.example.test_project.dto;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ResponseDTO<T> implements Serializable {
    private int status;
    private Object data;

    private String message;

    private LocalDateTime timestamp;
}
