package com.dec.taskmanagementsystem.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonValue;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The Task class contains task details.
 * @author Hemant
 * @see <a href="https://docs.oracle.com/en/java/javase/17/docs/api/" target="_blank">Java Docs</a>
 */

@Entity
@Table(name = "tasks")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    @NotEmpty(message = "Invalid: task cannot be empty")
    private String task;
    private Boolean completed;
    @ManyToOne
    @JsonIgnore
    private User user;
}
