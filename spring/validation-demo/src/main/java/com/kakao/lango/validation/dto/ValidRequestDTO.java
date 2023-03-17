package com.kakao.lango.validation.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class ValidRequestDTO {
    @NotBlank
    private String name;
    @Email
    private String email;
    @Pattern(regexp = "01(?:0|1|[6-9])[.-]?(\\d{3}|\\d{4})[.-]?(\\d{4})$")
    private String phoneNumber;
    @Min(value = 20)
    @Max(value = 40)
    private int age;
    @Size(min = 0, max = 40)
    private String description;
    @Positive
    private int count;
    @AssertTrue
    private boolean booleanCheck;
}
