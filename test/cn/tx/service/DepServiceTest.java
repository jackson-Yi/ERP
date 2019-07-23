package cn.tx.service;

import static org.junit.Assert.fail;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.tx.model.Dep;
import cn.tx.query.DepQuery;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:ApplicationContext.xml"})
public class DepServiceTest {


	@Autowired
	private DepService depService;

	@Test
	public void testSaveDep() {
		Dep dep = new Dep();
		dep.setName("测试部");
		dep.setTel("010-7777777");
		depService.save(dep);
		
		
	}

	@Test
	public void testUpdateDep() {
		
	}

	@Test
	public void testGetDep() {
		Dep dep = depService.getObj(9);
		System.out.println(dep);
	}

	@Test
	public void testDeleteDep() {
	}

	@Test
	public void testQueryDepByCondition() {
		DepQuery dq = new DepQuery();
		dq.setName("开");
		
		
	}

}
