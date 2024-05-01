package com.example.taxidriverrestapplication.web.dto.taxidriver.response;

import com.example.taxidriverrestapplication.entity.TaxiDriver;
import com.example.taxidriverrestapplication.web.dto.company.CompanyResponse;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class TaxiDriverFullResponse {
    private Integer id;
    private String name;
    private String surname;
    private CompanyResponse company;
    private int age;
    private Integer drivingExperience;
    private Long salary;
    private String cars;


    public TaxiDriverFullResponse(TaxiDriver taxiDriver) {
        this.id = taxiDriver.getId();
        this.age = taxiDriver.getAge();
        this.name = taxiDriver.getName();
        this.surname = taxiDriver.getSurname();
        this.company = new CompanyResponse(taxiDriver.getCompany());
        this.drivingExperience = taxiDriver.getDrivingExperience();
        this.salary = taxiDriver.getSalary();
        this.cars = taxiDriver.getCars();
    }
}
