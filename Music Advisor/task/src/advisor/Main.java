package advisor;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;
import java.util.concurrent.Executor;

public class Main {

    public static String SERVER_PATH = "https://accounts.spotify.com";
    public static String REDIRECT_URI = "http://localhost:8080";
    public static String CLIENT_ID = "21f6c8f9857c4a11b2d6daa0074a5975";
    public static String CLIENT_SECRET = "528668f73a964b53819cdc17a2d0d108";
    public static String ACCESS_TOKEN = "";
    public static String ACCESS_CODE = "";
    public static String uri = SERVER_PATH + "/authorize"
            + "?client_id=" + CLIENT_ID
            + "&redirect_uri=" + REDIRECT_URI
            + "&response_type=code";
    public static final int PORT = 8080;
    private static final String CONTEXT = "/";


    static void getAccessCode() throws Exception {
        System.out.println("use this link to request the access code:\n" +
                uri);

        HttpServer server = HttpServer.create();
        server.bind(new InetSocketAddress(8080), 0);

        server.createContext(CONTEXT,
                new HttpHandler() {
                    @Override
                    public void handle(HttpExchange exchange) throws IOException {
                        String query = exchange.getRequestURI().getQuery();
                        String request;
                        if (query != null && query.contains("code")) {
                            ACCESS_CODE = query.substring(5);
                            System.out.println("code received");
                            request = "Got the code. Return back to your program.";
                        } else {
                            request = "Authorization code not found. Try again.";
                        }
                        exchange.sendResponseHeaders(200, request.length());
                        exchange.getResponseBody().write(request.getBytes());
                        exchange.getResponseBody().close();
                    }
                });

        server.start();

        System.out.println("waiting for code...");
        while (ACCESS_CODE.length() == 0) {
            Thread.sleep(100);
        }
        server.stop(1);
    }

    static void getAccessToken() throws Exception {
        System.out.println("making http request for access_token...\n" +
                "response:");

        HttpRequest request = HttpRequest.newBuilder()
                .header("Content-Type", "application/x-www-form-urlencoded")
                .uri(URI.create(SERVER_PATH + "/api/token"))
                .POST(HttpRequest.BodyPublishers.ofString(
                        "grant_type=authorization_code"
                                + "&code=" + ACCESS_CODE
                                + "&client_id=" + CLIENT_ID
                                + "&client_secret=" + CLIENT_SECRET
                                + "&redirect_uri=" + REDIRECT_URI))
                .build();

        HttpClient client = HttpClient.newBuilder().build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        ACCESS_TOKEN = response.body();

        System.out.println(response.body());
        System.out.println("---SUCCESS---");
    }


    public static void main(String[] args) {
        if(args.length > 1 && args[0].equals("-access")) {
            SERVER_PATH = args[1];
        }
        Scanner scanner = new Scanner(System.in);


        boolean exit = true;
        boolean access = false;

        while (exit) {

            String message = scanner.nextLine().toLowerCase();
            String[] m = null;

            if (message.startsWith("playlists")) {
                m = message.split(" ");
                message = m[0];
            }

            switch (message) {

                case "new": {
                    if (access) {
                        System.out.println("---NEW RELEASES---");
                    } else {
                        System.out.println("Please, provide access for application.");
                    }
                    continue;

                }
                case "auth": {
                    try {
                        getAccessCode();
                        getAccessToken();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    access = true;
                    break;

                }
                case "featured": {

                    if (access) {
                        System.out.println("---FEATURED---");
                    } else {
                        System.out.println("Please, provide access for application.");
                    }

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
