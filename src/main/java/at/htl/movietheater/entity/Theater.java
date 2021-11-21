package at.htl.movietheater.entity;

import javax.persistence.*;

@Entity(name = "MT_THEATER")
public class Theater {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TH_ID")
    private Long id;

    @Column(name = "TH_NAME", unique = true)
    private String name;

    @Column(name = "TH_CAPACITY")
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
