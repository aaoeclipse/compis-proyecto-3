package dataStructures;

import reader.DefaultValues;

import java.util.Objects;

public class Operations {
    int[] value;
    boolean[] leftOperation;

    public Operations() {
        value = new int[] {DefaultValues.CONCAT, DefaultValues.STAR,
                DefaultValues.ATLEASTONE, DefaultValues.OR, DefaultValues.ONEORLESS};
        leftOperation = new boolean[] {false, true, true, false, true};
    }

    public boolean getLeftOperation(int value){
        for (int i = 0; i < this.value.length; i++) {
            if (this.value[i] == value)
                return leftOperation[i];
        }
        return false;
    }

}
