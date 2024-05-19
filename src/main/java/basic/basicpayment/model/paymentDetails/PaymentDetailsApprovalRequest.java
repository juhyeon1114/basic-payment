package basic.basicpayment.model.paymentDetails;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDetailsApprovalRequest {

    private String cardNumber;
    private String expireDate;
    private String cvc;

    protected Integer getExpireMonth() {
        String exp = getExpireDate();
        return Integer.valueOf(exp.split("/")[0]);
    }

    protected Integer getExpireYear() {
        String exp = getExpireDate();
        return Integer.valueOf(exp.split("/")[1]);
    }

}
