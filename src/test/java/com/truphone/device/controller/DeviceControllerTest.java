package com.truphone.device.controller;

import com.truphone.device.dto.DeviceDTO;
import com.truphone.device.entity.Device;
import com.truphone.device.mapper.DeviceMapper;
import com.truphone.device.service.DeviceService;
import com.truphone.device.utils.TestUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@WebMvcTest(DeviceController.class)
public class DeviceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    DeviceService deviceService;

    @MockBean
    DeviceMapper deviceMapper;

    private final String URL = "/api/device";

    @Test
    public void testAddDevice() throws Exception {

        Device device = new Device();
        device.setId(1);
        device.setName("name");
        device.setBrand("brand");

        DeviceDTO deviceDTO = new DeviceDTO();
        deviceDTO.setId(1);
        deviceDTO.setName("name");
        deviceDTO.setBrand("brand");
        deviceDTO.setCreationTime(null);

        when(deviceService.addDevice(any(Device.class))).thenReturn(device);
        when(deviceMapper.convertToEntity(any(DeviceDTO.class))).thenReturn(device);
        when(deviceMapper.convertToDTO(any(Device.class))).thenReturn(deviceDTO);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post(URL).contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON).content(TestUtils.objectToJson(deviceDTO))).andReturn();

        int status = result.getResponse().getStatus();
        assertEquals("Incorrect Response Status", HttpStatus.CREATED.value(), status);

        verify(deviceService).addDevice(any(Device.class));

        Device deviceResult = TestUtils.jsonToObject(result.getResponse().getContentAsString(), Device.class);
        assertNotNull(deviceResult);
        assertEquals(1L, deviceResult.getId());

    }

    @Test
    public void testGetDeviceById() throws Exception {

        Device device = new Device();
        device.setId(1);
        device.setName("name");
        device.setBrand("brand");

        DeviceDTO deviceDTO = new DeviceDTO();
        deviceDTO.setId(1);
        deviceDTO.setName("name");
        deviceDTO.setBrand("brand");

        when(deviceService.getDeviceById(any(Long.class))).thenReturn(device);
        when(deviceMapper.convertToDTO(any(Device.class))).thenReturn(deviceDTO);

        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders.get(URL + "/{id}", 1L).accept(MediaType.APPLICATION_JSON))
                .andReturn();

        int status = result.getResponse().getStatus();
        assertEquals("Incorrect Response Status", HttpStatus.OK.value(), status);

        verify(deviceService).getDeviceById(any(Long.class));

        Device deviceResult = TestUtils.jsonToObject(result.getResponse().getContentAsString(), Device.class);
        assertNotNull(deviceResult);
        assertEquals(1L, deviceResult.getId());
    }

    @Test
    public void testListDevices() throws Exception {

        DeviceDTO deviceDTO = new DeviceDTO();
        deviceDTO.setId(1);
        deviceDTO.setName("name");
        deviceDTO.setBrand("brand");

        List<Device> devices = createDevices();
        when(deviceService.listDevices(Optional.empty())).thenReturn(devices);
        when(deviceMapper.convertToDTO(any(Device.class))).thenReturn(deviceDTO);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(URL).accept(MediaType.APPLICATION_JSON))
                .andReturn();

        int status = result.getResponse().getStatus();
        assertEquals("Incorrect Response Status", HttpStatus.OK.value(), status);

        verify(deviceService).listDevices(Optional.empty());

        @SuppressWarnings("unchecked")
        List<Device> devicesResult = TestUtils.jsonToList(result.getResponse().getContentAsString());

        assertNotNull("Devices not found", devicesResult);
        assertEquals("Incorrect Device List", devices.size(), devicesResult.size());

    }

    @Test
    public void testDeleteDevice() throws Exception {

        Device device = new Device();
        device.setId(1);
        when(deviceService.getDeviceById(any(Long.class))).thenReturn(device);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.delete(URL + "/{id}", 1L)).andReturn();

        int status = result.getResponse().getStatus();
        assertEquals("Incorrect Response Status", HttpStatus.NO_CONTENT.value(), status);

        verify(deviceService).deleteDevice(any(Long.class));

    }

    @Test
    public void testUpdateDevice() throws Exception {
        Device device = new Device();
        device.setId(1);
        device.setName("name");
        device.setBrand("brand");

        DeviceDTO deviceDTO = new DeviceDTO();
        deviceDTO.setId(1);
        deviceDTO.setName("name");
        deviceDTO.setBrand("brand");

        when(deviceService.getDeviceById(any(Long.class))).thenReturn(device);
        when(deviceMapper.convertToEntity(any(DeviceDTO.class))).thenReturn(device);
        when(deviceMapper.convertToDTO(any(Device.class))).thenReturn(deviceDTO);
        when(deviceService.updateDevice(any(Long.class), any(Device.class))).thenReturn(device);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.put(URL + "/{id}", 1L) .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON).content(TestUtils.objectToJson(deviceDTO))).andReturn();

        int status = result.getResponse().getStatus();
        assertEquals("Incorrect Response Status", HttpStatus.OK.value(), status);

        verify(deviceService).updateDevice(any(Long.class), any(Device.class));

    }

    private List<Device> createDevices() {
        Device device1 = new Device();
        device1.setId(1);
        device1.setName("name1");
        device1.setBrand("brand1");
        Device device2 = new Device();
        device2.setId(2);
        device2.setName("name2");
        device2.setBrand("brand2");
        return Arrays.asList(device1, device2);
    }
}
