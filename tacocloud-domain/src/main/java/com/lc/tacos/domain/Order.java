package com.lc.tacos.domain;

import lombok.Data;
import org.hibernate.validator.constraints.CreditCardNumber;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author DELL
 * @date 2022/4/19 17:06
 */
@Data
@Entity
@Table(name = "Taco_Order")
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date placedAt;

    @ManyToOne
    private User user;

    @NotBlank(message = "配送名字是必须的")
    private String deliveryName;

    @NotBlank(message = "街道地址是必须的")
    private String deliveryStreet;

    @NotBlank(message = "城市名称是必须的")
    private String deliveryCity;

    @NotBlank(message = "省份是必须的")
    private String deliveryState;

    @NotBlank(message = "邮政编码是必须的")
    private String deliveryZip;

    @CreditCardNumber(message = "不是有效的信用卡账号（16位置数字，不包含空格、字符以及其他非数字）")
    private String ccNumber;

    @Pattern(regexp = "^(0[1-9]|1[0-2])([\\/])([1-9][0-9])$",
            message = "必须是：MM/YY格式")
    private String ccExpiration;

    @Digits(integer = 3, fraction = 0, message = "无效的CVV（信用卡三位数字验证码）")
    private String ccCVV;

    @ManyToMany(targetEntity = Taco.class)
    private List<Taco> tacos = new ArrayList<>();

    public void addDesign(Taco design) {
        this.tacos.add(design);
    }

    @PrePersist
    void placedAt() {
        this.placedAt = new Date();
    }
}
