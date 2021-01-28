package br.com.zup.ecommerce.model.product;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class ProductImage {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @ManyToOne
    private Product product;

    @NotBlank
    private String link;

    public ProductImage(Product product, String link) {
        this.product = product;
        this.link = link;
    }

    public ProductImage() {
    }

    public Long getId() {
        return id;
    }

    public Product getProduct() {
        return product;
    }

    public String getLink() {
        return link;
    }
}
