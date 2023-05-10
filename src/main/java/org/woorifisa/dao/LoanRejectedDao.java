package org.woorifisa.dao;

import org.woorifisa.model.LoanPending;
import org.woorifisa.model.LoanRejected;

import java.util.List;

public interface LoanRejectedDao {
    /* 대출 승인 거부 명단 조회 */
    List<LoanRejected> findAllLoanRejecteds() throws Exception;
    
    /* 특정 승인 거부 기록 조회 - 유저 아이디 */
    List<LoanRejected> findAllLoanRejectedsByUserId(int userId) throws Exception;


    /* 특정 승인 거부 기록 조회 - 상품 코드 */
    List<LoanRejected> findAllLoanRejectedsByProductCode(String productCode) throws Exception;

    /* 승인 거부 기록 추가 */
    void insertLoanRejected(LoanPending loanPending, String description) throws Exception;
}