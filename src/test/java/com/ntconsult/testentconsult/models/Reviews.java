package com.ntconsult.testentconsult.models;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class Reviews {
    private int rating;
    private String comment;
    private LocalDateTime date;
    private String reviewerName;
    private String reviewerEmail;
}
