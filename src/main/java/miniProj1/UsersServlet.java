package miniProj1;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.boards.BoardController;
import com.boards.BoardVO;
import com.fasterxml.jackson.databind.ObjectMapper;

public class UsersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UsersController usersController = new UsersController();  
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UsersServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doService(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doService(request, response);
	}
	
	private Map<String, Object> convertMap(Map<String, String[]> map) {
		Map<String, Object> result = new HashMap<>();

		for (var entry : map.entrySet()) {
			if (entry.getValue().length == 1) {
				//문자열 1건  
				result.put(entry.getKey(), entry.getValue()[0]);
			} else {
				//문자열 배열을 추가한다  
				result.put(entry.getKey(), entry.getValue());
			}
		}
		
		return result;
	}
	
	//공통 처리 함수 
	private void doService(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//한글 설정 
		request.setCharacterEncoding("utf-8");
		String contentType = request.getContentType();
		
		ObjectMapper objectMapper = new ObjectMapper();
		UsersVO usersVO = null;
		if (contentType == null || contentType.startsWith("application/x-www-form-urlencoded")) {
			usersVO = objectMapper.convertValue(convertMap(request.getParameterMap()), UsersVO.class);
		} else if (contentType.startsWith("application/json")) {
			usersVO = objectMapper.readValue(request.getInputStream(), UsersVO.class);
		}
		System.out.println("usersVO " + usersVO);
		
		String action = usersVO.getAction();
		Object result = switch(action) {
		case "list" -> usersController.list(request, usersVO);
	    case "view" -> usersController.view(request, usersVO);
		case "delete" -> usersController.delete(request, usersVO);
		case "updateForm" -> usersController.updateForm(request, usersVO);
		case "update" -> usersController.update(request, usersVO);
		case "insertForm" -> usersController.insertForm(request);
		case "insert" -> usersController.insert(request, usersVO);
		case "existUserId" -> usersController.existUserId(request, usersVO);
		case "loginForm" -> usersController.loginForm(request);
		case "login" -> usersController.login(request, usersVO, response);
		//case "logout" -> usersController.logout(request);
		case "mypage" -> usersController.mypage(request, usersVO);
		default -> "";
			
		};
		
		if (result instanceof Map map) {
			//json 문자열을 리턴 
			response.setContentType("application/json;charset=UTF-8");
			response.getWriter().append(objectMapper.writeValueAsString(map));
		} else if (result instanceof String url) {
			if (url.startsWith("redirect:")) {
				//리다이렉트 
				response.sendRedirect(url.substring("redirect:".length()));
			} else {
				//3. jsp 포워딩 
				//포워딩 
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/users/"+url+".jsp");
				rd.forward(request, response);
			}
		}
	}
}


