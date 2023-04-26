package com.dynatrace.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ExchangeRate {

    private String code;
    private float maxMid;
    private float minMid;
}
