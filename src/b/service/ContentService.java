package b.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import b.truc.BoardDAO;
import b.truc.BoardVO;



public class ContentService implements BoardService {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		int bno = Integer.parseInt(request.getParameter("bno"));
		
		System.out.println(bno);
		BoardDAO dao = BoardDAO.getInstance();
		BoardVO vo = dao.getContent(bno);
		System.out.println(vo.toString());
		
		
		request.setAttribute("vo", vo);
	}

}
