package com.tbc.elf.app.uc.model;

import org.hibernate.annotations.*;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

/**
 * @author YangLiBo@HF
 * @since 2016年03月07日16:09:37
 */
@Entity
@Table(name = "t_uc_teacher")
public class Teacher {
    @Id
    @Column(name = "teacher_id", length = 32)
    private String teacherId;

    @Column(name = "name", length = 32)
    private String name;

    @OneToMany()
    @JoinColumn(name = "teacher_id")
   // @Cascade(CascadeType.SAVE_UPDATE)
    private Set<Student> students;

    public Teacher() {
        students = new HashSet<Student>(0);
    }

    public String getTeacherId() {
        return teacherId;
    }


    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }
}
