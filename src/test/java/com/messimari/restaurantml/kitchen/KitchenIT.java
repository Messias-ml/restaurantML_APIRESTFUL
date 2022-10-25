package com.messimari.restaurantml.kitchen;

import com.messimari.restaurantml.BaseTest;
import com.messimari.restaurantml.domain.model.KitchenEntity;
import com.messimari.restaurantml.domain.repository.KitchenRepository;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@TestPropertySource("../../../../application-test.properties")
public class KitchenIT extends BaseTest {

	@Autowired
	private KitchenRepository kitchenRepository;

	@BeforeEach
	public void setUp(){
		setUpBasic(port, KITCHENS);
		databaseCleaner.clearTables();
		prepareData();
	}

	private void prepareData() {
		KitchenEntity kitchen = new KitchenEntity();
		kitchen.setName("chinesa");
		KitchenEntity kitchen2 = new KitchenEntity();
		kitchen2.setName("tailandesa");
		kitchenRepository.saveAll(List.of(kitchenDefault(),kitchen2));
	}

	@Test
	public void whenExecuteRegisterKitchenThenReturnSuccess() {
		KitchenEntity kitchen = new KitchenEntity();
		kitchen.setName("chinesa");
		given()
				.body(kitchen)
				.contentType(ContentType.JSON)
		.when().post()
		.then().statusCode(201).body("name", equalTo(kitchen.getName()));
	}

	@Test
	public void whenExecuteSearchKitchenThenReturnSuccess() {
		given()
		.when().get()
		.then().statusCode(200);
	}

	@Test
	public void whenExecuteSearchKitchenByIdThenReturnSuccess() {
		given().pathParam("id",1)
		.when().get("{id}")
		.then().statusCode(200);
	}

	@Test
	public void whenExecuteRegisterKitchenThenReturnStatusCode400() {
		KitchenEntity kitchen = new KitchenEntity();
		kitchen.setName(null);
		given()
				.body(kitchen).contentType(ContentType.JSON)
		.when().post()
		.then().statusCode(400);
	}
}
