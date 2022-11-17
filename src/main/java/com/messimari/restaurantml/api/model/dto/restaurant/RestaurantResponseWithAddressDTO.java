package com.messimari.restaurantml.api.model.dto.restaurant;

import com.messimari.restaurantml.api.model.dto.formPayment.FormPaymentDTO;
import com.messimari.restaurantml.api.model.dto.address.AddressDTO;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class RestaurantResponseWithAddressDTO {

    private String name;

    private BigDecimal taxFrete;

    private String nameKitchen;

    private AddressDTO address;

    private List<FormPaymentDTO> formPayment;
}
