package basic.basicpayment.repository;

import basic.basicpayment.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MerchantRepository extends JpaRepository<Payment.Merchant, Long> {
}
