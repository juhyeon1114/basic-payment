package basic.basicpayment.domain.payment.model;

import basic.basicpayment.domain.appUser.model.AppUser;
import basic.basicpayment.domain.merchant.model.Merchant;
import basic.basicpayment.domain.paymentDetails.model.PaymentDetails;
import basic.basicpayment.global.model.BalanceCurrency;
import basic.basicpayment.global.model.BasicAuditEntity;
import basic.basicpayment.global.model.PaymentMethod;
import basic.basicpayment.global.model.PaymentStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
public class Payment extends BasicAuditEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    @Setter
    private Float amount;

    @Setter
    @Enumerated(EnumType.STRING)
    private BalanceCurrency currency;

    @Setter
    @Enumerated(EnumType.STRING)
    private PaymentStatus status;

    @Setter
    private String reason;

    @JsonIgnore
    @OneToOne(mappedBy = "payment")
    private PaymentDetails paymentDetails;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "merchant_id")
    private Merchant merchant;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private AppUser appUser;

    // === 연관 메서드 ===
    public void updateMerchant(Merchant merchant) {
        if (this.merchant != null) this.merchant.getPayments().remove(this);
        this.merchant = merchant;
        if (merchant != null) merchant.getPayments().add(this);
    }

    public void updateUser(AppUser appUser) {
        if (this.appUser != null) this.appUser.getPayments().remove(this);
        this.appUser = appUser;
        if (appUser != null) appUser.getPayments().add(this);
    }

}
