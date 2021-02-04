package me.oktop.javastudy.week12;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD) // 메소드에 사용될 수 있다고 지정
@Retention(RetentionPolicy.RUNTIME) // 런타임시 이 어노테이션을 참조
public @interface UserAnnotation { // @interface 를 통해 @UserAnnotation 으로 사용가능한 어노테이션이 생성됨

    int number();
}
