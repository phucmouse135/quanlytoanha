package com.javaweb.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "role")
public class RoleEntity extends BaseEntity {

    private static final long serialVersionUID = -6525302831793188081L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name")
    private String name;

    @Column(name="code")
    private String code;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @ManyToMany
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "role_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "user_id", nullable = false))
    private List<UserEntity> userEntities = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<UserEntity> getUsers() {
        return userEntities;
    }

    public void setUsers(List<UserEntity> users) {
        this.userEntities = users;
    }

}
