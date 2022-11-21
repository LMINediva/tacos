package com.lc.tacos.restclient;

import com.lc.tacos.domain.Ingredient;
import com.lc.tacos.domain.Taco;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.client.Traverson;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

/**
 * @author DELL
 * @date 2022/9/13 21:45
 */
@SpringBootApplication(scanBasePackages = "com.lc.tacos")
@Slf4j
public class RestExamples {

    public static void main(String[] args) {
        SpringApplication.run(RestExamples.class, args);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public CommandLineRunner fetchIngredients(TacoCloudClient tacoCloudClient) {
        return args -> {
            log.info("----------------------- GET -------------------------");
            log.info("通过集成开发环境获取原料");
            log.info("原料：" + tacoCloudClient.getIngredientById("CHED"));
        };
    }

    @Bean
    public CommandLineRunner putAnIngredient(TacoCloudClient tacoCloudClient) {
        return args -> {
            log.info("----------------------- PUT -------------------------");
            Ingredient before = tacoCloudClient.getIngredientById("LETC");
            log.info("之前：" + before);
            tacoCloudClient.updateIngredient(new Ingredient("LETC", "辣生菜葱沙律",
                    Ingredient.Type.VEGGIES));
            Ingredient after = tacoCloudClient.getIngredientById("LETC");
            log.info("之后：" + after);
        };
    }

    @Bean
    public CommandLineRunner deleteAnIngredient(TacoCloudClient tacoCloudClient) {
        return args -> {
            log.info("----------------------- DELETE -------------------------");
            //先加入一些配料，这样我们以后就可以把它们删除了……
            Ingredient beefFajita = new Ingredient("BFFJ", "沙爹牛肉", Ingredient.Type.PROTEIN);
            tacoCloudClient.createIngredient(beefFajita);
            Ingredient shrimp = new Ingredient("SHMP", "小虾", Ingredient.Type.PROTEIN);
            tacoCloudClient.createIngredient(shrimp);

            Ingredient before = tacoCloudClient.getIngredientById("BFFJ");
            log.info("之前：" + before);
            tacoCloudClient.deleteIngredient(before);
            Ingredient after = tacoCloudClient.getIngredientById("BFFJ");
            log.info("之后：" + after);
            before = tacoCloudClient.getIngredientById("SHMP");
            log.info("之前：" + before);
            tacoCloudClient.deleteIngredient(before);
            after = tacoCloudClient.getIngredientById("SHMP");
            log.info("之后：" + after);
        };
    }

    @Bean
    public CommandLineRunner addAnIngredient(TacoCloudClient tacoCloudClient) {
        return args -> {
            log.info("----------------------- POST -------------------------");
            Ingredient chix = new Ingredient("CHIX", "手撕鸡", Ingredient.Type.PROTEIN);
            Ingredient chixAfter = tacoCloudClient.createIngredient(chix);
            log.info("之后-1：" + chixAfter);
            /*Ingredient beefFajita = new Ingredient("BFFJ", "沙爹牛肉", Ingredient.Type.PROTEIN);
            URI uri = tacoCloudClient.createIngredient(beefFajita);
            log.info("之后-2：" + uri);
            Ingredient shrimp = new Ingredient("SHMP", "小虾", Ingredient.Type.PROTEIN);
            URI shrimpAfter = tacoCloudClient.createIngredient(shrimp);
            log.info("之后-3：" + shrimpAfter);*/
        };
    }

    /**
     * Traverson 例子
     */

    @Bean
    public Traverson traverson() {
        Traverson traverson = new Traverson(
                URI.create("http://localhost:8080/api"),
                MediaTypes.HAL_JSON);
        return traverson;
    }

    @Bean
    public CommandLineRunner traversonGetIngredients(TacoCloudClient tacoCloudClient) {
        return args -> {
            Iterable<Ingredient> ingredients =
                    tacoCloudClient.getAllIngredientsWithTraverson();
            log.info("----------------------- 用traverson取食材 -------------------------");
            for (Ingredient ingredient : ingredients) {
                log.info(" - " + ingredient);
            }
        };
    }

    @Bean
    public CommandLineRunner traversonRecentTacos(TacoCloudClient tacoCloudClient) {
        return args -> {
            Iterable<Taco> recentTacos = tacoCloudClient.getRecentTacosWithTraverson();
            log.info("----------------------- 使用traverson获取最新的煎饼 -------------------------");
            for (Taco taco : recentTacos) {
                log.info(" - " + taco);
            }
        };
    }

    @Bean
    public CommandLineRunner traversonSaveIngredient(TacoCloudClient tacoCloudClient) {
        return args -> {
            Ingredient pico = tacoCloudClient.addIngredient(
                    new Ingredient("PICO", "碎番茄粒", Ingredient.Type.SAUCE));
            List<Ingredient> allIngredients = tacoCloudClient.getAllIngredients();
            log.info("----------------------- 在保存PICO之后的所有配料 -------------------------");
            for (Ingredient ingredient : allIngredients) {
                log.info(" - " + ingredient);
            }
//            tacoCloudClient.deleteIngredient(pico);
        };
    }
}
