# 7주차 과제
> 자바의 패키지에 대해 학습하세요.

## 학습할 것
- [package 키워드](#package-키워드)
- [import 키워드](#import-키워드)
- [클래스패스](#클래스패스)
- [CLASSPATH 환경변수](#CLASSPATH-환경변수)
- [classpath 옵션](#classpath-옵션)
- [접근지시자](#접근지시자)

## package 키워드
클래스들의 모음을 패키지라고 하고 프로젝트를 편리하게 구분하여 관리할 수 있다.    
클래스를 생성할때 `package` 키워드를 통해 포함될 패키지를 지정할 수 있다.  
```java
package me.oktop.javastudy.week7;

public class SampleClass {
}

``` 
  
패키지의 이름은 모두 소문자 여야 하고 자바의 예약어를 사용하면 안된다.  
패키지는 관련된 클래스를 그룹화 하고 포함된 클래스의 네임스페이스를 정의하는 역할을 한다.  
관련된 클래스를 잘 패키징 한다면 처음보는사람이 패키지만 보고도 프로젝트가 어떻게 구성되어있는지 파악하기 쉽다.  
혼자 개발하는것보다 협업을 할때 더 강력한 힘을 발휘 하는것 같다.  
![image](https://user-images.githubusercontent.com/55048593/103454179-b8d41400-4d24-11eb-91ae-379ad00f9d0d.png)  
위 그림은 자바스터디 프로젝트의 패키지 구조이다.  
me.oktop.javastudy 패키지 아래에 주차 마다 진행하는 과제를 week 라는 네이밍을 붙여서 생성하고 그 하위에 관련된 클래스들을 생성하였다.

## import 키워드
`import` 키워드는 다른 패키지에 있는 클래스와 같은 파일을 해당 클래스에서 참조하려고 할 때 사용한다.  
같은 패키지의 클래스는 `import` 없이 참조 가능하다.  
```java
package me.oktop.javastudy.week4;
import org.junit.jupiter.api.BeforeEach; // 타 패키지 클래스 import
import org.junit.jupiter.api.Test; // 타 패키지 클래스 import

import static org.assertj.core.api.Assertions.assertThat; // 타 패키지 클래스 import

class ListNodeQueueTest {
    ListNodeQueue listNodeQueue;

    @BeforeEach
    void setUp() {
        listNodeQueue = new ListNodeQueue(1);
        listNodeQueue.push(2);
    }

    @Test
    void push_test() {
        assertThat(listNodeQueue.getHead().getValue()).isEqualTo(1);
        assertThat(listNodeQueue.getHead().getNext().getValue()).isEqualTo(2);
    }
}
```
위 코드는 Junit을 이용해 테스트코드를 작성할때 사용한 코드이다.  
@BeforeEach, @Test 와 같은 어노테이션은 org.junit.jupiter.api.* 패키지 하위에 있는 클래스들 이기때문에 `import` 키워드를 사용하여 참조 했다.    
오픈소스 클래스를 `import` 하였지만 내가 직접생성한 클래스도 위와 같이 `import` 할 수 있다.    
IDE의 도움을 받으면 사용하려는 클래스를 생성하기만 해도 자동으로 `import` 해준다. 
 
FQCN(Full Qualified Class Name) 은 클래스가 속한 패키지명을 모두 포함한 이름을 말한다.  
예를 들어  String 클래스를 포함하여 선언하는방법은 두가지가 있다.  
```java
String s = new String();

java.lang.String s = new String();
```
두번째 방법이 FQCN 이다.  
## 클래스패스
클래스패스란 자바에서 자바 가상머신이 클래스파일을 찾는 경로이다.  
자바 가상머신(JVM)은 프로그램을 실행할때 클래스 파일을 찾는데, 이때 클래스 패스를 기준으로 찾는다.  
클래스 패스를 지정하지 않으면 자바 가상머신은 필요한 클래스를 현재 디렉토리에서 찾는다.  

## CLASSPATH 환경변수
환경변수는 프로세스가 컴퓨터에서 동작하는 방식에 영향을 미치는 동적인 값이다.  
클래스패스를 환경 변수를 통해 설정할 수 있다.  
JVM이 시작될 때 JVM의 클래스 로더는 이 환경 변수를 호출 한다. 그래서 환경 변수에 설정되어있는 디렉토리가 호출되면서 그 디렉토리에 있는 클래스들을 JVM에 로드한다.  
그렇기 때문에 환경 변수로 클래스들이 위치한 디렉토리를 등록해야 한다.  
macos 에서는 .bash_profile 파일에 환경변수를 작성하여 등록 가능하다.  
![image](https://user-images.githubusercontent.com/55048593/103454824-ffc50800-4d2a-11eb-89e2-731a185241c1.png)

## classpath 옵션
컴파일러가 컴파일 하기 위해서 필요로 하는 클래스 파일들을 찾기 위해서 컴파일시 파일 경로를 지정해주는 옵션이다.  
javac -classpath /User/hayun/UdeaProjects/java-live-study/example 라는 명령어를 통해 옵션ㄴ으로 클래스패스를 지정해 줄 수 있다.   
classpath 를 환경변수로 설정 한 것보다 -classpath 옵션으로 설정한 것이 우선 순위가 더 높다.  

## 접근지시자
클래스, 메소드, 인스턴스 나 클래스 변수를 선언할 때 사용된다.
자바의 캡슐화를 위해 멤버 변수 앞에는 주로 private를 붙이고, getter,setter 메소드를 통해 접근한다.  
자바에서 사용하는 접근지시자는 4가지가 있다.  
`public` : 어디서든 접근 가능
`default` : 동일 패키지 내에서 접근 가능. 접근지시자를 선언하지 않은 경우
`protected` : 클래스 내부, 동일패키지, 상속받은 클래스에서만 접근 가능
`private` : 클래스 내부에서만 접근 허용
![image](https://user-images.githubusercontent.com/55048593/103455030-c55c6a80-4d2c-11eb-86e4-feb6171badc6.png)  
참조 : https://powerku.tistory.com/75 

## 참고
https://sangwoo0727.github.io/java/JAVA-10_Classpath/  
https://kils-log-of-develop.tistory.com/430
https://payoff.tistory.com/401