package com.messimari.restaurantml.api.controller;

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
    public StateEntity registerState(@RequestBody @Valid StateEntity state){
        return service.registerState(state);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<StateEntity> listStates(){
        return service.listStates();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public StateEntity listStateById(@PathVariable("id") Long id){
        return service.listStateById(id);
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
