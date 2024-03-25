package miniProj1;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Users {
	private String userid;
	private String userpassword;
	private String username;
	private int    userage;
	private String useremail;
}


