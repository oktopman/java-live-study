# 7주차 과제
> 자바의 인터페이스에 대해 학습하세요.

## 학습할 것
- [인터페이스 정의하는 방법](#인터페이스-정의하는-방법)
- [인터페이스 구현하는 방법](#인터페이스-구현하는-방법)
- [인터페이스 레퍼런스를 통해 구현체를 사용하는 방법](#인터페이스-레퍼런스를-통해-구현체를-사용하는-방법)
- [인터페이스의 기본 메소드 (Default Method), 자바 8](#인터페이스의-기본-메소드-(Default Method),-자바-8)
- [인터페이스의 static 메소드, 자바 8](#인터페이스의-static-메소드,-자바-8)
- [인터페이스의 private 메소드, 자바 9](#인터페이스의-private-메소드,-자바-9)

## 인터페이스의 개념과 사용하는 이유
인터페이스는 극단적으로 동일한 목적 하에 동일한 기능을 수행하게끔 강제하는 역할을 한다.  
자바의 다형성을 극대화하여 개발코드 수정을 줄이고 프로그램 유지보수성을 높이기 위해 인터페이스를 사용한다.  

## 인터페이스 정의하는 방법
`interface` 키워드를 통해 선언할 수 있고, 추상메소드를 선언 할 수 있다.  
```java
public interface Bank {
    
    Map<String, Object> sendData(SendDto sendDto);
}
```

## 인터페이스 구현하는 방법
선언한 interface 를 `implements` 키워드를 통해 구현 할 수 있다.  
interface 에 정의되어있는 메소드를 필수적으로 구현 해야 한다.  
```java
public class KBBank implements Bank {
    @Override
    public void deposit(int price) {
    }

    @Override
    public void withDraw(int price) {
    }
}
```

## 인터페이스 레퍼런스를 통해 구현체를 사용하는 방법
자바의 다형성은 자식클래스의 인스턴스를 부모클래스 타입으로 참조하는 것이 가능 하다.  
인터페이스도 해당 인터페이스 타입의 참조변수로 이를 구현한 클래스의 인스턴스를 참조 할 수 있다. 그렇기 때문에 좀 더 유연한 프로그래밍을 할 수 있다.  
```
List<String> list = new ArrayList<>();  // 유연
ArrayList<String> arrayList = new ArrayList<>(); // 비유연

List<String> list = new LinkedList<>(); // LinkedList 로 변환가능
ArrayList<String> arrayList = new LinkedList<>(); // 컴파일 에러
```

실무에서 사용할 땐 좀 더 유연하게 사용 할 수 있을것 같다.  

```java
public interface Bank {

    void deposit(int price); // 입금

    void withDraw(int price); // 출금

}
public class KBBank implements Bank {

    @Override
    public void deposit(int price) {
        System.out.println("kbbank deposit : " + price);
    }

    @Override
    public void withDraw(int price) {
        System.out.println("kbbank withDraw : " + price);
    }
}
public class WooriBank implements Bank {

    @Override
    public void deposit(int price) {
        System.out.println("WooriBank deposit : " + price);
    }

    @Override
    public void withDraw(int price) {
        System.out.println("WooriBank withDraw : " + price);
    }
}
public class BankFactory {

    public static Bank getBank(String type) {
        if (type.equals("kb")) {
            return new KBBank();
        } else if (type.equals("woori")) {
            return new WooriBank();
        }
        return null;
    }
}

class Main {
    public static void main(String[] args) {
        Bank bank = BankFactory.getBank("kb");
        bank.deposit(10); // kbbank deposit : 10
        bank = BankFactory.getBank("woori");
        bank.deposit(11); // WooriBank deposit : 11
    }
}
``` 
http://redutan.github.io/2016/03/31/anti-oop-if 에서 좀 더 심화된 인터페이스를 통한 리팩토링 방법을 소개한다.  
## 참고
http://redutan.github.io/2016/03/31/anti-oop-if  
https://woowabros.github.io/study/2018/03/05/sdp-sap.html  