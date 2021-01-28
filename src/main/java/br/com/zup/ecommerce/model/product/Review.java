package br.com.zup.ecommerce.model.product;

import br.com.zup.ecommerce.model.user.User;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Min(1)
    @Max(5)
    private int note;

    @NotBlank
    private String title;

    @NotBlank
    @Size(max = 500)
    private String description;

    @NotNull
    @ManyToOne
    private Product product;

    @NotNull
    @ManyToOne
    private User author;

    public Review(int note, String title, String description, Product product, User author) {
        this.note = note;
        this.title = title;
        this.description = description;
        this.product = product;
        this.author = author;
    }

    public Review() {
    }

    public Long getId() {
        return id;
    }

    public int getNote() {
        return note;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Product getProduct() {
        return product;
    }

    public User getAuthor() {
        return author;
    }
}
