package com.example.taxidriverrestapplication.web.controllers;

import com.example.taxidriverrestapplication.services.CompanyService;
import com.example.taxidriverrestapplication.web.dto.company.CompanyRequest;
import com.example.taxidriverrestapplication.web.dto.company.CompanyResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/company")
@Tag(name = "Company Management", description = "Operations related to managing companies")
public class CompanyController {
    private final CompanyService companyService;

    @GetMapping()
    @Operation(summary = "Get all companies", description = "Retrieve a list of all companies")
    public ResponseEntity<List<CompanyResponse>> getAllCompanies() {
        return ResponseEntity.ok(companyService.getAllCompanies().stream().map(CompanyResponse::new).toList());
    }

    @PostMapping()
    @Operation(summary = "Add a new company", description = "Create a new company with the provided details")
    public ResponseEntity<CompanyResponse> addCompany(@RequestBody @Valid CompanyRequest companyRequest) {
        CompanyResponse savedCompany = new CompanyResponse(companyService.addCompany(companyRequest));
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCompany);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a company", description = "Update the details of an existing company by ID")
    public ResponseEntity<CompanyResponse> putCompany(@PathVariable Integer id,
                                                      @RequestBody @Valid CompanyRequest companyRequest) {
        try {
            if (companyService.getCompany(id).isPresent()){
                CompanyResponse updatedCompany = new CompanyResponse(companyService.putCompany(id, companyRequest));
                return ResponseEntity.ok(updatedCompany);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a company", description = "Delete an existing company by ID")
    public ResponseEntity<?> deleteCompany(@PathVariable Integer id) {
        try {
            CompanyResponse deletedCompany = new CompanyResponse(companyService.deleteCompany(id));
            return ResponseEntity.ok().body(deletedCompany);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
