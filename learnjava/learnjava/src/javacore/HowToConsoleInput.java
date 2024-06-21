package javacore;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class HowToConsoleInput {
    public static void main(String[] args) throws IOException {
        bufferedReader();
        scanner();
        console();
    }

    private static void bufferedReader() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter your name:");
        String name = br.readLine();
        System.out.println("Name = " + name);

        System.out.println("Enter 5 numbers -");
        for (String elem : br.readLine().split(" ")) {
            System.out.print(elem + " ");
        }
        System.out.println();
    }

    private static void scanner() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter some text: ");
        String str = sc.nextLine();
        System.out.print(str);
        System.out.println();

        System.out.print("Enter an integer: ");
        int n = sc.nextInt();
        System.err.print(n);
        System.out.println();

        System.out.print("Enter a floating point number: ");
        float f = sc.nextFloat();
        System.err.print(f);
        System.out.println();

        sc.close();
    }

    private static void console() {
        System.out.print("Enter name: ");
        String name = System.console().readLine();
        System.out.println(name);
        System.out.println();
    }
}
