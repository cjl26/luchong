package com.project.m.api.common.biz.req;

import com.project.m.api.common.intf.req.InterfaceRequest;

public interface BizRequestParser<T extends BizRequest> {

	BizRequest parse(InterfaceRequest ir) throws Exception;

}
