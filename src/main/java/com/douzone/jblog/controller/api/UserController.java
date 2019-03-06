package com.douzone.jblog.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.douzone.dto.JSONResult;
import com.douzone.jblog.service.UserService;

@Controller("userApiController")
@RequestMapping("/user/api")
public class UserController {
	@Autowired
	private UserService userService;

	@ResponseBody
	@RequestMapping(value = "/join/{blogId}", method = RequestMethod.GET)
	public JSONResult checkId(@PathVariable String blogId ) {
		boolean exist = userService.checkId(blogId);
		System.out.println("Id exist : " + exist);
		return JSONResult.success(exist);
	}
}
