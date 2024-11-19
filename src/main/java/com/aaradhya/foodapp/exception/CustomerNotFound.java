package com.aaradhya.foodapp.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class CustomerNotFound extends RuntimeException {
    public final String msg;
}
