package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

import model.Employee;

@Component
@Transactional
public class EmployeeDao {
	
	@PersistenceContext
	private EntityManager manager;
	
	public List<Employee> getAllEmployees()
	{

		try {
			TypedQuery<Employee> query = manager.createQuery("SELECT e FROM Employee AS e",Employee.class);
			if(query.getResultList()!=null)
			{
				List<Employee> tmp = query.getResultList();	
				return tmp;
			}

		}
		catch(Exception exc)
		{
			System.out.println("Fatal " + exc.getMessage());
			return null;
		}
		return null;
		
		

	}
	public void insertEmployee(Employee employee)
	{
		manager.persist(employee);
	}

}
