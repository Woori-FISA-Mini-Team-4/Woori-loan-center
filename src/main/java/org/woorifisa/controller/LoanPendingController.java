package org.woorifisa.controller;

import lombok.AllArgsConstructor;
import org.woorifisa.dao.impl.LoanPendingDaoImpl;
import org.woorifisa.model.LoanPending;

import java.util.List;

@AllArgsConstructor
public class LoanPendingController {
  private LoanPendingDaoImpl loanPendingDao;

  /* 대출 승인 대기 명단 조회 */
  public List<LoanPending> findAllLoanPendings() throws Exception {
    return loanPendingDao.findAllLoanPendings();
  }

  /* 특정 승인 대기 기록 조회 - 유저 아이디 */
  public List<LoanPending> findAllLoanPendingsByUserId(int userId) throws Exception {
    return loanPendingDao.findAllLoanPendingsByUserId(userId);
  }

  /* 특정 승인 대기 기록 조회 - 상품 코드 */
  public List<LoanPending> findAllLoanPendingsByProductCode(String productCode) throws Exception {
    return loanPendingDao.findAllLoanPendingsByProductCode(productCode);
  }

  /* 승인 대기 기록 추가 */
  public void insertLoanPending(LoanPending loanPending) throws Exception {
    loanPendingDao.insertLoanPending(loanPending);
  }

  /* 승인 대기 기록 삭제 */
  public void deleteLoanPendingById(int id) throws Exception {
    loanPendingDao.deleteLoanPendingById(id);
  }
}
