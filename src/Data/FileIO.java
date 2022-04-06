package Data;

import javafx.scene.layout.HBox;

import java.util.*;
import java.io.*;

public class FileIO {
    /**
     * method to save the data in roster as a file using Objectstream.
     * @param path
     * @param roster
     */
    public void saveDataAsFile(File path, Roster roster) {
        try (ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(path.getAbsolutePath()))) {
            output.writeObject(roster);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * method to read the data in a file given as a parameter using Objectstream.
     * @param file
     * @return Roster object
     */
    public Roster readData(File file) {
        try (ObjectInputStream input = new ObjectInputStream(new FileInputStream(file.getAbsolutePath()))) {
            return (Roster) (input.readObject());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * method to set up a namelist from a file, reads the whole file, adds all names in the file to a Arraylist and returns it.
     * @param fileName
     * @return ArrayList of names
     */
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

    /**
     * method to save a person database in a file using a printwriter.
     * @param fileName
     * @param database
     */
    public void savePersonDatabase(String fileName, HashMap<Person, Boolean> database){
        Set<Person> keys = database.keySet();
        ArrayList<Person> people = new ArrayList<>(keys);
        keys.clear();

        try(PrintWriter printer = new PrintWriter(fileName)){
            for (int i = 0; i < people.size(); i++) {
                printer.println(people.get(i));

            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * method to test saving a person database in a file using printwriter.
     * @param fileName
     * @param database
     */
    public void savePersonDatabaseTester(String fileName, HashMap<Person, Boolean> database){
        Set<Person> keys = database.keySet();
        ArrayList<Person> people = new ArrayList<>(keys);

        Collection<Boolean> values = database.values();
        ArrayList<Boolean> trueORfalse = new ArrayList<>(values);
        keys.clear();

        try(PrintWriter printer = new PrintWriter(fileName)){
            for (int i = 0; i < people.size(); i++) {
                printer.print(people.get(i));
                printer.print(" ");
                printer.print(trueORfalse.get(i));
                printer.println("");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }


}
