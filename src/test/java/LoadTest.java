import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class LoadTest extends Thread {
    //List url, do with streams, check timerespond
    private long begin;
    private List<String> listForTesting = new ArrayList();

    @Before
    public void initialization() {
        listForTesting.add("http://youtube.com");
        listForTesting.add("http://google.com");
        listForTesting.add("http://yahoo.com");
        listForTesting.add("http://vk.com");
        listForTesting.add("http://yandex.com");
    }

    public StringBuilder handleWebPage(String url) throws IOException {
        URL targetUrl = new URL(url);
        begin = System.currentTimeMillis();
        HttpURLConnection connection = (HttpURLConnection) targetUrl.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("User-Agent", "Chrome/41.0.2228.0");
        return getWebPage(connection);
    }

    private StringBuilder getWebPage(HttpURLConnection connection) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder result = new StringBuilder();
        String nextString;
        while ((nextString = reader.readLine()) != null) {
            result.append(nextString);
        }
        long end = System.currentTimeMillis();
        System.out.println(end - begin);


        return result;
    }

    @Test
    public void doTet() {
        for (String s : listForTesting) {
            try {
                handleWebPage(s);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }


}
