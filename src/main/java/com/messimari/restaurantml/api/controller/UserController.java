package com.messimari.restaurantml.api.controller;

import com.messimari.restaurantml.api.model.dto.group.GroupIdDTO;
import com.messimari.restaurantml.api.model.dto.group.GroupNameDTO;
import com.messimari.restaurantml.api.model.dto.group.ListGroupIdDTO;
import com.messimari.restaurantml.api.model.dto.user.UserCompleteDTO;
import com.messimari.restaurantml.api.model.dto.user.UserBasicDTO;
import com.messimari.restaurantml.api.model.dto.user.UserRegisterDTO;
import com.messimari.restaurantml.domain.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService service;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void createUser(@RequestBody @Valid UserRegisterDTO userToRegister){
        service.createUser(userToRegister);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public CollectionModel<UserBasicDTO> findListUsers(){
        return service.findListUsers();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public UserCompleteDTO findByIdUser(@PathVariable Long id){
        return service.findByIdUser(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}/groups")
    public List<GroupNameDTO> findByIdGroupOfUser(@PathVariable Long id){
        return service.findByIdGroupOfUser(id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public void updateUser(@PathVariable Long id, @RequestBody @Valid UserBasicDTO userToUpdate){
        service.updateUser(id, userToUpdate);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/{id}/groups")
    public void updateGroupOfUser(@PathVariable Long id, @RequestBody ListGroupIdDTO groups){
        service.updateGroupOfUser(id, groups);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id){
        service.deleteUser(id);
    }
}
