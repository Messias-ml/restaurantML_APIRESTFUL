package com.messimari.restaurantml.api.controller;

import com.messimari.restaurantml.api.model.dto.FormPaymentDTO;
import com.messimari.restaurantml.domain.service.FormPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/formsPayments")
public class FormPaymentController {

    @Autowired
    private FormPaymentService service;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    private List<FormPaymentDTO> findFromsPayments(){
        return service.findFormsPayments();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    private FormPaymentDTO findByIdFromPayment(@PathVariable Long id){
        return service.findByIdFromPayment(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    private void createFormPayment(@RequestBody FormPaymentDTO formPaymentDTO){
        service.createFormPayment(formPaymentDTO);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    private void updateFormPayment(@PathVariable Long id, @RequestBody FormPaymentDTO formPaymentDTO){
        service.updateFormPayment(id, formPaymentDTO);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    private void deleteFormPayment(@PathVariable Long id, @RequestBody FormPaymentDTO formPaymentDTO){
        service.deleteFormPayment(id, formPaymentDTO);
    }
}
