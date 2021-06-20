package uk.co.whitehouse.max.threeletteracronymsv2.data;

import androidx.annotation.NonNull;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Acronym implements Serializable, Comparable<Acronym> {

    private final String acronym;
    private ArrayList<String> comments;
    private E_AcronymTypes type;
    private Date dateAdded;

    public Acronym(String acronym) {
        this.acronym = acronym;
    }

    public String getAcronym() {
        return acronym;
    }

    public ArrayList<String> getComments() {
        return comments;
    }

    public void setComments(ArrayList<String> comments) {
        this.comments = comments;
    }

    public void addComment(String comment) {
        comments.add(comment);
    }

    public void putComment(int idx, String comment) {
        comments.add(idx, comment);
    }

    public E_AcronymTypes getType() {
        return type;
    }

    public void setType(E_AcronymTypes type) {
        this.type = type;
    }

    public Date getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    }

    @Override
    public int compareTo(Acronym in) {
        return acronym.compareTo(in.acronym);
    }

    @NonNull
    @NotNull
    @Override
    public String toString() {
        return acronym;
    }
}
