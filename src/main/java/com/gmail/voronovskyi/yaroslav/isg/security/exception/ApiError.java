package com.gmail.voronovskyi.yaroslav.isg.security.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gmail.voronovskyi.yaroslav.isg.security.util.Constants;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
public class ApiError {

    private int error;
    private HttpStatus status;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constants.LOCAL_DATE_TIME_PATTERN)
    private LocalDateTime dateTime;
    private List<String> message;
}
