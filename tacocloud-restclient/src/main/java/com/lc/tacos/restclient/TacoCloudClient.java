package com.lc.tacos.restclient;

import com.lc.tacos.domain.Ingredient;
import com.lc.tacos.domain.Taco;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.client.Traverson;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.*;

/**
 * @author DELL
 * @date 2022/9/13 21:31
 */
@Service
@Slf4j
public class TacoCloudClient {
    private RestTemplate rest;
    private Traverson traverson;

    @Autowired
    public TacoCloudClient(RestTemplate rest, Traverson traverson) {
        this.rest = rest;
        this.traverson = traverson;
    }

    /*
     * GET 例子
     */

    /**
     * 指定参数为变量参数
     */
    /*public Ingredient getIngredientById(String ingredientId) {
        return rest.getForObject("http://localhost:8080/ingredientsx/{id}",
                Ingredient.class, ingredientId);
    }*/

    /**
     * 使用映射指定参数
     */
    /*public Ingredient getIngredientById(String ingredientId) {
        Map<String, String> urlVariables = new HashMap<>();
        urlVariables.put("id", ingredientId);
        return rest.getForObject("http://localhost:8080/ingredientsx/{id}",
                Ingredient.class, urlVariables);
    }*/

    /**
     * 用URL而不是用字符串请求
     */
    /*public Ingredient getIngredientById(String ingredientId) {
        Map<String, String> urlVariables = new HashMap<>();
        urlVariables.put("id", ingredientId);
        URI url = UriComponentsBuilder
                .fromHttpUrl("http://localhost:8080/ingredientsx/{id}")
                .build(urlVariables);
        return rest.getForObject(url, Ingredient.class);
    }*/
    public Ingredient getIngredientById(String ingredientId) {
        ResponseEntity<Ingredient> responseEntity =
                rest.getForEntity("http://localhost:8080/ingredientsx/{id}",
                        Ingredient.class, ingredientId);
        log.info("最后获取时间：" + responseEntity.getHeaders().getDate());
        return responseEntity.getBody();
    }

    public List<Ingredient> getAllIngredients() {
        return rest.exchange("http://localhost:8080/ingredientsx",
                HttpMethod.GET, null, new ParameterizedTypeReference<List<Ingredient>>() {
                }).getBody();
    }

    /**
     * PUT 例子
     */
    public void updateIngredient(Ingredient ingredient) {
        /*ResponseEntity<String> entity =
                rest.getForEntity("http://localhost:8080/design/recent", String.class);
        List<String> cookies = entity.getHeaders().get("Set-Cookie");
        System.out.println("cookie：" + cookies.get(0));
        String cookie = cookies.get(0).substring(11, 47);
        System.out.println("获取的cookie：" + cookie);
        //请求头
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-XSRF-TOKEN", cookie);
        HttpEntity<MultiValueMap<String, String>> formEntity
                = new HttpEntity<>(headers);*/

        rest.put("http://localhost:8080/ingredientsx/{id}",
                ingredient, ingredient.getId());
    }

    /**
     * DELETE 例子
     */
    public void deleteIngredient(Ingredient ingredient) {
        rest.delete("http://localhost:8080/ingredientsx/{id}",
                ingredient.getId());
    }

    /**
     * POST 例子
     */
    /*public Ingredient createIngredient(Ingredient ingredient) {
        return rest.postForObject("http://localhost:8080/ingredientsx",
                ingredient, Ingredient.class);
    }*/

    /**
     * 返回创建资源的地址
     */
    /*public URI createIngredient(Ingredient ingredient) {
        return rest.postForLocation("http://localhost:8080/ingredientsx",
                ingredient, Ingredient.class);
    }*/

    /**
     * 同时需要地址和响应载荷
     */
    public Ingredient createIngredient(Ingredient ingredient) {
        ResponseEntity<Ingredient> responseEntity =
                rest.postForEntity("http://localhost:8080/ingredientsx",
                        ingredient,
                        Ingredient.class);
        log.info("新资源创建于：" +
                responseEntity.getHeaders().getLocation());
        return responseEntity.getBody();
    }

    /**
     * 使用Traverson的RestTemplate的案例
     */
    public Iterable<Ingredient> getAllIngredientsWithTraverson() {
        ParameterizedTypeReference<CollectionModel<Ingredient>> ingredientType =
                new ParameterizedTypeReference<CollectionModel<Ingredient>>() {
                };

        CollectionModel<Ingredient> ingredientRes =
                traverson
                        .follow("ingredients")
                        .toObject(ingredientType);

        Collection<Ingredient> ingredients = ingredientRes.getContent();
        return ingredients;
    }

    /**
     * 获取最新创建的taco
     */
    public Iterable<Taco> getRecentTacosWithTraverson() {
        ParameterizedTypeReference<CollectionModel<Taco>> tacoType =
                new ParameterizedTypeReference<CollectionModel<Taco>>() {
                };
        CollectionModel<Taco> tacoRes =
                traverson
                        .follow("tacos")
                        .toObject(tacoType);
        Collection<Taco> tacos = tacoRes.getContent();
        return tacos;
    }

    /**
     * 将Traverson和RestTemplate组合起来，创建一个新的Ingredient
     */
    public Ingredient addIngredient(Ingredient ingredient) {
        String ingredientsUrl = traverson
                .follow("ingredients")
                .asLink()
                .getHref();
        return rest.postForObject(ingredientsUrl,
                ingredient,
                Ingredient.class);
    }
}
