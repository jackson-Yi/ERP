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
import cn.tx.model.ProductType;
import cn.tx.query.DepQuery;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:ApplicationContext.xml"})
public class ProductTypeServiceTest {


	@Autowired
	private ProductTypeService productTypeService;

	@Test
	public void testSaveDep() {
		ProductType pt = new ProductType();
		pt.setSupplierId(1);
		pt.setName("篮球");
		productTypeService.save(pt);
		
		
	}

	@Test
	public void testUpdateDep() {
		
	}

	@Test
	public void testGetDep() {
		ProductType obj = productTypeService.getObj(7);
		System.out.println(obj);
	}

	@Test
	public void testDeleteDep() {
	}

	@Test
	public void testQueryDepByCondition() {
		
		
	}

}
