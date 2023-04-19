package com.truphone.device.mapper;

import com.truphone.device.dto.DeviceDTO;
import com.truphone.device.entity.Device;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DeviceMapper {

    @Autowired
    ModelMapper modelMapper;

    public Device convertToEntity(DeviceDTO deviceDTO)  {
        return modelMapper.map(deviceDTO, Device.class);
    }

    public DeviceDTO convertToDTO(Device device) {
        return modelMapper.map(device, DeviceDTO.class);
    }
}
