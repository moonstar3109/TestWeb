package service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import b.truc.BoardVO;
import truc.UserDAO;
import truc.UserVO;

public class BoardServiceCenter implements UserService {
	
	@Override
	public int execute(HttpServletRequest request, HttpServletResponse response) {
		
		HttpSession session = request.getSession();
		UserVO vo = (UserVO)session.getAttribute("user");
		
		String name = vo.getId();
		
		UserDAO dao = UserDAO.getInstance();
		ArrayList<BoardVO> list = dao.boardService(name);
		
		if(list != null) {
			session.setAttribute("list", list);
			return  1;
		}
		return 0;
		
		
	}
}
