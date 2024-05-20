package basic.basicpayment.model.payment;

import basic.basicpayment.model.common.BalanceCurrency;
import basic.basicpayment.model.common.PaymentMethod;
import basic.basicpayment.model.paymentDetails.PaymentDetailsApprovalRequest;
import basic.basicpayment.utils.FeeUtils;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentApprovalRequest {

    @NotNull(message = "유저 ID를 입력해주세요.")
    private Long userId;

    @NotNull(message = "금액을 입력해주세요.")
    @Positive(message = "금액은 0보다 커야합니다.")
    private Float amount;

    @NotNull(message = "화폐 단위를 입력해주세요.")
    private BalanceCurrency currency;

    @NotBlank(message = "상점을 선택해주세요.")
    private String merchantId;

    @NotNull(message = "결제 수단을 입력해주세요.")
    private PaymentMethod paymentMethod;

    @Valid
    private PaymentDetailsApprovalRequest paymentDetails;

    public Float amountTotal() {
        return FeeUtils.addFee(amount);
    }

}
