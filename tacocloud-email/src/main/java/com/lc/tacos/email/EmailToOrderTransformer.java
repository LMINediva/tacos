package com.lc.tacos.email;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import org.apache.commons.text.similarity.LevenshteinDistance;
import org.springframework.integration.mail.transformer.AbstractMailMessageTransformer;
import org.springframework.integration.support.AbstractIntegrationMessageBuilder;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.stereotype.Component;
import jakarta.mail.Message;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>处理电子邮件内容为玉米卷订单</p>
 * <li>订单的电子邮箱是发送者的电子邮箱</li>
 * <li>电子邮件主题*必须*为“TACO ORDER”，否则将被忽略</li>
 * <li>电子邮件的每一行都以玉米卷设计的名称开头，后面跟冒号，
 * 然后是一个或多个以逗号分隔的列表中的成分名称。</li>
 *
 * <p>使用莱文斯坦距离算法将原料名与已知的原料集进行匹配。
 * 例如：“beef”将匹配“GROUND beef”并映射到“GRBF”；
 * “com”将匹配“corn Tortilla”并映射到“COTO”。</p>
 *
 * <p>电子邮件正文的示例可能是这样的：</p>
 *
 * <code>
 * 玉米肉卷饼：玉米、肉卷饼、生菜、西红柿、切达干酪<br/>
 * 素食：面粉、西红柿、生菜、莎莎酱
 * </code>
 *
 * <p>结果你点了两份玉米卷，分别叫”玉米肉卷“和”蔬菜卷“。
 * 成分为{COTO, CARN, LETC, TMTO, CHED}和
 * {FLTO, TMTO, LETC, SLSA}。</p>
 *
 * @author DELL
 * @date 2023/1/18 22:06
 */
@Component
public class EmailToOrderTransformer
        extends AbstractMailMessageTransformer<Order> {
    private static final String SUBJECT_KEYWORDS = "TACO ORDER";

    @Override
    protected AbstractIntegrationMessageBuilder<Order>
    doTransform(Message mailMessage) {
        Order tacoOrder = processPayload(mailMessage);
        return MessageBuilder.withPayload(tacoOrder);
    }

    private Order processPayload(Message mailMessage) {
        try {
            String subject = mailMessage.getSubject();
            if (subject.toUpperCase().contains(SUBJECT_KEYWORDS)) {
                String email =
                        ((InternetAddress) mailMessage.getFrom()[0]).getAddress();
                String content = mailMessage.getContent().toString();
                return parseEmailToOrder(email, content);
            }
        } catch (MessagingException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Order parseEmailToOrder(String email, String content) {
        Order order = new Order(email);
        String[] lines = content.split("\\r?\\n");
        for (String line : lines) {
            if (line.trim().length() > 0 && line.contains(":")) {
                String[] lineSplit = line.split(":");
                String tacoName = lineSplit[0].trim();
                String ingredients = lineSplit[1].trim();
                String[] ingredientsSplit = ingredients.split(",");
                List<String> ingredientCodes = new ArrayList<>();
                for (String ingredientName : ingredientsSplit) {
                    String code = lookupIngredientCode(ingredientName.trim());
                    if (code != null) {
                        ingredientCodes.add(code);
                    }
                }

                Taco taco = new Taco(tacoName);
                taco.setIngredients(ingredientCodes);
                order.addTaco(taco);
            }
        }
        return order;
    }

    private String lookupIngredientCode(String ingredientName) {
        for (Ingredient ingredient : ALL_INGREDIENTS) {
            String ucIngredientName = ingredientName.toUpperCase();
            if (LevenshteinDistance.getDefaultInstance()
                    .apply(ucIngredientName, ingredient.getName()) < 3 ||
                    ucIngredientName.contains(ingredient.getName()) ||
                    ingredient.getName().contains(ucIngredientName)) {
                return ingredient.getCode();
            }
        }
        return null;
    }

    private static Ingredient[] ALL_INGREDIENTS = new Ingredient[]{
            new Ingredient("FLTO", "面粉玉米饼"),
            new Ingredient("COTO", "玉米饼"),
            new Ingredient("GRBF", "碎牛肉"),
            new Ingredient("CARN", "猪肉丝"),
            new Ingredient("TMTO", "土豆丁"),
            new Ingredient("LETC", "生菜"),
            new Ingredient("CHED", "切达干酪"),
            new Ingredient("JACK", "蒙特雷杰克"),
            new Ingredient("SLSA", "萨尔萨辣酱"),
            new Ingredient("SRCR", "酸奶油")
    };
}
