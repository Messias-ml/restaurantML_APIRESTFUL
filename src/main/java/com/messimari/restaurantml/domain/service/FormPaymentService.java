package com.messimari.restaurantml.domain.service;

import com.messimari.restaurantml.api.model.dto.formPayment.FormPaymentDTO;
import com.messimari.restaurantml.domain.exception.EntityInUseException;
import com.messimari.restaurantml.domain.exception.RecordNotFoundException;
import com.messimari.restaurantml.domain.model.FormPaymentEntity;
import com.messimari.restaurantml.domain.repository.FormPaymentRepository;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.messimari.restaurantml.core.modelMapper.ModelMapperConvert.convert;
import static com.messimari.restaurantml.core.modelMapper.ModelMapperConvert.convertList;

@Service
@AllArgsConstructor
public class FormPaymentService {

    private FormPaymentRepository repository;

    public List<FormPaymentDTO> findFormsPayments() {
        List<FormPaymentEntity> formsPaymentsEntity = repository.findAll();
        return convertList(formsPaymentsEntity, FormPaymentDTO.class);
    }

    public void createFormPayment(FormPaymentDTO formPaymentDTO){
        FormPaymentEntity formPayment = convert(formPaymentDTO, FormPaymentEntity.class);
        repository.save(formPayment);
    }

    public FormPaymentDTO findByIdFromPayment(Long id) {
        FormPaymentEntity formPaymentEntity = repository.findById(id).orElseThrow(() -> new RecordNotFoundException(new Object[]{"Form Payment de id " + id}));
        return convert(formPaymentEntity, FormPaymentDTO.class);
    }

    public void updateFormPayment(Long id, FormPaymentDTO formPaymentDTO) {
        FormPaymentEntity formPaymentEntity = repository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException(new Object[]{"Form Payment de id " + id}));
        convert(formPaymentDTO, formPaymentEntity);
        repository.save(formPaymentEntity);
    }

    public void deleteFormPayment(Long id) {
        FormPaymentEntity formPaymentEntity = repository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException(new Object[]{"Form Payment de id " + id}));
        try {
            repository.delete(formPaymentEntity);
        } catch (DataIntegrityViolationException dt) {
            throw new EntityInUseException();
        }
    }
}
