package com.example.demo.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@EqualsAndHashCode
@Builder
public class Word {
    private Integer id;
    private String word;
    private String text;

    @Override
    public String toString() {
        return text;
    }
}
