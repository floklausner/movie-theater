package at.htl.movietheater.entity;

import javax.persistence.*;

@NamedQueries({
        @NamedQuery(
                name = "Theater.findByName",
                query = "select t from MT_THEATER t where t.name like :name"
        )
})
public class Theater {
    private Long id;

    private String name;

    private int capacity;

    public Theater() {
    }

    public Theater(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public String toString() {
        return String.format("%s (%s seats)", name, capacity);
    }
}
