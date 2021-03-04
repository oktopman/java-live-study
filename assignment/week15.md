# 15주차 과제
> 자바의 람다식에 대해 학습하세요.
  
## 학습할 것
- [람다식 사용법](#람다식-사용법)
- [함수형 인터페이스](#함수형-인터페이스)
- [Variable Capture](#Variable-Capture)
- [메소드, 생성자 레퍼런스](#메소드,-생성자-레퍼런스)

## 람다식 사용법
### 람다란
람다 표현식은 메서드로 전달할 수 있는 익명 함수를 단순화한 것이라고 말할 수 있다.  
이름은 없지만 파라미터 리스트, 바디, 반환 형식, 발생할 수 있는 예외 리스트는 가질 수 있다.  
람다는 세 부분으로 이루어진다.
```
Comparator<Apple> byWeight = (a1, a2) -> a1.getWeight().compareTo(a2.getWeight());
```
파라미터 리스트 : `(a1, a2)`
화살표 : `->`
람다 바디 : `a1.getWeight().compareTo(a2.getWeight());`    
화살표를 기준으로 왼쪽에 있는 것은 input, 오른쪽은 body 이다.  
`(a1, a2)` 에서 타입을 적어주지 않아도 컴파일러가 추론을 할 수 있기때문에 생략 가능하다.  

다음은 람다의 기본 문법이다.  
- (parameters) -> expression
- (parameters) -> { statements; }
## 함수형 인터페이스
람다는 함수형 인터페이스라는 문맥에서 람다표현식을 사용할 수 있다.  
함수형 인터페이스는 정확히 하나의 추상 메서드를 지정하는 인터페이스이다.  
```java
@FunctionalInterface
public interface FeeCalculator {
    int getFee(int fee);
}
```
위와 같은 형태로 함수형 인터페이스를 선언할 수 도있고, 자바에서 제공하는 함수형 인터페이스로 Comparator, Runnable 등이 있다.   
  
## Variable Capture

## 메소드, 생성자 레퍼런스