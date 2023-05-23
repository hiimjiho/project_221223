package com.PersonalProject.post;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.PersonalProject.post.bo.PostBO;
import com.PersonalProject.post.model.Post;
import com.PersonalProject.post.model.PostView;
import com.PersonalProject.postComment.bo.PostCommentBO;
import com.PersonalProject.postComment.model.PostCommentView;
@RequestMapping("/post")
@Controller
public class PostController {
	@Autowired
	private PostBO postBO;
	
	@Autowired
	private PostCommentBO postCommentBO;
	
	@GetMapping("/list_view")
	public String postListView(Model model) {
		List<PostView> postList = postBO.generatePostList();
		
		model.addAttribute("postList", postList);
		model.addAttribute("view", "post/postListView");
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
