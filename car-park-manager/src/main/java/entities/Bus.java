package entities;

import org.springframework.stereotype.Component;

import javax.persistence.*;

@Entity
@Table(name = "BUS")
@Component
public class Bus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "NUMBER")
    private Integer number;
    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "ROTE_ID")
    private Rote rote;

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

    public Rote getRote() {
        return rote;
    }

    public void setRote(Rote rote) {
         this.rote = rote;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{").append("ID: ").append(id).append(" | Number: ").append(number).append("}");
        return stringBuilder.toString();
    }
}
