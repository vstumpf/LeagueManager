package com.vstumpf.lolmanager.model;

import com.sun.istack.internal.Nullable;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.Transient;

import javax.persistence.*;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by Vincent on 7/23/2017.
 */

@Entity
@Table(name = "User")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="user_id")
    private Long id;

    @NotNull
    @Size(min = 4, max = 30)
    @Column(name = "username", length = 30, unique = true)
    private String username;

    @Column(name = "password", length = 60)
    @NotNull
    private String password;

    @Column(name = "first_name", length = 30)
    private String firstName;

    @Column(name = "last_name", length = 30)
    @NotNull
    private String lastName;

    @Column(name= "iso", length = 60)
    @Nullable
    private String iso;

    @Column(name = "enabled")
    @NotNull
    private boolean enabled;

    @Column(name = "last_password_reset_date")
    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    private Date lastPasswordResetDate;

    @ManyToMany(fetch  = FetchType.EAGER)
    @JoinTable(
            name = "User_Role",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles;
}
