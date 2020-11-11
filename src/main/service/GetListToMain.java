package main.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import b.truc.BoardDAO;
import b.truc.BoardVO;



public class GetListToMain implements Mainservice {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		BoardDAO dao = BoardDAO.getInstance();
		
		
	
		ArrayList<BoardVO>list = dao.getList(1,10);
		list.toString();
		
		
		
		request.setAttribute("list", list);
		System.out.println(list.toString());
		
	
	}

}
