package com.PersonalProject.post.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.PersonalProject.post.model.Post;

@Repository
public interface PostMapper {
	public List<Post> selectPostList();
}
