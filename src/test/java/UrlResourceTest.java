import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * @author gaoxin.wei
 */
public class UrlResourceTest {
    public static void main(String[] args) throws IOException {
        UrlResourceTest resourceTest = new UrlResourceTest();
        URL url = resourceTest.getClass().getClassLoader().getResource("webroot" + "/index.html");
        URLConnection urlConnection = url.openConnection();
        urlConnection.connect();
        InputStream inputStream = urlConnection.getInputStream();

        int BUFFER_SIZE = 2048;
        byte[] bytes = new byte[BUFFER_SIZE];
        int ch = inputStream.read(bytes, 0, BUFFER_SIZE);
        while (ch != -1) {
            ch = inputStream.read(bytes, 0, BUFFER_SIZE);
        }
    }
}
