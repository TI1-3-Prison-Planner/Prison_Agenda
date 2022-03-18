package Data;

import java.io.Serializable;

public class Person implements Serializable {
    private String name;
    private boolean isGuard;
    private boolean isInGroup;

    public Person(String name, boolean isGuard) {
        this.name = name;
        this.isGuard = isGuard;
        this.isInGroup = false;
    }

    public boolean isInGroup() {
        return this.isInGroup;
    }

    //boolean to check if the person is a guard.
    public boolean isGuard() {
        return this.isGuard;
    }



    public void setInGroup(boolean inGroup) {
        this.isInGroup = inGroup;
    }

    //Getter to return the name of a person.
    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        return '\n' + this.name;
    }
}
