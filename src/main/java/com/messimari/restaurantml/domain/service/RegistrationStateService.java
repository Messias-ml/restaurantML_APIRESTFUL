package com.messimari.restaurantml.domain.service;

import com.messimari.restaurantml.domain.exception.EntityInUseException;
import com.messimari.restaurantml.domain.exception.RecordNotFoundException;
import com.messimari.restaurantml.domain.model.StateEntity;
import com.messimari.restaurantml.domain.repository.StateRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class RegistrationStateService {

    private StateRepository repository;

    public StateEntity registerState(StateEntity state) {
        return repository.save(state);
    }

    public List<StateEntity> listStates() {
        return repository.findAll();
    }

    public StateEntity listStateById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException(new Object[]{id}));
    }

    public StateEntity updateState(Long id, StateEntity updatedState) {
        StateEntity stateEntity = listStateById(id);
        BeanUtils.copyProperties(updatedState, stateEntity, "id");
        return repository.save(stateEntity);
    }

    public void deleteState(Long id) {
        try {
            repository.delete(listStateById(id));
        }catch (DataIntegrityViolationException dt){
            throw new EntityInUseException();
        }
    }
}
