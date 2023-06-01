package com.PersonalProject.style.model;

import java.util.List;

import com.PersonalProject.styleComment.model.CommentView;
import com.PersonalProject.user.model.User;

public class StyleView {
	// 유저 정보
		private User user;
		
		// 글 1개
		private Style style;
		
		// 댓글 리스트
		private List<CommentView> commentList;
		
		// 좋아요 개수
		private int likeCount;
		
		// 좋아요 했는지 여부
		private boolean hetherLike;

		public User getUser() {
			return user;
		}

		public void setUser(User user) {
			this.user = user;
		}

		public Style getStyle() {
			return style;
		}

		public void setStyle(Style style) {
			this.style = style;
		}

		public List<CommentView> getCommentList() {
			return commentList;
		}

		public void setCommentList(List<CommentView> commentList) {
			this.commentList = commentList;
		}

		public int getLikeCount() {
			return likeCount;
		}

		public void setLikeCount(int likeCount) {
			this.likeCount = likeCount;
		}

		public boolean isHetherLike() {
			return hetherLike;
		}

		public void setHetherLike(boolean hetherLike) {
			this.hetherLike = hetherLike;
		}
		
		
}
