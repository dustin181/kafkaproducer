package com.kafkaproducer.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Employee {

    private String employeeId;
    private String name;
    private Integer salary;
}
