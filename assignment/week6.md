# 6주차 과제
> 자바의 상속에 대해 학습

## 학습할 것
- [자바 상속의 특징](#자바-상속의-특징)
  - [상속](#상속)
  - [자바 상속의 특징](#자바-상속의-특징)
  - [메소드 오버라이딩](#메소드-오버라이)
- [메소드 디스패치](#메소드-디스패치)
- [추상 클래스](#추상-클래스)
- [final 키워드](#final-키워드)
- [Object 클래스](#object-클래스)

## 자바의 상속
#### 상속
실생활에서 부모가 자식에게 재산을 물려주는 행위를 가르켜 상속 이라고 한다.  
Java에서도 비슷한 의미로 사용된다.  

부모클래스의 변수, 메소드를 자식 클래스가 물려받아서 그대로 사용 하거나 확장해서 사용할 수 있다.    
코드가 중복되는 것을 막을 수 있고 공통적인 코드를 변경할 시 시간을 단축 시킬 수 있다.  

#### 자바 상속의 특징
- 모든 클래스들은 Object 클래스의 자식 클래스
- extends 를 통한 상속
```JAVA
@Getter
@Setter
public class Human {
    public String name;
    public int age;

    public void plusAge() {
        this.age++;
    }
}

public class Development extends Human {
}
```
- 다중상속 불가능
자바는 다중상속을 지원하지 않으므로 하나의 클래스가 여러 부모클래스를 상속받을 수 없음  
`class cannot extends multiple classess` 라는 컴파일에러 메세
```java
public class Development extends Human, Monster {
}
``` 
- 자식 클래스는 부모 클래스가 가진 변수, 메소드를 모두 상속
```java
public class Main {

    public static void main(String[] args) {
        Development dev = new Development();
        dev.setName("oktopman");
        dev.setAge(20);
    }
}
```
- 부모의 private 로 선언된 자원은 상속 불가능    
부모클래스 내에 있는 변수나 메소드가 private 로 선언되어 있다면,  
변수는 상속은 받을 수 있지만 바로 접근이 불가능하므로 getter나 setter 로 접근해야한다.  
메소드는 상속 되지않는다, 메소드를 상속받으려면 public 으로 생성해야 한다.

- final 클래스는 상속불가속

#### 메소드 오버라이딩
부모클래스에서 상속받은 메소드를 확장하여 재사용 하는것.  
부모클래스에 존재하는 메소드여야 하고, 메소드명, 매개변수, 반환타입이 같아야 한다.  
  
```java
public class Human {
    public void introduceMySelf() {
        System.out.println(String.format("제이름은 %s 이고 나이는 %s 입니다.", this.name, this.age));
    }
}

public class Development extends Human {
    @Override
        public void introduceMySelf() {
            System.out.println(String.format("제 이름은 %s 입니다.", this.name));
        }
}
```

## 메소드 디스패치
메소드 디스패치에 대해서 잘 정리한 블로그의 링크를 공유한다. 취업준비할때 보자~
- 스태틱 디스패치
컴파일 시점에 정확히 어느 인스턴스가 실행되는지 알고있는 것  
- 다이나믹 디스패치  
객체가 실제 어떤 인스턴스를 사용하는지 런타임시점에 동적으로 알수 있는 것
- 더블 디스패칭  
[토비의봄 유튜브. 아주 잘설명되어있으니까 꼭 보자](https://www.youtube.com/watch?v=s-tXAHub6vg&t=294s)

## 추상클래스
객체를 직접 생성할 수 있는 실체 클래스의 공통적인 특성을 추출해서 선언한 클래스이다.  
추상클래스는 객체를 생성할 수 없다는 특징이 있다. 하지만 슈퍼클래스로는 사용 할 수 있기때문에 추상메서드를 상속받은 자식클래스에서 사용하고자 하는 메서드를 오버라이딩 해야 한다.  
추상클래스를 상속받을 경우에는 추상메서드를 반드시 재정의해야하며, 그렇지않으면 컴파일에러가 발생한다.      
`abstract` 키워드를 사용하여 선언한다.  
한개 이상의 추상 메서드를 가져야 하는데, 추상메서드는 내용이 없는 메서드이며, 구현하지 않고 선언만 한 메서드이다.
```java
public abstract class Base {
    LocalDate createdDate;
    abstract LocalDate getCreatedDate();
}

public class Human extends Base {
    @Override
        LocalDate getCreatedDate() {
            return LocalDate.now();
        }
}
```  
## final 키워드
final가 클래스나 변수,메서드 앞에 붙게 된다면 해당 엔티티는 변경할 수 없는 엔티티가 된다.  
엔티티들의 변경될 수 있는 여지를 막아줌으로써 실수로 엔티티를 변경하게 되는것을 방지할 수 있다.  
클래스 선언시 final 키워드를 사용하면, 다른 클래스에서 상속 받을 수 없는 클래스가 된다.  
메소드에 final을 붙인다면 오버라이딩할 수 없는 메소드가 되기때문에 자식클래스에서는 해당메소드를 오버라이딩하여 사용할 수 없다.   

## Object 클래스
자바의 최상위 클래스이며, 클래스 선언 시 다른 클래스를 상속받지 않으면 암시적으로 `java.lang.Object` 클래스를 상속받는다.  
[생활코딩](https://opentutorials.org/course/1223/6241) 에 자세히 설명되어 있다. 
