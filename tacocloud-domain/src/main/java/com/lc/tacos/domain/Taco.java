package com.lc.tacos.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author DELL
 * @date 2022/4/19 17:00
 */
@Data
@Entity
public class Taco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 5, message = "名字必须至少5个字符长")
    private String name;

    private Date createdAt;

    @ManyToMany(targetEntity = Ingredient.class, fetch = FetchType.EAGER)
    @NotEmpty(message = "你必须选择至少一种配料")
    private List<Ingredient> ingredients = new ArrayList<>();

    @PrePersist
    void createdAt() {
        this.createdAt = new Date();
    }
}
