package com.kafkaproducer.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Bars {

    private String symbol;
    private Double open;
    private Double close;
    private Double high;
    private Double low;
    private Integer volume;
    private String timeStamp;
}
