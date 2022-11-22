package com.messimari.restaurantml.domain.model;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "Group_Collection")
public class GroupEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_GROUP", nullable = false)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToMany
    @JoinTable(name = "Permition_Group",
            joinColumns = @JoinColumn(name = "ID_GROUP", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "ID_PERMITION", nullable = false))
    private Set<PermitionEntity> permissions = new HashSet<>();
}
