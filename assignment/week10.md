# 10주차 과제
> 자바의 멀티쓰레드 프로그래밍에 대해 학습하세요.

## 학습할 것
- [Thread 클래스와 Runnable 인터페이스](#Thread-클래스와-Runnable-인터페이스)
- [쓰레드의 상태](#쓰레드의-상태)
- [쓰레드의 우선순위](#쓰레드의-우선순위)
- [Main 쓰레드](#Main-쓰레드)
- [동기화](#동기화)
- [데드락](#데드락)

## 프로세스와 스레드
프로세스란 운영 체제 위에서 실행중인 프로그램(현재 실행중인 프로그램)이다.  
모든 프로세스에는 최소 하나 이상의 스레드가 존재한다.  
작업 관리자를 보면 한순간에 여러가지의 작업을 하는것 처럼 보인다.  
![image](https://user-images.githubusercontent.com/55048593/105049175-bce78c00-5aaf-11eb-9004-d82e22d60420.png)

그 이유는 운영체제가 짧은 시간에 수십번에서 수천번 실행할 프로세스를 교체하면서 작업하고 있기때문에 우리는 동시에 여러개의 작업이 실행되고 있다고 느끼는 것이다.  
여러개의 프로세스들을 왔다갔다하면서 실행할때 기존 프로세스의 상태 또는 레지스터 값을 저장하고 대기하며, 다음 프로세스를 실행하는 즉 대기<->실행을 번갈아 가며 프로세스들을 실행하는 것을 `콘텍스트 스위칭` 이라고 한다.  
위키백과에는 `문맥 교환(文脈交換, context switch)이란 하나의 프로세스가 CPU를 사용 중인 상태에서 다른 프로세스가 CPU를 사용하도록 하기 위해, 이전의 프로세스의 상태(문맥)를 보관하고 새로운 프로세스의 상태를 적재하는 작업을 말한다.`라고 정의되어 있다.     
실행중인 프로세스는 코어로 로딩이 되게 되는데, 프로세스의 구조는 stack,heap,data,code 4가지가 있다. 프로세스마다 이 구조를 가지고 있기 때문에 실행되는 프로세스마다 코어에 로딩이 된다.  
그리고 `콘텍스트 스위칭` 이 일어날 때 마다 실행될 프로세스가 가지고있는 stack,heap,data,code 는 로딩 되게 된다.   
그렇기 때문에 CPU의 부담이 늘어나고, 중복된 자원들이 비효율적으로 관리 된다는 단점이 있다.

이런 이유때문에 비효율을 줄이기 위해서 스레드 라는 개념이 탄생하게 된다.  
스레드는 한 프로세스 내에서 동작되는 여러 실행의 흐름으로 프로세스 하나의 자원을 공유하면서 여러 작업을 동시에 실행 시킬 수 있는 것을 말한다.  
하나의 프로세스는 여러개의 스레드를 가질 수 있으며 이를 멀티 스레드라고 한다.  
![image](https://user-images.githubusercontent.com/55048593/105059126-4ef49200-5aba-11eb-8762-e3bbd7424faf.png)
출처 : https://devuna.tistory.com/21

멀티스레드는 code, data, heap 영역을 공유하기 때문에 `컨텍스트 스위칭` 시에 비효율적인 자원 소모가 감소하게 된다.  
멀티스레드도 컨텍스트 스위칭 비용이 발생하긴 하지만, 멀티프로세스보다 훨씬 오버헤드 시간이 적다.  

## Thread 클래스와 Runnable 인터페이스
Thread 를 자바에서 구현하는 방법은 Thread 클래스를 상속받는법, Runnable 인터페이스를 구현하는법, 즉 2가지가 있다.  
Thread 클래스를 상속받으면 다른 클래스를 상속받을 수 없기 때문에, Runnable 인터페이스를 구현하는 방법이 일반적이다.  

### Thread 클래스를 상속
```java
public class MyThread extends Thread {

    @Override
    public void run() { // Thread 클래스의 run() 을 오버라이딩
        for (int i = 0; i < 3; i++) {
            try {
                Thread.sleep(1);
                System.out.println("Thread 를 상속받은 run method");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
```

### Runnable 인터페이스를 구현
```java
public class MyThread2 implements Runnable {
    @Override
    public void run() { // Runnable 인터페이스의 run() 을 구현
        for (int i = 0; i < 3; i++) {
            try {
                Thread.sleep(1);
                System.out.println("Runnable 을 구현한 run method");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
```
쓰레드를 구현 한다는 것은, 위의 두방법중 어떤것을 선택하든 쓰레드를 통해 작업하고자 하는 내용으로 run()의 몸통을 채우는 것이다.  

### 구현한 Thread 실행
```java
public class ExecuteThread {

    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        Runnable runnable = new MyThread2();
        Thread myThread2 = new Thread(runnable);

        myThread.start();
        myThread2.start();
    }
}
```
Thread 를 구현한 클래스 2개를 생성한 후 run() 메소드를 구현한다. for문을 돌때마다 sleep() 를 이용해 지연시키는 코드를 추가한 후 메인에서는 두 스레드를 실행시킨다.  
싱글스레드 일경우에는 start 시킨 객체가 순차적으로 실행되지만 위 코드는 멀티스레드로 동작하기 때문에 병렬로 실행하게 된다.  
start()를 호출하면 스레드가 실행되며 위 코드를 실행한 결과이다.  
![image](https://user-images.githubusercontent.com/55048593/105625192-c6d00d00-5e6a-11eb-9c14-465627c726c3.png)  

사실 start() 가 호출 되었다고 바로 실행되는것이아니고 실행대기 상태에 있다가 자신의 차례가 되어야 실행된다.  
이말이 잘 이해되지않는다... 다음에 제대로 공부할 기회가 있다면 확실히 알아보자!

## 쓰레드의 상태
Thread의 상태는
1. NEW : 쓰레드가 생성되고 아직 start() 가 호출되지 않은 상태
2. RUNNABLE : 실행 중 또는 실행 가능한 상태
3. BLOCKED : 동기화 블럭에 의해서 일시정지된 상태
4. WAITING, TIMED_WAITTING : 쓰레드의 작업이 종료되지는 않았지만 실행가능하지않은 일시정지 상태.
5. TERMINATED : 쓰레드의 작업이 종료된 상태
![image](https://user-images.githubusercontent.com/55048593/105625468-fa139b80-5e6c-11eb-9929-2566c001627c.png)
출처 : https://yadon079.github.io/2021/java%20study%20halle/week-10  

sleep() 는 지정된 시간동안 쓰레드를 멈추게 만든다. 그리고 지정시간이 다 되거나 interrupt() 가 호출되면 실행대기 상태가 된다.  
그렇기 때문에 위의 코드를 실행하여 Thread.sleep() 를 만날때마다 스레드가 일시정지가 되고 그동안 다른 스레드가 실행하게 되는것이다.(확실하지 않음! 시간될때 다시 제대로찾아보기)

## 쓰레드의 우선순위
쓰레드는 우선순위(priority) 라는 속성을 가지고 있는데, 이 우선순위의 값에 따라 쓰레드가 얻는 실행시간이 달라진다. 쓰레드가 수행하는 작업의 중요도에 따라  
쓰레드의 우선순위를 서로 다르게 지정하여 특정 쓰레드가 많은 작업시간을 갖도록 할 수 있다.  
```
myThread2.setPriority(10);
```
우선순위는 1~10 이며 숫자가 높을수록 우선순위가 높다. 해당 코드를 통해 생성한 스레드 인스턴스에 우선순위를 부여할 수 있다.  
`하지만 우선순위는 절대적으로 지켜지는 것이 아니다.` 다만 우선순위가 높은 쓰레드에게 상대적으로 많은 양의 실행시간이 주어지는것 일 뿐이다.   

## Main 쓰레드
Java 어플리케이션은 기본적으로 하나의 메인 쓰레드를 가진다.  
Java 프로그램을 실행하기 위해 Main Thread 는 main() 메소드를 실행한다. main() 메소드는 메인 쓰레드의 시작점을 선언 하는 것이다.  
메인 쓰레드는 자바에서 처음으로 실행되는 쓰레드이고, 모든 쓰레드는 메인 쓰레드로부터 생성된다.  
웹개발을 할때 주로 사용하고 있는 `SpringBoot` 또한 main thread 를 통해 실행시키고, 해당 스레드가 작업을 완료하고 종료될때까지(tomcat을 종료할때까지) 프로그램은 종료되지 않는다.  

## 동기화
싱글스레드 프로세스인 경우에는 프로세스 내에서 단 하나의 스레드만 작업하기 때문에 프로세스의 자원을 가지고 작업하는데 문제가 없다.  
하지만 멀티스레드 프로세스의 경우 여러 스레드가 같은 프로세스 내의 자원을 공유해서 작업하기 때문에 서로의 작업에 영향을 주게 된다.  
한 스레드가 진행중인 작업을 다른 스레드가 간섭하지 못하도록 막는것을 동기화(synchronization) 이라고 한다.  

## 데드락
2개 이상의 프로세스가 다른 프로세스의 작업이 끝나기만 기다리고 있어서 아무것도 완료되지 못하는 상태이다.    
## 참고
https://www.youtube.com/watch?v=DmZnOg5Ced8  
https://devuna.tistory.com/21  
https://yadon079.github.io/2021/java%20study%20halle/week-10  
