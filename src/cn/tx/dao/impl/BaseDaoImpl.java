package cn.tx.dao.impl;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.tx.dao.BaseDao;

public abstract class BaseDaoImpl<T, Q> extends HibernateDaoSupport implements BaseDao<T, Q> {

	@Override
	public void save(T t) {
		this.getHibernateTemplate().save(t);
	}

	@Override
	public void update(T t) {
		this.getHibernateTemplate().update(t);
	}

	@Override
	public T getObj(Integer id) {
		Class<?> class1 = getGenericClass();
		return  (T) this.getHibernateTemplate().get(class1, id);
	}

	@Override
	public void delete(Integer id) {
		T obj = getObj(id);
		this.getHibernateTemplate().delete(obj);
	}

	@Override
	public List<T> queryObjByCondition(final Q q, final List<String> exclude ) {
		@SuppressWarnings("unchecked")
		List<T> tList = this.getHibernateTemplate().executeFind(new HibernateCallback<List<T>>() {

			/**
			 * Session是spring开启的代理session，可以自动的开事务，提交事务和关闭session
			 */
			@Override
			public List<T> doInHibernate(Session session)
					throws HibernateException, SQLException {
				String hql = createHql(q);
				//创建查询对象
				Query query = session.createQuery(hql);
				setDynamicParam(query, q, exclude);
				//获得查询对象的类对象
				Class<? extends Object> class1 = q.getClass();
				Object object = null;
				try {
					//获得这个类对象的属性startNum
					Field startNumField = class1.getDeclaredField("startNum");
					//暴力破坏私有属性
					startNumField.setAccessible(true);
					//获得私有属性的值
					object = startNumField.get(q);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				List<T> list = (List<T>)query.setFirstResult((Integer)object).setMaxResults(5).list();
				
				return list;
			}
			
		});
		
		return tList;
	}
	
	public Long queryObjByConditionCount(final Q q, final List<String> exclude){
		
		Long totalCount = this.getHibernateTemplate().execute(new HibernateCallback<Long>() {

			@Override
			public Long doInHibernate(Session session) throws HibernateException,
					SQLException {
				
				String hql = createHqlCount(q);
				//创建查询对象
				Query query = session.createQuery(hql);
				setDynamicParam(query, q, exclude);
				Long count = (Long) query.uniqueResult();
				return count;
			}
			
			
		});
		
		return totalCount;
		
		
	}
	
	public Class<?> getGenericClass(){
		//获得泛型的父类
		Type genericSuperclass = this.getClass().getGenericSuperclass();
		//把泛型的父类做强制转换ParameterizedType
		ParameterizedType pt = (ParameterizedType) genericSuperclass;
		//根据ParameterizedType获得当前类的所有泛型的类型
		Type[] actualTypeArguments = pt.getActualTypeArguments();
		//获得T的具体类
		Class<?> class1 = (Class<?>) actualTypeArguments[0];
		return class1;
	}

	@Override
	public void delete(T t) {
		this.getHibernateTemplate().delete(t);
	}
	
	/**
	 * 创建hql
	 * @param q
	 * @return
	 */
	public abstract String createHql(Q q);
	
	
	public abstract String createHqlCount(Q q);
	
	
	public abstract String createHqlCondition(Q q);
	
	
	/**
	 * 动态设置参数
	 * @param query
	 * @param q
	 * exclude:排除某些属性
	 */
	public void setDynamicParam(Query query, Q q, List<String> exclude){
		Class<? extends Object> class1 = q.getClass();
		
		//反向解析查询对象，列出查询对象的所有属性
		Field[] fields = class1.getDeclaredFields();
		Field[] superFields = class1.getSuperclass().getDeclaredFields();
		List<Field> list1 = Arrays.asList(fields);
		List<Field> list2 = Arrays.asList(superFields);
		//创建一个大的集合，里面存储查询对象的属性对象和他父类的属性对象
		List<Field> fList = new ArrayList<Field>();
		fList.addAll(list1);
		fList.addAll(list2);
		
		//遍历集合
		for(Field f : fList){
			//获得属性的名字
			String fieldName = f.getName();
			if(exclude != null && exclude.contains(fieldName)){
				continue;
			}
			Object val = null;
			try {
				f.setAccessible(true);
				//获得属性的值
				val = f.get(q);
			} catch (IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}
			if(val != null){
				if(val.getClass() == String.class){
					if(StringUtils.isNotBlank(val.toString())){
						query.setParameter(fieldName, "%"+val+"%");
					}
					
				}else{
					query.setParameter(fieldName, val);
				}
			}
			
		}
		
	}

	
	public List<T> list(){
		Class<?> class1 = getGenericClass();
		//"from "注意中间有一个空格，不然会出现node to traverse cannot be null! 错误（from和表名之间要有空格）
		String hql = "from "+class1.getName();
		return this.getHibernateTemplate().find(hql);
	}
	
	@Override
	public List<T> queryObjByConditionNoPage(final Q q, final List<String> exclude ) {
		@SuppressWarnings("unchecked")
		List<T> tList = this.getHibernateTemplate().executeFind(new HibernateCallback<List<T>>() {

			/**
			 * Session是spring开启的代理session，可以自动的开事务，提交事务和关闭session
			 */
			@Override
			public List<T> doInHibernate(Session session)
					throws HibernateException, SQLException {
				String hql = createHql(q);
				//创建查询对象
				Query query = session.createQuery(hql);
				setDynamicParam(query, q, exclude);
				//获得查询对象的类对象
			
				List<T> list = (List<T>)query.list();
				
				return list;
			}
			
		});
		
		return tList;
	}
	
}
