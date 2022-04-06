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

    //setters for a Person object
    public void setInGroup(boolean inGroup) {
        this.isInGroup = inGroup;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGuard(boolean guard) {
        isGuard = guard;
    }

    //Getter to return the name of a person.
    public String getName() {
        return this.name;
    }

    //boolean to check if a person is in a group
    public boolean isInGroup() {
        return this.isInGroup;
    }

    //boolean to check if the person is a guard.
    public boolean isGuard() {
        return this.isGuard;
    }

    //toString method for a Person object
    @Override
    public String toString() {
        return '\n' + this.name;
    }
}
