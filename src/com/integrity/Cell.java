package com.integrity;

/**
 * Created by Michael Rudyy on 21-Sep-17.
 */
public class Cell<T,F> {
    T value1;
    F value2;

    Cell(){}

    Cell(T value1 , F value2){
        this.value1 = value1;
        this.value2 = value2;
    }

    public T getValue1() {
        return value1;
    }

    public void setValue1(T value1) {
        this.value1 = value1;
    }

    public F getValue2() {
        return value2;
    }

    public void setValue2(F value2) {
        this.value2 = value2;
    }
}
