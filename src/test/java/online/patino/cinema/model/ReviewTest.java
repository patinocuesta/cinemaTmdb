package online.patino.cinema.model;

import org.junit.Test;
import org.springframework.transaction.IllegalTransactionStateException;

import static org.junit.Assert.*;

public class ReviewTest {

    @Test
    public void setArticle() {
        Review comment = new Review();
        comment.setArticle("test commentaire");
        assertEquals("test commentaire", comment.getArticle());
    }

    @Test
    public void etatInitial() {
        Review comment = new Review();
        assertEquals(Review.getWaitingModeration(), comment.getState());
    }

    @Test
    public void goodTransitionToPublie() {
        Review comment = new Review();
        try {
            comment.validByModerator();
            assertEquals(Review.getPUBLISHED(), comment.getState());
        } catch (IllegalTransactionStateException e) {
            fail("Transition attendue");
        }
    }

    @Test
    public void badTransitionToPublie() {
        Review comment = new Review();
        try {
            comment.keepForEditByModerator();
            comment.validByModerator();
            fail("Transition non autorisée");
        } catch (IllegalTransactionStateException e) {
            assertEquals(Review.getMustBeModified(), comment.getState());
        }
    }

    @Test
    public void goodTransitionToAbandonne() {
        Review comment = new Review();
        try {
            comment.keepForEditByModerator();
            comment.abandonByUser();
            assertEquals(Review.getABANDONED(), comment.getState());
        } catch (IllegalTransactionStateException e) {
            fail("Transition attendue");
        }
    }

    @Test
    public void badTransitionToAbandoned() {
        Review comment = new Review();
        try {
            comment.validByModerator();
            comment.abandonByUser();
            fail("Transition vers Abandonne non autorisée");
        } catch (IllegalTransactionStateException e) {
            assertEquals(Review.getPUBLISHED(), comment.getState());
        }
    }

    @Test
    public void goodTransitionReject() {
        Review comment = new Review();
        try {
            comment.rejectByModerator();
            assertEquals(Review.getREJECTED(), comment.getState());
        } catch (IllegalTransactionStateException e) {
            fail("Transition attendue");
        }
    }

    @Test
    public void badTransitionReject() {
        Review comment = new Review();
        try {
            comment.validByModerator();
            comment.rejectByModerator();
            fail("Transition vers Rejected non autorisée");
        } catch (IllegalTransactionStateException e) {
            assertEquals(Review.getPUBLISHED(), comment.getState());
        }
    }


    @Test
    public void goodTransitionEditByUserFromPublished() {
        Review comment = new Review();
        try {
            comment.validByModerator();
            comment.editByUser();
            assertEquals(Review.getWaitingModeration(), comment.getState());
        } catch (IllegalTransactionStateException e) {
            fail("Transition attendue");
        }
    }


    @Test
    public void goodTransitionEditByUserFromWAITING_MODERATION() {
        Review comment = new Review();
        try {
            assertEquals(Review.getWaitingModeration(), comment.getState());
            comment.editByUser();
            assertEquals(Review.getWaitingModeration(), comment.getState());
        } catch (IllegalTransactionStateException e) {
            fail("Aucune transition attendue");
        }
    }


    @Test
    public void goodTransitionEditByUserFromMUST_BE_MODIFIED() {
        Review comment = new Review();
        try {
            comment.keepForEditByModerator();
            assertEquals(Review.getMustBeModified(), comment.getState());
            comment.editByUser();
            assertEquals(Review.getWaitingModeration(), comment.getState());
        } catch (IllegalTransactionStateException e) {
            fail("Transition attendue");
        }
    }

    @Test
    public void badTransitionEditByUserFromABANDONED() {
        Review comment = new Review();
        try {
            comment.keepForEditByModerator();
            comment.abandonByUser();
            comment.editByUser();
            fail("Transition non permise attendue");
        } catch (IllegalTransactionStateException e) {
            assertEquals(Review.getABANDONED(), comment.getState());
        }
    }
}