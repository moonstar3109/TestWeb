package service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import truc.UserDAO;
import truc.UserVO;



public class UserUpdateService implements UserService {

	@Override
	public int execute(HttpServletRequest request, HttpServletResponse response) {
		
		System.out.println(12345);
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String name = request.getParameter("name");
		String email1 = request.getParameter("email1");
		String email2 = request.getParameter("email2");
		String num1 = request.getParameter("num1");
		String num2 = request.getParameter("num2");
		String num3 = request.getParameter("num3");
		String add1 = request.getParameter("add1");
		String add2 = request.getParameter("add2");
		
		HttpSession session = request.getSession();
		UserVO vo = new UserVO(id,pw,name,email1,email2,num1,num2,num3,add1,add2);
		
		UserDAO dao = UserDAO.getInstance();
		int result = dao.update(vo);
		if(result == 1) { //정보 수정 완료
			session.setAttribute("user", vo);
			
			return 1;
			
		}
		return 0;
	}

}
