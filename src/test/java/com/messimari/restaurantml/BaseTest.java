package com.messimari.restaurantml;

import com.messimari.restaurantml.domain.model.FormPaymentEntity;
import com.messimari.restaurantml.domain.model.KitchenEntity;
import com.messimari.restaurantml.utils.DatabaseCleaner;
import io.restassured.RestAssured;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.server.LocalServerPort;

public class BaseTest {

    public static final String KITCHENS = "/kitchens";
    public static final String RESTAURANTS = "/restaurants";

    @LocalServerPort
    protected int port;

    @Autowired
    protected DatabaseCleaner databaseCleaner;

    protected KitchenEntity kitchenDefault() {
        KitchenEntity kitchen = new KitchenEntity();
        kitchen.setName("chinesa");
        return kitchen;
    }

    protected FormPaymentEntity formPaymentDefault(){
        return new FormPaymentEntity();
    }

    public static void setUpBasic(int port, String uri){
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        RestAssured.port = port;
        RestAssured.basePath = uri;
    }
}
