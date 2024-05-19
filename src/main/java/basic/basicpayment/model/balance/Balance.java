package basic.basicpayment.model.balance;

import basic.basicpayment.model.appUser.AppUser;
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

    public void updateBalance(Float newBalance) {
        balance += newBalance;
    }

    // === 연관 메서드 ===
    public void updateUser(AppUser appUser) {
        if (this.appUser != null) this.appUser.getBalances().remove(this);
        this.appUser = appUser;
        if (appUser != null) appUser.getBalances().add(this);
    }

    // === 생성 메서드 ===
    public static Balance create(BalanceDTO dto) {
        Balance entity = new Balance();
        entity.setBalance(dto.getBalance());
        entity.setCurrency(dto.getCurrency());
        entity.updateUser(dto.getAppUser());
        return entity;
    }



}
