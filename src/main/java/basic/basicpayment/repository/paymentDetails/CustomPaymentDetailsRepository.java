package basic.basicpayment.repository.paymentDetails;

import basic.basicpayment.model.paymentDetails.PaymentDetails;
import basic.basicpayment.repository.common.CustomRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CustomPaymentDetailsRepository extends CustomRepository<PaymentDetails, String> {

    @Override
    @Query(
            value = "select * from payment_details pd where pd.id = :id limit 1",
            nativeQuery = true
    )
    PaymentDetails findOneOrNull(@Param("id") String id);

    @Query(
            value = "select * from payment_details pd where pd.payment_id=:paymentId limit 1",
            nativeQuery = true
    )
    PaymentDetails findOneByPaymentId(@Param("paymentId") String paymentId);
}
