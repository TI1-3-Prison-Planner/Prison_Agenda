package Data;

import java.io.*;
import java.util.ArrayList;

public class RosterFileIO {

    public void saveDataAsFile(File path, Roster roster) {
        try(ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(path.getAbsolutePath()))) {
            output.writeObject(roster.getActivities());
        }catch(Exception e){
            e.printStackTrace();
        }

    }

    public static ArrayList<Roster> readData(File file) {
        try (ObjectInputStream input = new ObjectInputStream(new FileInputStream(file.getAbsolutePath()))) {
            return (ArrayList<Roster>) (input.readObject());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void addPeopleToList(File file, ArrayList<Person> people){

    }
}
