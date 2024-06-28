package com.example.taxidriverrestapplication.web.controllers;

import com.example.taxidriverrestapplication.entity.TaxiDriver;
import com.example.taxidriverrestapplication.services.TaxiDriverService;
import com.example.taxidriverrestapplication.web.dto.UploadJsonEntitiesResponse;
import com.example.taxidriverrestapplication.web.dto.taxidriver.request.filters.TaxiDriverFilterRequest;
import com.example.taxidriverrestapplication.web.dto.taxidriver.request.filters.TaxiDriverPaginationFilterRequest;
import com.example.taxidriverrestapplication.web.dto.taxidriver.request.TaxiDriverRequest;
import com.example.taxidriverrestapplication.web.dto.taxidriver.response.TaxiDriverFullResponse;
import com.example.taxidriverrestapplication.web.dto.taxidriver.response.TaxiDriverResponse;
import com.example.taxidriverrestapplication.web.dto.taxidriver.response.TaxiDriverShortResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


@Slf4j
@Controller
@RequestMapping("/api/taxi-driver")
@Tag(name = "Taxi Driver Management", description = "Operations related to managing taxi drivers")
public class TaxiDriverController {

    private final TaxiDriverService taxiDriverService;

    @Autowired
    public TaxiDriverController(TaxiDriverService taxiDriverService) {
        this.taxiDriverService = taxiDriverService;
    }

    @GetMapping()
    @Operation(summary = "Get all taxi drivers", description = "Retrieve a list of all taxi drivers")
    public ResponseEntity<List<TaxiDriverFullResponse>> getAllTaxiDrivers() {
        return ResponseEntity.ok(taxiDriverService.getAllTaxiDrivers());
    }

    @PostMapping()
    @Operation(summary = "Add a new taxi driver", description = "Create a new taxi driver with the provided details")
    public ResponseEntity<TaxiDriverFullResponse> saveTaxiDriver(@Valid @RequestBody TaxiDriverRequest taxiDriverRequest) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new TaxiDriverFullResponse(taxiDriverService.saveTaxiDriver(taxiDriverRequest)));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a taxi driver by ID", description = "Retrieve a specific taxi driver by ID")
    public ResponseEntity<TaxiDriverFullResponse> getTaxiDriver(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(new TaxiDriverFullResponse(taxiDriverService.getTaxiDriver(id)));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a taxi driver", description = "Update the details of an existing taxi driver by ID")
    public ResponseEntity<TaxiDriverResponse> updateTaxiDriver(@PathVariable Integer id,
                                                               @Valid @RequestBody TaxiDriverRequest taxiDriverRequest) {
       try {
           return ResponseEntity.ok(new TaxiDriverResponse(taxiDriverService.updateTaxiDriver(id, taxiDriverRequest)));
       } catch (IllegalArgumentException e) {
           return ResponseEntity.notFound().build();
       }
    }


    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a taxi driver", description = "Delete an existing taxi driver by ID")
    public ResponseEntity<?> deleteTaxiDriver(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok().body(new TaxiDriverResponse(taxiDriverService.deleteTaxiDriver(id)));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }



    @PostMapping("/_list")
    @Operation(summary = "Get list of taxi drivers with pagination", description = "Retrieve a paginated list of taxi drivers")
    public ResponseEntity<?> getListOfTaxiDrivers(@Valid @RequestBody TaxiDriverPaginationFilterRequest request) {
        try {
            return ResponseEntity.ok(taxiDriverService.getListOfTaxiDrivers(request));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/_report")
    @Operation(summary = "Download taxi driver report", description = "Generate and download a report of taxi drivers")
    public ResponseEntity<Void> downloadTaxiDriverReport(TaxiDriverFilterRequest request, HttpServletResponse response) throws IOException {
        List<TaxiDriverShortResponse> drivers = taxiDriverService.getReportOfTaxiDrivers(request);
        response.setContentType("text/csv");
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"drivers.csv\"");

        try (CSVPrinter csvPrinter = new CSVPrinter(response.getWriter(),
                CSVFormat.DEFAULT.withHeader("ID", "Name", "Surname", "CompanyId", "Age"))) {
            taxiDriverService.writeDriversIntoSCVFile(csvPrinter, drivers);
            csvPrinter.flush();
        }
        return ResponseEntity.ok().build();
    }

    @PostMapping("/upload")
    @Operation(summary = "Upload taxi drivers from a file", description = "Upload taxi drivers from a JSON file")
    public ResponseEntity<?> uploadTaxiDrivers(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            log.warn("File with drivers is empty");
            return ResponseEntity.badRequest().body("File with drivers is empty");
        }
        UploadJsonEntitiesResponse stats;
        try {
            stats = taxiDriverService.uploadTaxiDriversFromFile(file);
            log.info("File was processed, uploaded drivers: %d, invalid drivers: %d".formatted(stats.getSuccessful(), stats.getFailed()));
        } catch (Exception e) {
            log.warn("Error while uploading drivers from file");
            return ResponseEntity.badRequest().body("Error while uploading drivers from file: " + e.getMessage());
        }

        return ResponseEntity.ok(stats);

    }

}
