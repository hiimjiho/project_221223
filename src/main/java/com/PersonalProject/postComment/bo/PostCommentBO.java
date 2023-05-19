package com.PersonalProject.postComment.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.PersonalProject.postComment.dao.PostCommentMapper;

@Service
public class PostCommentBO {
	@Autowired
	private PostCommentMapper postCommentMapper;
	
	public int addPostCommentByContentPostId(int postId, String content) {
		return postCommentMapper;
	}
}
