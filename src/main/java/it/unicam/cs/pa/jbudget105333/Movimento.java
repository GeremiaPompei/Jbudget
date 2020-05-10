package it.unicam.cs.pa.jbudget105333;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

public class Movimento<T extends Tag> implements Comparable<Movimento> {

    private double value = 0.0;
    private ArrayList<T> tags = new ArrayList<T>();
    private LocalDate localDate = null;
    private int count = 0;

    public Movimento(double value, T tag, LocalDate localDate) {
        this.value = value;
        this.tags.add(tag);
        this.localDate = localDate;
        this.count = 1;
    }

    public Movimento(double value, ArrayList<T> tags, LocalDate localDateTime) throws IllegalArgumentException {
        if(check(tags))
            throw new IllegalArgumentException();
        this.value = value;
        this.tags.addAll(tags);
        this.localDate = localDateTime;
        this.count = 1;
    }

    private boolean check(ArrayList<? extends Tag> tags){
        if(tags.get(0) instanceof TagIn){
            for(Tag t : tags){
                if(t instanceof TagOut)
                    return true;
            }
        }else {
            for(Tag t : tags){
                if(t instanceof TagIn)
                    return true;
            }
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movimento<?> movimento = (Movimento<?>) o;
        return Double.compare(movimento.value, value) == 0 &&
                Objects.equals(tags, movimento.tags) &&
                Objects.equals(localDate, movimento.localDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, tags, localDate);
    }

    public int compareTo(Movimento m) {
        int comp = this.localDate.compareTo(m.getLocalDate());
        if(comp == 0)
            comp = (int)(this.value*100-m.getValue()*100);
        if(comp == 0)
            comp = this.tags.hashCode()-m.getTags().hashCode();
        return comp;
    }

    public double getValue() {
        return value;
    }

    public int getCount() {
        return count;
    }

    public void incrementCount() {
        this.count++;
    }

    public ArrayList<? extends Tag> getTags() {
        return tags;
    }

    public LocalDate getLocalDate() {
        return this.localDate;
    }
}
