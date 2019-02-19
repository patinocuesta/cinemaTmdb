package online.patino.cinema.model;

import org.springframework.transaction.IllegalTransactionStateException;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Entity
public class Review {



    private static final int WAITING_MODERATION = 1;
    private static final int PUBLISHED = 2;
    private static final int MUST_BE_MODIFIED = 3;
    private static final int REJECTED = 4;
    private static final int DELETED = 5;
    private static final int ABANDONED = 6;

    private static  final   Map<Integer, Integer[]> transitions;
      static {
          transitions = new HashMap<>();
          transitions.put(Review.REJECTED, new Integer[]{Review.WAITING_MODERATION});
          transitions.put(Review.DELETED, new Integer[]{Review.PUBLISHED});
          transitions.put(Review.PUBLISHED, new Integer[]{Review.WAITING_MODERATION});
          transitions.put(Review.MUST_BE_MODIFIED, new Integer[]{Review.WAITING_MODERATION});
          transitions.put(Review.ABANDONED, new Integer[]{Review.MUST_BE_MODIFIED});
          transitions.put(Review.WAITING_MODERATION, new Integer[]{Review.WAITING_MODERATION, Review.PUBLISHED, Review.MUST_BE_MODIFIED});
      }

    private int state = Review.WAITING_MODERATION;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;
    @Basic
    @Column(name = "article", nullable = true, length = -1)
    private String article;
    @Basic
    @Column(name = "datte", nullable = false)
    private Timestamp date;
    @ManyToOne
    @JoinColumn(name = "film_id")
    private Film film;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }


    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp datte) {
        this.date = datte;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Review review = (Review) o;

        if (id != review.id) return false;
        if (article != null ? !article.equals(review.article) : review.article != null) return false;
        if (date != null ? !date.equals(review.date) : review.date != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (article != null ? article.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        return result;
    }

    public static int getATTENTE_MODERATION() {
        return WAITING_MODERATION;
    }

    public static int getPUBLIE() {
        return PUBLISHED;
    }

    public static int getATTENTE_MODIFICATION() {
        return MUST_BE_MODIFIED;
    }

    public static int getREJETE() {
        return REJECTED;
    }

    public static int getSUPPRIME() {
        return DELETED;
    }

    public static int getABANDONNE() {
        return ABANDONED;
    }

    public int getState() {
        return this.state;
    }

    private boolean  canTransitTo(int targetState) {

        return Arrays.asList(transitions.get(targetState)).contains(this.getState());
    }


}

