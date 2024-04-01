package com.boards;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;


public class BoardService  {
	private static final long serialVersionUID = 1L;
      
	BoardDAO boardDAO = new BoardDAO();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardService() {
        super();
        // TODO Auto-generated constructor stub
    }

    public List<BoardVO> list(BoardVO boardVO) throws ServletException, IOException {
    	System.out.println("여기 오니?");
		return boardDAO.list(boardVO);
	}
	
	public BoardVO view(BoardVO boardVO) throws ServletException, IOException {
		return boardDAO.read(boardVO);
	}
	
	public int delete(BoardVO boardVO) throws ServletException, IOException {
		return boardDAO.delete(boardVO);
	}
	
	public BoardVO updateForm(BoardVO tb_board) throws ServletException, IOException {
		return boardDAO.read(tb_board);
	}
	
	public int update(BoardVO tb_board) throws ServletException, IOException {
		return boardDAO.update(tb_board);
	}
	
	public int insert(BoardVO tb_board) throws ServletException, IOException {
		return boardDAO.insert(tb_board);
	}
	
}
