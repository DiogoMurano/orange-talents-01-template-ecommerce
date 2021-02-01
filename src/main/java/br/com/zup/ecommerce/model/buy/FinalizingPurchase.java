package br.com.zup.ecommerce.model.buy;

import br.com.zup.ecommerce.model.product.Product;
import br.com.zup.ecommerce.model.user.User;
import br.com.zup.ecommerce.provider.gateway.GatewayType;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Entity
public class FinalizingPurchase {

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
    @ManyToOne
    private Product product;

    @NotNull
    @ManyToOne
    private User author;

    public FinalizingPurchase(int quantity, GatewayType gatewayType, Product product, User author) {
        this.quantity = quantity;
        this.gatewayType = gatewayType;
        this.product = product;
        this.author = author;
    }

    public FinalizingPurchase() {
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

    public User getAuthor() {
        return author;
    }

    public Product getProduct() {
        return product;
    }
}
