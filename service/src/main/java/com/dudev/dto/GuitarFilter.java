package com.dudev.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class GuitarFilter {

    String brand;
    String model;
    Integer year;
    String country;
    String changeType;
    String changeWish;
    Boolean closed;
}
