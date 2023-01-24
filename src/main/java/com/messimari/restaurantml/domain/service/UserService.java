package com.messimari.restaurantml.domain.service;

import com.messimari.restaurantml.api.model.dto.group.GroupNameDTO;
import com.messimari.restaurantml.api.model.dto.group.ListGroupIdDTO;
import com.messimari.restaurantml.api.model.dto.user.UserCompleteDTO;
import com.messimari.restaurantml.api.model.dto.user.UserRegisterDTO;
import com.messimari.restaurantml.api.model.dto.user.UserBasicDTO;
import com.messimari.restaurantml.domain.exception.EntityInUseException;
import com.messimari.restaurantml.domain.exception.RecordNotExistsException;
import com.messimari.restaurantml.domain.exception.RecordNotFoundException;
import com.messimari.restaurantml.domain.model.GroupEntity;
import com.messimari.restaurantml.domain.model.UserEntity;
import com.messimari.restaurantml.domain.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

import static com.messimari.restaurantml.core.modelMapper.ModelMapperConvert.convert;
import static com.messimari.restaurantml.core.modelMapper.ModelMapperConvert.convertList;

@Service
@AllArgsConstructor
public class UserService {

    private UserRepository repository;

    public void createUser(UserRegisterDTO userToRegister) {
        UserEntity userEntity = convert(userToRegister, UserEntity.class);
        repository.save(userEntity);
    }

    public List<UserBasicDTO> findListUsers() {
        List<UserEntity> allUser = repository.findAll();
        return convertList(allUser, UserBasicDTO.class);
    }

    public UserCompleteDTO findByIdUser(Long id) {
        UserEntity user = repository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException(new Object[]{id}));
        return convert(user, UserCompleteDTO.class);
    }

    public void updateUser(Long id, UserBasicDTO userToUpdate) {
        UserEntity userEntity = repository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException(new Object[]{id}));
        convert(userToUpdate, userEntity);
        userEntity.setId(id);
        repository.save(userEntity);
    }

    public void deleteUser(Long id) {
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException dt) {
            throw new EntityInUseException();
        } catch (EmptyResultDataAccessException empty) {
            throw new RecordNotFoundException(new Object[]{id});
        }
    }

    public List<GroupNameDTO> findByIdGroupOfUser(Long id) {
        List<GroupEntity> groupsEntity = repository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException(new Object[]{id})).getGroups();
        if (CollectionUtils.isEmpty(groupsEntity)) {
            throw new RecordNotExistsException(new Object[]{"Groups do user"});
        } else {
            return convertList(groupsEntity, GroupNameDTO.class);
        }
    }

    public void updateGroupOfUser(Long id, ListGroupIdDTO groups) {
        UserEntity userEntity = repository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException(new Object[]{id}));
        userEntity.setGroups(new ArrayList<>());
        repository.save(convert(groups, userEntity));
    }
}
