package com.benitomiyazato.learning.springboot.service;

import com.benitomiyazato.learning.springboot.entity.Department;
import com.benitomiyazato.learning.springboot.error.DepartmentNotFoundException;
import com.benitomiyazato.learning.springboot.repository.DepartmentRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DepartmentServiceTest {

    @Autowired
    private DepartmentService departmentService;

    @MockBean
    private DepartmentRepository departmentRepository;

    private Long notExistingDepartmentId;
    private String notExistingName;
    @BeforeEach
    void setUp() {
        notExistingDepartmentId = 99999999999L;
        notExistingName = "notExistingName";

        Department departmentModel =
                Department.builder()
                        .departmentName("TI")
                        .departmentAddress("Fulano de Tal, 123")
                        .departmentCode("TI 006").id(1L)
                        .build();

        // FindDepartmentByDepartmentName Mock
        Mockito.when(departmentRepository.findDepartmentByDepartmentNameIgnoreCase("TI")).thenReturn(departmentModel);
        Mockito.when(departmentRepository.findDepartmentByDepartmentNameIgnoreCase(notExistingName)).thenReturn(null);

        // FindDepartmentById Mock
        Mockito.when(departmentRepository.findById(1L)).thenReturn(Optional.of(departmentModel));
        Mockito.when(departmentRepository.findById(notExistingDepartmentId)).thenReturn(Optional.empty());
    }
    @Test
    @DisplayName("Returns department by name if given name exists in the Database")
    public void fetchDepartmentByName_returnDepartment_whenValidDepartmentNameIsGiven() throws DepartmentNotFoundException {
        String departmentName = "TI";

        Department foundDepartment = departmentService.fetchDepartmentByName(departmentName);
        Assertions.assertEquals(departmentName, foundDepartment.getDepartmentName());
    }

    @Test
    @DisplayName("Throws DepartmentNotFoundException if given name doesn't exist in the Database")
    public void fetchDepartmentByName_throwsDepartmentNotFoundException_whenUnexistingNameIsGiven() throws DepartmentNotFoundException {
        Assertions.assertThrows(DepartmentNotFoundException.class, () -> departmentService.fetchDepartmentByName(notExistingName));
    }

    @Test
    @DisplayName("Returns department by ID if given ID exists in the Database")
    public void fetchDepartmentById_returnDepartment_whenValidDepartmentIdIsGiven() throws DepartmentNotFoundException {
        Long departmentId = 1L;

        Department foundDepartment = departmentService.fetchDepartmentById(departmentId);
        Assertions.assertEquals(departmentId, foundDepartment.getId());
    }
    @Test
    @DisplayName("Throws DepartmentNotFoundException if given ID doesn't exist in the Database")
    public void fetchDepartmentById_throwsDepartmentNotFoundException_whenUnexistingIdIsGiven() throws DepartmentNotFoundException {
        Assertions.assertThrows(DepartmentNotFoundException.class, () -> departmentService.fetchDepartmentById(notExistingDepartmentId));
    }
}