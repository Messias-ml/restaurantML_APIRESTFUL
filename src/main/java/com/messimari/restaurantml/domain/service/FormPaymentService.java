package com.messimari.restaurantml.domain.service;

import com.messimari.restaurantml.api.model.dto.FormPaymentDTO;
import com.messimari.restaurantml.domain.exception.RecordNotFoundException;
import com.messimari.restaurantml.domain.model.FormPaymentEntity;
import com.messimari.restaurantml.domain.repository.FormPaymentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.messimari.restaurantml.core.ModelMapperConvert.convert;
import static com.messimari.restaurantml.core.ModelMapperConvert.convertList;

@Service
@AllArgsConstructor
public class FormPaymentService {

    private FormPaymentRepository repository;

    public List<FormPaymentDTO> findFormsPayments() {
        List<FormPaymentEntity> formsPaymentsEntity = repository.findAll();
        return convertList(formsPaymentsEntity, FormPaymentDTO.class);
    }

    public void registerFormPayment(FormPaymentDTO formPaymentDTO){
        FormPaymentEntity formPayment = convert(formPaymentDTO, FormPaymentEntity.class);
        repository.save(formPayment);
    }

    public FormPaymentDTO findByIdFromPayment(Long id) {
        FormPaymentEntity formPaymentEntity = repository.findById(id).orElseThrow(() -> new RecordNotFoundException(new Object[]{"Form Payment de id " + id}));
        return convert(formPaymentEntity, FormPaymentDTO.class);
    }
}