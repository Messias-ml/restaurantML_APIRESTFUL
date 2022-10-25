package com.messimari.restaurantml.restaurant;

import com.messimari.restaurantml.BaseTest;
import com.messimari.restaurantml.domain.dto.restaurant.IdFormPayment;
import com.messimari.restaurantml.domain.dto.restaurant.RestaurantRequestDTO;
import com.messimari.restaurantml.domain.repository.FormPaymentRepository;
import com.messimari.restaurantml.domain.repository.KitchenRepository;
import com.messimari.restaurantml.domain.repository.RestaurantRepository;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.math.BigDecimal;
import java.util.List;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@TestPropertySource("../../../../application-test.properties")
public class RestaurantIT extends BaseTest {

    @Autowired
    private KitchenRepository kitchenRepository;

    @Autowired
    private RestaurantRepository repository;

    @Autowired
    private FormPaymentRepository formPaymentRepository;

    @BeforeEach
    public void setUp(){
        setUpBasic(port, RESTAURANTS);
        databaseCleaner.clearTables();
        kitchenRepository.save(kitchenDefault());
        formPaymentRepository.save(formPaymentDefault());
    }

    @Test
    public void whenExecuteRegisterRestaurantThenSaveWithSuccessARestaurant(){
        RestaurantRequestDTO restaurantRequestDTO = restaurantToRegister();
        given()
                .body(restaurantRequestDTO)
                .contentType(ContentType.JSON)
        .when().post()
        .then().statusCode(201);
    }

    private RestaurantRequestDTO restaurantToRegister() {
        RestaurantRequestDTO restaurantRequestDTO = new RestaurantRequestDTO();
        restaurantRequestDTO.setName("restaurant test");
        restaurantRequestDTO.setIdKitchen(1L);
        restaurantRequestDTO.setTaxFrete(BigDecimal.valueOf(9.0));
        restaurantRequestDTO.setIdFormPayment(List.of(new IdFormPayment(1L)));
        return restaurantRequestDTO;
    }
}
