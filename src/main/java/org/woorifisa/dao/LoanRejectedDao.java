package org.woorifisa.dao;

import org.woorifisa.jdbc.DBConnection;
import org.woorifisa.model.LoanPending;
import org.woorifisa.model.LoanRejected;
import org.woorifisa.dao.LoanRejectedDao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface LoanRejectedDao {
    /* 대출 승인 거부 명단 조회 */
    public List<LoanRejected> findAllLoanRejecteds() throws Exception;
    
    /* 특정 승인 거부 기록 조회 - 유저 아이디 */
    List<LoanRejected> findAllLoanRejectedsByUserId(int userId) throws Exception;


    /* 특정 승인 거부 기록 조회 - 상품 코드 */
    List<LoanRejected> findAllLoanRejectedsByProductCode(String productCode) throws Exception;

    /* 승인 거부 기록 추가 */
    void insertLoanRejected(LoanPending loanPending, String description) throws Exception;
}


public class LoanRejectedDaoIml implements LoanRejectedDao{
	private LoanRejected buildLoan(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        int userId = resultSet.getInt("use_id");
        String productCode = resultSet.getString("product_code");
        BigDecimal amount = resultSet.getBigDecimal("amount");
        String sendDate = resultSet.getString("send_date");
        String reason = resultSet.getString("reason");
        String description = resultSet.getString("description");

        LoanRejected loanRejected1 = LoanRejected.builder()
         .id(id)
         .userId(userId)
         .productCode(productCode)
         .amount(amount)
         .sendDate(sendDate)
         .reason(reason)
         .description(description)
         .build();
        

        return loanRejected1;
    }
    /* 대출 승인 거부 명단 조회 */
	@Override
    public List<LoanRejected> findAllLoanRejecteds() throws Exception{
    	String sql = "SELECT * FROM loan";
    	Connection connection = null;
        PreparedStatement pstmt = null;
       ResultSet resultSet = null;
       
       
    	List<LoanRejected> loanRejecteds = new ArrayList<>();

        try  {connection = DBConnection.connect();
        	 pstmt = connection.prepareStatement(sql);
             resultSet = pstmt.executeQuery();

            while (resultSet.next()) {
                loanRejecteds.add(buildLoan(resultSet));
            }

        } catch (Exception e) {
            throw new Exception("명단을 불러오는데 실패했습니다. - " + e.getMessage());
        } finally {
        	DBConnection.exit(resultSet, pstmt, connection);
        }
        return loanRejecteds;
        
//        List<LoanRejected> loanRejecteds = new ArrayList<>();
//        // 데이터베이스에서 대출 승인 거부 명단 조회하는 코드 작성
//        // 조회 결과를 loanRejecteds 리스트에 추가 ?? 이거 맞나?
//        return loanRejecteds;

    }
	
	@Override
    /* 특정 승인 거부 기록 조회 - 유저 아이디 */
    public List<LoanRejected> findAllLoanRejectedsByUserId(int userId) throws Exception{
		String sql = "SELECT * FROM loan WHERE userId = ?";
        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet resultSet = null;

        List<LoanRejected> loanRejecteds = new ArrayList<>();

        try {
            connection = DBConnection.connect();
            pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, userId);
            resultSet = pstmt.executeQuery();

            while (resultSet.next()) {
                loanRejecteds.add(buildLoan(resultSet));
            }

        } catch (Exception e) {
            throw new Exception("특정 승인 거부 기록을 불러오는데 실패했습니다. - " + e.getMessage());
        } finally {
            DBConnection.exit(resultSet, pstmt, connection);
        }

        return loanRejecteds;
    }
	
	/* 특정 승인 거부 기록 조회 - 상품 코드 */
	@Override
	public List<LoanRejected> findAllLoanRejectedsByProductCode(String productCode) throws Exception{
	    String sql = "SELECT * FROM loan WHERE productCode = ?";
	    Connection connection = null;
	    PreparedStatement pstmt = null;
	    ResultSet resultSet = null;

	    List<LoanRejected> loanRejecteds = new ArrayList<>();

	    try {
	        connection = DBConnection.connect();
	        pstmt = connection.prepareStatement(sql);
	        pstmt.setString(1, productCode);
	        resultSet = pstmt.executeQuery();

	        while (resultSet.next()) {
	            loanRejecteds.add(buildLoan(resultSet));
	        }

	    } catch (Exception e) {
	        throw new Exception("특정 승인 거부 기록을 불러오는데 실패했습니다. - " + e.getMessage());
	    } finally {
	        DBConnection.exit(resultSet, pstmt, connection);
	    }

	    return loanRejecteds;
	}
	
	/* 승인 거부 기록 추가 */
	@Override
	public void insertLoanRejected(LoanPending loanPending, String description) throws Exception {
	    String sql = "INSERT INTO loan(user_id, product_code, amount, send_date, reason, description) VALUES (?, ?, ?, ?, ?, ?)";
	    Connection connection = null;
	    PreparedStatement pstmt = null;
	    
	    //하나씩 넣어줘!!
	    try {
	        connection = DBConnection.connect();
	        pstmt = connection.prepareStatement(sql);
	        pstmt.setInt(1, loanPending.getUserId());
	        pstmt.setString(2, loanPending.getProductCode());
	        pstmt.setBigDecimal(3, loanPending.getAmount());
	        pstmt.setString(4, loanPending.getSendDate());
	        pstmt.setString(5, loanPending.getReason());
	        pstmt.setString(6, description);
	        pstmt.executeUpdate();
	    } catch (Exception e) {
	        throw new Exception("승인 거부 기록 추가에 실패했습니다. - " + e.getMessage());
	    } finally {
	        DBConnection.exit(null, pstmt, connection);
	    }
	}

}
