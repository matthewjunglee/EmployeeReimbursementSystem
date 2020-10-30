package com.ers.repo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.ers.model.User;
import com.ers.model.UserRole;
import com.ers.util.ConnectionUtil;

public class UserDao implements DaoContract<User, Integer> {

	private static final Logger logger = LogManager.getLogger(UserDao.class);
	
	public User login(String username, String password) {
		logger.info(String.format("Login - username: %s", username));

		User u = new User();
		String sql = "SELECT * FROM login(?, ?);";
		
		try (Connection conn = ConnectionUtil.getInstance().getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, username);
			ps.setString(2, password);
			
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				u.setId(rs.getInt(1));
//				u.setUsername(rs.getString(2));
				u.setFirstName(rs.getString(3));
//				u.setLastName(rs.getString(4));
//				u.setEmail(rs.getString(5));
				u.setRoleId(new UserRole(rs.getInt(6)));
			}
			rs.close();
		} catch (SQLException e) {
			u.setId(0);
			logger.error(e.getMessage());
		}
		
		return u;
	}
	
	@Override
	public int create(User t) {
		logger.info(String.format("Create account - username: %s", t.getUsername()));
		int userId = 0;
		String sql1 = "INSERT INTO users VALUES (DEFAULT, ?, ?, ?, ?, ?, ?);";
		String sql2 = "SELECT user_id FROM users WHERE username = ?;";
		
		try (Connection conn = ConnectionUtil.getInstance().getConnection();
				PreparedStatement ps1 = conn.prepareStatement(sql1);
				PreparedStatement ps2 = conn.prepareStatement(sql2)) {
			ps1.setString(1, t.getUsername());
			ps1.setString(2, t.getPassword());
			ps1.setString(3, t.getFirstName());
			ps1.setString(4, t.getLastName());
			ps1.setString(5, t.getEmail());
			ps1.setInt(6, t.getRoleId().getId());
			ps1.executeUpdate();
			
			ps2.setString(1, t.getUsername());
			ResultSet rs = ps2.executeQuery();
			while (rs.next()) {
				userId = rs.getInt(1);
			}
		} catch (SQLException e) {
			logger.info(String.format("Username: %s already exists", t.getUsername()));
		}
		
		return userId;
	}


	@Override
	public List<User> findAll() {
		return null;
//		List<User> users = new ArrayList<>();
//		String sql = "SELECT * FROM users;";
//		
//		try (Connection conn = ConnectionUtil.getInstance().getConnection();
//				PreparedStatement ps = conn.prepareStatement(sql);
//				ResultSet rs = ps.executeQuery()) {
//			
//			while (rs.next()) {
//				users.add(new User(rs.getInt(1), rs.getString(2), null, rs.getString(4),
//							rs.getString(5), rs.getString(6), new UserRole(rs.getInt(7))));
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return users;
	}

	@Override
	public User findById(Integer i) {
		return null;
//		User u = new User();
//		String sql = "SELECT * FROM users WHERE user_id = ?;";
//		
//		try (Connection conn = ConnectionUtil.getInstance().getConnection();
//				PreparedStatement ps = conn.prepareStatement(sql)) {
//			ps.setInt(1, i);
//			
//			ResultSet rs = ps.executeQuery();
//			while (rs.next()) {
//				u.setId(rs.getInt(1));
//				u.setUsername(rs.getString(2));
//				u.setFirstName(rs.getString(4));
//				u.setLastName(rs.getString(5));
//				u.setEmail(rs.getString(6));
//				u.setRoleId(new UserRole(rs.getInt(7)));
//			}
//			rs.close();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		
//		return u;
	}

	@Override
	public int update(User t) {
		int updated = 0;
//		String sql = "UPDATE users SET username = ?, password = ?, first_name = ?, last_name = ?, email = ?"
//				+ "WHERE user_id = ?;";
//		
//		try (Connection conn = ConnectionUtil.getInstance().getConnection();
//				PreparedStatement ps = conn.prepareStatement(sql)) {
//			ps.setString(1, t.getUsername());
//			ps.setString(2, t.getPassword());
//			ps.setString(3, t.getFirstName());
//			ps.setString(4, t.getLastName());
//			ps.setString(5, t.getEmail());
//			ps.setInt(6, t.getId());
//			
//			updated = ps.executeUpdate();
//			
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
		return updated;
	}

	@Override
	public int delete(Integer i) {
		return 0;
//		int updated = 0;
//		String sql1 = "DELETE FROM reimbursement r WHERE r.author = ?;";
//		String sql2 = "DELETE FROM users u WHERE u.user_id = ?;";
//		
//		try (Connection conn = ConnectionUtil.getInstance().getConnection();
//				PreparedStatement ps1 = conn.prepareStatement(sql1);
//				PreparedStatement ps2 = conn.prepareStatement(sql2)) {
//			ps1.setInt(1, i);
//			ps2.setInt(1, i);
//			ps1.executeUpdate();
//			updated = ps2.executeUpdate();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		
//		return updated;
	}
	
}
