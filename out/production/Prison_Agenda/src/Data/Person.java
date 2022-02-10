package Data;

public class Person {

    private boolean isGuard = false;
    private boolean isPrisoner = false;
    private String name;
//    private String lastName;
    private boolean hasGun = false;
    private boolean hasShank = false;

    public Person(String name){
		this.name = name;
    }

    public boolean isGuard() {
        return isGuard;
    }

    public void setGuard(boolean guard) {
        isGuard = guard;
    }

    public boolean isPrisoner() {
        return isPrisoner;
    }

    public void setPrisoner(boolean prisoner) {
        isPrisoner = prisoner;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    public String getLastName() {
//        return lastName;
//    }

//    public void setLastName(String lastName) {
//        this.lastName = lastName;
//    }

    public Boolean getHasGun() {
        return hasGun;
    }

    public void setHasGun(Boolean hasGun) {
        this.hasGun = hasGun;
    }

	@Override
	public String toString() {
		return "Person{" +
				"isGuard=" + isGuard +
				", isPrisoner=" + isPrisoner +
				", name='" + name + '\'' +
				", hasGun=" + hasGun +
				", hasShank=" + hasShank +
				'}';
	}
}
