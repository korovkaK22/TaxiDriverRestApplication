package com.example.taxidriverrestapplication.entity;

import jakarta.persistence.*;
import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;


@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Table(name = "companies")
@Entity
@NoArgsConstructor
public class Company {

 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Integer id;

 @NotBlank(message = "Name cannot be null or empty.")
 @Column(name = "name")
 String name;

 @NotBlank(message = "Country cannot be null or empty.")
 @Column(name = "country")
 String country;

 @Min(value = 0, message = "Car amount can't be less than zero")
 @Column(name = "working_cars_amount")
 int workingCarsAmount;

}
