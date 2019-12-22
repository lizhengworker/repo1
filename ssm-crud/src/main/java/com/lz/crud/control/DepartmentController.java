package com.lz.crud.control;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lz.crud.bean.Department;
import com.lz.crud.bean.Msg;
import com.lz.crud.service.DepartmentService;


/**
 * ����Ͳ����йص�����
 * @author DELL
 *
 */
@Controller
public class DepartmentController {
	@Autowired
	private DepartmentService departmentService;
	
	/**
	 * �������в�����Ϣ
	 */
	@RequestMapping("/depts")
	@ResponseBody
	public Msg getDepts() {
		List<Department> list = departmentService.getDepts();
		return Msg.success().add("depts", list);
	}
}
