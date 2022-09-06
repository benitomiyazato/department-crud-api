package com.benitomiyazato.learning.springboot.controller;

import com.benitomiyazato.learning.springboot.entity.Department;
import com.benitomiyazato.learning.springboot.error.DepartmentNotFoundException;
import com.benitomiyazato.learning.springboot.service.DepartmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class DepartmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DepartmentService departmentService;

    private Department departmentModel;

    @BeforeEach
    void setUp() throws DepartmentNotFoundException {
        departmentModel = Department.builder()
                .departmentName("Mechanical")
                .departmentAddress("Fulano de Tal, 123")
                .departmentCode("Mech-001")
                .id(1L)
                .build();
    }

    @Test
    @DisplayName("Return ok status when saving operation is succesfull")
    void saveDepartment() throws Exception {
        Mockito.when(departmentService.saveDepartment(departmentModel)).thenReturn(departmentModel);


        // tries to make a post request to '/departments' passing a JSON object the same as departmentModel
        // and expects an OK STATUS as a response
        mockMvc
            .perform(post("/departments")
                .contentType(MediaType.APPLICATION_JSON)
                .content
                ("""
                    {
                        "departmentName":"Mechanical",
                        "departmentAddress":"Fulano de Tal, 123",
                        "departmentCode":"Mech-001"
                    }
                """)
                )
            .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Return department by its id when valid id is given")
    void fetchDepartmentById() throws Exception {
        Mockito.when(departmentService.fetchDepartmentById(1L)).thenReturn(departmentModel);

        // tries to make a get request for a specific ID = 1
        // and expects an object also with its ID = 1 as a response
        mockMvc
                .perform(get("/departments/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(departmentModel.getId()));
    }

    @Test
    @DisplayName("Return department by its name when valid name is given in lower case")
    void fetchDepartmentByName_returnDepartmentByName_whenValidNameIsGivenInLowerCase() throws Exception {
        Mockito.when(departmentService.fetchDepartmentByName("mechanical")).thenReturn(departmentModel);

        // tries to make a get request for a specific name = mechanical
        // and expects an object also with its name = mechanical (ignoring case) as a response
        mockMvc
                .perform(get("/departments/name/mechanical"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.departmentName").value(departmentModel.getDepartmentName()));
    }

    @Test
    @DisplayName("Return department by its name when valid name is given in upper case")
    void fetchDepartmentByName_returnDepartmentByName_whenValidNameIsGivenInUpperCase() throws Exception {
        Mockito.when(departmentService.fetchDepartmentByName("MECHANICAL")).thenReturn(departmentModel);

        // tries to make a get request for a specific name = MECHANICAL
        // and expects an object also with its name = mechanical (ignoring case) as a response
        mockMvc
                .perform(get("/departments/name/MECHANICAL").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.departmentName").value(departmentModel.getDepartmentName()));
    }
}