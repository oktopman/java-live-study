# 9주차 과제
> 자바의 예외 처리에 대해 학습하세요.

## 학습할 것
- [자바의 예외란?](#자바의-예외란?)
- [자바가 제공하는 예외 계층 구조](#자바가-제공하는-예외-계층-구조)
- [Exception과 Error의 차이](#Exception과-Error의-차이)
- [RuntimeException과 RE가 아닌 것의 차이](#RuntimeException과-RE가-아닌-것의-차이)
- [TRY-WITH-RESOURCE](#TRY-WITH-RESOURCE)
- [커스텀한 예외 만드는 방법](#커스텀한-예외-만드는-방법)

## 자바의 예외란?
자바에는 `Error(에러)` 와 `Exception(예외)` 개념이 있다.  
에러는 시스템 레벨에서 발생하는 심각한 수준의 오류이며, 개발자가 미리 예측하여 처리할 수 없다.  
예외는 개발자가 구현한 로직에서 발생하게 된다. 그렇기 때문에 예외가 발생할 만한 상황을 예측하여 처리해 놓을 수 있다.  

### 자바가 제공하는 예외 계층 구조
![image](https://user-images.githubusercontent.com/55048593/104726615-41b46c00-5777-11eb-876a-1e91380772c3.png)    
출처 : https://joswlv.github.io/2018/10/29/java_exception/  

모든 예외 클래스는 `Throwable` 클래스를 상속받고 있다.  
`Uncheck Exception` 은 `RuntimeException`을 상속하고 Checked Exception은 RuntimeException을 상속하지 않는다.  
`RuntimeException` 은 Checked Exception 과 UnChecked Exception을 구분하는 기준이다.  

### Checked Exception
코드를 작성할 때 반드시 명시적으로 처리 해야 하는 Exception 이며, try/catch 나 throws 를 통해서 처리 할 수 있다.  
![image](https://user-images.githubusercontent.com/55048593/104615369-87682a80-56cc-11eb-84bf-a53c8b41a97f.png)  

예외가 발생할 수 있기 때문에 IDE에서는 명시적으로 예외처리를 하라는 컴파일에러(빨간줄)가 발생 한다.   
try/catch 나 throws 를 통해 해당 Checked Exception 을 처리 할 수 있다.  

![image](https://user-images.githubusercontent.com/55048593/104615625-c26a5e00-56cc-11eb-9db1-553148daf0b4.png)  
JsonProcessingException 은 IOException 을 상속하는 Checked Exception 이다.  
위와 같은 방법으로 Checked Exception 을 처리 하게 되면, 자신이 try/catch를 통해 예외발생을 처리하거나, throws 로 호출한 해당 메소드를 호출한 곳으로 던지게 된다.  

### UnChecked Exception
Unchecked Exception은 `RuntimeException` 을 상속 한다.  
 예외처리를 강제하지 않기 때문에 Unchecked Exception 이라고 불리고, `RuntimeException`이 발생한다.  
 ![image](https://user-images.githubusercontent.com/55048593/104617606-fd6d9100-56ce-11eb-8b99-6fe7a17eff5d.png)
 `null` 이 할당된 `post` 에서 값을 꺼낼때 NullPointerException이 발생 한다.     
 
### Checked Exception vs UnChecked Exception  
 |  | Checked Exception | UnChecked Exception |
 |---|:---:|---:|
 | `예외처리` | 명시적 처리 필수 | 예외 처리를 하지 않아도 프로그램 실행에 문제없음 |
 | `대표 Exception` | IOException, SQLException | NullPointerException, IllegalArgumentException |  
   
## TRY-WITH-RESOURCE
자바를 이용해 외부 자원에 접근하는 경우엔 외부자원을 사용한 후에 제대로 자원을 닫아 줘야 한다.  
```java
public class TryWithResourceExample {

    public static void main(String[] args) {
        Scanner sc = null;
        try {
            sc = new Scanner(new File("test.txt"));
            while (sc.hasNext()) {
                System.out.println(sc.nextLine());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (sc != null) {
                sc.close();
            }
        }
    }
}
```
이런 코드는 너무 장황하고 다른사람이 이해하기 힘들며, 시간이 지나면 작성자도 보기 힘들다. 즉 가독성이 너무 없다 !  
JDK 1.7 에는 `AutoCloseable` 인터페이스가 추가 되었다.  
![image](https://user-images.githubusercontent.com/55048593/104837400-89620180-58f7-11eb-96eb-f6da175ee3ff.png)  
해당 인터페이스를 `implements` 한 클래스는 try-with-resource 문법을 사용할수 있게 되며,   
try/catch 절이 종료될 때 자동으로 `close()` 메소드를 호출해주기 때문에 가독성이 좋아지고 `close()` 를 호출하지 않는 실수를 방지할 수 있다.  
```java
public class TryWithResourceExample {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(new File("test.txt"))) {
            System.out.println("asdasd");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
```

Scanner 클래스는 `AutoCloseable` 인터페이스를 구현한 클래스 이기 때문에 자원의 자동해제가 가능하다.  
![image](https://user-images.githubusercontent.com/55048593/104837521-726fdf00-58f8-11eb-850f-31926754a560.png)  

![image](https://user-images.githubusercontent.com/55048593/104837533-8c112680-58f8-11eb-8f69-2ae9f3d92b7a.png)  

## 커스텀한 예외 만드는 방법
기존 정의된 예외 클래스를 상속받아 새로운 예외 클래스를 정의하여 사용할 수 있다.  
```java
public class CustomException extends RuntimeException {

    private final int code;

    public CustomException(String message, int code) { // 생성자
        super(message);
        this.code = code;
    }

    public CustomException(String msg) {
        this(msg, 100);
    }

    public int getCode() {
        return this.code;
    }

}

public class CustomExceptionExample {

    public static void main(String[] args) {
        if (true) {
            throw new CustomException("error");
        }
    }
}
```
![image](https://user-images.githubusercontent.com/55048593/104838110-2c1c7f00-58fc-11eb-9239-f41e932ef0fd.png)  
RuntimeException 을 상속받은 CustomException 을 만들고, 특정한 상황에서 Exception을 발생 시킨 예 이다.  
기존에는 커스텀한 예외 클래스를 만들때 주로 `Exception` 을 상속받아서 Checked Exception 으로 작성하는 경우가 많았다고 한다.  
Checked Exception은 반드시 예외 처리를 해주어야 하기 때문에 불필요한 경우에도 try/catch 문을 넣어서 코드가 복잡해지기 때문에 최근에는 `RuntimeException`을 상속받아서 작성한다고 한다.      

Java는 많은 예외 클래스를 제공하거나 직접 작성할 수 있는데, 다음 질문에 예 라고 답한 경우 고유한 예외 클래스를 작성해야 한다. 그렇지 않으면 기존것을 사용해야 한다. 
- Java 플랫폼에서 표시되지 않는 예외 유형이 필요합니까?
- 다른 벤더가 작성한 클래스에서 발생한 예외와 사용자의 예외를 구별할 수 있다면 사용자에게 도움이 될까요?
- 코드에서 관련 예외가 두개 이상 발생합니까?
- 다른 사람의 예외를 사용하는 경우 사용자가 해당 예외에 액세스 할 수 있습니까? 비슷한 질문은 패키지가 독립적이어야 한다는 것입니다.  
출처 : https://docs.oracle.com/javase/tutorial/essential/exceptions/creating.html
## 참고
- try-with-resource
    - https://www.baeldung.com/java-try-with-resources
    - https://multifrontgarden.tistory.com/192  

- custom exception
    - https://docs.oracle.com/javase/tutorial/essential/exceptions/creating.html
    - https://yadon079.github.io/2021/java%20study%20halle/week-09