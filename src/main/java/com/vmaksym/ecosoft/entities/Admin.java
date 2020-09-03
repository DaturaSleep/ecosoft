package com.vmaksym.ecosoft.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.util.Arrays;
import java.util.HashSet;

@Entity
@Data
@NoArgsConstructor
public class Admin extends ApplicationUser {
    public Admin(Integer id, String userName, String password) {
        super(id, userName, password, new HashSet<>(Arrays.asList(UserRole.ADMIN)));
    }
}
