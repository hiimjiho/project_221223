package com.PersonalProject.postComment;

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
import com.PersonalProject.postComment.bo.PostCommentBO;
import com.PersonalProject.postComment.model.PostComment;
@RequestMapping("/post_comment")
@Controller
public class PostCommentController {
	@Autowired
	private PostBO postBO;
	
	@Autowired
	private PostCommentBO postCommentBO;
	
	@GetMapping("/update_view")
	public String postCommentUpdata(Model model,
			HttpSession session,
			@RequestParam("postId") int postId,
			@RequestParam("postCommentId") int postCommentId) {
		
		int userId = (int)session.getAttribute("userId");
		Post post = postBO.getPostByPostId(postId);
		
		List<PostComment> postComment = postCommentBO.getPostCommentByPostIdUserIdPostCommentId(postId, userId, postCommentId);
		
		model.addAttribute("post", post);
		model.addAttribute("postComment", postComment);
		model.addAttribute("view", "post/postCommentUpdate");
		return "template/layout";
	}
}
