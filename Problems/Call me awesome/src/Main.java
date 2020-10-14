import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
       callAwesome();
    }

    // Do not change code below
    public static void callAwesome() {
        Scanner scanner = new Scanner(System.in);
        String name = scanner.next();
        System.out.println(name + ", you're awesome!");
    }
}