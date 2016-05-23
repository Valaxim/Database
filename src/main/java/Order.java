import javax.persistence.*;
import java.util.Formatter;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "order_user")
    private User user;


    @ManyToOne
    @JoinColumn(name = "order_cd")
    private CD cd;

    public Order() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public CD getCd() {
        return cd;
    }

    public void setCd(CD cd) {
        this.cd = cd;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Formatter formatter = new Formatter(sb);
        formatter.format("%1$-4d  \t User: %2$-30s  \t Tytuł: %3$-30s", id, user, cd);
        return '\n' + "ORDER" + formatter;
    }

}

