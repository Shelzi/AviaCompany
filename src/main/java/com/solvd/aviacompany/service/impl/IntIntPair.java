package com.solvd.aviacompany.service.impl;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IntIntPair {
    private int a;
    private int b;

    @Override
    public String toString() {
        return "[" + a + ", " + b + "]";
    }

    public void sumTwoPairs(IntIntPair o){
        a += o.a;
        b += o.b;
    }
}
