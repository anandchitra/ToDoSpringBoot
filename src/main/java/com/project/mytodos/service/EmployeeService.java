package com.project.mytodos.service;

import com.project.mytodos.DTO.EmployeeDTO;
import com.project.mytodos.DTO.LoginDTO;
import com.project.mytodos.entity.*;

public interface EmployeeService {
    String addEmployee(EmployeeDTO employeeDTO);
    LoginMessage loginEmployee(LoginDTO loginDTO);


}
