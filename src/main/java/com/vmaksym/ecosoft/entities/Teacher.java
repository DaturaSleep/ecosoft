package com.vmaksym.ecosoft.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Teacher extends ApplicationUser {

    @OneToMany
    @JsonIgnore
    private Set<Pupil> setOfPupils;

    public Teacher(Integer id, String userName, String password) {
        super(id, userName, password, new HashSet<>(Arrays.asList(UserRole.TEACHER)));
        this.setOfPupils = new HashSet<>(2);
    }

    public void addPupil(Pupil pupil) {
        if(!this.setOfPupils.contains(pupil)) {
            this.setOfPupils.add(pupil);
            pupil.setTeacher(this);
        }
    }

}
