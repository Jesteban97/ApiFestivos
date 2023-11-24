package ApiHolidayApplication.ApiHolidayApplication.models;

import java.util.Date;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "festivo")
public class Holiday {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "secuencia_festivo")
    @GenericGenerator(name = "secuencia_festivo", strategy = "increment")
    @Column(name = "id")
    private int id;

    @Column(name = "dia")
    private int day;

    @Column(name = "mes")
    private int month;

    @Column(name = "nombre", length = 100)
    private String name;

    @Column(name = "diaspascua")
    private int easterDay;

    @ManyToOne
    @JoinColumn(name = "idtipo", referencedColumnName = "id")
    private Type type;

    private Date date;

    public Holiday() {
    }

    public Holiday(int id, int day, int month, String name, int easterDay, Type type) {
        this.id = id;
        this.day = day;
        this.month = month;
        this.name = name;
        this.easterDay = easterDay;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getEasterDay() {
        return easterDay;
    }

    public void setEasterDay(int easterDay) {
        this.easterDay = easterDay;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}