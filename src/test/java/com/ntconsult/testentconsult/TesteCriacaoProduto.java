package com.ntconsult.testentconsult;

import static io.restassured.RestAssured.given;

import java.math.BigDecimal;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.ntconsult.testentconsult.models.CriacaoProdutoRequisicao;
import com.ntconsult.testentconsult.models.CriacaoProdutoResposta;

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
public class TesteCriacaoProduto {
        @BeforeAll
    public static void setup() {
        RestAssured.baseURI = Urls.URL_SISTEMA;
    }

    @Epic("JIRA: Criação de Produto")
    @Story("Post Criação de Produto")
    @Issue("JIRA-009")
    @Feature("ADD Produto 009")
    @Severity(SeverityLevel.NORMAL)
    @Test
    void produtoCriacao() {
        var novoProduto = new CriacaoProdutoRequisicao();
        novoProduto.setTitle("Perfume Oi");
        novoProduto.setDescription("Mega Discount, Impression of A...");
        novoProduto.setPrice(new BigDecimal("13"));
        novoProduto.setDiscountPercentage(new BigDecimal("8.4"));
        novoProduto.setRating(new BigDecimal("4.26"));
        novoProduto.setStock(65);
        novoProduto.setBrand("Impression of Acqua Di Gio");
        novoProduto.setCategory("fragrances");
        novoProduto.setThumbnail("https://i.dummyjson.com/data/products/11/thumnail.jpg");
        
        var resposta = given()
                .redirects().follow(true)
                .header("Content-Type", "application/json")
                .body(novoProduto)
                .when().post("https://dummyjson.com/products/add")
                .then()
                .extract()
                .response();

        var produtoResposta = resposta.jsonPath().getObject(".", CriacaoProdutoResposta.class);
        Assertions.assertNotEquals(0,produtoResposta.getId());
        Assertions.assertEquals(novoProduto.getTitle(), produtoResposta.getTitle());
        Assertions.assertEquals(novoProduto.getDescription(), produtoResposta.getDescription());
        Assertions.assertEquals(novoProduto.getPrice(), produtoResposta.getPrice());
        Assertions.assertEquals(novoProduto.getDiscountPercentage(), produtoResposta.getDiscountPercentage());
        Assertions.assertEquals(novoProduto.getRating(), produtoResposta.getRating());
        Assertions.assertEquals(novoProduto.getStock(), produtoResposta.getStock());
        Assertions.assertEquals(novoProduto.getBrand(), produtoResposta.getBrand());
        Assertions.assertEquals(novoProduto.getCategory(), produtoResposta.getCategory());
        Assertions.assertEquals(novoProduto.getThumbnail(), produtoResposta.getThumbnail());
        Assertions.assertEquals(201, resposta.statusCode() );

    }

    
}
