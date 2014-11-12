package pl.krever.jimcy.j_api;


import scala.collection.JavaConversions;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Enumeration;
import java.util.Map;


//TODO change to @Delegate from lombok
public class InMemoryClassLoader extends ClassLoader {

    private final pl.krever.jimcy.InMemoryClassLoader classLoader;

    InMemoryClassLoader(pl.krever.jimcy.InMemoryClassLoader classLoader){
        this.classLoader = classLoader;
    }

    public Map<String, Class<?>> getAllClasses() {
        return JavaConversions.mapAsJavaMap(classLoader.allClasses());
    }

    //forwarding public methods
    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        return classLoader.loadClass(name);
    }

    @Override
    public URL getResource(String name) {
        return classLoader.getResource(name);
    }

    @Override
    public Enumeration<URL> getResources(String name) throws IOException {
        return classLoader.getResources(name);
    }

    @Override
    public InputStream getResourceAsStream(String name) {
        return classLoader.getResourceAsStream(name);
    }

    @Override
    public void setPackageAssertionStatus(String packageName, boolean enabled) {
        classLoader.setPackageAssertionStatus(packageName, enabled);
    }

    @Override
    public void setDefaultAssertionStatus(boolean enabled) {
        classLoader.setDefaultAssertionStatus(enabled);
    }

    @Override
    public void setClassAssertionStatus(String className, boolean enabled) {
        classLoader.setClassAssertionStatus(className, enabled);
    }

    @Override
    public void clearAssertionStatus() {
        classLoader.clearAssertionStatus();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof InMemoryClassLoader)) return false;

        InMemoryClassLoader that = (InMemoryClassLoader) o;

        if (classLoader != null ? !classLoader.equals(that.classLoader) : that.classLoader != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return classLoader != null ? classLoader.hashCode() : 0;
    }
}
