package com.benitomiyazato.learning.springboot.repository;

import com.benitomiyazato.learning.springboot.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
    public Department findDepartmentByDepartmentNameIgnoreCase(String name);
}
