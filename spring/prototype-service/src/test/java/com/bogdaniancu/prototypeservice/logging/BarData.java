package com.bogdaniancu.prototypeservice.logging;

import lombok.Data;

@Data
@NotLogged
public class BarData {
    private String publicField1;
    private String publicField2;
    private String privateField3;
    private String publicField4;
}
