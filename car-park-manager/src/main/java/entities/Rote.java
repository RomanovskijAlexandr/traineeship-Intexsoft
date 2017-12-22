package entities;

import org.springframework.stereotype.Component;

import javax.persistence.*;

@Entity
@Table(name = "ROTE")
@Component
public class Rote {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "NUMBER")
    private Integer number;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{").append("Name: ").append(name).append(" | Number: ").append(number).append("}");
        return stringBuilder.toString();
    }
}
