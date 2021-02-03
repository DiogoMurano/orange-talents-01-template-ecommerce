package br.com.zup.ecommerce.model.buy;

import br.com.zup.ecommerce.model.product.Product;
import br.com.zup.ecommerce.model.user.User;
import br.com.zup.ecommerce.provider.gateway.GatewayType;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.Collections;
import java.util.Set;

@Entity
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Positive
    private int quantity;

    @NotNull
    @Enumerated(EnumType.STRING)
    private GatewayType gatewayType;

    @NotNull
    @Enumerated(EnumType.STRING)
    private PurchaseStatus purchaseStatus = PurchaseStatus.STARTED;

    @NotNull
    @ManyToOne
    private Product product;

    @NotNull
    @ManyToOne
    private User author;

    @OneToMany
    @NotNull
    private Set<Payment> payments = Collections.emptySet();

    public Purchase(int quantity, GatewayType gatewayType, Product product, User author) {
        this.quantity = quantity;
        this.gatewayType = gatewayType;
        this.product = product;
        this.author = author;
    }

    public Purchase() {
    }

    public Long getId() {
        return id;
    }

    public int getQuantity() {
        return quantity;
    }

    public GatewayType getGatewayType() {
        return gatewayType;
    }

    public PurchaseStatus getPurchaseStatus() {
        return purchaseStatus;
    }

    public User getAuthor() {
        return author;
    }

    public Product getProduct() {
        return product;
    }

    public Set<Payment> getPayments() {
        return payments;
    }

    @Override
    public String toString() {
        return " quantidade: " + quantity +
                "\n método de pagamento: " + gatewayType +
                "\n status: " + purchaseStatus +
                "\n prooduto: " + product.toString() +
                "\n autor: " + author;
    }
}
