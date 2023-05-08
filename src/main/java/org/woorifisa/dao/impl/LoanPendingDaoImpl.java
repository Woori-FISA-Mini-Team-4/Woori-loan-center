package org.woorifisa.dao.impl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.woorifisa.dao.LoanPendingDao;
import org.woorifisa.jdbc.DBConnection;
import org.woorifisa.model.LoanPending;

public class LoanPendingDaoImpl implements LoanPendingDao{
	
	private LoanPending buildLoanPendings(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        int userId = resultSet.getInt("user_id");
        String productCode = resultSet.getString("product_code");
        BigDecimal amount = resultSet.getBigDecimal("amount");
        String sendDate = resultSet.getString("send_date");
        String reason = resultSet.getString("reason");
        String repaymentMethod = resultSet.getString("repayment_method");
        String period = resultSet.getString("period");
        String status = resultSet.getString("status");
        

        LoanPending loanPending = LoanPending.builder()
         .id(id)
         .userId(userId)
         .productCode(productCode)
         .amount(amount)
         .sendDate(sendDate)
         .reason(reason)
         .repaymentMethod(repaymentMethod)
         .period(period)
         .status(status)
         .build();

        return loanPending;
    }
	@Override
	public List<LoanPending> findAllLoanPendings() throws Exception {
		String sql = "SELECT * FROM loan_pending";
		
		List<LoanPending> loanPendings = new ArrayList<>();

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		
		try {
			connection = DBConnection.connect();
			pstmt = connection.prepareStatement(sql);
			resultSet = pstmt.executeQuery();
			
			while(resultSet.next()) {
				loanPendings.add(buildLoanPendings(resultSet));
				
			}
		} catch(Exception e) {
			throw new Exception("명단을 불러오는데 실패했습니다." + e.getMessage());
		} finally {
			DBConnection.exit(resultSet, pstmt, connection);
		}
		return loanPendings;
	}

	@Override
	public List<LoanPending> findAllLoanPendingsByUserId(int userId) throws Exception {
		String sql = "SELECT * FROM loan_pending WHERE user_id = ?";
		
		List<LoanPending> loanPendings = new ArrayList<>();
		
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		
		try {
			connection = DBConnection.connect();
			pstmt = connection.prepareStatement(sql);
			pstmt.setInt(1, userId);
			resultSet = pstmt.executeQuery();
			
			while(resultSet.next()) {
				loanPendings.add(buildLoanPendings(resultSet));
				
			}
		} catch(Exception e) {
			throw new Exception("명단을 불러오는데 실패했습니다." + e.getMessage());
		} finally {
			DBConnection.exit(resultSet, pstmt, connection);
		}
		return loanPendings;
	}

	@Override
	public List<LoanPending> findAllLoanPendingsByProductCode(String productCode) throws Exception {
		String sql = "SELECT * FROM loan_pending WHERE product_code = ?";
		
		List<LoanPending> loanPendings = new ArrayList<>();
		
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		
		try {
			connection = DBConnection.connect();
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, productCode);
			resultSet = pstmt.executeQuery();
						
			while(resultSet.next()) {
				loanPendings.add(buildLoanPendings(resultSet));
				
			}
		} catch(Exception e) {
			throw new Exception("명단을 불러오는데 실패했습니다." + e.getMessage());
		} finally {
			DBConnection.exit(resultSet, pstmt, connection);
		}
		return loanPendings;
	}

	@Override
	  public void insertLoanPending(LoanPending loanPending) throws Exception {
	    String sql =
	     "INSERT INTO loan_pending (user_id, product_code, amount, send_date, reason, repayment_method, period, status) VALUES (?, ?, ?, ?, ?, ?, ?)";
	    try (Connection connection = DBConnection.connect();
	         PreparedStatement pstmt = connection.prepareStatement(sql)) {
	      pstmt.setInt(1, loanPending.getUserId());
	      pstmt.setString(2, loanPending.getProductCode());
	      pstmt.setBigDecimal(3, loanPending.getAmount());
	      pstmt.setString(4, loanPending.getSendDate());
	      pstmt.setString(5, loanPending.getReason());
	      pstmt.setString(6, loanPending.getRepaymentMethod());
	      pstmt.setString(7, loanPending.getStatus());
	      pstmt.executeUpdate();
	    } catch (Exception e) {
	      throw new Exception("상품 추가에 실패 했습니다. - " + e.getMessage());
	    }
	  }

	@Override
	  public void deleteLoanPendingById(int id) throws Exception {
	    String sql = "DELETE FROM loan_pending WHERE id = ?";
	    try (Connection connection = DBConnection.connect();
	         PreparedStatement pstmt = connection.prepareStatement(sql)) {

	      pstmt.setInt(1, id);

	      pstmt.executeUpdate();
	    } catch (Exception e) {
	      throw new Exception("기록을 삭제하는데 실패했습니다. - " + e.getMessage());
	    }
	  }
}
