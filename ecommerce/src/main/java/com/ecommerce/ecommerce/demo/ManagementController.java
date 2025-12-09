package com.ecommerce.ecommerce.demo;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/management")
@Tag(name = "Management")
public class ManagementController {

    @Operation(
            summary = "Get endpoint for management",
            description = "Accessible by ADMIN_READ or MANAGER_READ",
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200"),
                    @ApiResponse(description = "Unauthorized / Invalid Token", responseCode = "403")
            }
    )
    @GetMapping
    @PreAuthorize("hasAnyAuthority('ADMIN_READ','MANAGER_READ')")
    public String get() {
        return "GET:: management controller";
    }

    @Operation(
            summary = "Post endpoint for management",
            description = "Accessible by ADMIN_CREATE or MANAGER_CREATE",
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200"),
                    @ApiResponse(description = "Unauthorized / Invalid Token", responseCode = "403")
            }
    )
    @PostMapping
    @PreAuthorize("hasAnyAuthority('ADMIN_CREATE','MANAGER_CREATE')")
    public String post() {
        return "POST:: management controller";
    }

    @Operation(
            summary = "Put endpoint for management",
            description = "Accessible by ADMIN_UPDATE or MANAGER_UPDATE",
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200"),
                    @ApiResponse(description = "Unauthorized / Invalid Token", responseCode = "403")
            }
    )
    @PutMapping
    @PreAuthorize("hasAnyAuthority('ADMIN_UPDATE','MANAGER_UPDATE')")
    public String put() {
        return "PUT:: management controller";
    }

    @Operation(
            summary = "Delete endpoint for management",
            description = "Accessible by ADMIN_DELETE or MANAGER_DELETE",
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200"),
                    @ApiResponse(description = "Unauthorized / Invalid Token", responseCode = "403")
            }
    )
    @DeleteMapping
    @PreAuthorize("hasAnyAuthority('ADMIN_DELETE','MANAGER_DELETE')")
    public String delete() {
        return "DELETE:: management controller";
    }
}
