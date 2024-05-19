package basic.basicpayment.model;

import basic.basicpayment.model.common.BasicAuditEntity;
import basic.basicpayment.model.payment.Payment;
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
    private String id;

    @Setter
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "merchant")
    private List<Payment> payments = new ArrayList<>();

}