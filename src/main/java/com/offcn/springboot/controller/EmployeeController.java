package com.offcn.springboot.controller;

import com.offcn.springboot.dao.DepartmentDao;
import com.offcn.springboot.dao.EmployeeDao;
import com.offcn.springboot.entities.Department;
import com.offcn.springboot.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@Controller
public class EmployeeController {

    @Autowired
    EmployeeDao employeeDao;

    @Autowired
    DepartmentDao departmentDao;

    @GetMapping("/emps")
    public String empList(Model model){
        Collection<Employee> list = employeeDao.getAll();
        model.addAttribute("emps",list);
        return "emp/list";
    }

    @GetMapping("/emp")
    public String addEmp(Model model){
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("departments",departments);
        return "emp/add";
    }

    @PostMapping("/emp")
    public String saveEmp(Employee employee){
        System.out.println(employee.toString());
        employeeDao.save(employee);
        return "redirect:/emps";
    }

    @GetMapping("/emp/{id}")
    public String updateEmp(@PathVariable("id") Integer id,Model model){
        //根据id查询用户
        Employee employee = employeeDao.get(id);
        model.addAttribute("emp",employee);
        //显示所有的部门列表
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("departments",departments);
        return "emp/update";
    }
    @PutMapping("/emp")
    public String updateSaveEmp(Employee employee){
        System.out.println(employee);
        employeeDao.save(employee);
        return "redirect:/emps";
    }

    @DeleteMapping("/emp/{id}")
    public String deleteEmp(@PathVariable("id") Integer id){
        employeeDao.delete(id);
        System.out.println("删除成功");
        return "redirect:/emps";
    }
}
