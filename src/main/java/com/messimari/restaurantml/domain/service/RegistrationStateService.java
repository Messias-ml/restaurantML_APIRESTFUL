package com.messimari.restaurantml.domain.service;

import com.messimari.restaurantml.api.controller.CityController;
import com.messimari.restaurantml.api.controller.StateController;
import com.messimari.restaurantml.api.model.dto.city.CityResponseDTO;
import com.messimari.restaurantml.api.model.dto.state.StateDTO;
import com.messimari.restaurantml.domain.exception.EntityInUseException;
import com.messimari.restaurantml.domain.exception.RecordNotFoundException;
import com.messimari.restaurantml.domain.model.StateEntity;
import com.messimari.restaurantml.domain.repository.StateRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.messimari.restaurantml.core.modelMapper.ModelMapperConvert.convert;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@AllArgsConstructor
@Service
public class RegistrationStateService {

    private StateRepository repository;

    public StateEntity createState(StateEntity state) {
        return repository.save(state);
    }

    public List<StateEntity> findListStates() {
        return repository.findAll();
    }

    public StateDTO findByIdState(Long id) {
        StateEntity stateEntity = repository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException(new Object[]{id}));
        StateDTO state = convert(stateEntity, StateDTO.class);
        state.add(linkTo(methodOn(StateController.class).findByIdState(state.getId())).withSelfRel());
        state.add(linkTo(methodOn(StateController.class).findListStates()).withRel("States"));
        return state;
    }

    public StateEntity updateState(Long id, StateEntity updatedState) {
        StateEntity stateEntity = repository.findById(id).orElseThrow();
        BeanUtils.copyProperties(updatedState, stateEntity, "id");
        return repository.save(stateEntity);
    }

    public void deleteState(Long id) {
        try {
            StateEntity stateEntity = repository.findById(id).orElseThrow();
            repository.delete(stateEntity);
        }catch (DataIntegrityViolationException dt){
            throw new EntityInUseException();
        }
    }
}
