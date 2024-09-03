package com.ntconsult.testentconsult;

import static io.restassured.RestAssured.given;

import com.ntconsult.testentconsult.models.LoginRequisicao;
import com.ntconsult.testentconsult.models.LoginResposta;

public class Util {
    public static LoginResposta fazer_login(){
         var login = new LoginRequisicao();
        login.setUsername("emilys");
        login.setPassword("emilyspass");
        var resposta = given()
                .redirects().follow(true)
                .header("Content-Type", "application/json")
                .body(login)
                .when().post("https://dummyjson.com/auth/login")
                .then().extract().response();
        return resposta.jsonPath().getObject(".", LoginResposta.class);
    }
}
