package com.gzyct.m.api.busi.util;

import java.util.Date;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;

import org.springframework.data.domain.Pageable;

/**
 * 对普通的sql语句添加分页功能
 * 
 * @author wenxiao
 *
 */
public class SqlUtil {

	// 条件输入的关键字
	public static String CONDITION_KEY_INPUT_PROPERTY = "property"; // 属性
	public static String CONDITION_KEY_INPUT_COMPARISON = "comparison"; // 比较条件
	public static String CONDITION_KEY_INPUT_VALUE = "value"; // 查询的值

	// 比较条件
	public static String CONDITION_KEY_CONPARISON_EQUAL = "eq"; // 相等
	public static String CONDITION_KEY_CONPARISON_GREATER_AND_EQUAL = "ge"; // 大于等于
	public static String CONDITION_KEY_CONPARISON_LESS_AND_EQUAL = "le"; // 小于等于
	public static String CONDITION_KEY_CONPARISON_GREATER_THAN = "gt"; // 大于
	public static String CONDITION_KEY_CONPARISON_LESS_THAN = "lt"; // 小于
	public static String CONDITION_KEY_CONPARISON_LIKE = "lk"; // 相似
	
	// 通过传入的map 数据获取类型为date 的 Predicate - date 类型暂时不支持 like
	public static Predicate getDatePredicateByConditionMap(CriteriaBuilder criteriaBuilder, Expression<Date> expresson,
			String comparisonStr, Date dateObj) {

		Predicate predicate = null;
		// 根据 comparisonStr 获取 predicate
		if (comparisonStr.equalsIgnoreCase(CONDITION_KEY_CONPARISON_EQUAL)) {
			predicate = criteriaBuilder.equal(expresson, dateObj);
		} else if (comparisonStr.equalsIgnoreCase(CONDITION_KEY_CONPARISON_GREATER_AND_EQUAL)) {
			predicate = criteriaBuilder.greaterThanOrEqualTo(expresson, dateObj);
		} else if (comparisonStr.equalsIgnoreCase(CONDITION_KEY_CONPARISON_LESS_AND_EQUAL)) {
			predicate = criteriaBuilder.lessThanOrEqualTo(expresson, dateObj);
		} else if (comparisonStr.equalsIgnoreCase(CONDITION_KEY_CONPARISON_GREATER_THAN)) {
			predicate = criteriaBuilder.greaterThan(expresson, dateObj);
		} else if (comparisonStr.equalsIgnoreCase(CONDITION_KEY_CONPARISON_LESS_THAN)) {
			predicate = criteriaBuilder.lessThan(expresson, dateObj);
		}

		return predicate;
	}
	
	// 通过传入的map 数据获取类型为string 的 Predicate
	public static Predicate getStringPredicateByConditionMap(CriteriaBuilder criteriaBuilder,
			Expression<String> expresson, String comparisonStr, String stringObj) {

		Predicate predicate = null;
		// 根据 comparisonStr 获取 predicate
		if (comparisonStr.equalsIgnoreCase(CONDITION_KEY_CONPARISON_EQUAL)) {
			predicate = criteriaBuilder.equal(expresson, stringObj);
		} else if (comparisonStr.equalsIgnoreCase(CONDITION_KEY_CONPARISON_GREATER_AND_EQUAL)) {
			predicate = criteriaBuilder.greaterThanOrEqualTo(expresson, stringObj);
		} else if (comparisonStr.equalsIgnoreCase(CONDITION_KEY_CONPARISON_LESS_AND_EQUAL)) {
			predicate = criteriaBuilder.lessThanOrEqualTo(expresson, stringObj);
		} else if (comparisonStr.equalsIgnoreCase(CONDITION_KEY_CONPARISON_GREATER_THAN)) {
			predicate = criteriaBuilder.greaterThan(expresson, stringObj);
		} else if (comparisonStr.equalsIgnoreCase(CONDITION_KEY_CONPARISON_LESS_THAN)) {
			predicate = criteriaBuilder.lessThan(expresson, stringObj);
		} else if (comparisonStr.equalsIgnoreCase(CONDITION_KEY_CONPARISON_LIKE)) {
			predicate = criteriaBuilder.like(expresson, "%" + stringObj + "%");
		}
		return predicate;
	}
	
	
	public static String getPageSql(String sql, Pageable pageable) {

		Integer from = pageable.getPageNumber() * pageable.getPageSize();
		Integer end = from + pageable.getPageSize();

		StringBuilder sb = new StringBuilder();
		/*
		 * sb.append("SELECT * FROM (SELECT A.*, ROWNUM RN  FROM (");
		 * sb.append(sql); sb.append(") A )WHERE RN BETWEEN ");
		 * sb.append(String.valueOf(from+1)); sb.append(" AND ");
		 * sb.append(String.valueOf(end));
		 */
		sb.append("SELECT * FROM (SELECT A.*, ROWNUM RN FROM (");
		sb.append(sql);
		sb.append(") A WHERE ROWNUM <= ");
		sb.append(String.valueOf(end));
		sb.append(" ) WHERE RN >= ");
		sb.append(String.valueOf(from + 1));

		return sb.toString();
	}

}
