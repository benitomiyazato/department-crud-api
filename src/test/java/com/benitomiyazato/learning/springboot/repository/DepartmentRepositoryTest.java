package com.benitomiyazato.learning.springboot.repository;

import com.benitomiyazato.learning.springboot.entity.Department;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class DepartmentRepositoryTest {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    private Department modelDepartment;
    private Long persistedDepartmentId;

    @BeforeEach
    void setUp() {
        modelDepartment = Department.builder()
                .departmentName("Mechanical")
                .departmentAddress("Beltrano de Tal, 123")
                .departmentCode("MecH-003")
                .build();

        persistedDepartmentId = (Long) testEntityManager.persistAndGetId(modelDepartment);
    }

    @Test
    @DisplayName("Return department when a valid ID is given")
    public void findById_returnDepartment_whenValidIdIsGiven(){
        Department fetchedDepartment = departmentRepository.findById(persistedDepartmentId).get();

        Assertions.assertEquals(persistedDepartmentId, fetchedDepartment.getId());
    }

    @Test
    @DisplayName("Return department by its name when given name in lowercase")
    public void findDepartmentByDepartmentNameIgnoreCase_returnDepartmentByDepartmentName_whenValidNameIsGivenInLowerCase(){
        Department fetchedDepartment = departmentRepository.findDepartmentByDepartmentNameIgnoreCase("mechanical");
        Assertions.assertEquals(modelDepartment, fetchedDepartment);
    }
    @Test
    @DisplayName("Return department by its name when given name in uppercase")
    public void findDepartmentByDepartmentNameIgnoreCase_returnDepartmentByDepartmentName_whenValidNameIsGivenInUpperCase(){
        Department fetchedDepartment = departmentRepository.findDepartmentByDepartmentNameIgnoreCase("MECHANICAL");
        Assertions.assertEquals(modelDepartment, fetchedDepartment);
    }
}