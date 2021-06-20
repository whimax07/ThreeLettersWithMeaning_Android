package uk.co.whitehouse.max.threeletteracronymsv2.data;

import android.content.Context;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SaveAcronyms {

    private static final String fileName = "SavedAcronyms.jobj";

    public static void save(Context context, AcronymList acronyms) {
        File file = new File(context.getFilesDir(), fileName);
        try (FileOutputStream fos = new FileOutputStream(file)) {
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(acronyms);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static AcronymList load(Context context) {
        File file = new File(context.getFilesDir(), fileName);
        AcronymList acronyms = new AcronymList();
        try (FileInputStream fin = new FileInputStream(file)) {
            ObjectInputStream ois = new ObjectInputStream(fin);
            Object objIn = ois.readObject();
            if (objIn instanceof AcronymList) {
                acronyms = (AcronymList) objIn;
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return acronyms;
    }
}
