package uk.co.whitehouse.max.threeletteracronymsv2.data;

import androidx.annotation.NonNull;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Iterator;

public class AcronymList extends ArrayList<Acronym> {

    @NonNull
    @NotNull
    @Override
    public String toString() {
        Iterator<Acronym> it = iterator();
        if (! it.hasNext()) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        for (;;) {
            sb.append(it.next());
            if (! it.hasNext())
                return sb.append(']').toString();
            sb.append(',').append(' ').append('\n');
        }
    }
}
