import com.google.common.collect.Sets;
import com.google.inject.Guice;
import com.google.inject.Injector;
import org.pack.SparkAnonTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.pack.AppInjector;
import spark.Route;
import spark.Spark;

import java.io.IOException;
import java.util.Set;

public class Server {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, IOException, InstantiationException, IllegalAccessException {

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext();

        Set<Route> test = Sets.newHashSet();

        test.add(new SparkAnonTest());

        Injector injector = Guice.createInjector(new AppInjector());

        Spark.setPort(80);
        SparkWebFramekwork.startWithRoutesAt("org.pack");
        //SparkWebFramekwork.startWithRoutesAt("org.pack",applicationContext);
        //SparkWebFramekwork.startWithRoutesAt("org.pack",test);
        //SparkWebFramekwork.startWithRoutesAt("org.pack",injector);
    }
}
