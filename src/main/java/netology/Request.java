package netology;

import org.apache.commons.codec.Charsets;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.URI;
import java.util.List;

public class Request {


    List<NameValuePair> getQueryParams() {

        int portNumber = 9999;


        try (final var serverSocket = new ServerSocket(portNumber)) {
            while (true) {
                try (
                        final var socket = serverSocket.accept();
                        final var in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        final var out = new BufferedOutputStream(socket.getOutputStream());
                ) {
                    final var requestLine = in.readLine();
                    final var parts = requestLine.split(" ");

                    System.out.println(parts[1] + "  " + parts[0]);

                    final var path = parts[1];
                    URI uri = URI.create(path);


                    String query = uri.getQuery();
                    List<NameValuePair> params = URLEncodedUtils.parse(query, Charsets.UTF_8);
                    for (NameValuePair param : params) {
                        String name = param.getName();
                        System.out.println(name + "  " + param);
                    }
                    return params;
                }


            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    List<NameValuePair> getQueryParam(String name, List<NameValuePair> params) {
        for (NameValuePair param : params) {
            String nameOfParam = param.getName();
            if (name.equals(nameOfParam)) {
                System.out.println(name + "  " + param);
                return (List<NameValuePair>) param;
            } else {
                System.out.println("Не найдено параметра с таким именем!");
            }
        }


        return null;
    }
}
