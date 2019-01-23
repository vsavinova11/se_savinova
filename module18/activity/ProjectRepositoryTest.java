package sef.module18.activity;

import junit.framework.AssertionFailedError;
import junit.framework.TestCase;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

public class ProjectRepositoryTest  extends TestCase {
    private ProjectRepository projectRepository;

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
        projectRepository = new ProjectRepositoryImpl(conn);
    }

    public void testFindProjectByID(){
        try {
            Project result = projectRepository.findProjectByID(1);
            assertEquals(result.getProjectID(), 1);
            assertEquals(result.getProjectName().toUpperCase(), "ONLINE INSURANCE SYSTEM");
        }catch(AssertionFailedError e){
            logger.error(sef.module.percentage.Percentage.setFailedCount(1, e.getMessage()));
            fail();
        }
        catch (HRSSystemException e) {
            e.printStackTrace();
        }
    }

    public void testFindProjectByName(){
        try {
            List<Project> results = projectRepository.findProjectByName("ONLINE INSURANCE SYSTEM");
            assertEquals(1, results.size());

            assertEquals(results.get(0).getProjectID(), 1);
            assertEquals(results.get(0).getProjectName().toUpperCase(), "ONLINE INSURANCE SYSTEM");

        } catch (HRSSystemException e) {
            e.printStackTrace();
        }
    }

    public void testFindProjectByEmployee(){
        try {
            List <Project>results = projectRepository.findProjectByEmployee(1);
            assertEquals(1, results.size());

            assertEquals(results.get(0).getProjectID(), 1);
            assertEquals(results.get(0).getProjectName().toUpperCase(), "ONLINE INSURANCE SYSTEM");

        } catch (HRSSystemException e) {
            e.printStackTrace();
        }
    }

    public void testInsertProject(){
        try {
            Project project = new ProjectImpl(0, "Altair system", "Employee time tracking");
            int result = projectRepository.insertProject(project);
            assertEquals(result, 5);

        } catch (HRSSystemException e) {
            System.out.println(e.getCause().getMessage());
            fail();
        }catch(AssertionFailedError e){
            logger.error(sef.module.percentage.Percentage.setFailedCount(1, e.getMessage()));
            fail();
        }

    }
    public void testUpdateProject(){
        try {
            Project project = new ProjectImpl(0, "Online-system Altair", "Employee time tracking");
            boolean result = projectRepository.updateProject(project);
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