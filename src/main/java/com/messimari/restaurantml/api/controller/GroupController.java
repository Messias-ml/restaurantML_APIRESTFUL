package com.messimari.restaurantml.api.controller;

import com.messimari.restaurantml.api.model.dto.group.GroupCompleteDTO;
import com.messimari.restaurantml.api.model.dto.group.GroupNameDTO;
import com.messimari.restaurantml.api.model.dto.group.GroupRegisterDTO;
import com.messimari.restaurantml.api.model.dto.permition.PermitionDTO;
import com.messimari.restaurantml.domain.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/groups")
public class GroupController {

    @Autowired
    private GroupService service;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<GroupNameDTO> findListGroups(){
        return service.findListGroups();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public GroupCompleteDTO findByIdGroup(@PathVariable Long id){
        return service.findByIdGroup(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}/permitions")
    public Set<PermitionDTO> findByIdPermitionOfGroup(@PathVariable Long id){
        return service.findByIdPermitionOfGroup(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void createGroup(@RequestBody GroupRegisterDTO groupToRegister){
        service.createGroup(groupToRegister);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public void updateGroup(@PathVariable Long id, @RequestBody GroupRegisterDTO groupToUpdate){
        service.updateGroup(id, groupToUpdate);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteGroup(@PathVariable Long id){
        service.deleteGroup(id);
    }
}
