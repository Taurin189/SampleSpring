package com.spring.sample.entity;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "todo")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
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
