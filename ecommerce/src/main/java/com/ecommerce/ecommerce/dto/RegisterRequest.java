package com.ecommerce.ecommerce.dto;

import com.ecommerce.ecommerce.enums.RoleType;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {
    private String fullName;
    private String email;
    private String password;
    private String phone;
    private RoleType role; // ✅ must be RoleType
}

