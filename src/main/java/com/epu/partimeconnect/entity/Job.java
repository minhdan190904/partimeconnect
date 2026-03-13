package com.epu.partimeconnect.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "jobs")
public class Job extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employer_id", nullable = false)
    private Employer employer;

    @Column(nullable = false, length = 150)
    private String title;

    @Column(length = 180, unique = true)
    private String slug;

    @Column(length = 255)
    private String shortDescription;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(columnDefinition = "TEXT")
    private String requirements;

    @Column(columnDefinition = "TEXT")
    private String benefits;

    @Column(nullable = false, length = 30)
    private String jobGroup;

    @Column(precision = 12, scale = 2)
    private BigDecimal salaryAmount;

    @Column(nullable = false, length = 20)
    private String salaryUnit;

    @Column(length = 20)
    private String workingType;

    @Column(length = 100)
    private String shiftLabel;

    @Column(length = 100)
    private String workingTimeText;

    private Integer quantity;

    @Column(length = 100)
    private String province;

    @Column(length = 100)
    private String district;

    @Column(length = 255)
    private String workAddress;

    @Column(precision = 10, scale = 7)
    private BigDecimal latitude;

    @Column(precision = 10, scale = 7)
    private BigDecimal longitude;

    @Column(length = 255)
    private String imageUrl;

    @Column(nullable = false, length = 20)
    private String status = "OPEN";

    @Column(nullable = false)
    private Boolean published = true;
}
