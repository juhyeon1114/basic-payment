package basic.basicpayment.model.dto;

import basic.basicpayment.model.common.BalanceCurrency;
import basic.basicpayment.model.common.PaymentStatus;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class PaymentApprovalResponse {

    private String paymentId;
    private PaymentStatus status;
    private Float amountTotal;
    private BalanceCurrency currency;
    private LocalDateTime timestamp;

}
