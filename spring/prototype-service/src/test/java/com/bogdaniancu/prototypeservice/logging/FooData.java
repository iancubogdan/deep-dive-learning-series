package com.bogdaniancu.prototypeservice.logging;

import lombok.Data;

@Data
public class FooData {
    private String publicField1;
    private String publicField2;
    @NotLogged
    private String privateField3;
    private String publicField4;
}
