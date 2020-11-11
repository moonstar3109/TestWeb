package Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import main.service.GetListToMain;
import main.service.Mainservice;


@WebServlet("*.main")
public class MainController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public MainController() {
      
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		disPatch(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		disPatch(request, response);
	}
	protected void disPatch(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		HttpSession session = request.getSession();
		request.setCharacterEncoding("utf-8");
		String uri = request.getRequestURI();
		String conPath = request.getContextPath();
		
		String command = uri.substring( conPath.length() );
		System.out.println(uri);
		System.out.println(conPath);
		System.out.println(command);
		System.out.println(1);
		
		Mainservice service;
		
	
		if (command.equals("/get.main")) {
			service = new GetListToMain();
			service.execute(request,response);
			request.getRequestDispatcher("index.jsp").forward(request, response);
			
		}
		
	}

}
