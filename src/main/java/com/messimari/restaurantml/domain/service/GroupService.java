package com.messimari.restaurantml.domain.service;

import com.messimari.restaurantml.api.model.dto.group.GroupCompleteDTO;
import com.messimari.restaurantml.api.model.dto.group.GroupNameDTO;
import com.messimari.restaurantml.api.model.dto.group.GroupRegisterDTO;
import com.messimari.restaurantml.api.model.dto.permition.PermitionDTO;
import com.messimari.restaurantml.domain.exception.EntityInUseException;
import com.messimari.restaurantml.domain.exception.RecordNotFoundException;
import com.messimari.restaurantml.domain.model.GroupEntity;
import com.messimari.restaurantml.domain.repository.GroupRepository;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.messimari.restaurantml.core.ModelMapperConvert.*;

@Service
@AllArgsConstructor
public class GroupService {

    private GroupRepository repository;

    public void createGroup(GroupRegisterDTO groupToRegister) {
        GroupEntity groupEntity = convert(groupToRegister, GroupEntity.class);
        repository.save(groupEntity);
    }

    public List<GroupNameDTO> findListGroups() {
        List<GroupEntity> allGroup = repository.findAll();
        return convertList(allGroup, GroupNameDTO.class);
    }

    public GroupCompleteDTO findByIdGroup(Long id) {
        GroupEntity group = repository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException(new Object[]{id}));
        return convert(group, GroupCompleteDTO.class);
    }

    public Set<PermitionDTO> findByIdPermitionOfGroup(Long id) {
        GroupEntity group = repository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException(new Object[]{id}));
        return convertSet(group.getPermissions(), PermitionDTO.class);
    }

    public void updateGroup(Long id, GroupRegisterDTO groupToUpdate) {
        GroupEntity groupEntity = repository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException(new Object[]{id}));
        groupEntity.setPermissions(new HashSet<>());
        convert(groupToUpdate, groupEntity);
        groupEntity.setId(id);
        repository.save(groupEntity);
    }

    public void deleteGroup(Long id) {
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException dt) {
            throw new EntityInUseException();
        }catch (EmptyResultDataAccessException empty){
            throw new RecordNotFoundException(new Object[]{id});
        }
    }
}
