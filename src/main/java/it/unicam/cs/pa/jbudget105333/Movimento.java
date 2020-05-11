package it.unicam.cs.pa.jbudget105333;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Objects;

public class Movimento<T extends Tag> implements Comparable<Movimento> {

    private double value = 0.0;
    private ArrayList<T> tags = new ArrayList<T>();
    private LocalDateTime localDateTime = null;
    private int count = 0;

    public Movimento(double value, T tag, LocalDateTime localDateTime) {
        this.value = value;
        this.tags.add(tag);
        this.localDateTime = localDateTime;
        this.count = 1;
    }

    public Movimento(double value, ArrayList<T> tags, LocalDateTime localDateTime) throws IllegalArgumentException {
        if(check(tags))
            throw new IllegalArgumentException();
        this.value = value;
        this.tags.addAll(tags);
        this.localDateTime = localDateTime;
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
                Objects.equals(localDateTime, movimento.localDateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, tags, localDateTime);
    }

    public int compareTo(Movimento m) {
        int comp = this.localDateTime.compareTo(m.getLocalDateTime());
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

    public LocalDateTime getLocalDateTime() {
        return this.localDateTime;
    }
}
