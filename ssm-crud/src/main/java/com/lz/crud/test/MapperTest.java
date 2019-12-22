package com.lz.crud.test;


import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.lz.crud.bean.Department;
import com.lz.crud.bean.Employee;
import com.lz.crud.dao.DepartmentMapper;
import com.lz.crud.dao.EmployeeMapper;
/**
 * �Ƽ�Spring����Ŀ�Ϳ���ʹ��Spring�ĵ�Ԫ���ԣ������Զ�ע��������Ҫ�����
 * 1.����spring test ģ��
 * 2@ContextConfigurationָ��Spring�����ļ���λ��
 * @RunWithָ��ʹ�õĵ�Ԫ����ģ��
 * 3.ֱ��autowireע��
 * @author DELL
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class MapperTest {
	@Autowired
	DepartmentMapper departmentMapper;
	@Autowired
	EmployeeMapper employeeMapper;
	
	@Test
	public void testCRUD() {
		/*
		 * //1.�ܹؼ�springioc���� ApplicationContext ioc = new
		 * ClassPathXmlApplicationContext("applicationContext.xml"); //2.�������л�ȡmapper
		 * DepartmentMapper bean = ioc.getBean(DepartmentMapper.class);
		 */
		System.out.println(departmentMapper);
		//1.���뼸������
//		departmentMapper.insertSelective(new Department(1, "������"));
//		departmentMapper.insertSelective(new Department(2, "���Բ�"));

		//2.����Ա������ ����Ա������
//		employeeMapper.insertSelective(new Employee(null, "likuan", "M", "likuan@163.com", 1));
	
		//3.����������Ա����ʹ�ÿ���ִ������������sqlsession
		for(int i=2;i<3;i++) {
			String name = UUID.randomUUID().toString().substring(0, 5);
			employeeMapper.insertSelective(new Employee(null, name, "M", name+"@li.com", 1));
		}
		System.out.println("�����������");
	}
}
