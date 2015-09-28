package com.kirill.app.models;

/**
 * Created by Admin
 *
 * @author Admin
 * @since 24.09.2015
 */
public class Food {

    private int id;
    private String itemName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Food)) return false;

        Food food = (Food) o;

        if (getId() != food.getId()) return false;
        return getItemName().equals(food.getItemName());

    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + getItemName().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Food{" +
                "id=" + id +
                ", itemName='" + itemName + '\'' +
                '}';
    }
}
