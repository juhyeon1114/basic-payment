package basic.basicpayment.model;

import basic.basicpayment.model.common.BalanceCurrency;
import basic.basicpayment.model.common.BasicAuditEntity;
import basic.basicpayment.model.common.PaymentMethod;
import basic.basicpayment.model.common.PaymentStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
public class Payment extends BasicAuditEntity {

    @Id
    private String id;

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
