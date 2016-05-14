package models;

import java.util.*;
import javax.persistence.*;
import javax.validation.Constraint;

import play.db.ebean.*;
import play.data.validation.*;
import play.data.format.*;

@Entity
public class Task extends Model {

    @Id
    public Long id;
    @Constraints.Required
    public String title;
    public boolean done = false;
    @Formats.DateTime(pattern="MM/dd/yy")
    public Date dueDate;
    @ManyToOne
    public User assignedTo;
    public String folder;
    @ManyToOne
    public Project project;




    public static Model.Finder<Long,Task> find = new Model.Finder(Long.class, Task.class);

    public static List<Task> findTodoInvolving(String user) {
        return find.fetch("project").where()
                .eq("done", false)
                .eq("project.members.email", user)
                .findList();
    }

    public static Task create(Task task, Long project, String folder) {
        task.project = Project.find.ref(project);
        task.folder = folder;
        task.save();
        return task;
    }

    public static List<Task> findByProject(Long project) {
        return Task.find.where()
                .eq("project.id", project)
                .findList();
    }
}