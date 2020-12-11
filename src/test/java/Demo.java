import com.hy.ssm.pojo.Emp;
import com.hy.ssm.service.EmpService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:Spring.xml"})
public class Demo {

    @Autowired
    private EmpService empService;

    @Test
    public void demo1() {
       // empService.queryPage(1, new Emp());
    }
}
