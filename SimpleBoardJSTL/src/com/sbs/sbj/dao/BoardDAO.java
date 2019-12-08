package com.sbs.sbj.dao;

import static com.sbs.sbj.dao.CommonAPI.close;
import static com.sbs.sbj.dao.CommonAPI.getCon;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.sbs.sbj.vo.BoardVO;
import com.sbs.sbj.vo.CommentVO;

public class BoardDAO {
	
	public final static int SHOW_BOARD_ITEM_COUNT = 5;
	
	//보드 리스트 가져오기
	public static List<BoardVO> getBoardList(BoardVO param) {
		List<BoardVO> list = new ArrayList();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		final int START_IDX = (param.getPage() - 1) * SHOW_BOARD_ITEM_COUNT;
		
		String sql = " SELECT A.i_board, A.grp, A.seq, A.FLOOR, A.title, A.regdatetime, A.cnt, B.nm " + 
				" FROM t_board A " + 
				" INNER JOIN t_user B " + 
				" ON A.uid = B.uid " + 
				" ORDER BY A.grp DESC, A.seq " + 
				" LIMIT ?, ? "; 
		
		try {
			con = getCon();
			ps = con.prepareStatement(sql);
			ps.setInt(1, START_IDX);
			ps.setInt(2, SHOW_BOARD_ITEM_COUNT);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				int i_board = rs.getInt("i_board");
				int grp = rs.getInt("grp");
				int seq = rs.getInt("seq");
				int floor = rs.getInt("floor");
				String title = rs.getString("title");
				String regdatetime = rs.getString("regdatetime");
				int cnt = rs.getInt("cnt");
				String nm = rs.getString("nm");
				
				BoardVO vo = new BoardVO();
				vo.setI_board(i_board);
				vo.setGrp(grp);
				vo.setSeq(seq);
				vo.setFloor(floor);
				vo.setTitle(title);
				vo.setRegdatetime(regdatetime);
				vo.setCnt(cnt);
				vo.setNm(nm);
				
				list.add(vo);
			}
						
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con, ps, rs);
		}		
		
		return list;
	}
	
	//총 페이징 수 가져오기
	public static int getPagingCnt() {
		int result = 1;
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = "SELECT CEIL(COUNT(i_board) / ?) FROM t_board";
		
		try {
			con = getCon();
			ps = con.prepareStatement(sql);
			ps.setInt(1, SHOW_BOARD_ITEM_COUNT);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				result = rs.getInt(1);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con, ps, rs);
		}
		
		return result;
	}
	
	public static BoardVO getBoardDetail(BoardVO param) {
		BoardVO result = null;
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = " SELECT A.*, B.fav_cnt, C.nm FROM t_board A "
				+ " LEFT JOIN ( " + 
				"	SELECT i_board, COUNT(i_board) AS fav_cnt FROM t_favorite " + 
				"	WHERE i_board = ? " + 
				"	GROUP BY i_board " + 
				" ) B " + 
				" ON A.i_board = B.i_board " +
				" INNER JOIN t_user C " + 
				" ON A.uid = C.uid" + 
				" WHERE A.i_board = ? ";
		
		try {
			con = getCon();
			ps = con.prepareStatement(sql);
			ps.setInt(1, param.getI_board());
			ps.setInt(2, param.getI_board());
			rs = ps.executeQuery();
			if(rs.next()) {
				String title = rs.getString("title");
				String content = rs.getString("content");
				String regdatetime = rs.getString("regdatetime");
				int cnt = rs.getInt("cnt");
				int fav_cnt = rs.getInt("fav_cnt");
				String nm = rs.getString("nm");
				String uid = rs.getString("uid");
				
				result = new BoardVO();
				result.setI_board(param.getI_board());
				result.setTitle(title);
				result.setContent(content);
				result.setRegdatetime(regdatetime);
				result.setCnt(cnt);
				result.setFav_cnt(fav_cnt);
				result.setNm(nm);
				result.setUid(uid);
			}
			
		} catch (Exception e) {			
			e.printStackTrace();
		} finally {
			close(con, ps, rs);
		}
		
		return result;
	}
	
	//댓글 리스트 가져오기
	public static List<CommentVO> getCommentList(BoardVO param) {
		 List<CommentVO> list = new ArrayList();
		 
		 Connection con = null;
		 PreparedStatement ps = null;
		 ResultSet rs = null;
		 
		 String sql = " SELECT A.*, B.nm FROM t_comment A " + 
		 		" INNER JOIN t_user B ON A.uid = B.uid " + 
		 		" WHERE i_board = ? " +
		 		" ORDER BY i_comment DESC ";
		 
		 try {
			con = getCon();
			ps = con.prepareStatement(sql);
			ps.setInt(1, param.getI_board());
			rs = ps.executeQuery();
			while(rs.next()) {
				int i_comment = rs.getInt("i_comment");
				String cmt = rs.getString("cmt");
				String r_datetime = rs.getString("r_datetime");
				String uid = rs.getString("uid");
				String nm = rs.getString("nm");
								
				CommentVO vo = new CommentVO();
				vo.setI_comment(i_comment);
				vo.setCmt(cmt);
				vo.setR_datetime(r_datetime);
				vo.setUid(uid);
				vo.setNm(nm);
				
				list.add(vo);
			}
		} catch (Exception e) {			
			e.printStackTrace();
		} finally {
			close(con, ps, rs);
		}		 
		
		return list;
	}
	
	//댓글 등록
	public static int regComment(CommentVO param) {
		int result = 0;
		
		Connection con = null;
		PreparedStatement ps = null;
		
		String sql = " INSERT INTO t_comment (i_board, cmt, uid) "
				+ " VALUES (?, ?, ?) ";
		
		try {
			con = getCon();
			ps = con.prepareStatement(sql);
			ps.setInt(1, param.getI_board());
			ps.setString(2, param.getCmt());
			ps.setString(3, param.getUid());
			
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con, ps);
		}
		
		return result;
	}

	//댓글 삭제
	public static int delComment(CommentVO param) {
		int result = 0;
		
		return result;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
