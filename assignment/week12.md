# 12주차 과제
> 자바의 애노테이션에 대해 학습하세요.

## 학습할 것
- [애노테이션 정의하는 방법](#애노테이션-정의하는-방법)
- [@retention](#@retention)
- [@target](#@target)
- [@documented](#@documented)
- [애노테이션 프로세서](#애노테이션-프로세서)

## 애노테이션 정의하는 방법
### 애노테이션이란?
어노테이션은 클래스나 메소드 등의 선언시에 `@`를 사용하는것을 말한다. JDK5부터 등장했고 메타데이터라고 불리기도 한다.  
컴파일러에게 정보를 알려주거나 실행할 때 별도의 처리가 필요할 때 사용한다.    
어노테이션을 달았을 때 영향이 있는 어노테이션도 있고, 그렇지 않은 것도 있다.  
어노테이션은 클래스, 메소드, 변수 등 모든 요소에 선언할 수 있다.  
- warning 및 error에 대해 컴파일러에게 알림
- 컴파일 타임에 소스 코드 조작
- 런타임시 동작 수정 또는 검   

사용자가 직접 어노테이션을 만들어서 사용할 수도 있다.  
어노테이션은 컴파일수준에서 해석하기 때문에 정적인 값만 들어갈 수 있다. 동적으로 변경되는값은 들어갈수 없다. 
```java
@RestController
public class HelloContoller {
    private static final String hello = "hello";
    @GetMapping(hello) // final 이기때문에 정적인값임. 값이 변경될수없기때문에 어노테이션의 value로 사용할 수 있다.
    public String hello() {
        return "hello";
    }
}
```

자바에서 제공되는 애노테이션은 크게 2가지로 나눌수 있는데, `자바코드를 작성할때 사용되는 어노테이션`과 `어노테이션을 선언하기 위한 메타 어노테이션`이 있다.
### 자바 built-in 애노테이션
자바코드를 작성할 때 사용되는 어노테이션이다.  
- @Override
- @SuppressWarnings
- @Deprecated
- @SafeVarargs
- @FunctionalInterface
- @Native

컴파일러 warning 및 오류를 생성하거나 억제할수 있기때문에 개발시 일관되게 적용하는게 좋다.  

### 메타 어노테이션
메타 어노테이션이란 어노테이션의 어노테이션이다. 즉 `어노테이션을 정의하는데 사용되는 어노테이션`을 말한다.  
- @Target
- @Retention
- @Documented
- @Inherited

## retention
얼마나 오래 어노테이션 정보가 유지되는지를 선언한다.
### SOURCE  
소스파일에만 존재하고 컴파일을 하고나면 어노테이션의 정보가 없어진다. 이 어노테이션을 주석으로만 사용하겠다는 의미이다.  
예를 들어 `@Override` 가 있다.  
`@Override` 를 달면 진짜로 오버라이딩 한 메소드인지 컴파일 단계 에서 체크를 한다. . 컴파일이후에는 따로 체크하는것이 없다. 주석으로만 사용된다.  

### CLASS
컴파일러가 어노테이션의 정보를 클래스 파일에 저장할 수는 있지만, 클래스 파일이 JVM에 로딩될 때는 어노테이션의 정보가 무시되어 실행시에 어노테이션 정보를 얻을 수 없다.  
이러한 이유때문에 `CLASS`는 기본값임에도 불구하고 잘 사용되지 않는다.  
이 어노테이션에 대한 정보를 바이트코드에도 남겨놓는다. 하지만 메모리에는 적재하지않아서 어노테이션정보가 누락된다. 그래서 리플렉션을 통해 어노테이션정보를 읽어오는것은 불가능하다.  

### RUNTIME
실행시에 리플렉션을 통해 클래스 파일에 저장된 어노테이션의 정보를 읽어서 처리할 수 있다.  

## target
어노테이션이 적용 가능한 대상을 지정하는데 사용한다.  
target을 지정하지 않으면 모든 대상에 어노테이션을 사용할 수 있다.  
### 적용대상
- ANNOTATION_TYPE : 어노테이션
- CONSTRUCTOR : 생성자
- FIELD : 필드(멤버변수, enum 상수)
- LOCAL_VARIABLE : 지역변수
- METHOD : 메소드
- PACKAGE : 패키지
- PARAMETER : 파라미터
- TYPE : 타입(클래스, 인터페이스, enum)
- TYPE_PARAMETER : 타입 매개변수(java8)
- TYPE_USE : 타입이 사용되는 모든곳(java8)

## documented
javadoc으로 api 문서를 만들 때 어노테이션에 대한 설명도 포함도록 지정해주는 것이다.  

## 애노테이션 프로세서
자바 컴파일러의 컴파일 단계에서 유저가 정의한 어노테이션의 소스코드를 분석하고 처리하기 위해 사용되는 훅.  
`AbstractProcessor` 를 implements 하여 구현체를 만들 수 있다.
lombok의 `@Getter`, `@Setter` 같은 어노테이션도 컴파일타임에 컴파일러가 getter/setter를 만들어 주는 방식이다.  

## 참고
https://gowoonsori.site/java/annotation/  
자바의 신 VOL.1