package info.kszewczyk.seleniumtest;

import org.junit.Test;
import org.reflections.Reflections;
import org.reflections.scanners.MethodAnnotationsScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class TestClasses {

    private Optional<String> location;

    public TestClasses(String location) {
        this.location = Optional.ofNullable(location);
    }

    public Class<?>[] load() {
        var reflection = new Reflections(new ConfigurationBuilder()
                .setUrls(ClasspathHelper.forPackage(location.orElseThrow(() -> new IllegalArgumentException("No package location"))))
                .setScanners(new MethodAnnotationsScanner())
        );
        Set<Class<?>> classes = new HashSet<>();
        Set<Method> methods = reflection.getMethodsAnnotatedWith(Test.class);
        methods.stream().forEach(method -> classes.add(method.getDeclaringClass()));
        return classes.toArray(new Class<?>[classes.size()]);
    }
}
