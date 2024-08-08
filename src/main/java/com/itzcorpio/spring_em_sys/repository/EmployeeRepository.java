package com.itzcorpio.spring_em_sys.repository;

import com.itzcorpio.spring_em_sys.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
