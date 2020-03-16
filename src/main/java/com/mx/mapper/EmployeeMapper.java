package com.mx.mapper;

import com.mx.pojo.Employee;

public interface EmployeeMapper {
    int insert(Employee record);

    int insertSelective(Employee record);
}