package miniProj1;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.UUID;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import  miniProj1.UsersService;
import  miniProj1.UsersVO;
import miniProj1.UsersServlet;
import miniProj1.UsersDAO;


public class UsersController {
	private static final long serialVersionUID = 1L;

	//xml 또는 어노터이션 처리하면 스프링 
	//어노터이션 처리하면 스프링 부트   
	UsersService usersService = new UsersService();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UsersController() {
        super();
        // TODO Auto-generated constructor stub
    }

	public Object list(HttpServletRequest request, UsersVO usersVO) throws ServletException, IOException {
		System.out.println("목록");
		
		
		//1. 처리
		List<UsersVO> list = usersService.list(usersVO);
		
		//2. jsp출력할 값 설정
		request.setAttribute("list", list);
		
		return "list";
	}
	
	public Object view(HttpServletRequest request, UsersVO users) throws ServletException, IOException {
		System.out.println("상세보기");
		
		request.setAttribute("users", usersService.view(users));
		return "view";
	}
	
	public Object delete(HttpServletRequest request, UsersVO usersVO) throws ServletException, IOException {
		System.out.println("삭제");
		//1. 처리
		int updated = usersService.delete(usersVO);
		

		Map<String, Object> map = new HashMap<>();
		if (updated == 1) { //성공
			map.put("status", 0);
		} else {
			map.put("status", -99);
			map.put("statusMessage", "회원 정보 삭제 실패하였습니다");
		}
		
		return map;
	}
	
	public Object updateForm(HttpServletRequest request, UsersVO users) throws ServletException, IOException {
		System.out.println("수정화면");
		//1. 처리
		//usersDAO.read(user);
		
		//2. jsp출력할 값 설정
		request.setAttribute("users", usersService.updateForm(users));
		
		return "updateForm"; 
	}
	
	public Object update(HttpServletRequest request,UsersVO usersVO) throws ServletException, IOException {
		System.out.println("수정");
		
		//1. 처리
		int updated = usersService.update(usersVO);
		

		Map<String, Object> map = new HashMap<>();
		if (updated == 1) { //성공
			map.put("status", 0);
		} else {
			map.put("status", -99);
			map.put("statusMessage", "회원 정보 수정 실패하였습니다");
		}
		
		return map;
	}
	
	public Object insertForm(HttpServletRequest request) throws ServletException, IOException {
		System.out.println("등록화면");
		//1. 처리
		
		//2. jsp출력할 값 설정
		return "insertForm";
	}
	
	public Object insert(HttpServletRequest request, UsersVO usersVO) throws ServletException, IOException {
		System.out.println("등록");
		Map<String, Object> map = new HashMap<>();
		

		return map;
	}

	public Object existUserId(HttpServletRequest request, UsersVO usersVO) throws ServletException, IOException {
		//1. 처리
		//System.out.println("existUserId userid->" + userVO.getUserid());
		UsersVO existUser = usersService.view(usersVO);
		Map<String, Object> map = new HashMap<>();
		System.out.println(existUser);
		
		if (existUser == null) { //사용가능한 아이디
			map.put("existUser", false);
		} else { //사용 불가능 아아디 
			map.put("existUser", true);
		}
		return map;
	}

	public Object loginForm(HttpServletRequest request) {
		return "loginForm";
	}
	public String login(HttpServletRequest request, UsersVO userSVO, HttpServletResponse response) throws ServletException, IOException {
		UsersVO loginVO = usersService.view(userSVO);
		
		if (userSVO.isEqualPassword(loginVO)) {
			//로그인 사용자의 정보를 세션에 기록한다
			HttpSession session = request.getSession();
			session.setAttribute("loginVO", loginVO);
			session.setMaxInactiveInterval(30*60*1000);
			

			
		} else {
			//map.put("statusMessage", "아이디 또는 비밀번호가 잘못되었습니다");
			return "redirect:users.do?action=loginForm&err=invalidUserId";
		}
		return "redirect:board.do?action=list";
	}


	public Object mypage(HttpServletRequest request, UsersVO usersVO) throws ServletException, IOException {
		System.out.println("상세보기");
		
		return "mypage";
	}
}
