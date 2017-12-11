package service;

import java.util.List;

import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Model.Employee;
import dao.EmployeeDao;

@Service
public class EmployeeService {
	
	@Autowired
	private EmployeeDao employeeDao;
	
	
	public List<Employee> findAllEmployees()
	{
		return employeeDao.getAllEmployees();
	}
	
	public void addEmployee(Employee employee)
	{
		employeeDao.insertEmployee(employee);
	}
	
	

}
