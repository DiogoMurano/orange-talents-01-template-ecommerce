package br.com.zup.ecommerce.model.product;

import br.com.zup.ecommerce.model.user.User;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
public class Ask {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    private String title;

    @NotNull
    private LocalDateTime createdAt = LocalDateTime.now();

    @NotNull
    @ManyToOne
    private User author;

    @NotNull
    @ManyToOne
    private Product product;

    public Ask(String title, User author, Product product) {
        this.title = title;
        this.author = author;
        this.product = product;

        this.createdAt = LocalDateTime.now();
    }

    public Ask() {
    }

    public String getLink() {
        return "mercadolivre.com.br/product/ask/" + id;
    }

    public String getTitle() {
        return title;
    }

    public User getAuthor() {
        return author;
    }

    public Product getProduct() {
        return product;
    }
}
