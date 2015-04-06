package com.skplanet.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.skplanet.dto.MemberVO;

public class MemberDAO {

	
	private static MemberDAO instance = new MemberDAO();
	
	public static MemberDAO getInstance(){
		return instance;
	}
	private MemberDAO(){
		
	}

	// DBCP을 위한 Connection!
	public Connection getConnection() throws Exception{
		Connection conn = null;
		Context initContext = new InitialContext();
		Context envContext = (Context)initContext.lookup("java:/comp/env");
		DataSource ds = (DataSource)envContext.lookup("jdbc/TestDB");
		conn = ds.getConnection();
		System.out.println("DBCP연동 successful");
		return conn;
	}
	
	// 사용자 인증을 위한 method
	public int userCheck(String userid, String pwd){
		int result = -1;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		// SQL : SELECT 
		String sql = "select pwd from member where userid=?";
		
		try{
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userid);
			rs = pstmt.executeQuery();
			if(rs.next()){
				if(rs.getString("pwd")!=null && rs.getString("pwd").equals(pwd) ){
					System.out.println("result 1");
					result = 1;
				}
				else{
					System.out.println("result 0");
					result = 0;
				}
			}
			else{
				System.out.println("result -1");
				result = -1;
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try {
				if(rs !=null) rs.close();
				if(pstmt !=null) pstmt.close();
				if(conn !=null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	public MemberVO getMember(String userid){
		MemberVO mVo = null;
		String sql = "select * from member where userid=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userid);
			rs = pstmt.executeQuery();
			if(rs.next()){
				mVo = new MemberVO();
				mVo.setName(rs.getString("name"));
				mVo.setUserid(rs.getString("userid"));
				mVo.setPwd(rs.getString("pwd"));
				mVo.setEmail(rs.getString("email"));
				mVo.setPhone(rs.getString("phone"));
				mVo.setAdmin(rs.getInt("admin"));
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try{
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
		return mVo;
	}
	
	//아이디 중복체크를 위한 메소드추가
	public int confirmID(String userid){
		int result = -1;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		// SQL : SELECT 
		String sql = "select userid from member where userid=?";
		
		try{
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userid);
			rs = pstmt.executeQuery();
			if(rs.next()){
				System.out.println("result 1");
				result = 1;
			}
			else{
				System.out.println("result -1");
				result = -1;
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			try {
				if(rs!=null)rs.close();
				if(pstmt !=null) pstmt.close();
				if(conn !=null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;		
	}
	
	// 회원정보를 DB에 추가하기위한 메소드 추
	public int insertMember(MemberVO mVo) {
		int result = -1;
		String sql = "insert into member values(?, ?, ?, ?, ?, ?)";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mVo.getName());
			pstmt.setString(2, mVo.getUserid());
			pstmt.setString(3, mVo.getPwd());
			pstmt.setString(4, mVo.getEmail());
			pstmt.setString(5, mVo.getPhone());
			pstmt.setInt(6, mVo.getAdmin());
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	// 회원정보수정을 위한 
	public int updateMember(MemberVO mVo) {
		int result = -1;
		String sql = "update member set pwd=?, email=?,"
				+ "phone=?, admin=? where userid=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mVo.getPwd());
			pstmt.setString(2, mVo.getEmail());
			pstmt.setString(3, mVo.getPhone());
			pstmt.setInt(4, mVo.getAdmin());
			pstmt.setString(5, mVo.getUserid());
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	
	
}


