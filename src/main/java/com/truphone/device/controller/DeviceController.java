package com.truphone.device.controller;

import com.truphone.device.dto.DeviceDTO;
import com.truphone.device.entity.Device;
import com.truphone.device.exception.RecordNotFoundException;
import com.truphone.device.mapper.DeviceMapper;
import com.truphone.device.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/device")
public class DeviceController {

    @Autowired
    DeviceService deviceService;

    @Autowired
    DeviceMapper deviceMapper;

    @PostMapping
    public ResponseEntity<DeviceDTO> addDevice(@RequestBody DeviceDTO deviceDTO ) {
        try {
            Device device = deviceService.addDevice(deviceMapper.convertToEntity(deviceDTO));
            return new ResponseEntity<>(deviceMapper.convertToDTO(device), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<DeviceDTO> getDeviceById(@PathVariable("id") long id)  {
        try {
            Device device = deviceService.getDeviceById(id);
            if (null != device) {
                return new ResponseEntity<>(deviceMapper.convertToDTO(device), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (RecordNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<List<DeviceDTO>> listDevices(@RequestParam(required = false) Optional<String> brand){
        try {
            List<Device> devices = deviceService.listDevices(brand);

            if (devices.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(devices.stream()
                            .map(deviceMapper::convertToDTO).collect(Collectors.toList()), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<DeviceDTO> updateDevice(@PathVariable("id") long id, @RequestBody DeviceDTO deviceRequest)  {

        try {
            Device device = deviceService.updateDevice(id, deviceMapper.convertToEntity(deviceRequest));
            if (null != device) {
                return new ResponseEntity<>(deviceMapper.convertToDTO(device), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (RecordNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deleteDevice(@PathVariable("id") long id)  {
        try {
            deviceService.deleteDevice(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RecordNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
