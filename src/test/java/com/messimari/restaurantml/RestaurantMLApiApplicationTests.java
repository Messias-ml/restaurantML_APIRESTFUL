package com.messimari.restaurantml;

import com.messimari.restaurantml.domain.model.KitchenEntity;
import com.messimari.restaurantml.domain.service.RegistrationKitchenService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.bind.MethodArgumentNotValidException;

import javax.validation.ConstraintViolationException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RestaurantMLApiApplicationTests {

	@Autowired
	private RegistrationKitchenService service;

	@Test
	public void whenExecuteRegisterKitchenThenReturnSuccess() {
		KitchenEntity kitchen = new KitchenEntity();
		kitchen.setName("chinesa");
		KitchenEntity kitchenEntity = service.registerKitchen(kitchen);
		assertNotNull(kitchenEntity);
		assertNotNull(kitchenEntity.getId());
	}

	@Test
	public void whenExecuteRegisterKitchenThenThrowHandleMethodArgumentNotValid() {
		KitchenEntity kitchen = new KitchenEntity();
		kitchen.setName(null);
		assertThrows(ConstraintViolationException.class, () -> service.registerKitchen(kitchen));
	}
}
