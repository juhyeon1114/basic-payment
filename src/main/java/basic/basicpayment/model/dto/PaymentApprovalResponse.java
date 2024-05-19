package basic.basicpayment.model.dto;

import basic.basicpayment.model.payment.Payment;
import basic.basicpayment.model.common.BalanceCurrency;
import basic.basicpayment.model.common.PaymentStatus;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PaymentApprovalResponse {

    private String paymentId;
    private PaymentStatus status;
    private Float amountTotal;
    private BalanceCurrency currency;
    private LocalDateTime timestamp;

    public static PaymentApprovalResponse success(Payment payment) {
        return new PaymentApprovalResponse(
                payment.getId(),
                payment.getStatus(),
                payment.getAmount(),
                payment.getCurrency(),
                payment.getCreatedAt()
        );
    }

}
