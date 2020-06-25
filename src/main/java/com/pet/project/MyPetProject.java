package com.pet.project;

/**
 * Created by Tushar on 6/25/20.
 */

import com.DI.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

public class MyPetProject {

    public enum ePostType {TWITTER, REDDIT };

    DependencyInjector di;

    public void initBeans() {
        try {
            di = new DependencyInjector("config.properties");
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
    }

    public Post announce(ePostType eType) {
        Post post = null;
        if (eType == ePostType.TWITTER) {
            post = (TwitterPost) di.getBean("twitter");
        } else {
            post = (RedditPost) di.getBean("reddit");
        }

        return post;
    }


}
