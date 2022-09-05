package com.benitomiyazato.learning.springboot.service;

import com.benitomiyazato.learning.springboot.entity.Department;
import com.benitomiyazato.learning.springboot.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public Department saveDepartment(Department department) {
        return departmentRepository.save(department);
    }

    @Override
    public List<Department> fetchDepartmentList() {
        return departmentRepository.findAll();
    }

    @Override
    public Department fetchDepartmentById(Long departmentId) {
        Optional<Department> fetchedDepartment = departmentRepository.findById(departmentId);
        return fetchedDepartment.get();
    }

    @Override
    public void deleteDepartmentById(Long departmentId) {
        departmentRepository.deleteById(departmentId);
    }

    @Override
    public Department updateDepartment(Long departmentId, Department departmentUpdate) {
        Optional<Department> departmentInDatabaseOptional = departmentRepository.findById(departmentId);
        Department departmentInDataBase;

        // if this optional is null, stops here
        if (departmentInDatabaseOptional.isEmpty()) {
            return null;
        }

        departmentInDataBase = departmentInDatabaseOptional.get();

        // these conditions make sure you can update only one field of the Department if you want
        // it does so by checking if each specific field of the request body is null
        // if it's not null, the field is updated.
        if (departmentUpdate.getDepartmentName() != null & !departmentUpdate.getDepartmentName().equals("")) {
            departmentInDataBase.setDepartmentName(departmentUpdate.getDepartmentName());
        }

        if (departmentUpdate.getDepartmentAddress() != null & !departmentUpdate.getDepartmentName().equals("")) {
            departmentInDataBase.setDepartmentAddress(departmentUpdate.getDepartmentAddress());
        }

        if (departmentUpdate.getDepartmentCode() != null & !departmentUpdate.getDepartmentName().equals("")) {
            departmentInDataBase.setDepartmentCode(departmentUpdate.getDepartmentCode());
        }

        return departmentRepository.save(departmentInDataBase);
    }

    @Override
    public Department fetchDepartmentByName(String name) {
        return departmentRepository.findDepartmentByDepartmentNameIgnoreCase(name);
    }

}
