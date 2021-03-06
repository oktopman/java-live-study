# 2주차 과제
> 자바의 프리미티브 타입, 변수 그리고 배열을 사용하는 방법을 익힙니다.

## 학습할 것
- [프리미티브 타입 종류와 값의 범위 그리고 기본 값](#프리미티브-타입-종류와-값의-범위-그리고-기본-값)
- [프리미티브 타입과 레퍼런스 타입](#프리미티브-타입과-레퍼런스-타입)
- [리터럴](#리터럴)
- [변수 선언 및 초기화하는 방법](#변수-선언-및-초기화하는-방법)
- [변수의 스코프와 라이프타임](#변수의-스코프와-라이프타임)
- [타입 변환, 캐스팅 그리고 타입 프로모션](#타입-변환,-캐스팅-그리고-타입-프로모션)
- [1차 및 2차 배열 선언하기](#1차-및-2차-배열-선언하기)
- [타입 추론, var](#타입-추론,-var)

## 프리미티브 타입 종류와 값의 범위 그리고 기본 값 
 | |타입|값의 범위|기본값|
 |---|---|---|---|
 |논리형|boolean|true, false|false|
 |정수형 |byte|-128 ~ 127|0|
 |정수형 |short|-32,768 ~ 32,767|0|
 |정수형 |int|-2,147,483,648 ~ 2,147,483,647|0|
 |정수형 |long| -9,223,372,036,854,775,808 ~ 9,223,372,036,854,775,807|0L|
 |실수형 |float|소수점 7자리 까지|0.0F|
 |실수형 |double|소수점 16자리 까지|0.0|
 |문자형 |char|0 ~ 65,535|\u0000|
 참고 : 자바의 신 vol. 1 서적  
 
 ## 프리미티브 타입과 레퍼런스 타입
 자바에서는 기본형(프리미티브), 참조형(레퍼런스) 두가지로 자료형을 나눌 수 있다. 
 자바에서 자료형을 두가지로 나눠놓은 이유는 효율성을 위해서 라고 한다.  
 프리미티브 타입의 변수는 값을 직접 포함하고 참조형 타입의 변수는 메모리 내의 다른 곳에 저장된 객체를 참조한다.
 - 프리미티브 타입  
   - 8개의 기본 자료형을 가진다
   - 기본값을 가지기때문에 null 이 존재하지 않는다
   - 실제 값을 저장하는 공간으로 스택 메모리에 저장된다
   - 값의 범위가 있기때문에 값을 변수에 담을때 컴파일 에러가 발생한다
   - 초기화 할 때 값을 바로 적어준다
   
 - 레퍼런스 타입
   - null이 존재한다
   - 값이 저장되어 있는 곳의 주소값을 저장하는 공간으로 힙 메모리에 저장된
   - 초기화 할 때 new 라는 예약어를 사용한다
   - String 은 new 를 통해 초기화 하지않아도 된다.
   ```
   String studyName1 = "whiteship live study";
   String studyName2 = new String("whiteship live study");
   // 두 변수의 결과는 동일 
   ```
   참고 : 자바의 신 vol. 1 서적
 
 ## 리터럴
 리터럴은 값 자체를 말하고 변수에 저장된다.
```
int a = 1;
```
리터럴은 1 이며(정수 리터럴) 변수인 a 에 담긴다.
 
## 변수 선언 및 초기화하는 방법
변수는 메모리에 공간에 할당되고 값을 저장할 수 있는 공간이다.  
```
int a; // default 0 초기화
int b = 10; // 10 으로 초기화
String s = "hello"; // hello 라는 문자열을 s 변수에 담아서 생성. hello 라는 값으로 초기화
```

## 변수의 스코프와 라이프타임
변수의 스코프는 사용 할 수 있는 범위를 얘기하고 {} 안에 선언했을 경우 영역이 끝나기전까지 어디서든 사용 가능하다.  
변수의 종류에는 클래스변수와 인스턴스 변수, 지역변수가 있는데 클래스변수와 인스턴스 변수는 클래스 영역내에 선언된다.  
- 클래스 변수  
클래스 변수는 static 키워드를 붙일경우를 말하고, 클래스가 메모리에 올라갔을때 생성이 된다.  

- 인스턴스 변수    
인스턴스 변수는 클래스 영역에 선언이 되고 객체화 했을 때 생성된다.  
```
Study study = new Study(); // 시점에 클래스변수 생성
```
- 지역 변수   
지역 변수는 메소드나 초기화 블록 내부에서 주로 생성하고 메소드나 생성자가 호출될 때 생성된다.  
메소드 내에서만 사용이 가능하고 메소드 종료시점에 같이 소멸된다.  

- 변수의 스코프
```
public class Human {
    // 인스턴스 변수
    private String name;
    private int age;
    private LocalDate birthday;

    // 클래스 변수
    private static String gender;

    public void localVariableExample() {
        for (int i = 0; i < 5; i++) {
            System.out.println(i); // i는 메소드 안에서만 사용되는 지역변
        }
    }
}
```  
## 타입 변환, 캐스팅 그리고 타입 프로모션
기본 자료형과 참조 자료형 모두 괄호로 묶여서 변환할 수 있다.  
변수의 데이터타입의 자동형변환(Promotion), 강제 형변환(Casting) 이라고 한다.  
- 강제 형변환(캐스팅)  
기본 자료형 변수는 boolean을 제외한 나머지 타입은 서로 형변환이 가능하다.  
하지만 각 타입간에 가지는 범위가 다르기 때문에 형변환을 통해 값이 변할 수 있으니 조심해서 사용해야 하고 꼭 원하는 결과나 나오는지 테스트 해야 한다.  
```
@Test
void test() {
    double a = 3.14;
    int b = (int) a;
    System.out.println(b); // 3
}
```
- 참조자료형 형변환  
참조 자료형은 인터페이스나 상속 관계로 엮여있는 클래스 간에 캐스팅 가능하다.  
```
class Car {}
class Sonata extends Car {}
class Test {
    public static void main(String[] args) {
        Car car1 = new Car();
        Car car2 = new Sonata(); // 하위클래스에서 상위 클래스로 변환
    }
}
```
하위 클래스는 상위 클래스를 상속받았기때문에 상위 클래스의 변수나 메소드를 사용 할 수 있어서 상위 클래스의 자료형으로 변환해서 생성할 수 있다.  
- 프로모션  
자동 형변환은 프로그램 실행 도중에 자동으로 타입 형변환이 일어나는것을 말한다.  
작은 메모리 크기의 데이터 타입 -> 큰 메모리 크기의 데이터타입 으로 자동으로 변환이 이루어지는 것이다.  
byte(1) < short(2) < int(4) < long(8) < float(4) < double(8)  
long -> float 가 가능한 이유는 표현할 수 있는 값의 범위가 float가 더 크기 때문이다.  
```
byte a = 10;
int b = a; // byte 타입을 int 타입 변수에 저장
```

## 1차 및 2차 배열 선언하기
배열은 동일한 자료형을 정해진 수만큼 저장하는 순서를 가진 레퍼런스 타입 자료형이다.  
배열을 선언하고 사용해보자.  
```java
public class ArrayTest {

    public static void main(String[] args) {
        int[] array = new int[3];
        array[0] = 10;
        array[1] = 20;
        array[2] = 30;

        System.out.println("1차원 배열");
        for (int i : array) {
            System.out.println(i);
        }

        int[][] array2 = new int[2][3];
        array2[0][0] = 10;
        array2[0][1] = 20;
        array2[0][2] = 30;
        array2[1][0] = 40;
        array2[1][1] = 50;
        array2[0][2] = 60;

        System.out.println("2차원 배열");
        for (int[] arr : array2) {
            for (int i : arr) {
                System.out.println(i);
            }
        }
    }
}
```
```
1차원 배열
10
20
30
2차원 배열
10
20
30
40
50
60
```

### 타입 추론, var
타입추론은 타입을 명시적으로 적지않아도, 컴파일러가 알아서 이 변수의 타입을 대입된 리터럴로 추론하는 것이다.
```
Map<String, Object> map = new HashMap<>();
```
이것은 map 변수에 담길 데이터타입이 `Map<String, Object>` 라는 것을 map 변수의 데이터 타입을 바탕으로 추론해낼 수 있기때문에 가능한 것이다.  
자바10 부터는 `var` 키워드를 통해 타입 추론이 가능하다.  