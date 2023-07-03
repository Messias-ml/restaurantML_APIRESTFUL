package com.messimari.restaurantml.api.controller;

import com.messimari.restaurantml.api.model.dto.state.StateDTO;
import com.messimari.restaurantml.domain.model.StateEntity;
import com.messimari.restaurantml.domain.service.RegistrationStateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/states")
public class StateController {

    @Autowired
    private RegistrationStateService service;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public StateEntity createState(@RequestBody @Valid StateEntity state){
        return service.createState(state);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<StateEntity> findListStates(){
        return service.findListStates();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public StateDTO findByIdState(@PathVariable("id") Long id){
        return service.findByIdState(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{id}")
    public StateEntity updateState(@PathVariable Long id,
                                       @RequestBody StateEntity updatedState){
        return service.updateState(id, updatedState);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteState(@PathVariable("id") Long id){
        service.deleteState(id);
    }
}
