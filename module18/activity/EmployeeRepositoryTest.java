package sef.module18.activity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import junit.framework.AssertionFailedError;
import junit.framework.TestCase;


public class EmployeeRepositoryTest extends TestCase  {
    private EmployeeRepository employeeRepository;

    private Connection conn;
    private String url ;
    private String username;
    private String password;
    Log logger = LogFactory.getLog(this.getClass());

    protected void setUp() throws Exception {
        super.setUp();
        username = "sa";
        password = "";
        Class.forName("org.h2.Driver");
        url = "jdbc:h2:~/test";
        conn = DriverManager.getConnection(url, username, password);
        conn.setAutoCommit(false);
        System.out.println("Connection successfully established!");
        employeeRepository = new EmployeeRepositoryImpl(conn);
    }

    public void testFindEmployeeByID(){
        try {
            Employee result = employeeRepository.findEmployeeByID(1);
            assertEquals(result.getFirstName().toUpperCase(), "JOHN");
            assertEquals(result.getLastName().toUpperCase(), "DOE");
            assertEquals(result.getProfLevel(), 1);
        }catch(AssertionFailedError e){
            logger.error(sef.module.percentage.Percentage.setFailedCount(1, e.getMessage()));
            fail();
        }
        catch (HRSSystemException e) {
            e.printStackTrace();
        }
    }

    public void testFindEmployeeByName(){
        try {
            List <Employee>results = employeeRepository.findEmployeeByName("J", "DOE");
            assertEquals(2, results.size());

            assertEquals(results.get(0).getFirstName().toUpperCase(), "JOHN");
            assertEquals(results.get(0).getLastName().toUpperCase(), "DOE");
            assertEquals(results.get(0).getProfLevel(), 1);


            assertEquals(results.get(1).getFirstName().toUpperCase(), "JANE");
            assertEquals(results.get(1).getLastName().toUpperCase(), "DOE");
            assertEquals(results.get(1).getProfLevel(),2) ;

        } catch (HRSSystemException e) {
            e.printStackTrace();
        }
    }

    public void testFindEmployeeByProfLevel(){
        try {
            List <Employee>results = employeeRepository.findEmployeeByProfLevel(1);
            assertEquals(2, results.size());

            assertEquals(results.get(0).getFirstName().toUpperCase(), "JOHN");
            assertEquals(results.get(0).getLastName().toUpperCase(), "DOE");
            assertEquals(results.get(0).getProfLevel(), 1);


            assertEquals(results.get(1).getFirstName().toUpperCase(), "SCOTT");
            assertEquals(results.get(1).getLastName().toUpperCase(), "FEIST");
            assertEquals(results.get(1).getProfLevel(),1) ;

        } catch (HRSSystemException e) {
            e.printStackTrace();
        }
    }
    public void testFindEmployeeByProject(){
        try {
            List <Employee>results = employeeRepository.findEmployeeByProject(1);
            assertEquals(1, results.size());

            assertEquals(results.get(0).getFirstName().toUpperCase(), "JOHN");
            assertEquals(results.get(0).getLastName().toUpperCase(), "DOE");
            assertEquals(results.get(0).getProfLevel(), 1);

        } catch (HRSSystemException e) {
            e.printStackTrace();
        }
    }

    public void testInsertEmployee(){
        try {
            Employee employee = new EmployeeImpl(0, "Jack", "Bauer", 3);
            int result = employeeRepository.insertEmployee(employee);
            assertEquals(result, 6);

        } catch (HRSSystemException e) {
            System.out.println(e.getCause().getMessage());
            fail();
        }catch(AssertionFailedError e){
            logger.error(sef.module.percentage.Percentage.setFailedCount(1, e.getMessage()));
            fail();
        }

    }
    public void testUpdateEmployee(){
        try {
            Employee employee = new EmployeeImpl(3, "Jack", "Bauer", 3);
            boolean result = employeeRepository.updateEmployee(employee);
            assertTrue(result);
        } catch (HRSSystemException e) {
            System.out.println(e.getCause().getMessage());
            fail();
        }catch(AssertionFailedError e){
            logger.error(sef.module.percentage.Percentage.setFailedCount(1, e.getMessage()));
            fail();
        }

    }

    protected void tearDown() throws Exception {
        try{
            super.tearDown();
            conn.close();
            System.out.println("Connection closed!");
        }catch(AssertionFailedError e){
            logger.error(sef.module.percentage.Percentage.setFailedCount(1, e.getMessage()));
            logger.error(sef.module.percentage.Percentage.setFailedCount(true,4));
        }
    }

}