# 9주차 과제
> 자바의 예외 처리에 대해 학습하세요.

## 학습할 것
- [자바에서 예외 처리 방법 (try, catch, throw, throws, finally](#자바에서-예외-처리-방법)
- [자바가 제공하는 예외 계층 구조](#자바가-제공하는-예외-계층-구조)
- [Exception과 Error의 차이](#Exception과-Error의-차이)
- [RuntimeException과 RE가 아닌 것의 차이](#RuntimeException과-RE가-아닌-것의-차이)
- [커스텀한 예외 만드는 방법](#커스텀한-예외-만드는-방법)

## 예외
자바에는 `Error(에러)` 와 `Exception(예외)` 개념이 있다.  
에러는 시스템 레벨에서 발생하는 심각한 수준의 오류이며, 개발자가 미리 예측하여 처리할 수 없다.  
예외는 개발자가 구현한 로직에서 발생하게 된다. 그렇기 때문에 예외가 발생할 만한 상황을 예측하여 처리해 놓을 수 있다.  

### 예외 계층 구조
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
 | `Rollback` | X | O |
 | `대표 Exception` | IOException, SQLException | NullPointerException, IllegalArgumentException |  
 
## Rollback
`Checked Exception` 과 `UnChecked Exception` 의 롤백 여부에 대해 코드로 알아 보자.  
 
 