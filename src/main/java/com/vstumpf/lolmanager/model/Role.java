package com.vstumpf.lolmanager.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by Vincent on 7/24/2017.
 */

@Entity
@Table(name = "Role")
public class Role {

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="role_id")
    private int id;

    @Getter
    @Setter
    @Column(name="role", length = 60)
    @NotNull
    @Enumerated(EnumType.STRING)
    private RoleName role;

    @Getter
    @Setter
    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    private List<User> users;


}
