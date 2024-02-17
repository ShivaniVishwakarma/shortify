package com.testo.shortify.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "`USER`")
public class User extends AbstractEntity<Long>{

    private String username;
    private String email;
    private String password;
    private String roles;
}
