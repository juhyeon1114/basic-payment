package basic.basicpayment.model.payment;

import basic.basicpayment.model.AppUser;
import basic.basicpayment.model.Merchant;
import basic.basicpayment.model.common.BalanceCurrency;
import basic.basicpayment.model.common.PaymentMethod;
import basic.basicpayment.model.common.PaymentStatus;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PaymentDTO {

    private PaymentMethod paymentMethod;
    private Float amount;
    private BalanceCurrency currency;
    private PaymentStatus status;
    private String reason;
    private Merchant merchant;
    private AppUser appUser;

    public static PaymentDTO reqToDto(PaymentApprovalRequest req, Merchant merchant, AppUser appUser) {
        return new PaymentDTO(
                req.getPaymentMethod(),
                req.getAmount(),
                req.getCurrency(),
                PaymentStatus.approved,
                null,
                merchant,
                appUser
        );
    }


}
