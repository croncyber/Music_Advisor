package advisor;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        boolean exit = true;

        while (exit) {
            String message = scanner.nextLine().toLowerCase();
            String[] m = null;

            if (message.startsWith("playlists")) {
                m = message.split(" ");
                message = m[0];
            }

            switch (message) {
                case "new": {
                    System.out.print("---NEW RELEASES---");
                    continue;

                }
                case "featured": {
                    System.out.println("---FEATURED---");
                    break;
                }
                case "categories": {
                    System.out.print("---CATEGORIES---");
                    break;
                }
                case "playlists": {
                    System.out.printf("---%s PLAYLISTS---", m[1].toUpperCase());
                    break;
                }
                case "exit": {
                    System.out.println("---GOODBYE!---");
                    exit = false;
                    break;
                }
            }
        }

    }
}
