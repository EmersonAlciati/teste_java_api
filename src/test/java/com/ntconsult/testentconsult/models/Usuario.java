package com.ntconsult.testentconsult.models;

import java.math.BigDecimal;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Usuario {
    private int id;
    private String firstName;
    private String lastName;
    private String maidenName;
    private int age;
    private String gender;
    private String email;
    private String phone;
    private String username;
    private String password;
    private String birthDate;
    private String image;
    private String bloodGroup;
    private int height;
    private BigDecimal weight;
    private String eyeColor;
    private UsuarioCabelo hair;
    private String domain;
    private String ip;
    private Endereco address;
    private String macAddress;
    private String university;
    private Bank bank;
    private Companhia company;
    private String ein;
    private String ssn;
    private String userAgent;



}
