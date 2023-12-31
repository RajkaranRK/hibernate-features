package com.rk.hibernate.features.service;


import com.rk.hibernate.features.one.domain.onetoone.Address;
import com.rk.hibernate.features.one.repository.AddressWriteRepository;
import com.rk.hibernate.features.request.AddressDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService {

    @Autowired
    private AddressWriteRepository addressWriteRepository;

    public Address createAddress(AddressDO addressDO){
        Address address = Address.builder()
                .city(addressDO.getCity())
                .state(addressDO.getState())
                .houseNumber(addressDO.getHouseNumber())
                .zipCode(addressDO.getZipCode())
                .build();
        return addressWriteRepository.save(address);
    }

    public Address getAddress(Long id){
        return addressWriteRepository.findById(id).get();
    }
}
