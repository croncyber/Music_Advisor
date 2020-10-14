import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double a = scanner.nextDouble();
        double b = scanner.nextDouble();
        double c = scanner.nextDouble();

        double v = Math.pow(b, 2) - 4 * a * c;
        double minX = (-b - Math.sqrt(v)) / (2 * a);
        double maxX = (-b + Math.sqrt(v)) / (2 * a);

        System.out.printf("%f %f", Math.min(minX, maxX), Math.max(minX, maxX));
    }
}