package com.lz.crud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lz.crud.bean.Department;
import com.lz.crud.dao.DepartmentMapper;
@Service
public class DepartmentService {
	@Autowired 
	private DepartmentMapper departmentMapper;
	public List<Department> getDepts() {
		// TODO Auto-generated method stub
		return departmentMapper.selectByExample(null);
	}
	
}
