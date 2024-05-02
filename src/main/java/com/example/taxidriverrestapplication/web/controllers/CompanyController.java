package com.example.taxidriverrestapplication.web.controllers;

import com.example.taxidriverrestapplication.entity.Company;
import com.example.taxidriverrestapplication.services.CompanyService;
import com.example.taxidriverrestapplication.web.dto.company.CompanyRequest;
import com.example.taxidriverrestapplication.web.dto.company.CompanyResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import java.util.List;


@Controller
@RequestMapping("/api/company")
public class CompanyController {
    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }


    @GetMapping()
    public ResponseEntity<List<CompanyResponse>> getAllCompanies() {
        return ResponseEntity.ok(companyService.getAllCompanies().stream().map(CompanyResponse::new).toList());
    }

    @PostMapping()
    public ResponseEntity<CompanyResponse> addCompany(@RequestBody @Valid CompanyRequest companyRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(new CompanyResponse(companyService.addCompany(companyRequest)));


    }

    @PutMapping("/{id}")
    public ResponseEntity<CompanyResponse> putCompany(@PathVariable Integer id,
                                                      @RequestBody @Valid CompanyRequest companyRequest) {
        try {
            return ResponseEntity.ok(new CompanyResponse(companyService.putCompany(id, companyRequest)));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCompany(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok().body(new CompanyResponse(companyService.deleteCompany(id)));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

}
