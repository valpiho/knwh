package com.pibox.knwh.exception.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UniqueFieldExistExceptionResponse {

    private String vatNumber;
}
