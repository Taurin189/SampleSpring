package app.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "todo")
public class ToDoEntity {
    @Id
    Integer id;
    String todo;
    @Column(name = "person_in_charge")
    String personInCharge;
    @Column(name = "is_finished")
    boolean isFinished;
}
