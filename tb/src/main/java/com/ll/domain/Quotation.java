package com.ll.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public class Quotation {
    @Getter
    public int id;
    @Getter
    @Setter
    public String content;
    @Getter
    @Setter
    public String author;
}

