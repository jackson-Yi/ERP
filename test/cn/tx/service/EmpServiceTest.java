package cn.tx.service;

import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.tx.model.Emp;
import cn.tx.query.EmpQuery;
import cn.tx.utils.Page;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:ApplicationContext.xml"})
public class EmpServiceTest {


	@Autowired
	private EmpService empService;

	@Test
	public void testSaveEmp() {
		Emp emp = new Emp();
		emp.setAddress("北京");
		emp.setBirthday(new Date());
		emp.setEmail("renliangge@126.com");
		emp.setGender(1);
		emp.setName("任亮");
		emp.setPassword("123");
		emp.setTel("9999");
		emp.setUsername("renliang");
		empService.save(emp);
		
	}

	@Test
	public void testUpdateEmp() {
		Emp emp = empService.getObj(9);
		emp.setUsername("rl");
		empService.update(emp);
	}

	@Test
	public void testGetEmp() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteEmp() {
		empService.delete(10);
	}

	@Test
	public void testQueryEmpByCondition() {
		EmpQuery empQuery = new EmpQuery();
		empQuery.setPageNo(2);
		/*empQuery.setUsername("a");
		empQuery.setGender(1);*/
		List<String> exclude = new ArrayList<String>();
		exclude.add("pageNo");
		exclude.add("startNum");
		
		
		Page page = empService.queryObjByCondition(empQuery, exclude);
		System.out.println(page);
		
	}

}
