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

    public String getName() {
        return this.name;
    }

    public boolean isGuard() {
        return this.isGuard;
    }

    public boolean isInGroup() {
        return isInGroup;
    }

    public void setInGroup(boolean inGroup) {
        isInGroup = inGroup;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
