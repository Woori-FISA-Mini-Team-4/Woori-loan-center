package org.woorifisa.dao;

import org.woorifisa.model.LoanPending;

import java.util.List;

public interface LoanPendingDao {
  /* 대출 승인 대기 명단 조회 */
  List<LoanPending> findAllLoanPendings() throws Exception;

  /* 특정 승인 대기 기록 조회 - 유저 아이디 */
  List<LoanPending> findAllLoanPendingsByUserId(int userId) throws Exception;

  /* 특정 승인 대기 기록 조회 - 상품 코드 */
  List<LoanPending> findAllLoanPendingsByProductCode(String productCode) throws Exception;

  /* 승인 대기 기록 추가 */
  void insertLoanPending(LoanPending loanPending) throws Exception;

  /* 승인 대기 기록 삭제 */
  void deleteLoanPendingById(int id) throws Exception;
}