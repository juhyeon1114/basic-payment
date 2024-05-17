package basic.basicpayment.model;

import basic.basicpayment.model.common.BasicAuditEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
public class PaymentDetails extends BasicAuditEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    private String cardNumber;

    @Setter
    private Integer expireMonth;

    @Setter
    private Integer expireYear;

    @Setter
    private String cvc;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "payment_id")
    private Payment payment;

    public String getExpireDate() {
        if (expireMonth == null || expireYear == null) return "";

        String month = expireMonth < 10 ?  "0" + expireMonth : String.valueOf(expireMonth);
        String year = expireYear < 10 ?  "0" + expireYear : String.valueOf(expireYear);
        return month + "/" + year;
    }

}
