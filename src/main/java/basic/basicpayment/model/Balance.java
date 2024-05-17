package basic.basicpayment.model;

import basic.basicpayment.model.common.BalanceCurrency;
import basic.basicpayment.model.common.BasicAuditEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
public class Balance extends BasicAuditEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    private Float balance;

    @Setter
    @Enumerated(EnumType.STRING)
    private BalanceCurrency currency;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private AppUser appUser;

    // === 연관 메서드 ===
    public void updateUser(AppUser appUser) {
        if (this.appUser != null) this.appUser.getBalances().remove(this);
        this.appUser = appUser;
        if (appUser != null) appUser.getBalances().add(this);
    }

}
