package org.woorifisa.controller;

import lombok.AllArgsConstructor;
import org.woorifisa.dao.impl.LoanRejectedImpl;
import org.woorifisa.model.LoanPending;
import org.woorifisa.model.LoanRejected;

import java.util.List;

@AllArgsConstructor
public class LoanRejectedController {
  private LoanRejectedImpl loanRejected;
  /* 대출 승인 거부 명단 조회 */
  public List<LoanRejected> findAllLoanRejecteds() throws Exception {
    return loanRejected.findAllLoanRejecteds();
  }

  /* 특정 승인 거부 기록 조회 - 유저 아이디 */
  public List<LoanRejected> findAllLoanRejectedsByUserId(int userId) throws Exception {
    return loanRejected.findAllLoanRejectedsByUserId(userId);
  }

  /* 특정 승인 거부 기록 조회 - 상품 코드 */
  public List<LoanRejected> findAllLoanRejectedsByProductCode(String productCode) throws Exception {
    return loanRejected.findAllLoanRejectedsByProductCode(productCode);
  }

  /* 승인 거부 기록 추가 */
  public void insertLoanRejected(LoanPending loanPending, String description) throws Exception {
    loanRejected.insertLoanRejected(loanPending, description);
  }
}
