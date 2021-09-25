package org.echocat.kata.java.part1.models;

import lombok.Getter;

import java.util.List;



abstract public class BaseProduct implements PrettyPrintable {
    @Getter
    private String title;
    @Getter
    private String isbn;
    @Getter
    private List<String> authorEmails;
}