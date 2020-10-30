package com.ers.repo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.ers.model.Reimbursement;
import com.ers.model.ReimbursementStatus;
import com.ers.model.ReimbursementType;
import com.ers.util.ConnectionUtil;

public class ReimbursementDao implements DaoContract<Reimbursement, Integer> {

	private static final Logger logger = LogManager.getLogger(ReimbursementDao.class);

	@Override
	public int create(Reimbursement t) {
		logger.info("Create request");

		int updated = 0;
		String sql = "INSERT INTO reimbursement VALUES (DEFAULT, ?, ?, NULL, ?, NULL, ?, NULL, DEFAULT, ?);";

		try (Connection conn = ConnectionUtil.getInstance().getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setBigDecimal(1, t.getAmount());
			ps.setTimestamp(2, t.getSubmitted());
			ps.setString(3, t.getDescription());
			ps.setInt(4, t.getAuthorId());
			ps.setInt(5, t.getTypeId().getId());

			updated = ps.executeUpdate();

		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		return updated;
	}

	@Override
	public List<Reimbursement> findAll() {
		List<Reimbursement> reimbs = new ArrayList<>();
		String sql = "SELECT * FROM get_reimbursements();";

		try (Connection conn = ConnectionUtil.getInstance().getConnection();
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()) {
			while (rs.next()) {
				reimbs.add(new Reimbursement(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getBigDecimal(4),
						rs.getTimestamp(5), rs.getTimestamp(6), rs.getString(7), rs.getBytes(8),
						new ReimbursementStatus(rs.getInt(9)), new ReimbursementType(rs.getInt(10))));
			}
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}

		return reimbs;
	}

	@Override
	public Reimbursement findById(Integer i) {
		return null;
//		Reimbursement r = new Reimbursement();
//		String sql = "SELECT * FROM get_reimbursement_by_id(?);";
//
//		try (Connection conn = ConnectionUtil.getInstance().getConnection();
//				PreparedStatement ps = conn.prepareStatement(sql)) {
//			ps.setInt(1, i);
//
//			ResultSet rs = ps.executeQuery();
//			while (rs.next()) {
//				r = new Reimbursement(rs.getInt(1), rs.getInt(2), rs.getBigDecimal(3),
//						rs.getTimestamp(4), rs.getTimestamp(5), rs.getString(6), rs.getBytes(7),
//						new ReimbursementStatus(rs.getInt(8)), new ReimbursementType(rs.getInt(9)));
//			}
//			rs.close();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//
//		return r;
	}

//	public List<Reimbursement> findByAuthorId(Integer i) {
//		List<Reimbursement> reimbs = new ArrayList<>();
//		String sql = "SELECT * FROM get_reimbursements_by_author_id(?);";
//
//		try (Connection conn = ConnectionUtil.getInstance().getConnection();
//				PreparedStatement ps = conn.prepareStatement(sql)) {
//			ps.setInt(1, i);
//
//			ResultSet rs = ps.executeQuery();
//			while (rs.next()) {
//				reimbs.add(new Reimbursement(rs.getInt(1), rs.getInt(2), rs.getBigDecimal(3),
//						rs.getTimestamp(4), rs.getTimestamp(5), rs.getString(6), rs.getBytes(7),
//						new ReimbursementStatus(rs.getInt(8)), new ReimbursementType(rs.getInt(9))));
//			}
//			rs.close();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//
//		return reimbs;
//	}

	@Override
	public int update(Reimbursement t) {
		return 0;
//		int updated = 0;
//		String sql = "UPDATE reimbursement SET amount = ?, description = ?, receipt = ?, type_id = ?"
//				+ "WHERE id = ?;";
//
//		try (Connection conn = ConnectionUtil.getInstance().getConnection();
//				PreparedStatement ps = conn.prepareStatement(sql)) {
//			ps.setBigDecimal(1, t.getAmount());
//			ps.setString(2, t.getDescription());
//			ps.setBytes(3, t.getReceipt());
//			ps.setInt(4, t.getTypeId().getId());
//			ps.setInt(5, t.getId());
//
//			updated = ps.executeUpdate();
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return updated;
	}

	@Override
	public int delete(Integer i) {
		logger.info(String.format("Deleting request - ID: %d", i));

		int updated = 0;
		String sql = "DELETE FROM reimbursement r WHERE r.id = ?;";

		try (Connection conn = ConnectionUtil.getInstance().getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setInt(1, i);
			updated = ps.executeUpdate();
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}

		return updated;
	}
	
	public int approveDeny(Reimbursement r) {
		logger.info(String.format("Resolving request: %d", r.getId()));

		int updated = 0;
		String sql = "UPDATE reimbursement SET status_id = ?, resolved = ?, resolver = ? WHERE id = ?;";
		
		try (Connection conn = ConnectionUtil.getInstance().getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setInt(1, r.getStatusId().getId());
			ps.setTimestamp(2, r.getResolved());
			ps.setInt(3, r.getResolverId());
			ps.setInt(4, r.getId());

			updated = ps.executeUpdate();

		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		
		return updated;
	}

}
