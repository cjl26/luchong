package com.project.m.api.common.intf.resp;

import com.project.m.api.common.biz.resp.BaseResp;
import com.project.m.api.common.intf.req.InterfaceRequest;

public interface RespConvertor {

	InterfaceResp convert(InterfaceRequest request, BaseResp bizResp) throws Exception;
}
