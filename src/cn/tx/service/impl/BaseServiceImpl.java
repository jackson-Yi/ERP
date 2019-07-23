package cn.tx.service.impl;

import java.lang.reflect.Field;
import java.util.List;

import cn.tx.dao.BaseDao;
import cn.tx.service.BaseService;
import cn.tx.utils.Page;

public class BaseServiceImpl<T, Q> implements BaseService<T, Q> {

	BaseDao<T, Q> baseDao;
	
	@Override
	public void save(T t) {
		baseDao.save(t);
	}

	@Override
	public void update(T t) {
		baseDao.update(t);
	}

	@Override
	public T getObj(Integer id) {
		return (T) baseDao.getObj(id);
	}

	@Override
	public void delete(Integer id) {
		baseDao.delete(id);
	}

	@Override
	public void delete(T t) {
		baseDao.delete(t);
	}

	

	@Override
	public List<T> list() {
		return baseDao.list();
	}

	@Override
	public Page queryObjByCondition(Q q, List<String> exclude) {
		//创建page对象
		Page page = new Page();
		//获得查询对象中的pageNo
		Class<? extends Object> class1 = q.getClass();
		try {
			Field field = class1.getDeclaredField("pageNo");
			//获得pageNo
			field.setAccessible(true);
			Integer pageNo = (Integer) field.get(q);
			//把pageNo设置给page对象
			page.setPageNo(pageNo);
			//计算出来开始行号
			int startNum = page.getStartNum();
			//给q查询对象设置startNum
			Field startNumField = class1.getDeclaredField("startNum");
			//获得pageNo
			startNumField.setAccessible(true);
			startNumField.set(q, startNum);
			//查询结果集
			List<T> list = baseDao.queryObjByCondition(q, exclude);
			//把结果集设置给page对象
			page.setList(list);
			//查询当前条件下的总记录数
			Long count = baseDao.queryObjByConditionCount(q, exclude);
			page.setTotalCount(new Integer(count+""));
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return page;
	}

	public List<T> queryObjByConditionNoPage(final Q q, final List<String> exclude ){
		return baseDao.queryObjByConditionNoPage(q, exclude);
	}

}
