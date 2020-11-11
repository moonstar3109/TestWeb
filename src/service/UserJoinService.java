package service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import truc.UserDAO;
import truc.UserVO;


public class UserJoinService implements UserService {

	@Override
	public int execute(HttpServletRequest request, HttpServletResponse response) {
		
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String name = request.getParameter("name");
		String num1 = request.getParameter("num1");
		String num2 = request.getParameter("num2");
		String num3 = request.getParameter("num3");
		String email1 = request.getParameter("email");
		String email2 = request.getParameter("emai2");
		String add1 = request.getParameter("add1");
		String add2 = request.getParameter("add2");
		
		//중복검사
		UserDAO dao = UserDAO.getInstance();
		int result = dao.CheckId(id); //중복 1  중복x 0 
		if(result == 1 ) { //이미 존재하는 회원
			return 1;
		} else {
			UserVO vo = new UserVO(id,pw,name,num1,num2,num3,email1,email2,add1,add2);
			dao.join(vo); 
			return 0;
		}
	}

}
