package emelyanov.partslist.model;

import javax.persistence.*;

/**
 * Модель - абстрагирует сущность детали для компьютера.
 */
@Entity
@Table(name = "part")
public class Part {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "essential")
    boolean essential;

    @Column(name = "quantity")
    private int quantity;

    public Part() {
    }

    public Part(int id, String name, boolean essential, int quantity) {
        this.id = id;
        this.name = name;
        this.essential = essential;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isEssential() {
        return essential;
    }

    public void setEssential(boolean essential) {
        this.essential = essential;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Part{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", essential=" + essential +
                ", quantity=" + quantity +
                '}';
    }
}
