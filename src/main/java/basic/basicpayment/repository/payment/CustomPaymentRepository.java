package basic.basicpayment.repository.payment;

import basic.basicpayment.model.payment.Payment;
import basic.basicpayment.repository.common.CustomRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CustomPaymentRepository extends CustomRepository<Payment, String> {

    @Override
    @Query(
            value = "select * from payment p where p.id = :id limit 1",
            nativeQuery = true
    )
    Payment findOneOrNull(@Param("id") String id);

}
