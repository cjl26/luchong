package cloudPayAdmin.util.pagebean;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;

import cloudPayAdmin.util.WebUtil;

@Component
public class HqlPageBeanHelper {

	/**
	 * 
	 * @param em
	 * @param hqlParam
	 * @param clazz
	 * @return
	 */
	public <T> PageBean<T> findHqlPageBeanByNativeQuery(EntityManager em, HqlParam hqlParam, String countString) {
		Query queryTest = em.createNativeQuery(hqlParam.getHql(), hqlParam.getClazz());
		List<Object> params = hqlParam.getParams();
		for(int i=0;i<params.size();i++) {
			queryTest.setParameter(i+1, params.get(i));
		}
		
		@SuppressWarnings("unchecked")
		List<T> result = queryTest.setFirstResult(hqlParam.getStart()).setMaxResults(hqlParam.getPageSize()).getResultList();
		//System.out.println("queryTest.getResultList() = " + result);
		//System.out.println("result = " + JSONObject.toJSONString(result));		
		Long totalCount = getTotalCountBySql(em, countString);
		Integer totalPage = (int)(totalCount % hqlParam.getPageSize() == 0 ? totalCount / hqlParam.getPageSize() : (totalCount / hqlParam.getPageSize()) + 1);
		Integer pageNo = (hqlParam.getStart() + hqlParam.getPageSize()) / hqlParam.getPageSize();
		return new PageBean<T>(result, totalPage, totalCount, pageNo, hqlParam.getPageSize());
		
		//-------------------------
		
//		Query queryTest = em.createNativeQuery(hqlParam, TAdminMenu.class);
//		List<Object> params = hqlParam.getParams();
//		for(int i=0;i<params.size();i++) {
//			queryTest.setParameter(i+1, params.get(i));
//		}
//		
//		List<TAdminMenu> result = queryTest.setFirstResult(hqlParam.getStart()).setMaxResults(hqlParam.getPageSize()).getResultList();
//		System.out.println("queryTest.getResultList() = " + result);
//		System.out.println("result = " + JSONObject.toJSONString(result));		
//		Long totalCount = HqlPageBeanHelper.getTotalCountBySql(entityManager, countString);
//		Integer totalPage = (int)(totalCount % hqlParam.getPageSize() == 0 ? totalCount / hqlParam.getPageSize() : (totalCount / hqlParam.getPageSize()) + 1);
//		Integer pageNo = (hqlParam.getStart() + hqlParam.getPageSize()) / hqlParam.getPageSize();
//		PageBean<TAdminMenu> adminMenuPage = new PageBean<TAdminMenu>(result, totalPage, totalCount, pageNo, hqlParam.getPageSize());
	}

	/**
	 * 查找sql结果，并映射到bean
	 * 
	 * @param sql
	 *            sql语句
	 * @param pageNo
	 *            页数
	 * @param pageSize
	 *            一页大小
	 * @param clazz
	 *            结果映射的类
	 * @return
	 */
	public <T> PageBean<T> findHqlPageBean(EntityManager em, HqlParam hqlParam) {
		System.out.println("hqlParam.getHql() = " + hqlParam.getHql());
		System.out.println("hqlParam.getClazz() = " + hqlParam.getClazz());

		@SuppressWarnings("unchecked")
		TypedQuery<T> tq = em.createQuery(hqlParam.getHql(), hqlParam.getClazz());

		List<Object> params = hqlParam.getParams();
		for (int i = 0; i < params.size(); i++) {
			tq.setParameter(i + 1, params.get(i));
		}
		
		if(hqlParam.getStart() != null) {
			tq.setFirstResult(hqlParam.getStart());
		}
		
		if(hqlParam.getPageSize() != null) {
			tq.setMaxResults(hqlParam.getPageSize());
		}
		
		List<T> result = tq.getResultList();

		Long totalCount = getTotalCount(em, hqlParam);
		
		Integer totalPage = null;
		
		if(hqlParam.getPageSize() != null) {
			totalPage = (int) (totalCount % hqlParam.getPageSize() == 0 ? totalCount / hqlParam.getPageSize()
					: (totalCount / hqlParam.getPageSize()) + 1);
		} else {
			totalPage = 1;
		}
		
	/*	Integer totalPage = (int) (totalCount % hqlParam.getPageSize() == 0 ? totalCount / hqlParam.getPageSize()
				: (totalCount / hqlParam.getPageSize()) + 1);*/
		Integer pageNo = null;
		if(hqlParam.getStart() != null && hqlParam.getPageSize() != null) {
			pageNo = (hqlParam.getStart() + hqlParam.getPageSize()) / hqlParam.getPageSize();
		} else {
			pageNo = 1;
		}
		/*Integer pageNo = (hqlParam.getStart() + hqlParam.getPageSize()) / hqlParam.getPageSize();*/

		return new PageBean<T>(result, totalPage, totalCount, pageNo, hqlParam.getPageSize() == null ? Integer.MAX_VALUE : hqlParam.getPageSize());

	}

	/**
	 * 获得总记录数
	 * 
	 * @param em
	 * @param sql
	 * @return
	 */
	private Long getTotalCount(EntityManager em, HqlParam hqlParam) {
		String upperHql = StringUtils.replace(hqlParam.getHql(), "from", "FROM");
		String countHql = "select count(" + WebUtil.alias + ") " + StringUtils.substring(upperHql, StringUtils.indexOf(upperHql, "FROM"));
		
		if(StringUtils.containsIgnoreCase(countHql, "group by")) {
			countHql = StringUtils.substring(countHql, 0,StringUtils.indexOfIgnoreCase(countHql, "group by"));
		}
		
		if(StringUtils.containsIgnoreCase(countHql, "order by")) {
			countHql = StringUtils.substring(countHql, 0,StringUtils.indexOfIgnoreCase(countHql, "order by"));
		}
		
		TypedQuery<Long> tq = em.createQuery(countHql, Long.class);
		List<Object> params = hqlParam.getParams();
		for (int i = 0; i < params.size(); i++) {
			tq.setParameter(i + 1, params.get(i));
		}
		return tq.getSingleResult();
	}

	/**
	 * 通过sql来查总数
	 * 
	 * @param em
	 * @param sql
	 * @return
	 */
	private Long getTotalCountBySql(EntityManager em, String sql) {
		Query query = em.createNativeQuery(sql);
		if (query.getResultList() == null || query.getResultList().size() == 0) {
			return 0l;
		}
		return Long.parseLong(query.getResultList().get(0).toString());
	}
}
