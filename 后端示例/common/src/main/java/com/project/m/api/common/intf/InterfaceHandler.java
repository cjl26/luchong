package com.project.m.api.common.intf;

import com.project.m.api.common.intf.req.InterfaceRequest;
import com.project.m.api.common.intf.resp.InterfaceResp;

public interface InterfaceHandler {

	InterfaceResp handle(InterfaceRequest ir) throws Exception;

}
