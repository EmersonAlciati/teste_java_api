package com.ntconsult.testentconsult;

import static io.restassured.RestAssured.given;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import com.ntconsult.testentconsult.models.LoginErroResposta;
import com.ntconsult.testentconsult.models.LoginRequisicao;
import com.ntconsult.testentconsult.models.LoginResposta;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Issue;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import io.restassured.RestAssured;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class TesteLoginTestes {
    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = Urls.URL_SISTEMA;
    }

    @Epic("JIRA: Login")
    @Story("Post Login")
    @Issue("JIRA-003")
    @Feature("Funcionalidade 003")
    @Severity(SeverityLevel.NORMAL)
    @Test
    void loginAutenticacao() {
        var login = new LoginRequisicao();
        login.setUsername("emilys");
        login.setPassword("emilyspass");
        var resposta = given()
                .redirects().follow(true)
                .header("Content-Type", "application/json")
                .body(login)
                .when().post("https://dummyjson.com/auth/login")
                .then().extract().response();

        var loginResposta = resposta.jsonPath().getObject(".", LoginResposta.class);
        Assertions.assertEquals(1, loginResposta.getId());
        Assertions.assertEquals("emilys", loginResposta.getUsername());
        Assertions.assertEquals("emily.johnson@x.dummyjson.com", loginResposta.getEmail());
        Assertions.assertEquals("Emily", loginResposta.getFirstName());
        Assertions.assertEquals("Johnson", loginResposta.getLastName());
        Assertions.assertEquals("female", loginResposta.getGender());
        Assertions.assertEquals("https://dummyjson.com/icon/emilys/128", loginResposta.getImage());
        Assertions.assertNotNull(loginResposta.getToken());
        Assertions.assertNotNull(loginResposta.getRefreshToken());

    }

    @Epic("JIRA: Login Errado")
    @Story("Post Login")
    @Issue("JIRA-004")
    @Feature("Funcionalidade 004")
    @Severity(SeverityLevel.NORMAL)
    @Test
    void loginAutenticacaoTrocado() {
        var login = new LoginRequisicao();
        login.setUsername("emilys1");
        login.setPassword("emilyspass");
        var resposta = given()
                .redirects().follow(true)
                .header("Content-Type", "application/json")
                .body(login)
                .when().post("https://dummyjson.com/auth/login")
                .then()
                .extract().response();

        var loginResposta = resposta.jsonPath().getObject(".", LoginErroResposta.class);
        Assertions.assertEquals("Invalid credentials", loginResposta.getMessage());

    }
}
