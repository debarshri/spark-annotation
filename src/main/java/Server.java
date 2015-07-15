import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

public class Server {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, IOException, InstantiationException, IllegalAccessException {

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext();

        SparkWebFramekwork.startWithRoutesAt("org.pack");
        SparkWebFramekwork.startWithRoutesAt("org.pack",applicationContext);
    }
}
