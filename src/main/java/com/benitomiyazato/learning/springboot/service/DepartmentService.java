package com.benitomiyazato.learning.springboot.service;

import com.benitomiyazato.learning.springboot.entity.Department;
import com.benitomiyazato.learning.springboot.error.DepartmentNotFoundException;

import java.util.List;

public interface DepartmentService {
    public Department saveDepartment(Department department);

    public List<Department> fetchDepartmentList();

    public Department fetchDepartmentById(Long departmentId) throws DepartmentNotFoundException;

    public void deleteDepartmentById(Long departmentId);

    public Department updateDepartment(Long departmentId, Department departmentUpdate) throws DepartmentNotFoundException;

    public Department fetchDepartmentByName(String name) throws DepartmentNotFoundException;

}
