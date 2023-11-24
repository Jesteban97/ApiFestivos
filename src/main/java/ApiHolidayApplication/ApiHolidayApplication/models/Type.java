package ApiHolidayApplication.ApiHolidayApplication.models;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tipo")
public class Type {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "secuencia_tipo")
    @GenericGenerator(name = "secuencia_tipo", strategy = "increment")
    @Column(name = "id")
    private int id;

    @Column(name = "tipo", length = 100, unique = true)
    private String type;

    public Type(int id, String type) {
        this.id = id;
        this.type = type;
    }

    public Type() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}