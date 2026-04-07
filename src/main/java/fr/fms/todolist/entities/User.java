package fr.fms.todolist.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Collection;

@Entity
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 5, max = 50)
    private String name;

    @OneToMany (mappedBy = "user")
    @ElementCollection(targetClass = Task.class)
    private Collection<Task> tasks;

    public User(){

    }
    public User(String name){
        this.name = name;
    }

    @Override
    public String toString() {
        return "User {name : " + name + " }";
    }

    //region Getter and setter

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //endregion
}
