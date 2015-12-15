package com.epam.sukhoverka.dao;

import com.epam.sukhoverka.model.Person;

import java.io.*;

public class FileDataSource implements DataSource {

    {
        pathToFile = getClass().getClassLoader().getResource("personDb.txt").getFile();
    }

    private String pathToFile;

    public void writePerson(Person person) {

        try(FileOutputStream fos = new FileOutputStream(pathToFile);
            BufferedOutputStream bos = new BufferedOutputStream(fos);
        ObjectOutputStream oos = new ObjectOutputStream(bos);){
            oos.writeObject(person);
        } catch (IOException e) {
            // todo log error
            e.printStackTrace();
        }

    }

    public Person readPerson(String name) {
        Person person = null;

        try(FileInputStream fin = new FileInputStream(pathToFile);
            BufferedInputStream bis = new BufferedInputStream(fin);
            ObjectInputStream ois = new ObjectInputStream(bis);) {

            person = (Person) ois.readObject();
            return person;

        } catch (Exception e) {
            // todo write to log
            e.printStackTrace();
        }
        return person;
    }
}
