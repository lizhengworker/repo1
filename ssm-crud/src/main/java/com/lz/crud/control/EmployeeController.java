package com.lz.crud.control;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lz.crud.bean.Employee;
import com.lz.crud.bean.Msg;
import com.lz.crud.service.EmployeeService;

/**
 * ����Ա��crud����
 * @author DELL
 *
 */
@Controller
public class EmployeeController {
	@Autowired
	EmployeeService employeeService;
	
	/**
	 * ������������һ
	 * ����ɾ����1-2-3
	 * ����ɾ����1
	 * 
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/emp/{ids}",method=RequestMethod.DELETE)
	public Msg deleteEmp(@PathVariable("ids")String ids){
		//����ɾ��
		if(ids.contains("-")){
			List<Integer> del_ids = new ArrayList<>();
			String[] str_ids = ids.split("-");
			//��װid�ļ���
			for (String string : str_ids) {
				del_ids.add(Integer.parseInt(string));
			}
			employeeService.deleteBatch(del_ids);
		}else{
			Integer id = Integer.parseInt(ids);
			employeeService.deleteEmp(id);
		}
		return Msg.success();
	}
	
	 /* 
	 * 
	 * ���������
	 * ����Ҫ��֧��ֱ�ӷ���PUT֮�������Ҫ��װ�������е�����
	 * 1��������HttpPutFormContentFilter��
	 * 2���������ã����������е����ݽ�����װ��һ��map��
	 * 3��request�����°�װ��request.getParameter()����д���ͻ���Լ���װ��map��ȡ����
	 * Ա�����·���
	 * @param employee
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/emp/{empId}",method=RequestMethod.PUT)
	public Msg saveEmp(Employee employee,HttpServletRequest request){
		System.out.println("�������е�ֵ��"+request.getParameter("gender"));
		System.out.println("��Ҫ���µ�Ա�����ݣ�"+employee);
		employeeService.updateEmp(employee);
		return Msg.success()	;
	}
	
	/**
	 * ����id��ѯԱ��
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/emp/{id}",method=RequestMethod.GET)
	@ResponseBody
	public Msg getEmp(@PathVariable("id")Integer id){
		
		Employee employee = employeeService.getEmp(id);
		return Msg.success().add("emp", employee);
	}
	
	/**
	 * ����û����Ƿ����
	 * @param empName
	 * @return
	 */
	@RequestMapping("/checkuser")
	@ResponseBody
	public Msg checkuser(@RequestParam("empName") String empName) {
		//���ж��û����Ƿ��ǺϷ��ı��ʽ;
		String regx = "(^[a-zA-Z0-9_-]{6,16}$)|(^[\u2E80-\u9FFF]{2,5})";
		if(!empName.matches(regx)){
			return Msg.fail().add("va_msg", "�û���������6-16λ���ֺ���ĸ����ϻ���2-5λ����");
		}
		boolean b = employeeService.checkUser(empName);
		if(b) {
			return Msg.success();
		}else {
			return Msg.fail().add("va_msg", "�û���������");
		}
	}
	
	/**
	 * �����û�
	 * @param employee
	 * @return
	 */
	@RequestMapping(value="/emp",method=RequestMethod.POST)
	@ResponseBody
	public Msg saveEmp(@Valid Employee employee,BindingResult result) {
		if(result.hasErrors()) {
			//У��ʧ�ܣ�Ӧ�÷���ʧ�ܣ���ģ̬������ʾУ��ʧ�ܵĴ�����Ϣ
			Map<String, Object> map = new HashMap<>();
			List<FieldError> errors = result.getFieldErrors();
			for (FieldError fieldError : errors) {
				System.out.println("������ֶ�����"+fieldError.getField());
				System.out.println("������Ϣ��"+fieldError.getDefaultMessage());
				map.put(fieldError.getField(), fieldError.getDefaultMessage());
			}
			return Msg.fail().add("errorFields", map);
		}else {
			employeeService.saveEmp(employee);
			return Msg.success();
		}
	}
	
	/**
	 * ʹ��ResponseBody��Ҫ����jackson��
	 * @param pn
	 * @param model
	 * @return
	 */
	@RequestMapping("/emps")
	@ResponseBody
	public Msg getEmpsWithJson(@RequestParam(value = "pn",defaultValue = "1") Integer pn,
			Model model) {
		//�ⲻ��һ����ҳ��ѯ
		//����PageHelper��ҳ���
		//�ڲ�ѯ֮ǰֻ��Ҫ���÷�ҳ��� ����ҳ�� �Լ�ÿҳ�Ĵ�С
		PageHelper.startPage(pn, 5);
		//startPage������Ĳ�ѯ����һ����ҳ��ѯ
		List<Employee> emps = employeeService.getAll();
		//ʹ��PageInfo��װ��ѯ��Ľ��,ֻ��Ҫ��pageInfo����ҳ��
		//PageInfo��װ����ϸ��Ϣ��������ѯ���������� ������ʾ5ҳ
		PageInfo page = new PageInfo(emps,5);
		return Msg.success().add("pageInfo", page);
	}
	/**
	 * ��ѯԱ������(��ҳ��ѯ)
	 * @return
	 */
	//@RequestMapping("/emps")
	public String getEmps(@RequestParam(value = "pn",defaultValue = "1") Integer pn,
			Model model) {
		//�ⲻ��һ����ҳ��ѯ
		//����PageHelper��ҳ���
		//�ڲ�ѯ֮ǰֻ��Ҫ���÷�ҳ��� ����ҳ�� �Լ�ÿҳ�Ĵ�С
		PageHelper.startPage(pn, 5);
		//startPage������Ĳ�ѯ����һ����ҳ��ѯ
		List<Employee> emps = employeeService.getAll();
		//ʹ��PageInfo��װ��ѯ��Ľ��,ֻ��Ҫ��pageInfo����ҳ��
		//PageInfo��װ����ϸ��Ϣ��������ѯ���������� ������ʾ5ҳ
		PageInfo page = new PageInfo(emps,5);
		model.addAttribute("pageInfo", page);
		return "list";
	}
}
