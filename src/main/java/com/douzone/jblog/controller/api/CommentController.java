package com.douzone.jblog.controller.api;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.douzone.dto.JSONResult;
import com.douzone.jblog.service.CommentService;
import com.douzone.jblog.vo.CommentVo;

@Controller
@RequestMapping("/{id:(?!assets).*}/api")
public class CommentController {
	
	@Autowired
	private CommentService commentService;
	
	@ResponseBody
	@RequestMapping("/add/{postNo}/{content}")
	public JSONResult addComment(
		@PathVariable String id,
		CommentVo commentVo) {
		
//		commentVo.setContent(commentVo.getContent().replaceAll("\n", "<br />"));
		boolean result = commentService.addComment(commentVo);
		System.out.println("Insert Comment result : " + result);
		
		return JSONResult.success(result);
	}
	
	@ResponseBody
	@RequestMapping("/list/{postNo}")
	public JSONResult listComment(
		@PathVariable String id,
		@PathVariable Optional<Long> postNo) {
		System.out.println("1111");
		System.out.println("Comment List by postNo : " + postNo);
		List<CommentVo> list = commentService.getList(postNo.get());
		
		System.out.println(list);
		
		return JSONResult.success(list);
	}
}
