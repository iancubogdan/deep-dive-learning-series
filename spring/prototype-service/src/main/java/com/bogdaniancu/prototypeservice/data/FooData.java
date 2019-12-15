package com.bogdaniancu.prototypeservice.data;

import com.bogdaniancu.prototypeservice.aspects.NotLogged;
import lombok.Data;

@Data
public class FooData {
    private String publicField1;
    private String publicField2;
    @NotLogged
    private String privateField3;
    private String publicField4;
}
