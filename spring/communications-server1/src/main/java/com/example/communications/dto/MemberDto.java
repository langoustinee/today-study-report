package com.example.communications.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class MemberDto {
    private String email;
    private String name;
    private String organization;
}
