package basic.basicpayment.model.dto;

import basic.basicpayment.model.common.BalanceCurrency;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EstimateRequest {

    private Float amount;
    private BalanceCurrency currency;
    private String merchantId;
    private Long userId;

}
