package online.patino.cinema.model;

import org.springframework.transaction.IllegalTransactionStateException;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import java.sql.Timestamp;
import java.util.*;

@Entity
public class Review {



    private static final int WAITING_MODERATION = 1;
    private static final int PUBLISHED = 2;
    private static final int MUST_BE_MODIFIED = 3;
    private static final int REJECTED = 4;
    private static final int DELETED = 5;
    private static final int ABANDONED = 6;

    private static  final   Map<Integer, List<Integer>> transitions;

    static {
        transitions = new HashMap<>();
        transitions.put(Review.WAITING_MODERATION,Arrays.asList (Review.WAITING_MODERATION, Review.PUBLISHED, Review.MUST_BE_MODIFIED,Review.REJECTED));
        transitions.put(Review.PUBLISHED,Arrays.asList (Review.WAITING_MODERATION, Review.DELETED));
        transitions.put(Review.MUST_BE_MODIFIED,Arrays.asList (Review.WAITING_MODERATION, Review.ABANDONED));
        transitions.put(Review.ABANDONED, new ArrayList<>());
        transitions.put(Review.DELETED, new ArrayList<>());
        transitions.put(Review.REJECTED, new ArrayList<>());
    }
    private boolean  canTransitTo(int targetState) {

        return Review.transitions.get(this.getState()).contains(targetState);
    }

    private int state = Review.WAITING_MODERATION;
    public void validByModerator() throws IllegalTransactionStateException{
        transitTo(Review.PUBLISHED);
    }

    public void keepForEditByModerator() throws IllegalTransactionStateException {
        transitTo(Review.MUST_BE_MODIFIED);
    }

    public void abandonByUser() throws IllegalTransactionStateException {
        transitTo(Review.ABANDONED);
    }

    public void rejectByModerator() throws IllegalTransactionStateException {
        transitTo(Review.REJECTED);
    }

    public void editByUser() throws IllegalTransactionStateException {
        transitTo(Review.WAITING_MODERATION);
    }

    private void transitTo(int target) throws IllegalTransactionStateException {
        if (canTransitTo(target)) {
            this.state = target;
        } else {
            throw new IllegalTransactionStateException("Transition non autorisée");
        }
    }

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

    public static int getWaitingModeration() {
        return WAITING_MODERATION;
    }

    public static int getPUBLISHED() {
        return PUBLISHED;
    }

    public static int getMustBeModified() {
        return MUST_BE_MODIFIED;
    }

    public static int getREJECTED() {
        return REJECTED;
    }

    public static int getDELETED() {
        return DELETED;
    }

    public static int getABANDONED() {
        return ABANDONED;
    }

    public static Map<Integer, List<Integer>> getTransitions() {
        return transitions;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
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





}

