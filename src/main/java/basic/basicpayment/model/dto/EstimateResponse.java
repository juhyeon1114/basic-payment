package basic.basicpayment.model.dto;

import basic.basicpayment.model.common.BalanceCurrency;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class EstimateResponse {

    private Float estimateTotal;
    private Float fees;
    private BalanceCurrency currency;

}
