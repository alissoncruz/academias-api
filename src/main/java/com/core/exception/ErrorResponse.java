package com.core.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse {
    private String message;
    private List<String> details;
    private Boolean businessException;
    private Boolean notFoundException;
    private Boolean badRequestException;
}
