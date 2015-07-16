import com.google.inject.Injector;
import org.pack.SparkUrl;
import org.reflections.Reflections;
import org.reflections.scanners.ResourcesScanner;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.scanners.TypeAnnotationsScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.util.FilterBuilder;
import org.springframework.context.ApplicationContext;
import spark.Route;
import spark.Spark;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class SparkWebFramekwork {
    public static void startWithRoutesAt(String pack) throws ClassNotFoundException, NoSuchMethodException, IOException, IllegalAccessException, InstantiationException {
        Set<Class<?>> typesAnnotatedWith = getClasses(pack);
        for(Class<?> aClass : typesAnnotatedWith)
        {
            Annotation[] annotations = aClass.getDeclaredAnnotations();

            for(Annotation annotation : annotations)
            {
                System.out.println(annotation);
            }

            SparkUrl annotation = aClass.getAnnotation(SparkUrl.class);

            String method = annotation.method();

            if(method.equalsIgnoreCase("GET"))
            {
                Spark.get(annotation.path(),(Route)aClass.newInstance() );
            }
        }
    }

    private static Set<Class<?>> getClasses(String pack) {
        List<ClassLoader> classLoadersList = new LinkedList<ClassLoader>();
        classLoadersList.add(ClasspathHelper.contextClassLoader());
        classLoadersList.add(ClasspathHelper.staticClassLoader());

        Reflections reflections = new Reflections(new ConfigurationBuilder()
                .setScanners(new SubTypesScanner(false /* don't exclude Object.class */), new TypeAnnotationsScanner(), new ResourcesScanner())
                .setUrls(ClasspathHelper.forClassLoader(classLoadersList.toArray(new ClassLoader[0])))
                .filterInputsBy(new FilterBuilder().include(FilterBuilder.prefix(pack))));
        return reflections.getTypesAnnotatedWith(SparkUrl.class);
    }

    public static void startWithRoutesAt(String pack, ApplicationContext applicationContext) {

        Set<Class<?>> classes = getClasses(pack);

        for(Class<?> aClass : classes)
        {
            Annotation[] annotations = aClass.getDeclaredAnnotations();

            for(Annotation annotation : annotations)
            {
                System.out.println(annotation);
            }

            SparkUrl annotation = aClass.getAnnotation(SparkUrl.class);

            String method = annotation.method();

            if(method.equalsIgnoreCase("GET"))
            {
                Spark.get(annotation.path(), (Route) applicationContext.getBeansOfType(aClass));
            }
        }
    }

    public static void startWithRoutesAt(String pack, Set<Route> routes) {

        Set<Class<?>> classes = getClasses(pack);

        for(Class<?> aClass : classes)
        {
            Annotation[] annotations = aClass.getDeclaredAnnotations();

            for(Annotation annotation : annotations)
            {
                System.out.println(annotation);
            }

            SparkUrl annotation = aClass.getAnnotation(SparkUrl.class);

            String method = annotation.method();

            if(method.equalsIgnoreCase("GET"))
            {
                for(Route route : routes)
                {
                    if(route.getClass().equals(aClass))
                    {
                        Spark.get(annotation.path(), route);
                    }
                }
            }
            else if(method.equalsIgnoreCase("POST"))
            {
                for(Route route : routes) {
                    if (route.getClass().equals(aClass)) {
                        Spark.post(annotation.path(), route);
                    }
                }
            }
            else if(method.equalsIgnoreCase("PUT"))
            {
                for(Route route : routes) {
                    if (route.getClass().equals(aClass)) {
                        Spark.put(annotation.path(), route);
                    }
                }
            }
            else if(method.equalsIgnoreCase("DELETE"))
            {
                for(Route route : routes) {
                    if (route.getClass().equals(aClass)) {
                        Spark.delete(annotation.path(),route);
                    }
                }
            }
        }
    }

    public static void startWithRoutesAt(String pack, Injector injector) {
        Set<Class<?>> classes = getClasses(pack);

        for(Class<?> aClass : classes)
        {
            Annotation[] annotations = aClass.getDeclaredAnnotations();

            for(Annotation annotation : annotations)
            {
                System.out.println(annotation);
            }

            SparkUrl annotation = aClass.getAnnotation(SparkUrl.class);

            String method = annotation.method();

            if(method.equalsIgnoreCase("GET"))
            {
              Spark.get(annotation.path(),(Route)injector.getInstance(aClass));
            }
            else if(method.equalsIgnoreCase("POST"))
            {
                Spark.post(annotation.path(), (Route) injector.getInstance(aClass));
            }
            else if(method.equalsIgnoreCase("PUT"))
            {
                Spark.put(annotation.path(), (Route) injector.getInstance(aClass));
            }
            else if(method.equalsIgnoreCase("DELETE"))
            {
                Spark.delete(annotation.path(), (Route) injector.getInstance(aClass));
            }
        }
    }
}
