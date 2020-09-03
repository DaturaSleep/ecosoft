package com.vmaksym.ecosoft.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.util.Arrays;
import java.util.HashSet;

@Entity
@Data
@NoArgsConstructor
public class Pupil extends ApplicationUser {

    @ManyToOne
    private Teacher teacher;

    public Pupil(Integer id, String userName, String password) {
        super(id, userName, password, new HashSet<>(Arrays.asList(UserRole.PUPIL)));
    }

    public void setTeacher(Teacher teacher) {
        if(this.teacher == null){
            this.teacher = teacher;
            teacher.addPupil(this);
        }


    }

}
