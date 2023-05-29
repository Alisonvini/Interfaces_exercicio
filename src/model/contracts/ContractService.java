package model.contracts;

import java.time.LocalDate;
import java.time.LocalDateTime;

import model.entities.Contract;
import model.entities.Installment;

public class ContractService {

	private PaymentService onlinePaymentService;

	public ContractService(PaymentService onlinePaymentService) {
		this.onlinePaymentService = onlinePaymentService;
	}

	public void processContract(Contract contract, int months) {

		double basicQuota = contract.getTotalValue() / months;

		for (int i = 1; i <= months; i++) {
			LocalDate dueDate = contract.getDate().plusMonths(i);

			double interest = onlinePaymentService.interest(basicQuota, i);
			double fee = onlinePaymentService.paymentFee(basicQuota + interest);
			double quota = basicQuota + interest + fee;
			
			contract.getInstallment().add(new Installment(dueDate, quota));

		}

	}

}
