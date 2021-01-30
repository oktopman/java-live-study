# 10주차 과제
> 자바의 열거형에 대해 학습하세요.

## 학습할 것
- [enum 정의하는 방법](#enum-정의하는-방법)
- [enum이 제공하는 메소드](#enum이-제공하는-메소드)
- [java.lang.Enum](#java.lang.Enum)
- [enum 활용](#enum-활용)
- [EnumSet](#EnumSet)

## enum 정의하는 방법
### Enum 이란?
enum 은 서로 관련된 상수를 편리하게 선언하기 위한 것으로 상수를 여러개 정의할 때 사용한다.  
열거형 이라고 부르고 자바 1.5 부터 문법적으로 지원하기 시작했다.  
enum 을 잘 사용하면 코드의 가독성을 높이고 논리적인 오류를 줄일 수 있고 `Typesafe` 하기 때문에 개발자의 실수를 방지할 수 있다.  
enum을 선언하고 사용하는 간단한 예 이다.  
```java
public enum Language {
    JAVA("java"),
    JAVASCRIPT("javascript"),
    KOTLIN("kotlin");

    private String description;

    Language(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
public class EnumPractice {

    private void printLanguageEnum(Language language) {
        switch (language) {
            case JAVASCRIPT:
                System.out.println(language.getDescription()); break;
            case JAVA:
                System.out.println(language.getDescription()); break;
            case KOTLIN:
                System.out.println(language.getDescription()); break;
        }
    }

    public static void main(String[] args) {
        EnumPractice enumPractice = new EnumPractice();
        enumPractice.printLanguageEnum(Language.JAVA);
    }
}
```
```
java
```
### enum 을 사용하지 않았을 때의 문제
만약 모든 정보들을 상수값으로 관리한다고 했을때 이름의 충돌이 발생할 수 있다.
```java
public class Info {
    public static final String JAVA = "language";
    public static final String JAVA = "coffee"; 
}
``` 
프로그래밍 언어의 `JAVA` 커피 `JAVA` 는 이름은 같지만 다른 의미를 가진다. 그렇기 때문에 위의 예시처럼 사용하면 컴파일 에러가 발생한다.  
```java
// 변수명으로 구분
public class Info { 
    public static final String JAVA_LANGUAGE = "language";
    public static final String JAVA_COFFEE = "coffee"; 
}

// interface 로 구분
public interface Language {
    String JAVA = "language";
}

public interface Coffee {
    String JAVA = "coffee";
}
```
위의 예시 처럼 변수명을 다르게 만들거나 인터페이스를 따로 만들어서 구분해주는 방법도 있다. 하지만 이런식으로 상수를 인터페이스로 관리하는 것은 안티패턴이라고 한다.  
인터페이스는 규약을 정하기 위해 만든것이지, 이런식으로 쓰라고 만든 개념이 아니다.  
위와 같은 방법으로 구분을 할 수 있지만 타입이 같은 자료형 이기 때문에 아래와 같은 코드가 컴파일에러 없이 실행 될 수 있다.    
```
    if (Language.JAVA == Coffee.JAVA) {}
```
프로그래밍 언어의 java와 커피의 java 는 비교조차 되어서는 안되는 개념이기때문에 애초에 저런 코드를 작성하는 실수를 할 수 없게끔 컴파일 과정에서 막아야 한다.
이러한 것을 `Typesafe` 하지 않다고 한다.  
```java
public class Language {
    public static final String JAVA = "language";
}

public class Coffee {
    public static final String JAVA = "coffee";
}
```
위와 같이 서로 다른객체로 만들어주면 위에 언급했던 문제들은 해결할 수 있지만 switch문에 조건에는 들어갈 수 없다. switch문의 조건으로 들어갈 수 없는 데이터타입 이기 때문이다.  

## enum이 제공하는 메소드
enum 클래스가 제공하는 대표적인 메소드는 다음과 같다.  
- `static E values()` : 해당 열거체의 모든 상수를 저장한 배열을 생성하여 반환
- `static E valueOf(String name)` : 전달된 문자열과 일치하는 해당 열거체의 상수를 반환
- `protected void finalize()` : 해당 Enum 클래스가 final 메소드를 가질 수 없게 됨
- `String name()` : 해당 열거체 상수의 이름을 반환
- `int ordinal()` : 해당 열거체 상수가 열거체 정의에서 정의된 순서(0부터 시작)를 반환

```java
public enum Language {
    JAVA("java"),
    JAVASCRIPT("javascript"),
    KOTLIN("kotlin");

    private String description;

    Language(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
public class EnumPractice {
        public static void main(String[] args) {
            Arrays.stream(Language.values())
                    .forEach(o -> System.out.println(o.getDescription()));    
            System.out.println(Language.valueOf("JAVA"));
            System.out.println(Language.JAVA.name());
            System.out.println(Language.JAVA.ordinal());
    
        }
}
```
```
java
javascript
kotlin
JAVA
JAVA
0
```
`ordinal()` 는 대부분의 개발자가 개발시 사용할 일은 거의없고 enum의 내부적인 데이터 구조에서 사용하기 위해 만들어졌다고 docs 에 정의 되어있다.  
 
## java.lang.Enum
java.lang 에 포함된 Enum 클래스는 모든 자바 열거형의 조상이다. 모든 열거형은 Enum 클래스를 상속받기 때문에 enum type은
별도의 상속을 받을 수 없다.  
```java
public abstract class Enum<E extends Enum<E>>
        implements Comparable<E>, Serializable {}
```
enum 으로 정의한 클래스를 컴파일한 후 클래스파일을 보면 위의 추상클래스를 상속받고 있는것을 확인 할 수 있다.  

## enum 활용
enum 에는 추상메서드를 추가할 수 있고, 각 열거형 타입에서는 추가된 추상 메서드를 구현함으로써 정의된 상수마다 각각에 맞는 기능을 구현할 수 있다.
```
abstract void print(); // 추상메서드를 각 상수에 오버라이드하여 구현
```  
```java
public enum Language {
    JAVA("java") {
        @Override
        void print() {
            System.out.println("java 입니다.");
        }
    },
    JAVASCRIPT("javascript") {
        @Override
        void print() {
            System.out.println("javascript 입니다.");
        }
    },
    KOTLIN("kotlin") {
        @Override
        void print() {
            System.out.println("kotlin 입니다.");
        }
    };

    private String description;

    Language(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    abstract void print();
}
public class EnumPractice {
    public static void main(String[] args){
      Language language = Language.JAVA;
      System.out.println("실행결과");
      language.print();
    }
}
```
```
실행결과
java 입니다.
```
아래 글은 enum 을 활용하여 코드를 리팩토링, 개선할 때 참고할만한 내용이다.   
https://woowabros.github.io/tools/2017/07/10/java-enum-uses.html  
https://jojoldu.tistory.com/137  
을 참고 할 수 있다.  
## EnumSet
EnumSet 열거형을 위해 고안된 특별한 Set 인터페이스 구현체이다. 열거형 데이터를 위한 Set이 필요한 경우에 사용한다.  
### EnumSet의 특징
- EnumSet은 AbstractSet 클래스를 상속하고 Set 인터페이스를 구현한다.
- 오직 열거형 상수만을 값으로 가질 수 있다. 또한 모든 값은 같은 enum type이어야 한다.
- null value를 추가하는 것을 허용하지 않는다. NullPointerException을 던지는 것도 허용하지 않는다.
- ordinal 값의 순서대로 요소가 저장된다.
- tread-safe하지 않다. 동기식으로 사용하려면 Collections.synchronizedMap을 사용하거나, 외부에서 동기화를 구현해야한다.
- 모든 메서드는 arithmetic bitwise operation을 사용하기 때문에 모든 기본 연산의 시간 복잡도가 O(1)이다.

```java
public enum Language {
    JAVA, JAVASCRIPT, KOTLIN
}

public class EnumPractice {
    public static void main(String[] args){
        EnumSet<Language> set1, set2, set3, set4, set5;
        
        set1 = EnumSet.allOf(Language.class);
        set2 = EnumSet.of(Language.JAVA);
        set3 = EnumSet.complementOf(set2);
        set4 = EnumSet.range(Language.JAVASCRIPT, Language.KOTLIN);
        set5 = EnumSet.noneOf(Language.class);
        set5.add(Language.KOTLIN);
        set5.add(Language.JAVASCRIPT);
        set5.remove(Language.JAVASCRIPT);

        System.out.println("set1 = " + set1);
        System.out.println("set2 = " + set2);
        System.out.println("set3 = " + set3);
        System.out.println("set4 = " + set4);
        System.out.println("set5 = " + set5);
        System.out.println(set5.contains(Language.JAVASCRIPT));
    }
}
```
```
set1 = [JAVA, JAVASCRIPT, KOTLIN]
set2 = [JAVA]
set3 = [JAVASCRIPT, KOTLIN]
set4 = [JAVASCRIPT, KOTLIN]
set5 = [KOTLIN]
false
```
EnumSet은 은 Set을 상속받고 있기때문에 Set 타입으로 정의된 변수에도 값을 담을 수 있다.  
예를 들어 enum에 있는 모든 상수들을 Set 으로 가지고 싶다면, 굳이 for문이나 stream 등 을 이용해 하나씩 담아 주지 않아도 된다.  
```
// stream 을 이용해 변수에 상수 담기
Set<Language> allLanguageSet = Arrays.stream(Language.values()).collect(Collectors.toSet());
// EnumSet 을 이용해 변수에 상수 담기
Set<Language> enumSetLanguage = EnumSet.allOf(Language.class);
```
위 두줄의 코드는 같은 결과를 나타낸다. `EnumSet`의 `allOf` 를 이용하면 코드가 짧아진다.  
## 참고
https://wisdom-and-record.tistory.com/52 
http://www.tcpschool.com/java/java_api_enum  