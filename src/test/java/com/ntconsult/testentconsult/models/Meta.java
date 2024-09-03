package com.ntconsult.testentconsult.models;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class Meta {
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String barcode;
    private String qrCode;
    
}
