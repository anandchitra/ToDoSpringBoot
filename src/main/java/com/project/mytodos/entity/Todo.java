package com.project.mytodos.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;


import java.util.Date;
@Getter
@Setter
@AllArgsConstructor
@Entity
@NoArgsConstructor
@Table(name = "Todo")
@ToString
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private int id;
    @Column(name="user_name")
    private String userName;
    @Column(name="description")
    private String description;
    @Column(name="target_date")
    private Date targetDate;
    @JsonProperty("done")
    @Column(name="is_Done")
    private boolean done;


}
