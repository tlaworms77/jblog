package com.douzone.jblog.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.douzone.dto.JSONResult;
import com.douzone.jblog.service.CategoryService;
import com.douzone.jblog.service.PostService;
import com.douzone.jblog.vo.CategoryVo;

@Controller("categoryApiController")
@RequestMapping("/category/api")
public class CategoryController {
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private PostService postService;
	
	@ResponseBody
	@RequestMapping(value="/add/{name}/{description}/{userNo}", method=RequestMethod.GET)
	public JSONResult add(
		CategoryVo vo) {
		
		categoryService.add(vo);
		vo = categoryService.getLastInsert();
		int postAmount = postService.getAmountByCategoryNo(vo.getNo());
		vo.setPostAmount(postAmount);
		return JSONResult.success(vo);
	}
	
	@ResponseBody
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public JSONResult list() {
		List<CategoryVo> list = categoryService.getList();
		System.out.println(list);
		return JSONResult.success(list);
	}
	
	@ResponseBody
	@RequestMapping(value="/delete/{no}", method=RequestMethod.GET)
	public JSONResult delete(@PathVariable long no) {
		System.out.println("no : " + no);
		boolean result = categoryService.deleteRow(no);
		System.out.println("category delete result : " + result);
		return JSONResult.success(result);
	}
}
