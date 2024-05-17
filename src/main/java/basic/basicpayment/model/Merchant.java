package basic.basicpayment.model;

import basic.basicpayment.model.common.BasicAuditEntity;
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