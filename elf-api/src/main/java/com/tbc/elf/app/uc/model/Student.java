package com.tbc.elf.app.uc.model;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;


/**
 * @author YangLiBo@HF
 * @since 2016年03月07日15:57:00
 */
@Entity
@DynamicUpdate(true)
@Table(name = "t_uc_student")
public class Student {

    @Id
    @Column(name = "id", length = 32)
    private String id;

    @Column(name = "name", length = 32)
    private String name;

    /*@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "teacher_id")*/
    @Transient
    private Teacher teacher;

    public String getId() {
        return id;
    }


    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }
}
