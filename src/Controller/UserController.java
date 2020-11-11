package Controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.BoardServiceCenter;
import service.UserDeleteService;
import service.UserJoinService;
import service.UserLoginFormService;
import service.UserLoginService;
import service.UserService;
import service.UserUpdateService;
import truc.UserVO;

@WebServlet("*.user")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public UserController() {
      
    	
    	
    	
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		disPatch(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		disPatch(request, response);
	}
	protected void disPatch(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		request.setCharacterEncoding("utf-8");
		String uri = request.getRequestURI();
		String conPath = request.getContextPath();
		
		String command = uri.substring( conPath.length() );
		System.out.println(uri);
		System.out.println(conPath);
		System.out.println(command);
		System.out.println(2);
		
		UserService service;
		
		if(command.equals("/user/user_join.user")) {//회원가입 화면 처리
			
			request.getRequestDispatcher("user_join.jsp").forward(request, response);
			
			
		}else if (command.equals("/user/user_login.user")) {//로그인 화면처리
			request.getRequestDispatcher("user_login.jsp").forward(request, response);
			
		}else if (command.equals("/user/joinForm.user")) { //회원가입 요청
			
			service = new UserJoinService();
			int result = service.execute(request, response); //중복시 1 , 성공시 0
			
			if(result == 1 ) { //중복
				request.setAttribute("msg", "이미 존재하는 회원입니다");
				request.getRequestDispatcher("user_join.jsp").forward(request, response);
				
			} else { //성공
				response.sendRedirect("user_login.user");
			}
		
		}else if(command.equals("/user/loginForm.user")) {
			service = new UserLoginFormService();
			int result = service.execute(request, response);
			
			if(result ==1 ) { //로그인 성공
				response.sendRedirect("mypage.user");
				
			
				
			}else {
				request.setAttribute("msg", "아이디 비밀번호를 확인하세요");
				request.getRequestDispatcher("user_login.jsp").forward(request, response);
			}
		}else if (command.equals("/user/mypage.user")){ //마이페이지로 이동
			service = new BoardServiceCenter();
			service.execute(request, response);
			request.getRequestDispatcher("user_mypage.jsp").forward(request, response);
		
		}else if(command.equals("/user/updateForm.user")) { //정보변경페이지
			
			request.getRequestDispatcher("user_mypageinfo.jsp").forward(request, response);
			
			
		}else if (command.equals("/user/updateSet.user")) {
			service = new UserUpdateService();
			int result = service.execute(request, response);
			System.out.println("결과값:"+result);
			if(result == 1 ) {//업데이트 성공1
				//문자열의 형태로 스크립트를 작성해서 out.println()화면에 전달
				response.setContentType("text/html; charset=UTF-8");				
				PrintWriter out = response.getWriter();//출력스트림
				out.println("<script>");
				out.println("alert('회원정보 수정이 정상 처리 되었습니다')");
				out.println("location.href='user_mypage.jsp';");
				out.println("</script>");
				
			
				
				
				
				
				
			}else { //실패시 0
				response.sendRedirect("mypage.user"); // 마이페이지로 이동.
				
				
				
			}
		}else if (command.equals("/user/logout.user")) { //로그아웃
			request.getSession().invalidate();
			response.sendRedirect(conPath+"/index.jsp");
		
		}else if(command.equals("/user/userdelete.user")) {
			System.out.println("실행");
			service = new UserDeleteService();
			int result = service.execute(request, response);
			
			if(result == 1) {//성공
				System.out.println(1);
				response.sendRedirect( request.getContextPath() );
			}else { //실패
				request.setAttribute("msg", "비밀번호를 다시 확인해주세요");
				System.out.println(2);
				request.getRequestDispatcher("mypage.user").forward(request, response);
				
			}
			
			
		}
		
		
		
	}
}