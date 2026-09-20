package com.example.rest_api.adapter.out.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Table(name = "opportunity")
@Entity(name = "opportunity")
@Setter
@Getter
public class Opportunity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotEmpty
    @Column(name="nameCompany", nullable = false, length = 80)
    private String nameCompany;

    @Column(nullable = false, length = 200)
    private String description;

    @Column(name = "valueCompany", precision = 10, scale = 2)
    private BigDecimal valueCompany;
}
