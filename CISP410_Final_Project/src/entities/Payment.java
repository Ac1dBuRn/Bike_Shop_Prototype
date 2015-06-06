package entities;

import java.util.Date;

public class Payment
{
	public Long paymentId;
	public String paymentStatus;
	public Date paymentDate;
	public Double taxPercentage;
	public Double cashPaymentAmt;
	public String ccName;
	public String ccNum;
	public String ccType;
	public Date ccExpirationDate;
}
