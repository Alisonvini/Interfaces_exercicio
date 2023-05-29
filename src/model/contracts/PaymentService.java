package model.contracts;

public interface PaymentService {
	
	double paymentFee(double amount);
	double interest(double amount, int months);

}
