package javacore;

import java.text.MessageFormat;

public class FormattingOutput {
    public static void main(String[] args) {
        printf();
        stringFormat();
        stringFormatted();
        messageFormat();
        stringTemplate();
    }

    private static void printf() {
        // %[flags][width][.precision]conversion-character

        // conversion-character
        /*
         * %s - string
         * %d - decimal integer
         * %f - floating point
         * %t - date/time
         * %b - boolean
         * %B - uppercase boolean
         * %n - new line
         */
        System.out.println("_______________________________");
        System.out.println("_________STRING_______________");
        System.out.printf("baeldung%nline%nterminator%n");

        System.out.printf("'%s'%n", "Java");
        System.out.printf("'%15s'%n", "Java");
        // '-' Aligns the formatted printf output to the left
        System.out.printf("'%-15s'%n", "Java");
        System.out.printf("'%-15S'%n", "Java");
        System.out.println("_______________________________");

        // integer formatting
        System.out.println("_______________________________");
        System.out.println("_________INTEGER_______________");
        System.out.printf("%d %n", 123456789);
        System.out.printf("%,15d %n", 123456789);
        System.out.printf("%+,15d %n", 123456789);
        System.out.printf("%-+,15d %n", 123456789);
        System.out.printf("%0,15d %n", 123456789);
        System.out.printf("%15o %n", 123456789);
        System.out.printf("%15x %n", 123456789);
        System.out.println("_______________________________");

        // floating point
        System.out.println("_______________________________");
        System.out.println("__________FLOATING_____________");
        System.out.printf("%+,.3f %n", 1234.12345d);
        System.out.printf("%,.5f %n", 1234.12345f);
        System.out.println("_______________________________");

        // scientific notation
        System.out.println("_______________________________");
        System.out.println("__________SCIENTIFIC___________");
        System.out.printf("%e %n", 1234.12345);
        System.out.printf("%+.3e %n", 1234.12345);
        System.out.printf("%+.5e %n", 1234.12345);
        System.out.println("_______________________________");

        System.out.println("_______________________________");
        System.out.println("___________CHAR________________");
        System.out.printf("%c %n", 'a');
        System.out.printf("%C %n", 'a');
        System.out.println("_______________________________");

        System.out.println("_______________________________");
        System.out.println("__________SCIENTIFIC___________");
        System.out.println("_______________________________");
    }

    private static void stringFormat() {
        // String.format() returns a String while printf does not.
        System.out.println();
        System.out.println(String.format("%s", "abcd"));
        System.out.println(String.format("%+,15d", 123456));
        System.out.println();
    }

    private static void stringFormatted() {
        System.out.println();
        String source = """
                public void print(%s object) {
                    System.out.println(Objects.toString(object));
                }
                """.formatted("Integer");
        System.out.println(source);
        System.out.println();
    }

    private static void messageFormat() {
        System.out.println(MessageFormat.format("My name is {0}. My age is {1}.", "Aayush", "xx"));
    }

    private static void stringTemplate() {
        // Requires JDK 21
        // String line = STR."My name is \{name}. My age is \{age}";
    }
}
