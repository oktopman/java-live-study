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
`@FunctionalInterface` 는 함수형 인터페이스임을 가리키는 어노테이션이다.  
이 어노테이션을 인터페이스에 선언했지만 실제로 함수형 인터페이스가 아니라면 컴파일 에러가 발생한다.  
위와 같은 형태로 함수형 인터페이스를 선언할 수 도있고, 자바에서 제공하는 함수형 인터페이스로 Comparator, Runnable 등이 있다.    
  
### 람다 활용
람다를 통해 유연하고 간결한 코드를 구현할 수 있다.  
```java
public class LambdaTest {
    public static String processFile(String filePath) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            return br.readLine();
        }
    }
}
```
위 코드는 파일에서 한번에 한줄만 읽을 수 있다.  
람다를 이용해 한번에 두줄을 읽거나 가장 자주 사용되는 단어를 반환하는 등, processFile 메서드의 동작을 파라미터화 하여 결과를 다르게 리턴받을 수 있다.  
processFile 메서드가 다른 동작을 수행할 수 있도록 processFile 메서드로 동작을 전달해야 한다. 함수형 인터페이스를 생성하여 동작을 전달한다.  
```java
@FunctionalInterface
public interface BufferedReaderProcessor {
    String process(BufferedReader br) throws IOException ;
}

public class LambdaTest {
    public static String processFile(BufferedReaderProcessor brp, String filePath) {
            String line = "";
            try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
                line = brp.process(br); // BufferedReader 객체 처리
            } catch (IOException e) {
                e.printStackTrace();
            }
            return line;
        }
}
``` 
람다 표현식으로 함수형 인터페이스의 추상메서드 구현을 직접 전달할 수 있으며 전달된 코드는 함수형 인터페이스의 인스턴스로 전달된 코드와 같은 방식으로 처리한다.  
```
// 한 행을 처리하는 코드
BufferedReaderProcessor bufferedReaderProcessor = br -> br.readLine();
String oneLine = processFile(bufferedReaderProcessor, "aaa.txt");

// 두 행을 처리하는 코드
BufferedReaderProcessor bufferedReaderProcessor = br -> br.readLine() + br.readLine();
String twoLines = processFile(bufferedReaderProcessor, "aaa.txt");
```

### 함수형 인터페이스 사용
다양한 람다 표현식을 사용하려면 공통의 함수 디스크립터를 기술하는 힘수형 인터페이스 집합이 필요하다.  
자바8 에서는 `java.util.function` 에서 Predicate, Consumer, Function 등 인터페이스를 제공 한다.  

## Variable Capture

## 메소드, 생성자 레퍼런스