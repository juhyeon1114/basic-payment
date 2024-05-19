package basic.basicpayment.model.paymentDetails;

import basic.basicpayment.model.payment.Payment;
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

    // === Getter ===
    public String getExpireDate() {
        if (expireMonth == null || expireYear == null) return "";

        String month = expireMonth < 10 ?  "0" + expireMonth : String.valueOf(expireMonth);
        String year = expireYear < 10 ?  "0" + expireYear : String.valueOf(expireYear);
        return month + "/" + year;
    }

    // === 연관 메서드 ===
    public void updatePayment(Payment payment) {
        if (this.payment != null) this.payment.setPaymentDetails(this);
        this.payment = payment;
        if (payment != null) payment.setPaymentDetails(this);
    }

    // === 생성 메서드 ===
    public static PaymentDetails create(PaymentDetailsDTO dto) {
        PaymentDetails entity = new PaymentDetails();
        entity.setCardNumber(dto.getCardNumber());
        entity.setExpireMonth(dto.getExpireMonth());
        entity.setExpireYear(dto.getExpireYear());
        entity.setCvc(dto.getCvc());
        entity.updatePayment(dto.getPayment());
        return entity;
    }

}
