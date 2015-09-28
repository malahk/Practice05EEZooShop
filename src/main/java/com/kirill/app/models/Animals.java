package com.kirill.app.models;

/**
 * Created by Admin
 *
 * @author Admin
 * @since 24.09.2015
 */
public class Animals {

    private int id;
    private String name;
    private int price;
    private String type;
    private Food food;
    private Accessories accs;

    public Accessories getAccs() {
        return accs;
    }

    public void setAccs(Accessories accs) {
        this.accs = accs;
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Animals)) return false;

        Animals animals = (Animals) o;

        if (getId() != animals.getId()) return false;
        if (getPrice() != animals.getPrice()) return false;
        if (!getName().equals(animals.getName())) return false;
        if (!getType().equals(animals.getType())) return false;
        if (!getFood().equals(animals.getFood())) return false;
        return getAccs().equals(animals.getAccs());

    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + getName().hashCode();
        result = 31 * result + getPrice();
        result = 31 * result + getType().hashCode();
        result = 31 * result + getFood().hashCode();
        result = 31 * result + getAccs().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Animals{" +
                "accs=" + accs +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", type='" + type + '\'' +
                ", food=" + food +
                '}';
    }
}
