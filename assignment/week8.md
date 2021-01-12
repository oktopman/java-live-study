# 8주차 과제
> 자바의 인터페이스에 대해 학습하세요.

## 학습할 것
- [인터페이스 정의하는 방법](#인터페이스-정의하는-방법)
- [인터페이스 구현하는 방법](#인터페이스-구현하는-방법)
- [인터페이스 레퍼런스를 통해 구현체를 사용하는 방법](#인터페이스-레퍼런스를-통해-구현체를-사용하는-방법)
- [인터페이스의 Default Method 자바 8](#인터페이스의-Default-Method-자바-8)
- [인터페이스의 static 메소드, 자바 8](#인터페이스의-static-메소드,-자바-8)
- [인터페이스의 private 메소드, 자바 9](#인터페이스의-private-메소드,-자바-9)

## 인터페이스의 개념과 사용하는 이유
인터페이스는 극단적으로 동일한 목적 하에 동일한 기능을 수행하게끔 강제하는 역할을 한다.  
자바의 다형성을 극대화하여 개발코드 수정을 줄이고 프로그램 유지보수성을 높이기 위해 인터페이스를 사용한다.  
또한 다른 개발자들과 협업을 할때, 각자의 부분을 완성할 때까지 기다리지 않고 규약만 정해둔 상태에서 각자의 부분만 따로 나눠서 작성된 코드를 컴파일 할 수 있다.  
코드의 종속성을 줄일수 있고 유지보수성을 높일 수 있게 해준다.   

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

## 인터페이스의 기본 메소드 (Default Method), 자바 8
인터페이스는 기능에 대한 선언만 가능하기 때문에, 실제 코드를 구현한 로직은 포함될 수 없었다.  
하지만 자바8부터 추가된 Default Method(디폴트 메서드) 라는것이 추가되었는데, 메서드 선언시에 `default` 를 명시하게 되면 인터페이스 내부에 코드를 포함시킬 수 있다.  
```java
public interface Bank {

    default String getBankVendor(String type) {
        if ("woori".equals(type)) {
            return "우리은행";
        } else if ("kb".equals(type)) {
            return "KB국민은행";
        }
        return "새마을금고"; 
    }
}
```  
디폴트 메서드는 `implements` 받는 클래스들에게 기본적으로 제공하고, 구현부가 마음에 들지않는다면 오버라이딩을 통해 재구현 할 수 있기때문에 시스템 운영시 운영 유지보수성이 좋아진다.  
새로운 기능을 추가할때 기존에 해당 인터페이스를 구현하고 있는 클래스들에게 영향을 주지않고 공통적인 기능을 추가 할 수 있다.  

## 인터페이스의 static 메소드, 자바 8
자바8 에서 도입된 기능이고 인터페이스에 `static` 메소드 추가가 가능하다. 인터페이스에서 제공해주는 것으로 무조건 사용해야 하고 상속이 불가능 하다.    
```java
public interface Bank {
    static void deposit(int price) {
        System.out.println("입금");
    }   
}
```

## 인터페이스의 private 메소드, 자바 9
이전에는 디폴트메서드를 생성할때도 로직에 중복적인 코드가 발생하지만 중복적인 코드도 public method로 만들어야 하는 단점이 있었다.  
자바9 에서는 interface에 private 메소드가 추가 되었다.  
인터페이스 내에서만 사용할 수 있고, 로직을 분리하는데 사용된다.(코드의 중복을 피하고 interface에 대한 캡슐화 유지가능)    
```java
public interface Bank {
    default void print() {
        printBank();
    }
    private void printBank() {
        System.out.println("print bank");
    }

}
```

## 인터페이스의 default, static 메소드가 등장하면서 추상클래스가 의미없어지는게 아닌가?
인터페이스
```java
public interface JoinMember extends StaticJoinMember {
    default void preJoin(){
            System.out.println("pre member");
        }
    
        default void afterJoin(){
            System.out.println("after member");
        }   
}
```

추상클래스
```java
public abstract class AbstractJoinMember implements JoinMember{

    private String message = "이런 클래스는 그럼 필요가 없나??";

    @Override
    public void preJoin(){
        System.out.println(message);
    }

    public void setMessage(String message){
        this.message = message;
    }
}
```
인터페이스에서 모든 것을 구현 할 수 없다.  
인터페이스에서는 위 추상클래스처럼 
```java
private String message = "이런 클래스는 필요가 없나??";
```
와 같은 변수를 선언할 수 없다.(상수는 선언가능)  
추상클래스에서 많은 것들을 인터페이스로 옮겨서 구현 할 수 있게된 것은 맞지만, 추상클래스에서 할 수 있는 것들이 있기 때문에 추상클래스의 효용가치는 존재한다.  
  
## 참고
http://redutan.github.io/2016/03/31/anti-oop-if  
https://woowabros.github.io/study/2018/03/05/sdp-sap.html  
https://www.notion.so/4b0cf3f6ff7549adb2951e27519fc0e6  
https://limkydev.tistory.com/197  