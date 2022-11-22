package com.messimari.restaurantml.api.controller;

import com.messimari.restaurantml.api.model.dto.permition.PermitionDTO;
import com.messimari.restaurantml.domain.service.PermitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/permitions")
public class PermitionController {

    @Autowired
    private PermitionService service;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void createPermition(@RequestBody @Valid PermitionDTO permition){
        service.createPermition(permition);
    }

    @GetMapping
    public List<PermitionDTO> findAllPermitions(){
        return service.findAllPermitions();
    }
}
