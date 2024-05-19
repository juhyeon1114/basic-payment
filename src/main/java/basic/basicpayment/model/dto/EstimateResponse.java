package basic.basicpayment.model.dto;

import basic.basicpayment.model.common.BalanceCurrency;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EstimateResponse {

    private Float estimateTotal;
    private Float fees;
    @Setter private BalanceCurrency currency;

    public void setEstimateTotal(Float amount) {
        this.fees = (float) (amount * 0.03);
        this.estimateTotal = amount + this.fees;
    }

    public static EstimateResponse create(Float amount, BalanceCurrency currency) {
        EstimateResponse instance = new EstimateResponse();
        instance.setEstimateTotal(amount);
        instance.setCurrency(currency);
        return instance;
    }

}
