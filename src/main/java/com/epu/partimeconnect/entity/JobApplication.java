package com.epu.partimeconnect.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "job_applications")
public class JobApplication extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "job_id", nullable = false)
    private Job job;

    @Column(nullable = false, length = 120)
    private String fullName;

    @Column(nullable = false, length = 20)
    private String phone;

    @Column(length = 100)
    private String email;

    private Integer birthYear;

    @Column(length = 10)
    private String gender;

    @Column(length = 150)
    private String schoolName;

    @Column(length = 100)
    private String major;

    @Column(columnDefinition = "TEXT")
    private String experienceText;

    @Column(length = 150)
    private String availableTime;

    @Column(columnDefinition = "TEXT")
    private String note;

    @Column(length = 255)
    private String cvFileUrl;

    @Column(nullable = false, length = 30)
    private String status = "NEW";
}
