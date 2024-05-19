package basic.basicpayment.repository.paymentDetails;

import basic.basicpayment.model.paymentDetails.PaymentDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentDetailsRepository extends JpaRepository<PaymentDetails, Long>, CustomPaymentDetailsRepository {
}
