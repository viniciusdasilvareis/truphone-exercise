package com.truphone.device.service;

import com.truphone.device.entity.Device;
import com.truphone.device.exception.RecordNotFoundException;
import com.truphone.device.repository.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DeviceService {

    @Autowired
    DeviceRepository deviceRepository;

    public Device addDevice(Device device) {
        return deviceRepository.save(device);
    }

    public Device getDeviceById(long id) throws RecordNotFoundException {
        Optional <Device> device = deviceRepository.findById(id);

        if (device.isPresent()) {
            return device.get();
        } else {
            throw new RecordNotFoundException("No device exist for given id");
        }
    }

    public List<Device> listDevices(Optional<String> brand)
    {
        List<Device> devices;

        if (brand.isPresent()){
            devices =  deviceRepository.findByBrand(brand.get());
        } else {
            devices = deviceRepository.findAll();
        }
        return devices;
    }

    public Device updateDevice(long id, Device device) throws RecordNotFoundException{
        Device newEntity;
        Optional<Device> deviceDatabase = deviceRepository.findById(id);

        if(deviceDatabase.isPresent()) {
            newEntity = deviceDatabase.get();
            newEntity.setName(device.getName());
            newEntity.setCreationTime(device.getCreationTime());
            newEntity.setBrand(device.getBrand());
            newEntity = deviceRepository.save(newEntity);
        } else {
            throw new RecordNotFoundException("No device exist for given id");
        }
        return newEntity;
    }

    public void deleteDevice(Long id) throws RecordNotFoundException
    {
        Optional<Device> device = deviceRepository.findById(id);

        if(device.isPresent())
        {
            deviceRepository.deleteById(id);
        } else {
            throw new RecordNotFoundException("No device exist for given id");
        }
    }
}
