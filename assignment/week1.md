## 목표

자바 소스 파일(.java)을 JVM으로 실행하는 과정 이해하기.

## 학습할 것
- JVM이란 무엇인가
- 컴파일 하는 방법
- 실행하는 방법
- 바이트코드란 무엇인가
- JIT 컴파일러란 무엇이며 어떻게 동작하는지
- JVM 구성 요소
- JDK와 JRE의 차이

## JVM이란 무엇인가
Java Virtual Machine의 약자이며 자바 가상 머신의 약자를 따서 부르는 용어.
JVM의 역할은 자바 어플리케이션을 클래스 로더를 통해 읽어들여 자바 API와 함께 실행한다.
자바가 OS에 종속적이지 않게 해주고 garbage collection 과 메모리 관리 등을 해준다. 

## 컴파일 하는 방법
자바로 작성된 코드를 컴퓨터가 이해할 수 있게 바이너리코드로 변환하는 과정을 컴파일 이라 한다.  
커맨드를 이용하여 .java 파일을 컴파일하여 .class을 생성 할 수 있다.  
javac 를 이용하여 컴파일 할 수 있는 이유는 JDK 에 있는 컴파일을 담당하는 javac.exe 가 있기 때문이다.  
Hello.java 클래스를 만들고 Hello 라는 문자열을 출력하는 내용을 넣었다.
![image](https://user-images.githubusercontent.com/55048593/99273971-1099e700-286d-11eb-9a6f-d2eafcd8a3d4.png)

## 실행하는 방법
JRE 에 있는 java.exe 파일은 실행을 담당한다.
java 명령어를 통해 실행 가능하다.
![image](https://user-images.githubusercontent.com/55048593/99274054-2e674c00-286d-11eb-92e1-9d98282b0e9e.png)







