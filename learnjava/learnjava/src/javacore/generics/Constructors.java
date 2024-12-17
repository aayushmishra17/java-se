package javacore.generics;

public class Constructors {

    public static void main(String[] args) {
        Constructors obj = new Constructors();

        Container containerObj1 = obj.new Container(10);
        Container containerObj2 = obj.new Container(10.11);

        System.out.println(containerObj1._value);
        System.out.println(containerObj2._value);
    }

    class Container {
        double _value;

        public <T extends Number> Container(T val) {
            _value = val.doubleValue();
        }
    }

}
