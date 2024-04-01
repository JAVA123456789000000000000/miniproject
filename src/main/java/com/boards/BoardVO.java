package com.boards;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BoardVO {
	
	public BoardVO(String bno, String btitle, String bcontent,String userid) {
		this(bno, btitle, bcontent, "", "",userid);
	}

	
	public BoardVO(String bno, String btitle, String bcontent, String bwriter, String bdate,String userid) {
		this(bno, btitle, bcontent, bwriter, bdate,userid, "", "");
	}


	private String bno;
	private String btitle;
	private String bcontent;
	private String bwriter;
	private String bdate;
	private String userid;
	
	//실행 명령 필드 
	private String action;

	//검색키
	private String searchKey;

}

