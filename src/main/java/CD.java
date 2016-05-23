import javax.persistence.*;
import java.util.Formatter;

@Entity
@Table(name = "cd")
public class CD {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "author")
    private String author;

    @Column(name = "title")
    private String title;

    @Column(name = "status")
    private String status;

    @Column(name = "price")
    private Integer price;

    public CD() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Formatter formatter = new Formatter(sb);
        formatter.format("%1$-4d  \t Autor: %2$-30s  \t Tytuł: %3$-30s  \t Status: %4$-12s", id, author, title,status);
        return '\n' + "CD" + formatter;

       /* return '\n' + "CD" + '\t' + "id=" + id + '\t' +
                "author=" + author + '\t' +
                "title=" + title + '\t' +
                "status=" + status + '\t' ;

                */
    }
}

