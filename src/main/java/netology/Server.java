package netology;
import java.util.concurrent.Executors;

public class Server {
    public static void startServer() {
        int poolsNumber = 64;
        final MyHttpServer myHttpServer = new MyHttpServer();
        Executors.newFixedThreadPool(poolsNumber).execute(myHttpServer);
    }
}