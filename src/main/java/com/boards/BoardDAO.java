package com.boards;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.boards.BoardVO;


public class BoardDAO {
   
    private static Connection conn = null;
    private static PreparedStatement boardListPstmt = null;
    private static PreparedStatement boardListPstmt2 = null;
    private static PreparedStatement boardInsertPstmt = null;
    private static PreparedStatement boardDeletePstmt = null;
    private static PreparedStatement boardDetailPstmt = null;

    private static PreparedStatement boardUpdatePstmt = null;
    private static PreparedStatement boardDeleteAllPstmt = null;


    static {

    	try {
            // 1. JDBC 드라이버 로딩
            Class.forName("org.mariadb.jdbc.Driver");
            // 2. DB 연결
            conn = DriverManager.getConnection(
                    "jdbc:mariadb://localhost:3306/miniproj_db",
                    "bituser", //계정이름
                    "1004" //계정비밀번호
            );
            // 3. SQL 실행 객체 준비
            // 4. SQL 실행
            System.out.println("연결 성공");
            conn.setAutoCommit(false);

            boardListPstmt = conn.prepareStatement("select  b. *, name bwriter from tb_board b inner join"
            		+ "  tb_users u on b.userid=u.userid; ");
            boardListPstmt2 = conn.prepareStatement("select * from tb_board where  btitle like ?");
            boardInsertPstmt = conn.prepareStatement("insert into tb_board (btitle, bcontent, userid) values (?, ?, ?)");
            boardDetailPstmt = conn.prepareStatement("select  b. *, name bwriter from tb_board b inner join "
            		+ " tb_users u on b.userid=u.userid where bno = ?");
            //delete 가 되지 않았던 이유: ? 개수에 맞춰서 setString() 을 해주어야 한다.
            boardDeletePstmt = conn.prepareStatement("delete from tb_board where bno = ?");
            boardDeleteAllPstmt = conn.prepareStatement("delete from tb_board");
            boardUpdatePstmt = conn.prepareStatement("update tb_board set btitle = ?, bcontent = ? where bno = ?");
            // 5. 결과 처리
            // 6. 연결 해제
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }
    
    public List<BoardVO> list(BoardVO boardVO) {
        List<BoardVO> list = new ArrayList<>();
        try {
        	ResultSet rs = null;
        	String searchKey = boardVO.getSearchKey();
        	if (searchKey != null && searchKey.length() != 0) {
        		boardListPstmt2.setString(1, "%" + searchKey + "%");
        		rs = boardListPstmt2.executeQuery();
        	} else {
        		rs = boardListPstmt.executeQuery();
        	}
            
            while (rs.next()) {
                BoardVO tb_board = new BoardVO(rs.getString("bno")
                        , rs.getString("btitle")
                        , rs.getString("bcontent")
                        , rs.getString("bwriter")
                        , rs.getString("bdate")
                        , rs.getString("userid") 
                                       );
                 
                
                list.add(tb_board);
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
            
        }
        System.out.println(list);
        return list;
    }
    public int insert(BoardVO boardVO){
        int updated = 0;
        try{
            boardInsertPstmt.setString(1, boardVO.getBtitle());
            boardInsertPstmt.setString(2, boardVO.getBcontent());
            boardInsertPstmt.setString(3, boardVO.getUserid());
            updated = boardInsertPstmt.executeUpdate();
            conn.commit();
        }catch (Exception e){
            e.printStackTrace();
        }
        return updated;
    }
    public BoardVO read(BoardVO boardVO) {

        BoardVO tb_board = null;
        try {
            boardDetailPstmt.setString(1, boardVO.getBno());

            ResultSet rs = boardDetailPstmt.executeQuery();
            if (rs.next()) {

            	tb_board = BoardVO.builder()
            			.btitle(rs.getString("btitle"))
            			.bcontent(rs.getString("bcontent"))
            			.bno(rs.getString("bno"))
            			.bdate(rs.getString("bdate"))
            			.bwriter(rs.getString("bwriter"))
            			.build();
            }
            rs.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return tb_board;
    }

    public int update(BoardVO boardVO) {
        int updated = 0;
        try {
            boardUpdatePstmt.setString(1,boardVO.getBtitle());
            boardUpdatePstmt.setString(2, boardVO.getBcontent());
            boardUpdatePstmt.setString(3, boardVO.getBno());
            updated = boardUpdatePstmt.executeUpdate();
            conn.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return updated;

    }

    public int delete(BoardVO boardVO) {
        int updated = 0;

        try {
            boardDeletePstmt.setString(1, boardVO.getBno());
            updated = boardDeletePstmt.executeUpdate();
            conn.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return updated;
    }

    public int clear() {
        int updated = 0;
        try {
            updated = boardDeleteAllPstmt.executeUpdate();
            conn.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return updated;
    }

}