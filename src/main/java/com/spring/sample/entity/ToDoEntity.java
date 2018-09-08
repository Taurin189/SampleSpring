package com.spring.sample.entity;

import javax.persistence.*;

import lombok.Builder;
import lombok.Data;

@Entity
@Table(name = "todo")
@Builder
@Data
public class ToDoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;
    String todo;
    @Column(name = "person_in_charge")
    String personInCharge;
    @Column(name = "is_finished")
    boolean isFinished;
}
