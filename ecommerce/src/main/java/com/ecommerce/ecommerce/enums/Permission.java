package com.ecommerce.ecommerce.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Permission {

    // Admin permissions
    ADMIN_READ("admin:read"),
    ADMIN_UPDATE("admin:update"),
    ADMIN_CREATE("admin:create"),
    ADMIN_DELETE("admin:delete"),

    // Manager permissions
    MANAGER_READ("management:read"),
    MANAGER_UPDATE("management:update"),
    MANAGER_CREATE("management:create"),
    MANAGER_DELETE("management:delete"),

    // Product permissions
    PRODUCT_READ("product:read"),
    PRODUCT_CREATE("product:create"),
    PRODUCT_UPDATE("product:update"),
    PRODUCT_DELETE("product:delete"),

    // Order permissions
    ORDER_READ("order:read"),
    ORDER_CREATE("order:create"),
    ORDER_UPDATE("order:update"),
    ORDER_DELETE("order:delete"),

    // User management permissions
    USER_READ("user:read"),
    USER_CREATE("user:create"),
    USER_UPDATE("user:update"),
    USER_DELETE("user:delete");

    @Getter
    private final String permission;
}