package com.ntconsult.testentconsult;

import static io.restassured.RestAssured.given;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.ntconsult.testentconsult.models.LoginErroResposta;
import com.ntconsult.testentconsult.models.Produto;

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
public class TesteProdutoTestes {

     @BeforeAll
	public static void setup() {
		RestAssured.baseURI = Urls.URL_SISTEMA;
	}

    @Epic("Teste Produto")
	@Story("Teste Produto")
	@Issue("JIRA-005")
	@Feature("Funcionalidade 005")
	@Severity(SeverityLevel.NORMAL)
	@Test
	void buscar_produto() {
        var token = Util.fazer_login();
		var resposta=given()
        .header("Authorization" , "Bearer " + token.getToken())
		.when().get("/auth/products")
		.then()
        .extract().response();
        

        
		var produtos = resposta.jsonPath().getList("products",Produto.class);
		Assertions.assertEquals(1, produtos.get(0).getId() );
        Assertions.assertEquals("Essence Mascara Lash Princess", produtos.get(0).getTitle() );
	}

    @Epic("Teste Produto sem Bearer")
	@Story("Teste Produto sem Bearer")
	@Issue("JIRA-006")
	@Feature("Funcionalidade 006")
	@Severity(SeverityLevel.NORMAL)
	@Test
	void buscar_produto_sem_bearer() {
		var resposta=given()
		.when().get("/auth/products")
		.then()
        .extract().response();

		var loginResposta = resposta.jsonPath().getObject(".",LoginErroResposta.class);
        Assertions.assertEquals("Authentication Problem", loginResposta.getMessage());
        Assertions.assertEquals(403, resposta.statusCode() );
	}

    @Epic("Teste Produto Bearer Invalido")
	@Story("Teste Produto Bearer Invalido")
	@Issue("JIRA-007")
	@Feature("Funcionalidade 007")
	@Severity(SeverityLevel.NORMAL)
	@Test
	void buscar_produto_bearer_invalido() {

		var resposta=given()
        .header("Authorization" , "Bearer 123" )
		.when().get("/auth/products")
		.then()
        .extract().response();

		var loginResposta = resposta.jsonPath().getObject(".",LoginErroResposta.class);
        Assertions.assertEquals("Invalid/Expired Token!", loginResposta.getMessage());
        Assertions.assertEquals(401, resposta.statusCode() );
	}

    @Epic("Teste Produto Quantidade")
	@Story("Teste Quantidade Produto")
	@Issue("JIRA-011")
	@Feature("Funcionalidade 012")
	@Severity(SeverityLevel.NORMAL)
	@Test
	void buscar_quantidade_produto() {

		var resposta=given()
		.when().get("/products")
		.then()
        .extract().response();

		var produto = resposta.jsonPath().getList("products",Produto.class);
        Assertions.assertEquals(200, resposta.statusCode() );
        Assertions.assertNotEquals(0,produto.get(0).getId());
        Assertions.assertNotNull(produto.get(0).getTitle());
        Assertions.assertNotNull(produto.get(0).getTags());
        Assertions.assertNotEquals(0,resposta.jsonPath().getInt("total"));

	}

    @Epic("Teste Produto Buscar Produto por ID")
	@Story("este Produto Buscar Produto por ID")
	@Issue("JIRA-012")
	@Feature("Funcionalidade 012")
	@Severity(SeverityLevel.NORMAL)
	@Test
	void buscar_produto_id() {

		var resposta=given()
		.when().get("/products/1")
		.then()
        .log().all()
        .extract().response();

		var produto = resposta.jsonPath().getObject(".",Produto.class);
        Assertions.assertEquals(200, resposta.statusCode() );
        Assertions.assertEquals(1,produto.getId());
        Assertions.assertEquals("Essence Mascara Lash Princess" , produto.getTitle());
        Assertions.assertEquals("beauty", produto.getTags().get(0));
        Assertions.assertEquals("mascara", produto.getTags().get(1));


	}
    

    
}
