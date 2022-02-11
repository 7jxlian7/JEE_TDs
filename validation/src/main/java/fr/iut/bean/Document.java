package fr.iut.bean;

import fr.iut.validation.Name;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class Document extends DueDocument{
    @NotNull
    @Name
    private String title;
    @NotNull
    private String content;
    @NotNull
    @fr.iut.validation.Date
    private Date creationDate;
    @fr.iut.validation.Date
    private Date lastModification;
    @NotNull
    protected Person creator;
    protected Person lastModifier;

    public Document(Date dueDate, String title, String content, Date creationDate, Date lastModification, Person creator, Person lastModifier) {
        super(dueDate);
        this.title = title;
        this.content = content;
        this.creationDate = creationDate;
        this.lastModification = lastModification;
        this.creator = creator;
        this.lastModifier = lastModifier;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getLastModification() {
        return lastModification;
    }

    public void setLastModification(Date lastModification) {
        this.lastModification = lastModification;
    }

    public Person getCreator() {
        return creator;
    }

    public void setCreator(Person creator) {
        this.creator = creator;
    }

    public Person getLastModifier() {
        return lastModifier;
    }

    public void setLastModifier(Person lastModifier) {
        this.lastModifier = lastModifier;
    }
}
