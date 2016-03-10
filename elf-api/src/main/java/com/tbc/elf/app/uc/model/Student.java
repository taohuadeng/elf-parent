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

    @Column(name = "name1", length = 32)
    private String name1;

    @Column(name = "name2", length = 32)
    private String name2;

    @Column(name = "name3", length = 32)
    private String name3;


    @Column(name = "name4", length = 32)
    private String name4;
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

    public String getName1() {
        return name1;
    }

    public void setName1(String name1) {
        this.name1 = name1;
    }

    public String getName2() {
        return name2;
    }

    public void setName2(String name2) {
        this.name2 = name2;
    }

    public String getName3() {
        return name3;
    }

    public void setName3(String name3) {
        this.name3 = name3;
    }

    public String getName4() {
        return name4;
    }

    public void setName4(String name4) {
        this.name4 = name4;
    }
}
