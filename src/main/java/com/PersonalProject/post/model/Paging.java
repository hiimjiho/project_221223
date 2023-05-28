package com.PersonalProject.post.model;

public class Paging {

	private int pageNum; // 현재 페이지 번호
	private int listLimit; // 페이지 당 게시물 목록 갯수
	private int listCount; // 총 게시물 수
	private int pageListLimit; // 페이지 당 표시할 페이지 번호 수
	private int maxPage; // 전체 페이지 수
	private int startPage; // 시작 페이지 번호
	private int endPage; // 끝 페이지 번호
	public int getPageNum() {
		return pageNum;
	}
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	public int getListLimit() {
		return listLimit;
	}
	public void setListLimit(int listLimit) {
		this.listLimit = listLimit;
	}
	public int getListCount() {
		return listCount;
	}
	public void setListCount(int listCount) {
		this.listCount = listCount;
	}
	public int getPageListLimit() {
		return pageListLimit;
	}
	public void setPageListLimit(int pageListLimit) {
		this.pageListLimit = pageListLimit;
	}
	public int getMaxPage() {
		return maxPage;
	}
	public void setMaxPage(int maxPage) {
		this.maxPage = maxPage;
	}
	public int getStartPage() {
		return startPage;
	}
	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}
	public int getEndPage() {
		return endPage;
	}
	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
	
	
}
