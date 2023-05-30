package com.PersonalProject.post;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.PersonalProject.post.bo.PostBO;
import com.PersonalProject.post.model.Paging;
import com.PersonalProject.post.model.Post;
import com.PersonalProject.post.model.PostView;
import com.PersonalProject.postComment.bo.PostCommentBO;
import com.PersonalProject.postComment.model.PostCommentView;
@RequestMapping("/post")
@Controller
public class PostController {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private PostBO postBO;
	
	@Autowired
	private PostCommentBO postCommentBO;
	
	@GetMapping("/list_view")
	public String postListView(
			Model model,
			HttpSession session,
			@RequestParam(value="page", required=false, defaultValue="1") int page) {
		
		Integer userId = (Integer)session.getAttribute("userId");
		List<PostView> postList = postBO.generatePostList(page, userId);
		Paging paging = postBO.pagingParam(page);
		
		model.addAttribute("postList", postList);
		model.addAttribute("paging", paging);
		model.addAttribute("view", "post/postListView");
		System.out.println("page=" + page);
		System.out.println("postList=" + postList);
		return "template/layout";
	}
	
	@GetMapping("/create_view")
	public String postCreateView(Model model) {
		
		model.addAttribute("view", "post/postCreate");
		return "template/layout";
	}
	
	@GetMapping("/detail_view")
	public String postDetailView(Model model,
			@RequestParam("postId") int postId) {
		
		List<PostCommentView> postCommentList = postCommentBO.generatePostCommentByPostId(postId);
		PostView postView = postBO.generatePostViewByPostId(postId);
		model.addAttribute("postView", postView);
		model.addAttribute("postCommentList", postCommentList);
		model.addAttribute("view", "post/postDetail");
		return "template/layout";
	}
	
	@GetMapping("/update_view")
	public String postUpdateView(Model model,
			HttpSession session,
			@RequestParam("postId") int postId) {
		int userId = (int)session.getAttribute("userId");
		Post post = postBO.getPostByPostIdUserId(postId, userId);
		model.addAttribute("post", post);
		model.addAttribute("view", "post/postUpdate");
		return "template/layout";
	}
	
}
