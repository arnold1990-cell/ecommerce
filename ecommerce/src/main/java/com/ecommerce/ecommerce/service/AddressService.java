package com.ecommerce.ecommerce.service;

import com.ecommerce.ecommerce.model.Address;
import com.ecommerce.ecommerce.model.User;
import com.ecommerce.ecommerce.repository.AddressRepository;
import com.ecommerce.ecommerce.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressService {

    private final AddressRepository addressRepository;
    private final UserRepository userRepository;

    /**
     * CREATE A NEW ADDRESS FOR A USER
     */
    public Address createAddress(Long userId, Address address) {

        // Make sure user exists
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        address.setUser(user);

        // If address is default, remove default from others
        if (address.isDefault()) {
            removeDefaultFromOtherAddresses(userId);
        }

        return addressRepository.save(address);
    }

    /**
     * GET ALL ADDRESSES FOR A USER
     */
    public List<Address> getUserAddresses(Long userId) {
        return addressRepository.findByUserId(userId);
    }

    /**
     * GET ADDRESS BY ID
     */
    public Address getAddressById(Long addressId) {
        return addressRepository.findById(addressId)
                .orElseThrow(() -> new RuntimeException("Address not found"));
    }

    /**
     * UPDATE AN EXISTING ADDRESS
     */
    public Address updateAddress(Long addressId, Address newDetails) {

        Address existing = getAddressById(addressId);

        existing.setStreet(newDetails.getStreet());
        existing.setCity(newDetails.getCity());
        existing.setCountry(newDetails.getCountry());
        existing.setPostalCode(newDetails.getPostalCode());
        existing.setPhone(newDetails.getPhone());

        // Handle default address logic
        if (newDetails.isDefault()) {
            removeDefaultFromOtherAddresses(existing.getUser().getId());
            existing.setDefault(true);
        }

        return addressRepository.save(existing);
    }

    /**
     * DELETE ADDRESS
     */
    public void deleteAddress(Long addressId) {
        addressRepository.deleteById(addressId);
    }

    /**
     * SET DEFAULT ADDRESS FOR USER
     */
    public Address setDefaultAddress(Long userId, Long addressId) {

        Address address = getAddressById(addressId);

        if (!address.getUser().getId().equals(userId)) {
            throw new RuntimeException("Address does not belong to the user");
        }

        removeDefaultFromOtherAddresses(userId);

        address.setDefault(true);
        return addressRepository.save(address);
    }

    /**
     * GET ALL ADDRESSES IN THE SYSTEM
     */
    public List<Address> getAllAddresses() {
        return addressRepository.findAll();
    }

    /**
     * INTERNAL HELPER:
     * Remove "default" from all addresses of the user
     */
    private void removeDefaultFromOtherAddresses(Long userId) {
        List<Address> addresses = addressRepository.findByUserId(userId);

        for (Address addr : addresses) {
            if (addr.isDefault()) {
                addr.setDefault(false);
                addressRepository.save(addr);
            }
        }
    }
}
