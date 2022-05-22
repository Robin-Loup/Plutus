package com.example.project;

import java.util.ArrayList;
import java.util.Objects;

public class Etiquette {
    public enum Tag {
        Food,
        Transport,
        Clothes,
        Other
    }
    Tag tag;
    ArrayList<Tag> list= new ArrayList<>();
    public Etiquette(Tag tag) {
        Objects.requireNonNull(tag);
        this.tag=tag;
    }

    public static Tag getTag(String name) {
        for(Tag t: Tag.values()){
            if(t.name().compareToIgnoreCase(name)==0) return t;
        }
        return null;
    }
}
