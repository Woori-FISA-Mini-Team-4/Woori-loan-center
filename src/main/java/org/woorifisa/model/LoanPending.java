package org.woorifisa.model;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoanPending {
	private int id;
	private int userId;
	private String productCode;
	private BigDecimal amount;
	private String sendDate;
	private String reason;
	private String period;
	private String repaymentMethod;
	private String status;
}
