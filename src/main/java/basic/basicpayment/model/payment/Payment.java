package basic.basicpayment.model.payment;

import basic.basicpayment.model.appUser.AppUser;
import basic.basicpayment.model.merchant.Merchant;
import basic.basicpayment.model.common.BalanceCurrency;
import basic.basicpayment.model.common.BasicAuditEntity;
import basic.basicpayment.model.common.PaymentMethod;
import basic.basicpayment.model.common.PaymentStatus;
import basic.basicpayment.model.paymentDetails.PaymentDetails;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;


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

    @Setter
    @JsonIgnore
    @OneToOne(mappedBy = "payment")
    private PaymentDetails paymentDetails;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "merchant_id")
    private Merchant merchant;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private AppUser appUser;

    // === Lifecycle ===
    @PrePersist
    public void prePersist() {
        id = String.valueOf(UUID.randomUUID());
    }

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

    // === 생성 메서드 ===
    public static Payment create(PaymentDTO dto) {
        Payment entity = new Payment();
        entity.setPaymentMethod(dto.getPaymentMethod());
        entity.setAmount(dto.getAmount());
        entity.setCurrency(dto.getCurrency());
        entity.setStatus(dto.getStatus());
        entity.setReason(dto.getReason());
        entity.updateMerchant(dto.getMerchant());
        entity.updateUser(dto.getAppUser());
        return entity;
    }

}
