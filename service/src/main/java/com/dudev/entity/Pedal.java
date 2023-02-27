package com.dudev.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "pedals", schema = "public")
public class Pedal {

    @Id
    private long id;
    private String model;
    private String description;
    private String media;
    private LocalDateTime timestamp;
    private long user;
    private double price;
    private long brand;
    private boolean isClosed;
    private long changeType;
    private double changeValue;
    private String changeWish;
}
