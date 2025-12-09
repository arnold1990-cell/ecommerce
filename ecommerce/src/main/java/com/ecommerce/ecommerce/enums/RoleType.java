package com.ecommerce.ecommerce.enums;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.ecommerce.ecommerce.enums.Permission.*;

@RequiredArgsConstructor
public enum RoleType {

    CUSTOMER(Collections.emptySet()), // Regular users

    MANAGER(
            Set.of(
                    PRODUCT_READ,
                    PRODUCT_CREATE,
                    PRODUCT_UPDATE,
                    PRODUCT_DELETE,
                    ORDER_READ,
                    ORDER_UPDATE
            )
    ),

    ADMIN(
            Set.of(
                    PRODUCT_READ,
                    PRODUCT_CREATE,
                    PRODUCT_UPDATE,
                    PRODUCT_DELETE,
                    ORDER_READ,
                    ORDER_CREATE,
                    ORDER_UPDATE,
                    ORDER_DELETE,
                    USER_READ,
                    USER_CREATE,
                    USER_UPDATE,
                    USER_DELETE
            )
    );

    @Getter
    private final Set<Permission> permissions;

    /**
     * Converts permissions + role into Spring Security authorities.
     */
    public List<SimpleGrantedAuthority> getAuthorities() {
        var authorities = getPermissions()
                .stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toList());

        // Add role authority for hasRole() checks
        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return authorities;
    }
}
