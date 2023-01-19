package com.kakao.lango.moviereview.service;

import com.kakao.lango.moviereview.domain.Employee;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Override
    public Employee createEmployee(String empId, String fname, String sname) {
        Employee employee = Employee.builder()
                .empId(empId)
                .firstName(fname)
                .secondName(sname)
                .build();
        return employee;
    }
}
