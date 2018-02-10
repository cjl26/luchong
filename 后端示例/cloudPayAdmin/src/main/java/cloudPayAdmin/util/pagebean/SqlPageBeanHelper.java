package cloudPayAdmin.util.pagebean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;

/**
 * jpa 执行sql的帮助类
 * @author hyj
 *
 */
@Component
public class SqlPageBeanHelper {
	
	/**
	 * 查找sql结果，并映射到bean
	 * @param sql    sql语句
	 * @param pageNo  页数
	 * @param pageSize  一页大小  
	 * @param clazz  结果映射的类
	 * @return
	 */
	public <T> PageBean<T> findSqlPageBean(EntityManager em , String sql , Integer pageNo , Integer pageSize , Class<T> clazz) {
		Query query = em.createNativeQuery(sql).setFirstResult((pageNo - 1) * pageSize).setMaxResults(pageSize);
		
		//如果映射类是 Map	
		query.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
	
		//映射后的结果集
		List<T> content = new ArrayList<T>();
		
		//查询的结果集
		List rows = query.getResultList();
		
		for(Object obj : rows) {
			T row = null;
			if(clazz == Map.class) {
				 row = (T)obj;
			} else {
				Map map = (Map)obj;
				row = JSONObject.parseObject(JSONObject.toJSONString(map), clazz); 
			}
			content.add(row);
		}
		
		Long totalCount = getTotalCount(em,sql);
		
		Integer totalPage = (int)(totalCount % pageSize == 0 ? totalCount / pageSize : (totalCount / pageSize) + 1);
			
		return new PageBean<T>(content, totalPage, totalCount, pageNo, pageSize);
	}
	
	/**
	 * 查找sql结果，并映射到bean 
	 * @param sql    sql语句
	 * @param pageNo  页数
	 * @param pageSize  一页大小  
	 * @param clazz  结果映射的类
	 * @param params  占位符参数
	 * @return
	 */
	public <T> PageBean<T> findSqlPageBean(EntityManager em , String sql , Integer pageNo , Integer pageSize , Class<T> clazz , Object... params) {
		Query query = em.createNativeQuery(sql).setFirstResult((pageNo - 1) * pageSize).setMaxResults(pageSize);
		
		for(int i=0;i<params.length;i++) {
			query.setParameter(i+1, params[i]);
		}
		
		//如果映射类是 Map
		query.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		
		//映射后的结果集
		List<T> content = new ArrayList<T>();
		
		//查询的结果集
		List rows = query.getResultList();
		
		for(Object obj : rows) {
			T row = null;
			if(clazz == Map.class) {
				 row = (T)obj;
			} else {
				Map map = (Map)obj;
				row = JSONObject.parseObject(JSONObject.toJSONString(map), clazz); 
			}
			content.add(row);
		}
		
		Long totalCount = getTotalCount(em,sql,params);
		
		Integer totalPage = (int)(totalCount % pageSize == 0 ? totalCount / pageSize : (totalCount / pageSize) + 1);
			
		return new PageBean<T>(content, totalPage, totalCount, pageNo, pageSize);
	}
	
	
	
	/**
	 * 获得总记录数
	 * @param em
	 * @param sql
	 * @return
	 */
	private Long getTotalCount( EntityManager em,String sql) {
		String upperSql = StringUtils.replace(sql, "from", "FROM");
		String countSql = "select count(*) count " + StringUtils.substring(upperSql, StringUtils.indexOf(upperSql, "FROM"));
		Query query = em.createNativeQuery(countSql);
		query.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		Map map = (Map)query.getSingleResult();
		Long count = MapUtils.getLong(map, "COUNT");
		return count;
	}
	
	/**
	 * 获得总记录数
	 * @param em   传入的  EntityManager
	 * @param sql  sql语句
	 * @param params 占位符参数
	 * @return
	 */
	private Long getTotalCount( EntityManager em,String sql,Object... params) {
		String upperSql = StringUtils.replace(sql, "from", "FROM");
		String countSql = "select count(*) count " + StringUtils.substring(upperSql, StringUtils.indexOf(upperSql, "FROM"));		
		Query query = em.createNativeQuery(countSql);
		
		for(int i=0;i<params.length;i++) {
			query.setParameter(i+1, params[i]);
		}
		query.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		Map map = (Map)query.getSingleResult();
		Long count = MapUtils.getLong(map, "COUNT");
		return count;
	}
	
}
