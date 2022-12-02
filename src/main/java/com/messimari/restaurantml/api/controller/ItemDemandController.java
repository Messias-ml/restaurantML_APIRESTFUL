package com.messimari.restaurantml.api.controller;

import com.messimari.restaurantml.api.model.dto.itemDemand.ItemDemandRequestDTO;
import com.messimari.restaurantml.domain.service.ItemDemandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("item/demand")
public class ItemDemandController {

    @Autowired
    private ItemDemandService service;

    @PostMapping
    public void registerItemDemand(@RequestBody ItemDemandRequestDTO item){
        service.registerItemDemand(item);
    }
}
