package com.truphone.device.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Entity
@Table(name="device")
@Data
public class Device {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "brand")
    private String brand;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH-mm:ss")
    @Column(name = "creationTime")
    private LocalDateTime creationTime;

}
