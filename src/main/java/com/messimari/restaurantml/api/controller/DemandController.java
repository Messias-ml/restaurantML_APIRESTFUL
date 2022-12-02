package com.messimari.restaurantml.api.controller;

import com.messimari.restaurantml.api.model.dto.demand.DemandCompleteDTO;
import com.messimari.restaurantml.api.model.dto.demand.DemandDTO;
import com.messimari.restaurantml.domain.service.DemandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/demand")
public class DemandController {

    @Autowired
    private DemandService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createDemand(@RequestBody @Valid DemandCompleteDTO demand){
        service.createDemand(demand);
    }

    @GetMapping("/restaurant/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<DemandDTO> findDemandByIdRestaurant(@PathVariable Long id){
        return service.findDemandByIdRestaurant(id);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public DemandDTO findByIdDemand(@PathVariable Long id){
        return service.findByIdDemand(id);
    }
}
