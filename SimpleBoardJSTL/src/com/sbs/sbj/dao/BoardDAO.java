package com.sbs.sbj.dao;

import static com.sbs.sbj.dao.CommonAPI.close;
import static com.sbs.sbj.dao.CommonAPI.getCon;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.sbs.sbj.vo.BoardVO;

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
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
