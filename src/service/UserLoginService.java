package service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import truc.UserDAO;
import truc.UserVO;

public class UserLoginService implements UserService {

	@Override
	public int execute(HttpServletRequest request, HttpServletResponse response) {
		
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		HttpSession session = request.getSession();
		
		UserDAO dao = UserDAO.getInstance();
		UserVO vo = dao.login(id, pw);
		
		System.out.println(vo.toString());
		
		if(vo != null ) {
			session.setAttribute("user", vo); //session에 사용자 정보 저장
			return 1;
		}
		return 0;
		
		
		
	}

}
