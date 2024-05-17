package basic.basicpayment.domain.merchant.model;

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
public class Merchant extends BasicAuditEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "merchant")
    private List<Payment> payments = new ArrayList<>();

}
