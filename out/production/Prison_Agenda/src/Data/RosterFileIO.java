package Data;

import java.io.*;
import java.util.ArrayList;

public class RosterFileIO {

    public void saveDataAsFile(File path, Roster roster) {
        try(ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(path.getAbsolutePath()))) {
            output.writeObject(roster);
        }catch(Exception e){
            e.printStackTrace();
        }

    }

    public Roster readData(File file) {
        try (ObjectInputStream input = new ObjectInputStream(new FileInputStream(file.getAbsolutePath()))) {
            return (Roster) (input.readObject());
        } catch (IOException | ClassNotFoundException  e) {
            e.printStackTrace();
            return null;
        }
    }

    public void addPeopleToList(File file, ArrayList<Person> people){

    }
}
