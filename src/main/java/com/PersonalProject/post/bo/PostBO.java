package com.PersonalProject.post.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.PersonalProject.post.dao.PostMapper;
import com.PersonalProject.post.model.Post;

@Service
public class PostBO {
	@Autowired
	private PostMapper postMapper;
	
	public List<Post> getPostList(){
		return postMapper.selectPostList();
	}
}
