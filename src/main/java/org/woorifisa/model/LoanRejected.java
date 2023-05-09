package org.woorifisa.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class LoanRejected {

	private int id;
	private int userId;
	private String productCode;
	private BigDecimal amount;
	private String sendDate;
	private String reason;
	private String description;

}