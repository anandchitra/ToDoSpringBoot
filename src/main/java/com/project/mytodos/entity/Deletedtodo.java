package com.project.mytodos.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@Entity
@NoArgsConstructor
@Table(name = "Deletedtodo")

public class Deletedtodo {
    @Id
    @Column(name="id")
    private int id;
    @Column(name="user_name")
    private String userName;
    @Column(name="description")
    private String description;
    @Column(name="target_date")
    private Date targetDate;

    @Column(name="is_Done")
    private boolean done;
}
