package com.douzone.jblog.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.douzone.jblog.service.BlogService;
import com.douzone.jblog.service.CategoryService;
import com.douzone.jblog.service.FileuploadService;
import com.douzone.jblog.service.PostService;
import com.douzone.jblog.vo.BlogVo;
import com.douzone.jblog.vo.CategoryVo;
import com.douzone.jblog.vo.PostVo;
import com.douzone.jblog.vo.UserVo;
import com.douzone.security.Auth;
import com.douzone.security.AuthUser;

@Auth
@Controller
@RequestMapping("/{id}/admin")
public class BlogAdminController {
	
	@Autowired
	private BlogService blogService;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private PostService postService;
	@Autowired
	private FileuploadService fileuploadService;
	
	@RequestMapping(value="/basic", method=RequestMethod.GET)
	public String basic(
		@AuthUser UserVo userVo, 
		Model model) {
		System.out.println("1111");
		model.addAttribute("blogVo", blogService.getBlogInfo(userVo.getNo()));
		System.out.println("2222");
		return "blog/blog-admin-basic";
	}
	
	@RequestMapping(value="/basic/update", method=RequestMethod.POST)
	public String basicUpdate(
		@ModelAttribute BlogVo blogVo,
		@RequestParam(value="upload-logo") MultipartFile multipartFile) {

		// image serverHD save & get local image path logic 
		String imageUrl = fileuploadService.restore(multipartFile);
		System.out.println(imageUrl);
		blogVo.setLogo(imageUrl);
		
		// dao -> blogvo insert logic
		boolean result = blogService.update(blogVo);
		System.out.println("BlogAdminController's basicUpdate <result : " + result + ">");
		
		return "redirect:/{id}/admin/basic";
	}
	
	@RequestMapping(value="/category", method=RequestMethod.GET)
	public String category(
		@AuthUser UserVo userVo, 
		Model model) {
		
		return "blog/blog-admin-category";
	}
	
	@RequestMapping(value="/write", method=RequestMethod.GET)
	public String write(
		@AuthUser UserVo authUser,
		Model model) {
		List<CategoryVo> list = new ArrayList<CategoryVo>();
		list = categoryService.getNames(authUser.getNo());
		model.addAttribute("list", list);
		
		return "blog/blog-admin-write";
	}
	
	@RequestMapping(value="/write", method=RequestMethod.POST)
	public String write(
		@ModelAttribute PostVo postVo,
		@RequestParam long category) {
		
		postService.write(postVo, category);
		
		return "redirect:/{id}/admin/write";
	}
	
}
