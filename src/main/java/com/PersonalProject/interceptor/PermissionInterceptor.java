package com.PersonalProject.interceptor;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component // 스프링 빈
public class PermissionInterceptor implements HandlerInterceptor {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws IOException {

		// 요청 URL을 가져온다.
		String uri = request.getRequestURI();
		logger.info("[$$$$$$$$ preHandler] uri:{}", uri); // /user/sign_in_view

		// 로그인 여부 확인 - 세션 확인
		HttpSession session = request.getSession();
		Integer userId = (Integer) session.getAttribute("userId");

		Integer adminId = (Integer) session.getAttribute("adminId");

		// 비로그인 && /Post create로 온 경우 => 로그인 페이지로 리다이렉트, return false(기존 컨트롤러 수행 방지)
		if (userId == null && uri.startsWith("/post/create_view")) {
			response.sendRedirect("/user/sign_in_view");
			return false; // 컨트롤러 수행 안함
		}

		// 비로그인 && /Post create로 온 경우 => 로그인 페이지로 리다이렉트, return false(기존 컨트롤러 수행 방지)
		if (userId == null && uri.startsWith("/profile/profile_update")) {
			response.sendRedirect("/user/sign_in_view");
			return false; // 컨트롤러 수행 안함
		}

		// 로그인 && /user로 온 경우 => 글 목록 페이지로 리다이렉트, return false(기존 컨트롤러 수행 방지)
		if (userId != null && uri.startsWith("/user")) {
			response.sendRedirect("/product/main_view");
			return false; // 컨트롤러 수행 안함
		}
		
		// 어드민 로그인 없이 어드민 메인뷰로 이동 불가
		if (adminId == null && uri.startsWith("/admin/admin_main_view")) {
			response.sendRedirect("/admin/login_page_view");
			return false; // 컨트롤러 수행 안함
		}
		
		// 어드민 아이디 없이 스타일 관리 페이지로 이동 불가
		if (adminId == null && uri.startsWith("/admin/style_management_view")) {
			response.sendRedirect("/admin/login_page_view");
			return false; // 컨트롤러 수행 안함
		}
		
		// 어드민 아이디 없이 물건 관리 페이지로 이동 불가
		if (adminId == null && uri.startsWith("/admin/product_management_view")) {
			response.sendRedirect("/admin/login_page_view");
			return false; // 컨트롤러 수행 안함
		}
		
		// 어드민 아이디 없이 신발 업데이트 페이지로 이동 불가
		if (adminId == null && uri.startsWith("/admin/product_update_view")) {
			response.sendRedirect("/admin/login_page_view");
			return false; // 컨트롤러 수행 안함
		}
		
		// 어드민 아이디 없이 신발 생성 페이지 이동 불가
		if (adminId == null && uri.startsWith("/admin/product_create_view")) {
			response.sendRedirect("/admin/login_page_view");
			return false; // 컨트롤러 수행 안함
		}
		
		// 어드민 아이디 없이 유저 관리 페이지 이동 불가
		if (adminId == null && uri.startsWith("/admin/user_management_view")) {
			response.sendRedirect("/admin/login_page_view");
			return false; // 컨트롤러 수행 안함
		}
		
		// 어드민 페이지 로그인 한 상태로 로그인 페이지 이동 불가
		if (adminId != null && uri.startsWith("/admin/login_page_view")) {
			response.sendRedirect("/admin/admin_main_view");
			return false; // 컨트롤러 수행 안함
		}

		return true; // 컨트롤러 수행
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView mav) {

		// view 객체가 존재한다는 것은 아직 jsp가 HTML로 변환되기 전이다.
		logger.info("[####### postHandle]");
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {
		// jsp가 HTML로 최종 변환된 후
		logger.info("[@@@@@@@ afterCompletion]");
	}
}
