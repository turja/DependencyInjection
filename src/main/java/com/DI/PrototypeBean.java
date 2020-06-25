package com.DI;

import java.io.*;

/**
 * Created by Tushar on 6/25/20.
 */
public class PrototypeBean implements Cloneable {

    public static Object clone(Object original) {

        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(bos);
            out.writeObject(original);

            ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
            ObjectInputStream in = new ObjectInputStream(bis);
            return in.readObject();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }
}
