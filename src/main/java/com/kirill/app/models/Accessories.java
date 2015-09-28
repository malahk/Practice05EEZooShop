package com.kirill.app.models;

/**
 * Created by Admin
 *
 * @author Admin
 * @since 24.09.2015
 */
public class Accessories {

    private int id;
    private String accsName;

    public String getAccsName() {
        return accsName;
    }

    public void setAccsName(String accsName) {
        this.accsName = accsName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Accessories)) return false;

        Accessories that = (Accessories) o;

        if (getId() != that.getId()) return false;
        return getAccsName().equals(that.getAccsName());

    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + getAccsName().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Accessories{" +
                "accsName='" + accsName + '\'' +
                ", id=" + id +
                '}';
    }
}
