package b.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import b.truc.BoardDAO;
import b.truc.BoardVO;
import util.copy.PageVO;



public class GetListService implements BoardService {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		BoardDAO dao = BoardDAO.getInstance();
		
		
	
		int pageNum = 1;
		int amount = 10;
		
		System.out.println(request.getParameter("pageNum"));
		System.out.println(request.getParameter("amount"));
		if(request.getParameter("pageNum") != null && request.getParameter("amount") != null) {
			pageNum = Integer.parseInt( request.getParameter("pageNum"));
			amount = Integer.parseInt( request.getParameter("amount"));
		}
		
		ArrayList<BoardVO>list = dao.getList(pageNum,amount);
		int total=dao.getTotal();
		PageVO vo = new PageVO(pageNum,amount,total);
		System.out.println(vo.toString());
		
		
		request.setAttribute("list", list);
		System.out.println(list.toString());
		
		
		request.setAttribute("pageVO", vo);
		
	}

}
