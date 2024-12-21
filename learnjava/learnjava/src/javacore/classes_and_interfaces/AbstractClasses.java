package javacore.classes_and_interfaces;

public class AbstractClasses {

    public static void main(String[] args) {
        EquilateralTriangle obj = new EquilateralTriangle(10);
        System.out.println("Perimeter = " + obj.perimeter());
    }

}

abstract class Shape {
    abstract double perimeter();

    void print() {
        System.out.println("Base Shape class.");
    }
}

class EquilateralTriangle extends Shape {

    double sideLength = 1.0d;

    public EquilateralTriangle(double sideLen) {
        super();
        sideLength = sideLen;
    }

    @Override
    double perimeter() {
        return sideLength * 3;
    }

    @Override
    void print() {
        super.print();
        System.out.println("Equilateral triangle.");
    }

}