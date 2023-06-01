package com.PersonalProject.style.bo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.PersonalProject.common.FileManagerService;
import com.PersonalProject.like.bo.LikeBO;
import com.PersonalProject.like.dao.LikeMapper;
import com.PersonalProject.like.model.Like;
import com.PersonalProject.post.model.Paging;
import com.PersonalProject.product.bo.ProductBO;
import com.PersonalProject.product.model.Product;
import com.PersonalProject.style.dao.StyleMapper;
import com.PersonalProject.style.model.Style;
import com.PersonalProject.style.model.StyleCard;
import com.PersonalProject.style.model.StyleView;
import com.PersonalProject.styleComment.bo.StyleCommentBO;
import com.PersonalProject.styleComment.model.CommentView;
import com.PersonalProject.user.bo.UserBO;
import com.PersonalProject.user.model.User;

@Service
public class StyleBO {
	@Autowired 
	private ProductBO productBO;
	
	@Autowired
	private StyleMapper styleMapper;
	
	@Autowired
	private FileManagerService fileManager;
	
	@Autowired
	private UserBO userBO;
	
	@Autowired
	private StyleCommentBO styleCommentBO;
	
	@Autowired
	private LikeMapper likeMapper;
	
	@Autowired
	private LikeBO likeBO;
	
	// 유저 프로필정보때 사용할 스타일 리스트
	public List<Style> getStyleListByUserId(int userId) {
		return styleMapper.selectStyleListByUserId(userId);
	}
	
//	public List<Style> getStyleByProductId(int productId) {
//		return styleMapper.selectStyleByProductId(productId);
//	}
	
	public List<Style> getStyleByProductIdLimit5(int productId) {
		return styleMapper.selectStyleByProductIdLimit4(productId);
	}
	
	public int addStyle(int productId, String content, int userId, MultipartFile file) {
		String imagePath = null;
		if(file != null) {
			imagePath = fileManager.saveFile(imagePath, file);
		}
		return styleMapper.insertStyle(productId, content, userId, imagePath);
	}
	
	private static final int PAGE_LIMIT = 5; // 한 페이지당 보여줄 글 갯수
	
	private static final int BLOCK_LIMIT = 5; // 
	
	public List<StyleCard> generateStyleCard(Integer userId){
		
		List<StyleCard> styleCard = new ArrayList<>();
		
		List<Style> styleList = styleMapper.selectStyleList();
		
		for(Style style : styleList) {
			StyleCard card = new StyleCard();
			
			// 글
			card.setStyle(style);
			
			// 글쓴 사람 정보
			User user = userBO.getUserById(style.getUserId());
			card.setUser(user);
			
			// 댓글들(styleId)
			List<CommentView> commentViewList = styleCommentBO.generateCommentViewList(style.getId());
			card.setCommentList(commentViewList);
			
			// 좋아요 눌렀는지 여부
			if(userId == null) {
				
				card.setHetherLike(false);
			}else {
				// 좋아요 누른 거
				Like like = likeMapper.selectLike(style.getId(), userId);
				if(like == null) {
					card.setHetherLike(false);
				}else {
					card.setHetherLike(true);
				}
			}
			
			card.setLikeCount(likeBO.likeCountByStyleIdUserId(style.getId()));
			styleCard.add(card);
		}
		return styleCard;
		
	}
	
	
	// 스타일 뿌리기
	public List<StyleCard> generateStyleCardByProductId(int productId, Integer userId, int page){
		int pageStart = (page -1) * PAGE_LIMIT;
		Map<String, Integer> pagingParams = new HashMap<>();
		pagingParams.put("start", pageStart);
		pagingParams.put("limit", PAGE_LIMIT);
		
		List<StyleCard> styleCard = new ArrayList<>();
		
		List<Style> styleList = styleMapper.selectStyleByProductId(productId, pageStart, PAGE_LIMIT);
		
		for(Style style : styleList) {
			StyleCard card = new StyleCard();
			
			// 신발 정보
			Product product = productBO.getProductByProductId(productId);
			card.setProduct(product);
			
			// 글
			card.setStyle(style);
			
			// 글쓴 사람 정보
			User user = userBO.getUserById(style.getUserId());
			card.setUser(user);
			
			// 댓글들(styleId)
			List<CommentView> commentViewList = styleCommentBO.generateCommentViewList(style.getId());
			card.setCommentList(commentViewList);
			
			// 좋아요 눌렀는지 여부
			if(userId == null) {
				
				card.setHetherLike(false);
			}else {
				// 좋아요 누른 거
				Like like = likeMapper.selectLike(style.getId(), userId);
				if(like == null) {
					card.setHetherLike(false);
				}else {
					card.setHetherLike(true);
				}
			}
			
			card.setLikeCount(likeBO.likeCountByStyleIdUserId(style.getId()));
			styleCard.add(card);
		}
		return styleCard;
		
	}
	
	public Paging pagingParamByProductId(int page, int productId) {
		 // 전체 글 갯수 조회
       int styleCount = styleMapper.countStyleByProductId(productId);
       
       // 전체 페이지 갯수 계산(10/3=3.33333 => 4)
       int maxPage = (int) (Math.ceil((double) styleCount / PAGE_LIMIT));
       
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
	
	// 스타일 페이지 페이징 
	public Paging pagingParam(int page) {
		 // 전체 글 갯수 조회
      int styleCount = styleMapper.countStyle();
      
      // 전체 페이지 갯수 계산(10/3=3.33333 => 4)
      int maxPage = (int) (Math.ceil((double) styleCount / PAGE_LIMIT));
      
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
	
	// 스타일 낱개 상세 페이지
	public StyleCard generateStyle(int styleId, Integer userId, int page) {
		
		int pageStart = (page -1) * PAGE_LIMIT;
		Map<String, Integer> pagingParams = new HashMap<>();
		pagingParams.put("start", pageStart);
		pagingParams.put("limit", PAGE_LIMIT);
		
		StyleCard styleCard = new StyleCard();
		
		Style style = styleMapper.selectStyleByStyleId(styleId);
		
		styleCard.setStyle(style);
		
		User user = userBO.getUserById(style.getUserId());
		styleCard.setUser(user);
		
		// 댓글들(styleId)
		List<CommentView> commentViewList = styleCommentBO.generateCommentViewListByStyleId(styleId, page);
		styleCard.setCommentList(commentViewList);
					
					// 좋아요 눌렀는지 여부
			if(userId == null) {				
				styleCard.setHetherLike(false);
				}else {
						// 좋아요 누른 거
				Like like = likeMapper.selectLike(style.getId(), userId);
					if(like == null) {
						styleCard.setHetherLike(false);
				}else {
					styleCard.setHetherLike(true);
				}
			}
					
		styleCard.setLikeCount(likeBO.likeCountByStyleIdUserId(style.getId()));
		return styleCard;
	}
	
	// 스타일 낱개 상세 페이지
	
	public Style getStyleByidUserId(int styleId, int userId) {
		return styleMapper.selectStyleByidUserId(styleId, userId);
	}
	
	// 스타일 뿌리기
	public List<StyleView> generateStyleCard(Integer userId, int page){
		
		int pageStart = (page -1) * PAGE_LIMIT;
		Map<String, Integer> pagingParams = new HashMap<>();
		pagingParams.put("start", pageStart);
		pagingParams.put("limit", PAGE_LIMIT);
			
		List<StyleView> styleCard = new ArrayList<>();
		
		// 스타일 리스트
		List<Style> styleList = styleMapper.selectStyle(pageStart, PAGE_LIMIT);
			
		for(Style style : styleList) {
			StyleView card = new StyleView();
				
			// 글
			card.setStyle(style);
				
			// 글쓴 사람 정보
			User user = userBO.getUserById(style.getUserId());
			card.setUser(user);
				
			// 댓글들(styleId)
			List<CommentView> commentViewList = styleCommentBO.generateCommentViewList(style.getId());
			card.setCommentList(commentViewList);
				
				// 좋아요 눌렀는지 여부
			if(userId == null) {
					
				card.setHetherLike(false);
			}else {
				// 좋아요 누른 거
				Like like = likeMapper.selectLike(style.getId(), userId);
				if(like == null) {
					card.setHetherLike(false);
				}else {
					card.setHetherLike(true);
				}
			}
				
				card.setLikeCount(likeBO.likeCountByStyleIdUserId(style.getId()));
				styleCard.add(card);
			}
			return styleCard;
		}
	
	// 게시글 삭제
	public void deleteStyleByStyleIdUserId(int styleId, int userId) {
		// 삭제할 때 좋아요 갯수와 댓글 내용 사진 모두 삭제해 주어야 한다.
		
		// 해당 스타일을 가져온다.
		Style style = getStyleByidUserId(styleId, userId);
		
		// 이미지 삭제
		fileManager.deleteFile(style.getShoesImagePath());
		
		// 댓글 삭제
		styleCommentBO.deleteCommentByStyleId(styleId);
		
		// 좋아요 삭제
		likeBO.deleteLikeByStyleId(styleId);
		
		// 게시글 삭제
		styleMapper.deleteStyleByUserIdStyleId(styleId, userId);
		
	}
	
}
