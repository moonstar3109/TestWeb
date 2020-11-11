package filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import truc.UserVO;





//1. Filter인터페이스를 상속받고, doFilter메서드를 오버라이딩 합니다.
//2. 필터를 등록하는 방법 @WebFilter어노테이션 방법 or web.xml에 필터 설정

//@WebFilter("/*") - 모든요청
//@WebFilter("*.board") - board로 끝나는 모든 요청
@WebFilter({"/board/write.board", "/board/regist.board"}) //글쓰기 화면이나, 글 등록시에만 세션검사
public class BoardFilter implements Filter{
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)throws IOException, ServletException {
		
		
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse res = (HttpServletResponse)response;
		
		HttpSession session = req.getSession();
		UserVO user = (UserVO)session.getAttribute("user");
		
		if(user == null ) { 
			
			res.setContentType("text/html; charset=UTF-8");
			PrintWriter out = res.getWriter();
			
			out.println("<script>");
			out.println("alert('로그인이 필요합니다')");
			out.println("location.href='/TestWeb/user/user_login.user';");
			out.println("</script>");
			
			
			
			
			return;
		}
		
		
		chain.doFilter(request, response);
	}
}
