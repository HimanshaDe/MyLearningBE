package com.example.test_project.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@ToString
@Table(name = "assignment")
public class Assignment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name="course_id", nullable = false)
    private Course course;

    @Column(nullable = false)
    private String description;

    @Column
    private Double grade;

    @Column(name = "submission_date", nullable = false)
    private LocalDateTime submissionDate;

}
