package basic.basicpayment.model.payment;

import basic.basicpayment.model.common.BalanceCurrency;
import basic.basicpayment.model.common.PaymentMethod;
import basic.basicpayment.model.paymentDetails.PaymentDetailsApprovalRequest;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentApprovalRequest {

    private Long userId;
    private Float amount;
    private BalanceCurrency currency;
    private String merchantId;
    private PaymentMethod paymentMethod;
    private PaymentDetailsApprovalRequest paymentDetails;

}
