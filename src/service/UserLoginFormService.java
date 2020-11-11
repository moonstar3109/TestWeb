package service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import truc.UserDAO;
import truc.UserVO;



public class UserLoginFormService implements UserService {

	@Override
	public int execute(HttpServletRequest request, HttpServletResponse response) {
		
		
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		HttpSession session = request.getSession();
		
		
		UserDAO dao = UserDAO.getInstance();
		UserVO vo = dao.login(id, pw);
		
		if(vo != null) { //로그인 정상 작동하여 UserVO에 값이 들어간 경우
			session.setAttribute("user", vo);
			return  1;
		}else { // 아이디와 비밀번호가 없는경우
			return 0;
		

		}
	}
}