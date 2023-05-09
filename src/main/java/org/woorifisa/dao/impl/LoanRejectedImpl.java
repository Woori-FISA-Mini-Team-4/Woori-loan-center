package org.woorifisa.dao.impl;
​
import org.woorifisa.dao.LoanRejectedDao;
import org.woorifisa.jdbc.DBConnection;
import org.woorifisa.model.LoanPending;
import org.woorifisa.model.LoanRejected;
​
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
​
public class LoanRejectedImpl implements LoanRejectedDao {
​
  public LoanRejected buildLoanRejected(ResultSet resultSet) throws Exception {
    int id = resultSet.getInt("id");
    int userId = resultSet.getInt("user_id");
    String productCode = resultSet.getString("product_code");
    BigDecimal amount = resultSet.getBigDecimal("amount");
    String sendDate = resultSet.getString("send_date");
    String reason = resultSet.getString("reason");
    String description = resultSet.getString("description");
​
    return LoanRejected.builder()
        .id(id)
        .userId(userId)
        .productCode(productCode)
        .amount(amount)
        .sendDate(sendDate)
        .reason(reason)
        .description(description)
        .build();
  }
​
  @Override
  public List<LoanRejected> findAllLoanRejecteds() throws Exception {
    String sql = "SELECT * FROM loan_rejected";
    List<LoanRejected> loanRejecteds = new ArrayList<>();
​
    try (Connection connection = DBConnection.connect();
        PreparedStatement pstmt = connection.prepareStatement(sql)) {
      ResultSet resultSet = pstmt.executeQuery();
​
      while (resultSet.next()) {
        loanRejecteds.add(buildLoanRejected(resultSet));
      }
​
    } catch (Exception e) {
      throw new Exception("명단을 불러오는데 실패했습니다. - " + e.getMessage());
    }
    return loanRejecteds;
  }
​
  @Override
  public List<LoanRejected> findAllLoanRejectedsByUserId(int userId) throws Exception {
    String sql = "SELECT * FROM loan_rejected WHERE user_id = ?";
    List<LoanRejected> loanRejecteds = new ArrayList<>();
​
    try (Connection connection = DBConnection.connect();
        PreparedStatement pstmt = connection.prepareStatement(sql)) {
      ResultSet resultSet = pstmt.executeQuery();
​
      pstmt.setInt(1, userId);
​
      while (resultSet.next()) {
        loanRejecteds.add(buildLoanRejected(resultSet));
      }
​
    } catch (Exception e) {
      throw new Exception("명단을 불러오는데 실패했습니다. - " + e.getMessage());
    }
    return loanRejecteds;
  }
​
  @Override
  public List<LoanRejected> findAllLoanRejectedsByProductCode(String productCode) throws Exception {
    String sql = "SELECT * FROM loan_rejected WHERE product_code = ?";
    List<LoanRejected> loanRejecteds = new ArrayList<>();
​
    try (Connection connection = DBConnection.connect();
        PreparedStatement pstmt = connection.prepareStatement(sql)) {
      ResultSet resultSet = pstmt.executeQuery();
​
      pstmt.setString(1, productCode);
​
      while (resultSet.next()) {
        loanRejecteds.add(buildLoanRejected(resultSet));
      }
​
    } catch (Exception e) {
      throw new Exception("명단을 불러오는데 실패했습니다. - " + e.getMessage());
    }
    return loanRejecteds;
  }
​
  @Override
  public void insertLoanRejected(LoanPending loanPending, String description) throws Exception {
    String sql =
        "INSERT INTO loan_rejected (user_id, product_code, amount, send_date, reason, description) VALUES (?, ?, ?, ?, ?, ?)";
​
    try (Connection connection = DBConnection.connect();
        PreparedStatement pstmt = connection.prepareStatement(sql)) {
​
      pstmt.setInt(1, loanPending.getUserId());
      pstmt.setString(2, loanPending.getProductCode());
      pstmt.setBigDecimal(3, loanPending.getAmount());
      pstmt.setString(4, loanPending.getSendDate());
      pstmt.setString(5, loanPending.getReason());
      pstmt.setString(6, description);
​
      pstmt.executeUpdate();
    } catch (Exception e) {
      throw new Exception("내역 추가에 실패했습니다. - " + e.getMessage());
    }
  }
}