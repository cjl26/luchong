package cloudPayAdmin.admin.controller;
//cloudpay/admin/toMainPage

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
//@RequestMapping("/cloudpay")
@RequestMapping("/hicatcard")
public class IndexController {
	
	/**
	 * 跳转到主页
	 * @return
	 */
	@RequestMapping("")
	public String toIndex(){
		return "redirect:/cloudpay/admin/toMainPage";
	}
}
