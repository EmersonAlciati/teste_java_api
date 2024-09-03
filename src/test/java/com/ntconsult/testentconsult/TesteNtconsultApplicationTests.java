package com.ntconsult.testentconsult;

import static io.restassured.RestAssured.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
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
class TesteNtconsultApplicationTests {

	@BeforeAll
	public static void setup() {
		RestAssured.baseURI = Urls.URL_SISTEMA;
	}

	@Epic("Teste Status OK")
	@Story("Status OK")
	@Issue("JIRA-001")
	@Feature("Funcionalidade 001")
	@Severity(SeverityLevel.NORMAL)
	@Test
	void buscar_status_da_aplicacao() {
		var resposta=given()
		.when().get("/test")
		.then().extract().response();

		Assertions.assertEquals("ok", resposta.jsonPath().getString("status"));
	}


}
