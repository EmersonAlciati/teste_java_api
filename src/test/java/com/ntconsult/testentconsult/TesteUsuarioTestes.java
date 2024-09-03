package com.ntconsult.testentconsult;

import static io.restassured.RestAssured.given;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import com.ntconsult.testentconsult.models.Usuario;
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
public class TesteUsuarioTestes {

    @BeforeAll
	public static void setup() {
		RestAssured.baseURI = Urls.URL_SISTEMA;
	}

    @Epic("Teste Usuario")
	@Story("Teste Usuário")
	@Issue("JIRA-002")
	@Feature("Funcionalidade 002")
	@Severity(SeverityLevel.NORMAL)
	@Test
	void buscar_usuário_autenticacao() {
		var resposta=given()
		.when().get("/users")
		.then().extract().response();

		var usuarios = resposta.jsonPath().getList("users",Usuario.class);
		Assertions.assertEquals(1, usuarios.get(0).getId() );
	}
    
}
