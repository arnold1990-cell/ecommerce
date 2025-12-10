package com.ecommerce.ecommerce.controller;

import com.ecommerce.ecommerce.model.Address;
import com.ecommerce.ecommerce.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SuppressWarnings("unused")
@RestController
@RequestMapping("/api/addresses")
@RequiredArgsConstructor
public class AddressController {

    private final AddressService addressService;

    /**
     * CREATE a new address for a user
     * POST: /api/addresses/user/{userId}
     */
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/user/{userId}")
    public ResponseEntity<Address> createAddress(
            @PathVariable Long userId,
            @RequestBody Address address
    ) {
        Address created = addressService.createAddress(userId, address);
        return ResponseEntity.ok(created);
    }

    /**
     * GET all addresses of a user
     * GET: /api/addresses/user/{userId}
     */
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Address>> getUserAddresses(@PathVariable Long userId) {
        return ResponseEntity.ok(addressService.getUserAddresses(userId));
    }

    /**
     * GET address by ID
     * GET: /api/addresses/{addressId}
     */
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{addressId}")
    public ResponseEntity<Address> getAddressById(@PathVariable Long addressId) {
        return ResponseEntity.ok(addressService.getAddressById(addressId));
    }

    /**
     * UPDATE address
     * PUT: /api/addresses/{addressId}
     */
    @PreAuthorize("isAuthenticated()")
    @PutMapping("/{addressId}")
    public ResponseEntity<Address> updateAddress(
            @PathVariable Long addressId,
            @RequestBody Address address
    ) {
        Address updated = addressService.updateAddress(addressId, address);
        return ResponseEntity.ok(updated);
    }

    /**
     * DELETE address
     * DELETE: /api/addresses/{addressId}
     */
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{addressId}")
    public ResponseEntity<Void> deleteAddress(@PathVariable Long addressId) {
        addressService.deleteAddress(addressId);
        return ResponseEntity.noContent().build();
    }

    /**
     * SET DEFAULT address for a user
     * PUT: /api/addresses/{userId}/{addressId}/default
     */
    @PreAuthorize("isAuthenticated()")
    @PutMapping("/{userId}/{addressId}/default")
    public ResponseEntity<Address> setDefaultAddress(
            @PathVariable Long userId,
            @PathVariable Long addressId
    ) {
        Address updated = addressService.setDefaultAddress(userId, addressId);
        return ResponseEntity.ok(updated);
    }

    /**
     * GET ALL addresses in the system
     * GET: /api/addresses
     */
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<Address>> getAllAddresses() {
        return ResponseEntity.ok(addressService.getAllAddresses());
    }
}
