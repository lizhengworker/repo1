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
 * 推荐Spring的项目就可以使用Spring的单元测试，可以自动注入我们需要的组件
 * 1.导入spring test 模块
 * 2@ContextConfiguration指定Spring配置文件的位置
 * @RunWith指定使用的单元测试模块
 * 3.直接autowire注入
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
		 * //1.攒关键springioc容器 ApplicationContext ioc = new
		 * ClassPathXmlApplicationContext("applicationContext.xml"); //2.从容器中获取mapper
		 * DepartmentMapper bean = ioc.getBean(DepartmentMapper.class);
		 */
		System.out.println(departmentMapper);
		//1.插入几个部门
//		departmentMapper.insertSelective(new Department(1, "开发部"));
//		departmentMapper.insertSelective(new Department(2, "测试部"));

		//2.生成员工数据 测试员工插入
//		employeeMapper.insertSelective(new Employee(null, "likuan", "M", "likuan@163.com", 1));
	
		//3.批量插入多个员工：使用可以执行批量操作的sqlsession
		for(int i=2;i<3;i++) {
			String name = UUID.randomUUID().toString().substring(0, 5);
			employeeMapper.insertSelective(new Employee(null, name, "M", name+"@li.com", 1));
		}
		System.out.println("批量插入完成");
	}
}
