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
	@RequestMapping(value="/list/{id}", method=RequestMethod.GET)
	public JSONResult list(
		@PathVariable String id ) {
		System.out.println("categorylist");
		List<CategoryVo> list = categoryService.getList(id);
		System.out.println(list);
		return JSONResult.success(list);
	}
	
	@ResponseBody
	@RequestMapping(value="/delete/{id}/{categoryNo}", method=RequestMethod.GET)
	public JSONResult delete(
		@PathVariable String id,
		@PathVariable long categoryNo) {
		System.out.println("id : " + id);
		boolean deleteIsPossible = categoryService.getRowCount(id);
		System.out.println("11111");
		if(!deleteIsPossible) {
			System.out.println("카테고리는 최소한 한개 이상 존재해야한다. fail");
			return JSONResult.success("fail");
		}
		System.out.println("22222");
		boolean childIsTrue = categoryService.getCategoryIsChild(categoryNo);
		System.out.println("33333");
		if(childIsTrue) {
			System.out.println("해당 카테고리에 포스트가 하나이상 존재하여 해당 카테고리를 삭제할 수 없습니다.  ischild");
			return JSONResult.success("ischild");
		}
		
		System.out.println("형이 여기왜있어....");
		
		boolean result = categoryService.deleteRow(categoryNo);
		System.out.println("category delete result : " + result);
		return JSONResult.success(result);
	}
}
