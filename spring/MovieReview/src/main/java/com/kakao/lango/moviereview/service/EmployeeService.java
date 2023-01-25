package com.kakao.lango.moviereview.service;

import com.kakao.lango.moviereview.domain.Employee;

public interface EmployeeService {
    Employee createEmployee(String empId, String fname, String sname);

}
