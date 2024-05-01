package com.example.taxidriverrestapplication.web.dto.taxidriver.response;

import com.example.taxidriverrestapplication.entity.TaxiDriver;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class TaxiDriverShortResponse {

    private Integer id;
    private String name;
    private String surname;
    private Integer companyId;
    private int age;


    public TaxiDriverShortResponse(TaxiDriver taxiDriver) {
        this.id = taxiDriver.getId();
        this.name = taxiDriver.getName();
        this.age = taxiDriver.getAge();
        this.surname = taxiDriver.getSurname();
        this.companyId = taxiDriver.getCompany().getId();

    }


}
