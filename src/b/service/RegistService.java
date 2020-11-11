package b.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import b.truc.BoardDAO;



public class RegistService implements BoardService{
	
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		String name = request.getParameter("writer");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		System.out.println(name);
		System.out.println(title);
		System.out.println(content);
			
		
		BoardDAO dao = BoardDAO.getInstance();
		dao.regist(name,title,content);
		
		
	}
}
