package com.bosonit.formacion.block11uploaddownloadfiles.exception;

import lombok.Data;

import java.util.Date;

@Data
public class CustomError {
    private Date timestamp = new Date();
    private int HttpCode;
    private String mensaje;
}
