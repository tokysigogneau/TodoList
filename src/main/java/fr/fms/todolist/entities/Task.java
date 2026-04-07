package fr.fms.todolist.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Task implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 5, max = 50)
    private String name;

    @NotNull
    private Date date;

    @NotNull
    @Size(min=5,max=100)
    private String description;

    @ManyToOne
    private Category category;

    @ManyToOne
    private Progression progression;

    public Task(String name, Date date, String description, Category category, Progression progression){
        this.name = name;
        this.date = date;
        this.description = description;
        this.category = category;
        this.progression = progression;
    }

    public String toString(){
        return " Task {name : "+ name + " date : " + date + " description" + description + "}";
    }

    //region getter and setter
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Progression getProgression() {
        return progression;
    }

    public void setProgression(Progression progression) {
        this.progression = progression;
    }

    //endregion
}
