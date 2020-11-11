package service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import truc.UserDAO;
import truc.UserVO;



public class UserDeleteService implements UserService {

	@Override
	public int execute(HttpServletRequest request, HttpServletResponse response) {
		
		String pw = request.getParameter("pw");
		
		HttpSession session = request.getSession();
		UserVO vo = (UserVO)session.getAttribute("user");
		String id = vo.getId();
		
		UserDAO dao = UserDAO.getInstance();
		if (dao.login(id, pw) != null) {
			dao.delete(vo);
			session.invalidate();
			return 1;
		}
		
		
		
		return 0;
		
		
	}

}
