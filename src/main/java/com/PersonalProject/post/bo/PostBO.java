package com.PersonalProject.post.bo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.PersonalProject.common.FileManagerService;
import com.PersonalProject.post.dao.PostMapper;
import com.PersonalProject.post.model.Paging;
import com.PersonalProject.post.model.Post;
import com.PersonalProject.post.model.PostView;
import com.PersonalProject.postComment.bo.PostCommentBO;
import com.PersonalProject.user.bo.UserBO;
import com.PersonalProject.user.model.User;


@Service
public class PostBO {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private PostMapper postMapper;
	
	@Autowired
	private UserBO userBO;
	
	@Autowired
	private PostCommentBO postCommentBO; 
	
	@Autowired
	private FileManagerService fileManager;
	
	private static final int PAGE_LIMIT = 10; // 한 페이지당 보여줄 글 갯수
	
	private static final int BLOCK_LIMIT = 10; // 
	
	// 프로필 화면 구성할 때 쓸 포스트 리스트
	public List<Post> getPostListByUserId(int userId){
		return postMapper.selectPostListByUserId(userId);
	};
	
	public int addPost(int userId, String loginId, String subject, String content, MultipartFile file) {
		String imagePath = null;
		if(file != null) {
			// 서버에 이미지 업로드 후 imagePath 받아옴
			imagePath = fileManager.saveFile(loginId, file);
		}
		return postMapper.insertPost(userId, loginId, subject, content, imagePath);
	}
	
	// 모든 포스트 불러오기
	public List<Post> getPostList(){
		return postMapper.selectPostList();
	}
	
	// 코멘트 수정을 위한 메서드
	public Post getPostByPostId(int postId) {
		return postMapper.selectPostByPostId(postId);
	}
	
	// 게시판에 글 목록 뿌리기(페이징 포함)
	public List<PostView> generatePostList(int page, Integer userId) {
		
		int pageStart = (page -1) * PAGE_LIMIT;
		Map<String, Integer> pagingParams = new HashMap<>();
		pagingParams.put("start", pageStart);
		pagingParams.put("limit", PAGE_LIMIT);
		
		
		List<PostView> postViewList = new ArrayList<>();
		
		List<Post> postList = postMapper.pagingPostList(pageStart, PAGE_LIMIT);
		
		for(Post post : postList) {
			PostView postView = new PostView();
			
			postView.setPost(post);
			
			User user = userBO.getUserById(post.getUserId());
			
			postView.setUser(user);
			
			postViewList.add(postView);
		}
		return postViewList;
	}
	
	// 게시판 글 상세 페이지
	public PostView generatePostViewByPostId(int postId) {
		PostView postView = new PostView();
		
		Post post = postMapper.selectPost(postId);
		
		postView.setPost(post);
		
		User user = userBO.getUserById(post.getUserId());
		
		postView.setUser(user);
		
		
		return postView;
	}
	
	// 글 수정 기능에 사용할 해당 글 찾아오기
	public Post getPostByPostIdUserId(int postId, int userId) {
		return postMapper.selectPostByPostIdUserId(postId, userId);
	}
	
	// 글 수정
	public void updatePost(
			int userId,
			String loginId,
			int postId,
			String subject,
			String content,
			MultipartFile file) {
		
		// 해당 글 가져오기
		Post post = getPostByPostIdUserId(postId, userId);
		logger.warn("[update post] post is null. postId:{}, userId:{}", postId, userId);
		if(post == null) {
			logger.warn("[update post] post is null. postId:{}, userId:{}", postId, userId);
		}
		
		String imagePath = null;
		if(file != null) {
			// 업로드
			imagePath = fileManager.saveFile(loginId, file);
			
			if(imagePath != null && post.getImagePath() != null) {
				fileManager.deleteFile(post.getImagePath());
			}
		}
		
		postMapper.updatePostByPostId(postId, subject, content, imagePath);
	}
	
	// 글 삭제
	public int deletePostByPostIdUserId(int postId, int userId) {
		// 해당 글 가져오기
		Post post = getPostByPostIdUserId(postId, userId);
		if(post == null) {
			logger.error("[글 삭제] post is null. postId:{}", postId, userId);
			return 0;
		}
		
		// 이미지 삭제
		if(post.getImagePath() != null) {
			fileManager.deleteFile(post.getImagePath());
		}
		
		postCommentBO.deletePostCommentByPostId(postId);
		
		return postMapper.deletePostByPostIdUserId(postId, userId);
	}
	
	// 어드민 페이지에서 사용할 포스트 삭제
	public int deletePostByPostId(int postId) {
		return postMapper.deletePostByPostId(postId);
	}
	
	// 페이징에 사용할 포스트 카운트
	public int countPost() {
		return postMapper.countPost();
	}
	
	// 어드민 페이지 유저 탈퇴 기능에 사용할 포스트 삭제
	public void deletePostByUserId(int userId) {
		List<Post> postList = postMapper.selectPostListByUserId(userId);
		
		for(Post post : postList) {
			// 댓글 삭제
			postMapper.deletePostByPostId(post.getId());
			// 이미지 삭제
			if(post.getImagePath() != null) {
				fileManager.deleteFile(post.getImagePath());
			}
			// 포스트 삭제
			postMapper.deletePostByUserId(userId);
		}
	}
	
	// 페이징 파람
	public Paging pagingParam(int page) {
		 // 전체 글 갯수 조회
        int boardCount = postMapper.countPost();
        
        // 전체 페이지 갯수 계산(10/3=3.33333 => 4)
        int maxPage = (int) (Math.ceil((double) boardCount / PAGE_LIMIT));
        
        // 시작 페이지 값 계산(1, 4, 7, 10, ~~~~)
        int startPage = (((int)(Math.ceil((double) page / BLOCK_LIMIT))) - 1) * BLOCK_LIMIT + 1;
        
        // 끝 페이지 값 계산(3, 6, 9, 12, ~~~~)
        int endPage = startPage + BLOCK_LIMIT - 1;
        
        if (endPage > maxPage) {
            endPage = maxPage;
        }
        Paging paging = new Paging();
        
        paging.setPage(page);
        paging.setMaxPage(maxPage);
        paging.setStartPage(startPage);
        paging.setEndPage(endPage);
        return paging;
	}

}
