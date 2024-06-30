package com.example.taxidriverrestapplication.web.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Tag(name = "Health Check", description = "Health check endpoint to verify the service status")
public class HealthCheckController {

    @GetMapping("/health")
    @Operation(summary = "Health check", description = "Check the health status of the application")
    public ResponseEntity<String> healthCheck() {
        return ResponseEntity.ok("OK");
    }

    @GetMapping("/version")
    @Operation(summary = "Health check", description = "Check the health status of the application")
    public ResponseEntity<Integer> versionCheck() {
        return ResponseEntity.ok(1);
    }
}
