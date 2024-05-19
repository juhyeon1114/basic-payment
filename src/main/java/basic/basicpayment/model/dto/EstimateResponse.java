package basic.basicpayment.model.dto;

import basic.basicpayment.model.common.BalanceCurrency;
import basic.basicpayment.utils.FeeUtils;
import basic.basicpayment.utils.MoneyUtils;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EstimateResponse {

    private Float estimateTotal;
    private Float fees;
    @Setter private BalanceCurrency currency;

    public void setEstimateTotal(Float amount) {
        this.fees = FeeUtils.getFees(amount);
        this.estimateTotal = FeeUtils.addFee(amount);
    }

    public Float getEstimateTotal() {
        return MoneyUtils.floorMoney(estimateTotal, currency);
    }

    public static EstimateResponse create(Float amount, BalanceCurrency currency) {
        EstimateResponse instance = new EstimateResponse();
        instance.setEstimateTotal(amount);
        instance.setCurrency(currency);
        return instance;
    }

}
