package b.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import b.truc.BoardDAO;


public class DeleteService implements BoardService {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int bno = Integer.parseInt(request.getParameter("bno"));		
		System.out.println(bno);
		
		BoardDAO dao = BoardDAO.getInstance();
		dao.delete(bno);
		
		
	}

}
