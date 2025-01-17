package com.example.test_project.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "employee_id",nullable = false, unique = true)
    private String employeeId;

    @Column(name = "employee_name",nullable = false)
    private String employeeName;
}
