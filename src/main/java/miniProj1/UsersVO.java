package miniProj1;

import miniProj1.UsersVO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsersVO {
	private String userid;
	private String password;
	private String name;
	private String gender;
	private String email;
	private String phone;
	
	private String password2;
	private String err;
	//실행 명령 필드 
	private String action;

	//검색키
	private String searchKey;
	
	//uuid
	private String useruuid;
	
	//자동로그인 여부 
	private String autologin;
	
	public UsersVO(String userid, String password, String name,  String gender, String email, String phone) {
		super();
		this.userid = userid;
		this.password = password;
		this.name = name;
		this.gender = gender;
		this.email = email;
		this.phone = phone;
	}
	
	public boolean isEmptySearchKey() {
		return searchKey == null || searchKey.length() == 0; 
	}
	
	public boolean isEqualPassword(UsersVO usersVO) {
		return usersVO != null && password.equals(usersVO.getPassword());
	}
	
}
