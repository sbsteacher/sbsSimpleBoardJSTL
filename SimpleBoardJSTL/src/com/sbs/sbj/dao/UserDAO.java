package com.sbs.sbj.dao;

import static com.sbs.sbj.dao.CommonAPI.close;
import static com.sbs.sbj.dao.CommonAPI.getCon;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLIntegrityConstraintViolationException;

import com.mysql.cj.jdbc.exceptions.MysqlDataTruncation;
import com.sbs.sbj.Utils;
import com.sbs.sbj.vo.UserVO;

public class UserDAO {
	
	//0:에러발생 1: 로그인 성공, 2: 아이디 없음, 3:비밀번호 다름
	public static int login(UserVO param) {
		int result = 0;
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = " SELECT * FROM t_user WHERE uid = ? ";
		
		try {
			con = getCon();
			ps = con.prepareStatement(sql);
			ps.setString(1, param.getUid());			
			rs = ps.executeQuery();
			if(rs.next()) {
				
				String dbUpw = rs.getString("upw");
				String upw = Utils.encryptSHA256(param.getUpw());
				
				if(upw.equals(dbUpw)) {					
					String nm = rs.getString("nm"); //로그인 정보 세션에 담기 위해
					param.setNm(nm);
					param.setUpw(null);
					result = 1;
				} else {
					result = 3;
				}
			} else {
				result = 2;
			}
			
		} catch (Exception e) {			
			e.printStackTrace();
		} finally {
			close(con, ps, rs);
		}
		
		return result;
	}
	
	//회원가입 (0:에러, 1:성공, 2:중복아이디 존재, 3:값이 너무 큼)
	public static int join(UserVO param) {
		int result = 0;
		
		Connection con = null;
		PreparedStatement ps = null;
		
		String sql = " INSERT INTO t_user (uid, upw, nm) "
				+ " VALUES (?, ?, ?) ";
		
		try {
			con = getCon();
			ps = con.prepareStatement(sql);
			ps.setString(1, param.getUid());
			ps.setString(2, Utils.encryptSHA256(param.getUpw()));
			ps.setString(3, param.getNm());
			result = ps.executeUpdate();
			
		} catch (SQLIntegrityConstraintViolationException e) {
			System.out.println("중복된 아이디가 있다!!!");
			result = 2;
		} catch(MysqlDataTruncation e) {
			System.out.println("내용이 길다!!!");
			result = 3;
		} catch (Exception e) {
			e.printStackTrace();			
		} finally {
			close(con, ps);
		}
		
		return result;
	}
}








