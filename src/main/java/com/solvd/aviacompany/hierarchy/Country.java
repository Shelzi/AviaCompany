package com.solvd.aviacompany.hierarchy;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Country {
    private int id;
    private String name;

    @Override
    public String toString() {
        return "(" +
                "id=" + id +
                ", name='" + name + '\'' +
                ')';
    }
}
