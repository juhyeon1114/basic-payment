package basic.basicpayment.domain.appUser.model;

import basic.basicpayment.domain.balance.model.Balance;
import basic.basicpayment.domain.payment.model.Payment;
import basic.basicpayment.global.model.BasicAuditEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class AppUser extends BasicAuditEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Setter
    private String email;

    @JsonIgnore
    @OneToMany(mappedBy = "appUser")
    private List<Balance> balances = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "appUser")
    private List<Payment> payments = new ArrayList<>();

}
