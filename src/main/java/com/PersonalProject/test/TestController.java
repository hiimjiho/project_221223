package com.PersonalProject.test;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {
//	@Autowired
//	private PostBO postBO;
	
	
	@ResponseBody
	@RequestMapping("/test1")
	public String helloWorld() {
		return "Hello world!";
	}

	@ResponseBody
	@RequestMapping("/test2")
		public Map<String, Object> test2(){
			Map<String, Object> map = new HashMap<>();
			map.put("a", 1);
			map.put("b", 2);
			map.put("c", 3);
			
			return map;
	}
	
//	@ResponseBody
//	@RequestMapping("/test3")
//	public List<Post> test3(){
//		return postBO.getPostList();
//	}
	
	
	@RequestMapping("/summernote")
	public String summerNote() {
		
		return "test/summerTest";
	}
}
