package step_1;

import com.noodles.prompt.TinyTomcat;

/**
 * @author gaoxin.wei
 */
public class TinyTomcatTest {
    public static void main(String[] args) {
        TinyTomcat tinyTomcat = new TinyTomcat();
        tinyTomcat.await();
    }
}
