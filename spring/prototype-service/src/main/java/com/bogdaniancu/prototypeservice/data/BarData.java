package com.bogdaniancu.prototypeservice.data;

import com.bogdaniancu.prototypeservice.aspects.NotLogged;
import lombok.Data;

@Data
@NotLogged
public class BarData {
    private String publicField1;
    private String publicField2;
    private String privateField3;
    private String publicField4;
}
