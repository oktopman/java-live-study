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
javac 를 이용하여 컴파일 할 수 있는 이유는 JDK 에 있는 컴파일러 javac.exe 가 있기 때문이다.  
Hello.java 클래스를 만들고 Hello 라는 문자열을 출력하는 내용을 넣었다.
![image](https://user-images.githubusercontent.com/55048593/99273971-1099e700-286d-11eb-9a6f-d2eafcd8a3d4.png)

## 실행하는 방법
JRE 에 있는 java.exe 파일은 실행을 담당한다.
java 명령어를 통해 실행 가능하다.  
![image](https://user-images.githubusercontent.com/55048593/99274054-2e674c00-286d-11eb-92e1-9d98282b0e9e.png)

## 바이너리코드란 무엇인가
컴퓨터(CPU)가 인식할 수 있게 기계어(0 과 1)로 구성된 이진코드. 
c언어는 컴파일러에 의해 소스파일(.c) 이 목적파일(.obj)로 변환될 때 기계어로 변환된다.  
하지만 완전한 기계어가 아니기때문에 실행 될 수 없다.  
변환된 목적파일은 링커에 의해 실행 가능한 실행파일(.exe)로 변환될 때함수나 헤어 파딩 등이 실제 메모리 주소를 코드에 반영하는 과정에서 일부 주소값이 변경된다.  
이러한 과정을 거쳐야 실행 가능한 기계어가 된다.     

## 바이트코드란 무엇인가
Java로 따지자면 작성한 소스코드를 JVM이 이해 할 수 있게 컴파일 한 코드를 말한다.
컴파일러(javac) 를 통해 .java -> .class 로 변환될 때 바이너리코드가 아닌 바이트 코드로 변환된다.  
그렇기 때문에 바이트 코드는 JVM이 이해 할 수 있고 실행이 가능하다. 
기본적으로 명령어를 하나씩 읽어서 처리하는 인터프리터 방식으로 동작한다.

## JIT 컴파일러란 무엇이며 어떻게 동작할까
프로그램을 실제 실행하는 시점에 기계어로 번역하는 컴파일 기법이다.
인터프리터의 단점을 보완하기 위해 도입된 방식으로, 바이트 코드 전체를 컴파일 하여 네이티브 코드로 변경하고 이후에는 해당 메서드를 더이상 인터프리팅 하지 않고 네이티브 코드로 실행하는 방식이다.
  
하나씩 실행하는것이 아니라 바이트코드 전체가 컴파일된 네이티브 코드를 실행하는 것이기 때문에 전체적인 실행속도는 인터프리팅 방식보다 빠르다.    
네이티브코드는 캐시에 보관하기 때문에 한번 컴파일된 코드는 캐시에서 바로 꺼내어 실행하기 때문에 빠르다.  
하지만 JIT컴파일러가 컴파일 하는 과정은 인터프리팅 하는것보다 오래걸리기 때문에 JVM은 내부적으로 해당 메서드가 자주 호출되는지 체크하고 컴파일하여 네이티브 코드를 생성한다.   
다음 그림과 같은 형태로 동작한다.  
![image](https://user-images.githubusercontent.com/55048593/99543748-de1bf580-29f6-11eb-87b6-8adf6803a3d4.png)
  
바이트코드를 IR로 변환하여 최적화를 수행하고 그 다음에 네이티브 코드를 생성한다.  

![image](https://user-images.githubusercontent.com/55048593/99544278-7619df00-29f7-11eb-9cf0-578e15afffc9.png)
오라클 핫스팟 VM은 핫스팟 컴파일러라고 불리는 JIT 컴파일러를 사용하며 내부적으로 컴파일이 필요한 '핫스팟'을 찾아낸 다음 이 핫스팟을 네이티브 코드로 컴파일 한다.  
핫스팟 VM은 컴파일된 바이트코드라도 해당 메서드가 자주 불리지않는다면 캐시에서 네이티브코드를 덜어내고 다시 인터프리터 모드로 동작한다.  

출처 및 참고: https://d2.naver.com/helloworld/1230

## JVM 구성 요소
![image](https://user-images.githubusercontent.com/55048593/99544834-196af400-29f8-11eb-942b-0bf6a8ed0a53.png)  
출처: https://d2.naver.com/helloworld/1230
  
Class Loader : JVM이 운영체제로부터 할당받은 메모리영역인 Runtime Data Areas로 컴파일된 바이트코드를 로드하는역할을 한다.
  
Execution Engine : Class Loader에 의해 메모리에 적재된 바이트코드들을 기계어로 변경해 명령어 단위로 실행하는 역할을 한다.
명령어 실행은 인터프리터 방식, JIT컴파일러 방식이 있다.

Runtime Data Area : JVM의 메모리 영역으로 자바 어플리케이션을 실행할 때 사용되는 데이터들을 적재하는 영역이다.  
- PC Register
- Stack Area
- Heap Area
- Native method stack
- Method Area 
로 구성 되어 있다.  
https://d2.naver.com/helloworld/1230 에 자세히 설명되어 있다.

Garbage Collector : Heap 메모리 영역에 생성(적재)된 객체들 중에 사용되지 않는 객체들을 탐색 후 제거하는 역할을 한다. 정확한 GC 시간은 알 수 없다.
https://d2.naver.com/helloworld/1329 에 자세히 설명 되어 있다.

## JDK와 JRE의 차이
JDK : Java Development Kit의 약자이고 자바로 프로그래밍 하기 위해 필요한 도구들을 포함한다.  
컴파일러, 자바 등이 있다. JRE 도 JDK에 포함 되어 있다.

JRE : Java Runtime Environment의 약자이고 JVM에서 '실행'할 때 필요한 라이브러리 파일들을 가지고 있다.

## 참고 자료
참고 : https://shrtorznzl.tistory.com/82  
참고 : https://jeong-pro.tistory.com/148