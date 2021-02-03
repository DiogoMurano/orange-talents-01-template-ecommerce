package br.com.zup.ecommerce.model.buy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;

@Entity
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private Purchase purchase;

    @NotNull
    @Positive
    private long transactionId;

    @NotNull
    @Enumerated(EnumType.STRING)
    private PaymentStatus status;

    @NotNull
    private LocalDateTime createdAt = LocalDateTime.now();

    public Payment(Purchase purchase, long transactionId, PaymentStatus status) {
        this.purchase = purchase;
        this.transactionId = transactionId;
        this.status = status;
    }

    public Payment() {
    }

    public Long getId() {
        return id;
    }

    public Purchase getPurchase() {
        return purchase;
    }

    public long getTransactionId() {
        return transactionId;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

}
