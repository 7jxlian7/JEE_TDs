package fr.iut.bean;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class DueDocument {
    @NotNull
    @fr.iut.validation.Date
    private Date dueDate;

    public DueDocument(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }
}
