package netology;

public class MyHttpServer implements Runnable {

    @Override
    public void run() {

        String name = "id";

        Request newRequest = new Request();
        newRequest.getQueryParam(name,newRequest.getQueryParams());

    }
}