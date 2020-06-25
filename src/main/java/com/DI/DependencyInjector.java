package com.DI;

import com.pet.project.RedditPost;
import com.pet.project.TwitterPost;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * Created by Tushar on 6/24/20.
 */
public class DependencyInjector {

    Map<String, Object> mapBeans = new HashMap<String, Object>();
    Map<String, EScope> mapScope = new HashMap<String, EScope>();

    public DependencyInjector(String propertiesFile) throws IOException {
        loadBean(propertiesFile);
    }

    /*
    If given time, the new class creation will be done using
    reflection
    */
    public void addBean(String name, EScope scope) {
        mapScope.put(name, scope);

        if (name.equals("twitter"))
            mapBeans.put(name, new TwitterPost());
        else if (name.endsWith("reddit"))
            mapBeans.put(name, new RedditPost());
    }

    public Object getBean(String name) {
        if (!mapScope.containsKey(name))
            return null;

        EScope scope = mapScope.get(name);
        if (scope == EScope.SINGLETON)
            return getSingletonBean(name);
        else if (scope == EScope.PROTOTYPE)
            return getPrototypeBean(name);

        return null;
    }

    public void loadBean(String filename) throws IOException {
        Properties properties = new Properties();
        InputStream input = new FileInputStream(filename);
        properties.load(input);
        Enumeration enuKeys = properties.keys();
        while (enuKeys.hasMoreElements()) {
            String key = (String) enuKeys.nextElement();
            String value = properties.getProperty(key);
            System.out.println(key + ": " + value);
            addBean(key, getBeanScope(value));
        }

        input.close();
    }

    //
    private EScope getBeanScope(String name) {
        if (name.equals("singleton")) return EScope.SINGLETON;
        else if (name.equals("prototype")) return EScope.PROTOTYPE;

        return EScope.NONE;
    }

    private Object getSingletonBean(String name) {
        return mapBeans.get(name);
    }

    private Object getPrototypeBean(String name) {
        return PrototypeBean.clone(mapBeans.get(name));
    }

}
