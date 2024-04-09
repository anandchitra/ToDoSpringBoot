package com.project.mytodos.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="EmployeeTask")
public class EmployeeTask {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private int id;
    @Column(name="first_name", length =50)
    private String firstName;
    @Column(name="description" ,length =255)
    private String description;
    @Column(name="target_date" , columnDefinition ="DATE")
    private Date targetDate;
    @Column(name="is_done" , columnDefinition = "TINYINT default 1")
    private boolean done;

    public EmployeeTask(String firstName, String description, Date targetDate, boolean done) {
        this.firstName = firstName;
        this.description = description;
        this.targetDate = targetDate;
        this.done = done;
    }
}
