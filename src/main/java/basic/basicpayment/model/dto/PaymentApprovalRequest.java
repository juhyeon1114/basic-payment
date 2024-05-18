package basic.basicpayment.model.dto;

import basic.basicpayment.model.PaymentDetails;
import basic.basicpayment.model.common.BalanceCurrency;
import basic.basicpayment.model.common.PaymentMethod;
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
    private PaymentDetails paymentDetails;

}
