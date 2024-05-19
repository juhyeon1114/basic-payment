package basic.basicpayment.model.merchant;

import basic.basicpayment.model.common.BasicAuditEntity;
import basic.basicpayment.model.payment.Payment;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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

    // === Lifecycle ===
    @PrePersist
    public void prePersist() {
        id = String.valueOf(UUID.randomUUID());
    }

    // === 생성 메서드 ===
    public static Merchant create(String name) {
        Merchant entity = new Merchant();
        entity.setName(name);
        return entity;
    }

}