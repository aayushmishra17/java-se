package javacore.annotations;

import java.lang.annotation.*;
import java.lang.reflect.*;

public class Annotations {

    public static void main(String[] args) {
        myMethod();
    }

    @MyAnno(str = "Annotation Example", val = 100)
    public static void myMethod() {

        Annotations mainObj = new Annotations();

        try {

            Class<?> c = mainObj.getClass();
            Method m = c.getMethod("myMethod");
            MyAnno anno = m.getAnnotation(MyAnno.class);

            System.out.println(anno.str() + " " + anno.val());

        } catch (NoSuchMethodException e) {
            System.out.println(e);
        }

    }

    @MyAnnoWithDefaults
    public static void anotherMethod(String str, int i) {

        Annotations mainObj = new Annotations();

        try {

            Method m = mainObj.getClass().getMethod("anotherMethod", String.class, int.class);
            MyAnnoWithDefaults myAnno = m.getAnnotation(MyAnnoWithDefaults.class);

            System.out.println(myAnno.str() + " " + myAnno.val());

        } catch (NoSuchMethodException e) {
            System.out.println(e);
        }

    }

}

@Retention(RetentionPolicy.RUNTIME)
@interface MyAnno {

    String str();

    int val();

}

@Retention(RetentionPolicy.RUNTIME)
@interface MyAnnoWithDefaults {

    String str() default "Example annotation";

    int val() default 100;

}
