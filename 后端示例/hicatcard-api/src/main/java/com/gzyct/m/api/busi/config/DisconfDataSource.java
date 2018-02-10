package com.gzyct.m.api.busi.config;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class DisconfDataSource extends AbstractRoutingDataSource {

	@Override
	protected Object determineCurrentLookupKey() {
		return "TARGET";
	}

}
