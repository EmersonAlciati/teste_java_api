package com.ntconsult.testentconsult.models;

import lombok.Data;

@Data
public class Companhia {
    private Endereco address;
    private String city;
    private Endereco coordinates;
    private String postalCode;
    private String state;
    private String department;
    private String name;
    private String title;



    
}
