package Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import b.service.BoardService;
import b.service.ContentService;
import b.service.DeleteService;
import b.service.GetListService;
import b.service.RegistService;
import b.service.UpdateService;



@WebServlet("*.board")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public BoardController() {
      
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
		
		
		System.out.println(command);
		BoardService service;
		
		if(command.equals("/board/list.board")) { //게시판 목록 보기
			service = new GetListService();
			service.execute(request, response);
			
			request.getRequestDispatcher("bbs.jsp").forward(request, response);
			
		}else if(command.equals("/main.board")){//메인화면
			service = new GetListService();
			service.execute(request, response);
		
		request.getRequestDispatcher("index.jsp").forward(request, response);
		}else if(command.equals("/board/write.board")) {//글쓰기

			request.getRequestDispatcher("bbs_write.jsp").forward(request, response);
		
		}else if (command.equals("/board/regist.board")) {
			
			service = new RegistService();
			service.execute(request, response);
			response.sendRedirect("list.board"); //다시 컨트롤러를 태워보내는 형식
			
		}else if (command.equals("/board/content.board")) { //확인하기
			
			service = new ContentService();
			service.execute(request, response);
			
			request.getRequestDispatcher("bbs_content.jsp").forward(request, response);
		
		}else if (command.equals("/board/modify.board")) {//수정하고싶어요
			service = new ContentService();
			service.execute(request, response);
			request.getRequestDispatcher("bbs_modify.jsp").forward(request, response);
	
		}else if (command.equals("/board/update.board")) { //수정사항 끝
			
			service = new UpdateService();
			service.execute(request, response);
			
			response.sendRedirect("content.board?bno="+request.getParameter("bno"));
			
		} else if (command.equals("/board/delete.board")) {
		
			service = new DeleteService();
			service.execute(request, response);
			
			response.sendRedirect("list.board");
		
		} 
		
	}
}
