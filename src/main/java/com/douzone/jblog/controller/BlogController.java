package com.douzone.jblog.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.douzone.jblog.service.BlogService;
import com.douzone.jblog.service.CategoryService;
import com.douzone.jblog.service.PostService;

@Controller
@RequestMapping("/{id:(?!assets).*}")
public class BlogController {

	@Autowired
	private BlogService blogService;
	@Autowired
	private PostService postService;
	@Autowired
	private CategoryService categoryService;
	
	@RequestMapping({"", "/{categoryPath}", "/{categoryPath}/{postPath}"})
	public String index(
		@PathVariable String id,
		@PathVariable Optional<Long> categoryPath,
		@PathVariable Optional<Long> postPath,
		ModelMap modelMap) {
		
		Long categoryNo = categoryPath.isPresent() ? categoryPath.get() : categoryService.getBasicNo(id);
		Long postNo = postPath.isPresent() ? postPath.get() : postService.getBasicNo(categoryNo);
		
		System.out.println("categoryNo : " + categoryNo);
		System.out.println("postNo : " + postNo);
		
		modelMap.putAll(blogService.getAll(id, categoryNo, postNo));
		
		return "blog/blog-main";
	}
	
}
