package com.lz.crud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lz.crud.bean.Employee;
import com.lz.crud.bean.EmployeeExample;
import com.lz.crud.bean.EmployeeExample.Criteria;
import com.lz.crud.dao.EmployeeMapper;
@Service
public class EmployeeService {
	@Autowired
	EmployeeMapper employeeMapper;
	/**
	 * ��ѯ����Ա��
	 * @return
	 */
	public List<Employee> getAll() {
		// TODO Auto-generated method stub
		return employeeMapper.selectByExampleWithDept(null);
	}
	/**
	 * ����Ա��
	 * @param employee
	 */
	public void saveEmp(Employee employee) {
		// TODO Auto-generated method stub
		employeeMapper.insertSelective(employee);
	}
	/**
	 * �����û����Ƿ����
	 */
	public boolean checkUser(String empName) {
		// TODO Auto-generated method stub
		EmployeeExample example = new EmployeeExample();
		Criteria criteria = example.createCriteria();
		criteria.andEmpNameEqualTo(empName);
		long count = employeeMapper.countByExample(example);
		return count==0;
	}
	public Employee getEmp(Integer id) {
		// TODO Auto-generated method stub
		Employee employee = employeeMapper.selectByPrimaryKey(id);
		return employee;
	}
	/**
	 * Ա������
	 * @param employee
	 */
	public void updateEmp(Employee employee) {
		// TODO Auto-generated method stub
		employeeMapper.updateByPrimaryKeySelective(employee);
	}
	/**
	 * Ա��ɾ��
	 * @param id
	 */
	public void deleteBatch(List<Integer> ids) {
		// TODO Auto-generated method stub
		EmployeeExample example = new EmployeeExample();
		Criteria criteria = example.createCriteria();
		//delete from xxx where emp_id in(1,2,3)
		criteria.andEmpIdIn(ids);
		employeeMapper.deleteByExample(example);
	}
	public void deleteEmp(Integer id) {
		// TODO Auto-generated method stub
		employeeMapper.deleteByPrimaryKey(id);
	}

}
