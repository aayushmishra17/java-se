package javacore.classes_and_interfaces;

import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class MethodReferences {

    public static void main(String[] args) {

        Consumer<String[]> f = MethodReferences::main; // Static method reference.
        f.accept(new String[0]);

        Supplier<Integer> s = "abcd"::length; // Un-bounded method reference.
        System.out.println(s.get());

        Consumer<String> c = System.out::println; // Bounded method reference. Operates on the System.out object.
        c.accept("abcd");

        BiFunction<String, Double, Rectangle> biFunc = Rectangle::new; // Constructor reference.
        Rectangle rectOb = biFunc.apply("New Rectangle", 100.00);
        System.out.println(rectOb.getName() + " " + rectOb.getArea());
    }

}

/*
 * Super type instance method reference.
 */
interface ShapeInterface {
    default double getArea() {
        return 1.0;
    }
}

class Rectangle implements ShapeInterface {
    private String name = "Unknown";
    private double area = 0.0;

    public Rectangle() {
        System.out.println("Constructor Rectangle() called.");
    }

    public Rectangle(String name) {
        this.name = name;
        System.out.println("Constructor Rectangle(String) called.");
    }

    public Rectangle(String name, double area) {
        this.name = name;
        this.area = area;
        System.out.println("Constructor Rectangle(String, double) called.");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double area) {
        this.area = area;
    }

    @Override
    public double getArea() {
        return area;
    }

    @Override
    public String toString() {
        return "name = " + getName() + ", area = " + getArea();
    }

    public void test() {
        // Uses the Rectangle.toString() method
        Supplier<String> s1 = this::toString;

        // Uses the Object.toString() method
        Supplier<String> s2 = Rectangle.super::toString;

        // Uses the Rectangle.getArea() method
        Supplier<Double> s3 = this::getArea;

        // Uses the Shape.getArea() method
        Supplier<Double> s4 = ShapeInterface.super::getArea;

        // Uses all method references and prints the results
        System.out.println("this::toString: " + s1.get());
        System.out.println("Rectangle.super::toString: " + s2.get());
        System.out.println("this::getArea: " + s3.get());
        System.out.println("Shape.super::getArea: " + s4.get());
    }
}
// ---------------------------------------------------------

/*
 * Constructor reference to generic class.
 */
// MyFunc is now a generic functional interface.
interface MyFunc<T> {
    MyClass<T> func(T n);// w w w . d e m o 2 s . c om
}

class MyClass<T> {
    private T val;

    // A constructor that takes an argument.
    MyClass(T v) {
        val = v;
    }

    // This is the default constructor.
    MyClass() {
        val = null;
    }

    T getVal() {
        return val;
    };
}

class GenericClassConstructorReference {

    public static void demo() {
        // Create a reference to the MyClass<T> constructor.
        MyFunc<Integer> myClassCons = MyClass<Integer>::new;

        // Create an instance of MyClass<T> via that constructor reference.
        MyClass<Integer> mc = myClassCons.func(100);

        // Use the instance of MyClass<T> just created.
        System.out.println("val in mc is " + mc.getVal());
    }

}
// ---------------------------------------------------------