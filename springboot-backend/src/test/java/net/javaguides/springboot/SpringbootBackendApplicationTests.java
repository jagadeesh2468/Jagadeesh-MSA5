package net.javaguides.springboot;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import com.mindtree.employeemanagerapp.EmployeeManagementApplication;
import com.mindtree.employeemanagerapp.model.Employee;
import com.mindtree.employeemanagerapp.repository.EmployeeRepository;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;


@SpringBootTest(classes=EmployeeManagementApplication.class)
@RunWith(SpringRunner.class)
class SpringbootBackendApplicationTests {
	    @Autowired
	    private EmployeeRepository employeeRepository;
	  

	    //JUnit test for saveEmployee
	    @Test
	    @Rollback(value = false)
	    @DirtiesContext 
	    public void saveEmployeeTest(){
            Employee employee=new Employee("glen","plesis","glenn@gmail.com");
	        employeeRepository.save(employee);

	        Assertions.assertThat(employee.getId()).isGreaterThan(0);
	    }

	    //JUnit test for getEmployeeById
	    @Test
	    @DirtiesContext 
	    public void getEmployeeTest(){

	        Employee employee = employeeRepository.findById(2L).get();

	        Assertions.assertThat(employee.getId()).isEqualTo(2L);

	    }
        //JUnit test for getListofEmployees
	    @Test
	    public void getListOfEmployeesTest(){

	        List<Employee> employees = employeeRepository.findAll();

	        Assertions.assertThat(employees.size()).isGreaterThan(0);

	    }
	    
	    //JUnit test for updateEmployee

	    @Test
	    @Rollback(value = false)
	    public void updateEmployeeTest(){

	        Employee employee = employeeRepository.findById(26L).get();

	        employee.setEmailId("praveen@gmail.com");

	        Employee employeeUpdated =  employeeRepository.save(employee);

	        Assertions.assertThat(employeeUpdated.getEmailId()).isEqualTo("praveen@gmail.com");

	    }
	    //JUnit test for deleteEmployee
	    @Test
	    @Rollback(value = false)
	    public void deleteEmployeeTest(){

	        Employee employee = employeeRepository.findById(20L).get();

	        employeeRepository.delete(employee);
	        Employee employee1 = null;

	        Optional<Employee> optionalEmployee = employeeRepository.findByEmailId("ramceagen@gmail.com");

	        if(optionalEmployee.isPresent()){
	            employee1 = optionalEmployee.get();
	        }

	        Assertions.assertThat(employee1).isNull();
	    }


	

}



	    

	  


	


