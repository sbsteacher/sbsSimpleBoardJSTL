package com.sbs.sbj.dao;

import static com.sbs.sbj.dao.CommonAPI.close;
import static com.sbs.sbj.dao.CommonAPI.getCon;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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
}
