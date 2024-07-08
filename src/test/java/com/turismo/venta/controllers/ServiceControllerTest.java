package com.turismo.venta.controllers;
import com.turismo.venta.entity.Services;
import com.turismo.venta.service.ServicesServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class ServiceControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ServicesServiceImpl serviceService;

    @InjectMocks
    private ServiceController serviceController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(serviceController).build();
    }

    @Test
    public void testGetAllServices() throws Exception {
        Services service1 = new Services("1", "Service1", "Description1", "Destination1", "01-01-2023", "100");
        Services service2 = new Services("2", "Service2", "Description2", "Destination2", "02-02-2023", "200");
        List<Services> servicesList = Arrays.asList(service1, service2);

        when(serviceService.getAllServices()).thenReturn(servicesList);

        mockMvc.perform(get("/api/v1/services"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value("1"))
                .andExpect(jsonPath("$[0].nombre").value("Service1"))
                .andExpect(jsonPath("$[1].id").value("2"))
                .andExpect(jsonPath("$[1].nombre").value("Service2"));

        verify(serviceService, times(1)).getAllServices();
    }

    @Test
    public void testFindServicesByDestino() throws Exception {
        Services service = new Services("1", "Service1", "Description1", "Destination1", "01-01-2023", "100");
        List<Services> servicesList = Arrays.asList(service);

        when(serviceService.findServicesByDestino(anyString())).thenReturn(servicesList);

        mockMvc.perform(get("/api/v1/services/Destination1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value("1"))
                .andExpect(jsonPath("$[0].nombre").value("Service1"));

        verify(serviceService, times(1)).findServicesByDestino(anyString());
    }

    @Test
    public void testFindServicesByFecha() throws Exception {
        Services service = new Services("1", "Service1", "Description1", "Destination1", "01-01-2023", "100");
        List<Services> servicesList = Arrays.asList(service);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate date = LocalDate.parse("01-01-2023", formatter);

        when(serviceService.findServicesByFecha(anyString())).thenReturn(servicesList);

        mockMvc.perform(get("/api/v1/services/fecha")
                        .param("date", "01-01-2023"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value("1"))
                .andExpect(jsonPath("$[0].nombre").value("Service1"));

        verify(serviceService, times(1)).findServicesByFecha(anyString());
    }

    @Test
    public void testDeleteService() throws Exception {
        doNothing().when(serviceService).deleteService(anyString());

        mockMvc.perform(delete("/api/v1/services/1"))
                .andExpect(status().isNoContent());

        verify(serviceService, times(1)).deleteService(anyString());
    }

    @Test
    public void testUpdateService() throws Exception {
        doNothing().when(serviceService).updateService(any(Services.class));

        mockMvc.perform(put("/api/v1/services/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":\"1\",\"nombre\":\"Service1\",\"descripcion\":\"Description1\",\"destino\":\"Destination1\",\"fecha\":\"01-01-2023\",\"precio\":\"100\"}"))
                .andExpect(status().isNoContent());

        verify(serviceService, times(1)).updateService(any(Services.class));
    }

    @Test
    public void testSaveService() throws Exception {
        doNothing().when(serviceService).saveService(any(Services.class));

        mockMvc.perform(post("/api/v1/services")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":\"1\",\"nombre\":\"Service1\",\"descripcion\":\"Description1\",\"destino\":\"Destination1\",\"fecha\":\"01-01-2023\",\"precio\":\"100\"}"))
                .andExpect(status().isNoContent());

        verify(serviceService, times(1)).saveService(any(Services.class));
    }
}