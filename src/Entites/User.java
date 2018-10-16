package Entites;

import java.util.Objects;

public class User extends Base {
    private String firstname;
    private String secondname;
    private int age;

    public User(int id, String firstname, String secondname, int age){
        super(id);

        this.firstname = firstname;
        this.secondname = secondname;
        this.age = age;
    }

    public String getFirstname(){
        return firstname;
    }
    public String getSecondname(){
        return secondname;
    }
    public int getAge(){
        return age;
    }

    public void setFirstname(String firstname){
        this.firstname = firstname;
    }
    public void setSecondname(String secondname){
        this.secondname = secondname;
    }
    public void setAge(int age){
        this.age = age;
    }

    //@Contract("null -> false")
    @Override
    public boolean equals(Object object){
        if (object == null)
            return false;
        if (!(object instanceof User))
            return false;
        if (this.hashCode() != object.hashCode())
            return false;
        User person = (User) object;
        return (Integer.compare(age, person.age) == 0 && Objects.equals(firstname, person.getFirstname()) && Objects.equals(secondname, person.secondname));
    }
    @Override
    public int hashCode() {
        return Objects.hash(firstname, secondname, age);
    }

}
