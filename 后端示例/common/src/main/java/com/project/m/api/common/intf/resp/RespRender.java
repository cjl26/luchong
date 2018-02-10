package com.project.m.api.common.intf.resp;

public interface RespRender<T> {

	void render(T output, InterfaceResp ir) throws Exception;
}
