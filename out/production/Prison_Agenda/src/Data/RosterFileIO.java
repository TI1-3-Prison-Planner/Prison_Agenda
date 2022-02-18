package Data;

import javafx.scene.layout.HBox;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class RosterFileIO {
    //method to save the data in roster as a file.
    public void saveDataAsFile(File path, Roster roster) {
        try (ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(path.getAbsolutePath()))) {
            output.writeObject(roster);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //method to read the data in a file given as a parameter.
    public Roster readData(File file) {
        try (ObjectInputStream input = new ObjectInputStream(new FileInputStream(file.getAbsolutePath()))) {
            return (Roster) (input.readObject());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<String> setUpNamelist(String fileName) {
        File file = new File(fileName);
        ArrayList<String> names = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                String fullName = scanner.nextLine();
                names.add(fullName);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return names;
    }
}
