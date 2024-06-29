package com.example.taxidriverrestapplication.web.dto.taxidriver.request;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaxiDriverRequest {

    @NotBlank(message = "Name cannot be null or empty.")
    @Size(min = 3, max = 255, message = "The length of name must be between 3 and 255 characters.")
    private String name;

    @NotBlank(message = "Surname cannot be null or empty.")
    @Size(min = 3, max = 255, message = "The length of surname must be between 3 and 255 characters.")
    private String surname;

    @Nullable
    private Integer companyId;

    @NotNull(message = "Driving experience cannot be null")
    private Integer age;

    @NotNull(message = "Driving experience cannot be null")
    private Integer drivingExperience;

    @NotNull(message = "Salary cannot be null")
    private Long salary;

    @Nullable
    private String cars;

}
