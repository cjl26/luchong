package com.project.m.api.common.intf.req;

public interface RequestConvertor<T> {

	InterfaceRequest convert(T request) throws Exception;

}
