package site.elven.test.java.lang;

import org.hibernate.annotations.Type;
import org.junit.Test;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

public class AnnotationTest {

    @Test
    public void test1(){

    }

    public void getAnnotation(Class clazz){
        Member member = (Member) clazz.getAnnotation(Member.class);
        Member member1 = clazz.getFields()[0].getAnnotation(Member.class);
    }
}

@Target({ElementType.TYPE, ElementType.FIELD})
@interface Member{
    String value();
    String name();
}
