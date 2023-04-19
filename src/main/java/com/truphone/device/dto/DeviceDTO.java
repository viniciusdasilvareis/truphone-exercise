package com.truphone.device.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
public class DeviceDTO {

    private long id;

    private String name;

    private String brand;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH-mm:ss")
    private LocalDateTime creationTime;

}
