package com.messimari.restaurantml.api.controller;

import com.messimari.restaurantml.api.controller.openAPI.DemandControllerOpenAPI;
import com.messimari.restaurantml.api.model.dto.demand.DemandCompleteDTO;
import com.messimari.restaurantml.api.model.dto.demand.DemandDTO;
import com.messimari.restaurantml.api.model.dto.demand.DemandToRestaurantDTO;
import com.messimari.restaurantml.domain.model.StatusDemand;
import com.messimari.restaurantml.domain.service.DemandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/demand")
public class DemandController implements DemandControllerOpenAPI {

    @Autowired
    private DemandService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createDemand(@RequestBody @Valid DemandCompleteDTO demand){
        service.createDemand(demand);
    }

    @GetMapping("/restaurant/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<DemandToRestaurantDTO> findAllDemandByIdRestaurant(@PathVariable Long id){
        return service.findAllDemandByIdRestaurant(id);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public DemandDTO findByIdDemand(@PathVariable Long id){
        return service.findByIdDemand(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateDemand(@PathVariable Long id, @RequestBody @Valid DemandCompleteDTO demand){
        service.updateDemand(id, demand);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateStatusDemand(@PathVariable Long id, @RequestBody StatusDemand statusDemand){
        service.updateStatusDemand(id, statusDemand);
    }
}
