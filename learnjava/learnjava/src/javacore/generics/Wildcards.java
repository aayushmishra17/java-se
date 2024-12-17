package javacore.generics;

import java.text.MessageFormat;

public class Wildcards {

    public static void main(String[] args) {

        Wildcards obj = new Wildcards();

        TwoD[] twoDArr = new TwoD[] {
                obj.new TwoD(5, 5),
                obj.new TwoD(10, 10),
                obj.new TwoD(15, 15),
        };
        ThreeD[] threeDArr = new ThreeD[] {
                obj.new ThreeD(5, 5, 5),
                obj.new ThreeD(10, 10, 10),
                obj.new ThreeD(15, 15, 15),
        };
        FourD[] fourDArr = new FourD[] {
                obj.new FourD(5, 5, 5, 5),
                obj.new FourD(10, 10, 10, 10),
                obj.new FourD(15, 15, 15, 15),
        };

        Coords<TwoD> twoDCoords = obj.new Coords<>(twoDArr);
        Coords<ThreeD> threeDCoords = obj.new Coords<>(threeDArr);
        Coords<FourD> fourDCoords = obj.new Coords<>(fourDArr);

        twoDCoords.showXY(twoDCoords);
        twoDCoords.showXY(threeDCoords);
        twoDCoords.showXY(fourDCoords);

        // threeDCoords.showXYZ(twoDCoords);
        threeDCoords.showXYZ(threeDCoords);
        threeDCoords.showXYZ(fourDCoords);

        // fourDCoords.showAll(twoDCoords);
        // fourDCoords.showAll(threeDCoords);
        fourDCoords.showAll(fourDCoords);
    }

    class TwoD {
        int _x;
        int _y;

        public TwoD(int x, int y) {
            _x = x;
            _y = y;
        }
    }

    class ThreeD extends TwoD {
        int _z;

        public ThreeD(int x, int y, int z) {
            super(x, y);
            _z = z;
        }
    }

    class FourD extends ThreeD {
        int _t;

        public FourD(int x, int y, int z, int t) {
            super(x, y, z);
            _t = t;
        }
    }

    class Coords<T extends TwoD> {
        T[] _coords;

        public Coords(T[] arr) {
            _coords = arr;
        }

        void showXY(Coords<?> arr) {
            for (int indx = 0; indx < arr._coords.length; indx++) {
                System.out.println(MessageFormat.format("X = {0}", arr._coords[indx]._x));
                System.out.println(MessageFormat.format("Y = {0}", arr._coords[indx]._y));
            }
        }

        void showXYZ(Coords<? extends ThreeD> arr) {
            for (int indx = 0; indx < arr._coords.length; indx++) {
                System.out.println(MessageFormat.format("X = {0}", arr._coords[indx]._x));
                System.out.println(MessageFormat.format("Y = {0}", arr._coords[indx]._y));
                System.out.println(MessageFormat.format("Z = {0}", arr._coords[indx]._z));
            }
        }

        void showAll(Coords<? extends FourD> arr) {
            for (int indx = 0; indx < arr._coords.length; indx++) {
                System.out.println(MessageFormat.format("X = {0}", arr._coords[indx]._x));
                System.out.println(MessageFormat.format("Y = {0}", arr._coords[indx]._y));
                System.out.println(MessageFormat.format("Z = {0}", arr._coords[indx]._z));
                System.out.println(MessageFormat.format("T = {0}", arr._coords[indx]._t));
            }
        }

        // Creates a lower bound of FourD.
        void showXY2(Coords<? super FourD> arr) {
            for (int indx = 0; indx < arr._coords.length; indx++) {
                System.out.println(MessageFormat.format("X = {0}", arr._coords[indx]._x));
                System.out.println(MessageFormat.format("Y = {0}", arr._coords[indx]._y));
            }
        }
    }

}
