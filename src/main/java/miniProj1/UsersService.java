package miniProj1;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;


public class UsersService {
	private static final long serialVersionUID = 1L;
    
	UsersDAO usersDAO = new UsersDAO();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UsersService() {
        super();
        // TODO Auto-generated constructor stub
    }

    public List<UsersVO> list(UsersVO usersVO) throws ServletException, IOException {
		return usersDAO.list();
	}
	
	public UsersVO view(UsersVO usersVO) throws ServletException, IOException {
		return usersDAO.read(usersVO.getUserid());
	}
	
	public int delete(UsersVO usersVO) throws ServletException, IOException {
		return usersDAO.delete(usersVO);
	}
	
	public UsersVO updateForm(UsersVO usersVO) throws ServletException, IOException {
		return usersDAO.read(usersVO.getUserid());
	}
	
	public int update(UsersVO usersVO) throws ServletException, IOException {
		return usersDAO.update(usersVO);
	}
	
	public int insert(UsersVO usersVO) throws ServletException, IOException {
		return usersDAO.insert(usersVO);
	}

	//public void updateUUID(UsersDAO usersDAO) {
		//usersDAO.updateUUID(usersDAO);
	
	
}

	


