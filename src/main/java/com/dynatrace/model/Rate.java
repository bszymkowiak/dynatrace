package com.dynatrace.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Rate {
    private String no;
    private String effectiveDate;
    private float bid;
    private float ask;
    private float mid;
    private float diffAskBid;

}
