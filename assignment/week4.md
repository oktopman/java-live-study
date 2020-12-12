## 목표

자바가 제공하는 제어문을 학습하세요.

## 학습할 것(선택문, 반복문)
반복문 : https://github.com/oktopman/java-live-study/blob/main/java-study/src/test/java/me/oktop/javastudy/week4/LoopTest.java  
선택문 : https://github.com/oktopman/java-live-study/blob/main/java-study/src/test/java/me/oktop/javastudy/week4/SwitchCaseTest.java

## 과제0. JUnit5 학습하세요.
과제들을 테스트코드를 사용하여 구현하였습니다.  
https://github.com/oktopman/java-live-study/tree/main/java-study/src/test/java/me/oktop/javastudy/week4

## 과제 1. live-study 대시 보드를 만드는 코드를 작성하세요.
- 깃헙 이슈 1번부터 18번까지 댓글을 순회하며 댓글을 남긴 사용자를 체크 할 것.  
- 참여율을 계산하세요. 총 18회에 중에 몇 %를 참여했는지 소숫점 두자리가지 보여줄 것.  
- Github 자바 라이브러리를 사용하면 편리합니다.  
- 깃헙 API를 익명으로 호출하는데 제한이 있기 때문에 본인의 깃헙 프로젝트에 이슈를 만들고 테스트를 하시면 더 자주 테스트할 수 있습니다.

https://github.com/oktopman/java-live-study/blob/main/java-study/src/test/java/me/oktop/javastudy/week4/DashboardTest.java
  
## 과제 2. LinkedList를 구현하세요.
- LinkedList에 대해 공부하세요.  
- 정수를 저장하는 ListNode 클래스를 구현하세요.  
- ListNode add(ListNode head, ListNode nodeToAdd, int position)를 구현하세요.  
- ListNode remove(ListNode head, int positionToRemove)를 구현하세요.  
- boolean contains(ListNode head, ListNode nodeTocheck)를 구현하세요.  

https://github.com/oktopman/java-live-study/blob/main/java-study/src/main/java/me/oktop/javastudy/week4/ListNode.java

## 과제 3. Stack을 구현하세요.
- int 배열을 사용해서 정수를 저장하는 Stack을 구현하세요.  
- void push(int data)를 구현하세요.  
- int pop()을 구현하세요.  

https://github.com/oktopman/java-live-study/blob/main/java-study/src/main/java/me/oktop/javastudy/week4/Stack.java

## 과제 4. 앞서 만든 ListNode를 사용해서 Stack을 구현하세요.
- ListNode head를 가지고 있는 ListNodeStack 클래스를 구현하세요.  
- void push(int data)를 구현하세요.  
- int pop()을 구현하세요.  

https://github.com/oktopman/java-live-study/blob/main/java-study/src/main/java/me/oktop/javastudy/week4/ListNodeStack.java
## 과제 5. Queue를 구현하세요.
- 배열을 사용해서 한번  
https://github.com/oktopman/java-live-study/blob/main/java-study/src/main/java/me/oktop/javastudy/week4/Queue.java
- ListNode를 사용해서 한번  
https://github.com/oktopman/java-live-study/blob/main/java-study/src/main/java/me/oktop/javastudy/week4/ListNodeQueue.java  