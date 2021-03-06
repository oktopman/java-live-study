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
자바8 에서는 `java.util.function` 에서 Predicate, Consumer, Function, Supplier 등 인터페이스를 제공 한다.
### Function<T,R>  
```
Function<T, R> {
R apply(T t);
}
```
T 타입 파라미터를 받아서 R 타입으로 리턴할때 사용한다.  
```java
public class FunctionTest {
    // string 타입을 파라미터로 받아서 int로 리턴하는 형태
    public static int toInt(String value) {
        return Integer.parseInt(value);
    }

    public static void main(String[] args) {
        Function<String, Integer> toInt = value -> Integer.parseInt(value);
        int number = toInt.apply("100");
        System.out.println(number); // 100
     
    }
}
```
### Consumer<T>
```  
Consumer<T> {
void accept(T t);
}
```
T 타입을 입력받아서 소비만 한다. 출력할때 주로 사용한다.  
```java
public class ConsumerTest {
    public static void main(String[] args){
      Consumer<String> print = value -> System.out.println(value);
      Consumer<String> greetings = value -> System.out.println("Hello " + value);
      print.accept("hello"); // hello
      greetings.accept("world"); // Hello world
      
      Consumer<List<String>> consumer = v -> System.out.println(v);
      List<String> s = Arrays.asList("1, 2, 3");
      consumer.accept(s); // [1,2,3]
    }
}
```
### Predicate<T>
```
Predicate<T>{
boolean test(T t);
}
```
T 타입을 입력받아서 boolean 으로 리턴 받는다.  
Function<T, Boolean> 으로 Predicate와 같은용도로 쓸수 있다. 하지만 Predicate 는 primitive type 으로 리턴하기 때문에 흔하게 사용할 수 있다.  
```java
public class PredicateTest {
    public static void main(String[] args){
        Predicate<String> predicate = value -> "test".equals(value);
        boolean isA = predicate.test("a");
        boolean isTest = predicate.test("test");
        System.out.println(isA);
        System.out.println(isTest);
        
        Predicate<Integer> isPositivePredicate = value -> value > 0;
        Predicate<Integer> isLessThan = value -> value < 3;
        Function<Integer, Boolean> function = value -> value > 0;
        boolean isPositive = isPositivePredicate.test(-1);
        boolean isPositiveUseFunction = function.apply(1);
        System.out.println(isPositive);
        System.out.println(isPositiveUseFunction);
        
        List<Integer> numbers = Arrays.asList(-5, -4, -3, -2, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8);
        System.out.println("positive integers : " + filter(numbers, isPositivePredicate));
        System.out.println("lessthan integers : " + filter(numbers, isLessThan));
    }

    private static <T> List<T> filter(List<T> numbers, Predicate<T> filter) {
        List<T> result = new ArrayList<>();
        numbers.forEach(value -> {
            if (filter.test(value)) {
                result.add(value);
            }
        });
        return result;
    }

}
```
### Supplier<T>
```
Supplier<T> {
T get();
}
```
입력값이 없는데 결과 값이 있다. 어떤경우에 사용할 수 있을까? lazy 하게 데이터를 처리할 수 있다.  
```java
public class SupplierTest {
    public static void main(String[] args){
      long start = System.currentTimeMillis();
      printIfValidIndex(0, () -> getVeryExpensiveValue());
      printIfValidIndex(1, () -> getVeryExpensiveValue());
      printIfValidIndex(2, () -> getVeryExpensiveValue());
      System.out.println((System.currentTimeMillis() - start) / 1000 + "초");
    }
    private static String getVeryExpensiveValue() {
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "hayun";
    }
    
    private static void printIfValidIndex(int number, Supplier<String> supplierValue) {
        if (number == 0) {
            System.out.println("the value is " + supplierValue.get());
        } else {
            System.out.println("Invalid");
        }
    }
}
```
Supplier 를 사용하지 않고 printIfValidIndex(0, getVeryExpensiveValue()); 만 호출한다면,   
getVeryExpensiveValue() 를 3번 호출해야 하기때문에 sleep 함수 때문에 9 초가 걸린다.  
Supplier 는 lazy 하게 처리 하기 때문에 if (number == 0) 에 부합했을때만 getVeryExpensiveValue() 를 호출하므로 3초 만에 처리 할 수 있다.  
실제 getVeryExpensiveValue() 를 호출하는 상황은 한번인데, supplier 를 사용하지 않는다면 3번 다 호출한다.  
불필요한 호출을 줄일 수 있다.  
## Variable Capture
람다식은 특정 상황에서 람다 함수 본문 외부에 선언된 변수에 접근이 가능하다.  
Java8 이전에는 익명의 내부 클래스가 이를 둘러싼 메서드에 대한 로컬 변수를 캡처할 때 이 문제가 발생 했다.  
컴파일러가 만족할 수 있도록 로컬 변수앞에 final 키워드를 추가해야만 했다.  
```java
public class VariableCaptureTest {

    public static void main(String[] args) {
        BiFunction<Integer, String, String> biFunction = (num, s) -> num + s;
        System.out.println(biFunction.apply(10, "이다"));
        Practice practice = new Practice();
        practice.run();
    }

    private void run() {
        final int number = 10; // effective final 변수. final 생략가능

        class LocalClass {
            void print() {
                int number = 11;
                System.out.println(number); // 11
            }
        }

        IntConsumer intConsumer = new IntConsumer() {
            int number = 13;
            @Override
            public void accept(int value) {
                System.out.println(number); // 13
            }
        };

        IntConsumer print = (number) -> { // scope가 같기 때문에 변수명이 같을 수 없음.
            System.out.println(number); 
        };

        print.accept(10);
    }
}
```
로컬클래스나 익명클래스는 쉐도잉이 가능 하다. 람다는 scope 가 같다 즉 메소드안에 있는 변수와 같은변수명을 쓸 수 없다. 그래서 람다는 쉐도잉이 일어나지않는다.
effective final 은 final 키워드가 붙어있지 않지만 final 키워드를 붙인 것과 동일하게 컴파일러가 처리한다. 결론적으로 초기화 하고 값이 변경되지 않는것을 의미한다.  
왜 이런 제약 조건이 있는걸까?  
- 지역 변수는 쓰레드끼리 공유가 안된다.
- JVM에서 인스턴스 변수는 힙 영역에 생성된다.
- 인스턴스 변수는 쓰레드끼리 공유가 가능하다.
결론적으로 지역변수가 스택에 저장되기 때문에 람다식에서 값을 바로 참조하는 것에 제약이 있어 복사된 값을 사용하는데 ,  
이때 멀티 쓰레드 환경에서 변경이 되면 동시성에 대한 이슈를 대응하기가 힘들다.  
  
```
Supplier<Integer> incrementer(int start) {
    return () -> start++;
}
```
위 소스는 컴파일 되지 않는다.  
start는 지역변수이고 람다식 내에서 수정하려고 하는데 람다가 start의 값을 캡쳐하기 때문이다. 즉 복사본을 만든다.  
변수를 final 값으로 지정하면 람다 내에서 start를 incrementer로 증가시키면 실제로 메소드 내의 start 인자가 수정될 수 있다.    

## 메소드, 생성자 레퍼런스
### 메소드 레퍼런스
```
Hello hello = new Hello() {
      @Override
      public void write(String writer) {
       
      }
};
```
위의 코드를 람다로 바꾸면 이런식으로 변경 가능하다.  
```
Hello hello = (w) -> {System.out.println(w)};
```
여기서 메소드 레퍼런스를 통해 한단계 더 줄일 수 있다.  
```
Hello hello = System.out::println;
```
이것을 사용하려면 전재조건이 하나 필요하다. 매개변수는 단 한개만 존재해야 한다. 왜냐하면
```
Hello hello = (w, i) -> System.out.println(i);
```
어떤것을 생략할지 모르기 때문이다.
  
### 생성자 레퍼런스
메서드 레퍼런스와 마찬가지로 무언가를 생략한 형태를 뜻한다. 아래와 같은 코드로 객체를 생성할 수 있다.  
```
Supplier<Hello> helloSupplier = Hello::new;
Hello hello = helloSupplier.get();
```
### 참고
https://b-programmer.tistory.com/279  
자바 8 인 액션  
케빈TV : https://www.youtube.com/channel/UCsOJxLxzQl8IbwGS-Cp5t8w  