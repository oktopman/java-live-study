package me.oktop.javastudy.week14;

import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class FruitTest {

    public static void main(String[] args) {
        // AppleRepository 를 통한 CRUD
        Apple apple = new Apple();
        apple.setId(1L);
        apple.setColor("red");
        AppleRepository appleRepository = new AppleRepository();
        appleRepository.save(apple);

        Apple updateApple = new Apple();
        updateApple.setId(1L);
        updateApple.setColor("red2");
        appleRepository.save(updateApple);
        List<Apple> apples = appleRepository.findAll();
        System.out.println("apples size : " + apples.size());
        apples.forEach(System.out::println);

        appleRepository.delete(1L);
        apples = appleRepository.findAll();
        System.out.println("apples size : " + apples.size());

        // GenericRepository 를 통한 CRUD
        GenericRepository<Banana, Long> genericRepository = new BananaRepository();
        Banana genericBanana = new Banana();
        genericRepository.save(genericBanana);
        List<Banana> genericBananas = genericRepository.findAll();
        System.out.println("genericBananas size : " + genericBananas.size());

        // GenericRepository를 상속받은 BananaRepository 를 통한 CRUD
        BananaRepository bananaRepository = new BananaRepository();
        Banana banana = new Banana();
        banana.setId(1L);
        banana.setColor("yellow");
        bananaRepository.save(banana);

        List<Banana> bananas = bananaRepository.findAll();
        System.out.println("bananas size: " + bananas.size());
        bananaRepository.delete(1L);
        bananas = bananaRepository.findAll();
        System.out.println("bananas size: " + bananas.size());

//        FruitTest fruitTest = new FruitTest();
//        Orange orange = new Orange();
//        fruitTest.send(1L, apple, Apple.class);

    }

    public <E extends Fruit<K>, T, K> T send(K key, E dto, Class<T> classes) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<T> responseEntity = restTemplate.exchange("url", HttpMethod.GET, null, classes, dto);
        return responseEntity.getBody();
    }
}
