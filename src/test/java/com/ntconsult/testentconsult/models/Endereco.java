package com.ntconsult.testentconsult.models;

import lombok.Data;

@Data
public class Endereco {
    private String address;
    private String city;
    private Cordenadas coordinates;
    private String postalCode;
    //A variavel StateCode não está descrita na documentação
    private String stateCode;
    private String state;
    //A variavel country não está descrita na documentação
    private String country;

}
