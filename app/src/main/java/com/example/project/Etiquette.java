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
}
