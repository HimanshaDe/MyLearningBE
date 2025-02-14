package com.example.test_project.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@Table(name = "quiz")
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    @JsonBackReference
    private Course course;

    @Column(nullable = false)
    private String question;
    @Column(nullable = false)
    private String answer;
    @Column(name = "options", columnDefinition = "TEXT[]", nullable = false)
    private List<String> options = new ArrayList<>();
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

}
