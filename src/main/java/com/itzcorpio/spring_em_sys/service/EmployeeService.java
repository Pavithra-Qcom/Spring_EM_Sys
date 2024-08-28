package com.itzcorpio.spring_em_sys.service;

import com.itzcorpio.spring_em_sys.model.Employee;
import com.itzcorpio.spring_em_sys.repository.EmployeeRepository;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    private final String storageDirectory = "C:\\Users\\rogue\\Downloads\\profile_pictures";

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Optional<Employee> getEmployeeById(Long id) {
        return employeeRepository.findById(id);
    }

    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }

    public Employee updateEmployee(Long id, Employee employee){
        Optional<Employee> emp = employeeRepository.findById(id);

        if(emp.isPresent()){
            Employee existingEmployee = emp.get();

            existingEmployee.setName(employee.getName());
            existingEmployee.setEmail(employee.getEmail());
            existingEmployee.setPosition(employee.getPosition());

            return employeeRepository.save(existingEmployee);
        }else {
            throw new RuntimeException("Employee not found");
        }
    }

    public void saveProfilePicture(MultipartFile file, Long employeeId) throws IOException {
        if (!Files.exists(Paths.get(storageDirectory))) {
            Files.createDirectories(Paths.get(storageDirectory));
        }
        String fileName = employeeId + "_" + file.getOriginalFilename();
        File destinationFile = new File(storageDirectory + File.separator + fileName);
        file.transferTo(destinationFile);

        Employee employee = employeeRepository.findById(employeeId).orElseThrow(() -> new RuntimeException("Employee not found"));
        employee.setProfilePicturePath(destinationFile.getPath());
        employeeRepository.save(employee);
    }

    public byte[] getProfilePicture(Long employeeId) throws IOException {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(() -> new RuntimeException("Employee not found"));
        String picturePath = employee.getProfilePicturePath();
        return Files.readAllBytes(Paths.get(picturePath));
    }

    public ByteArrayOutputStream exportEmployeeListPdf(List<Employee> employees) throws JRException
    {
        File jrxmlFile = new File("./report/employeeList.jrxml");

        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(employees);
        JasperReport jasperReport = JasperCompileManager.compileReport(jrxmlFile.getAbsolutePath());

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, dataSource);

        ByteArrayOutputStream pdfOutputStream = new ByteArrayOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, pdfOutputStream);

        return pdfOutputStream;
    }

}
