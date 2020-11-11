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





@WebFilter({"/board/modify.board","/board/update.board","/board/delete.board"})
public class BoardFilter2 implements Filter{
	
@Override
public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
		throws IOException, ServletException {
	
	HttpServletRequest req = (HttpServletRequest)request;
	
	
	
	
	HttpSession session = req.getSession();
	HttpServletResponse res = (HttpServletResponse)response;
	request.setCharacterEncoding("utf-8");
	
	
	UserVO user = (UserVO)session.getAttribute("user");
	if(user == null) {
		res.sendRedirect("/TestWeb/user/user_login.user");
		return;
	}
	String id = user.getId();
	String name = request.getParameter("name");
	
	
	if( name == null || !(id.equals(name)) ) {
		res.setContentType("text/html; charset=UTF-8");
		PrintWriter out = res.getWriter();
		out.println("<script>");
		out.println("alert('권한이 없습니다	')");
		out.println("location.href='/TestWeb/board/list.board';");
		out.println("</script>");
		return; 
		
	}
	
	chain.doFilter(request, response); 
	
	
	
	
	
	
	
	
}
}
