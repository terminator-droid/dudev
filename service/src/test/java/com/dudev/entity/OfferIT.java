package com.dudev.entity;

import com.dudev.basetest.TransactionManagementTestBase;
import org.junit.jupiter.api.Test;

import static com.dudev.entity.Role.USER;
import static com.dudev.util.EntityGenerator.getChangeType;
import static com.dudev.util.EntityGenerator.getOffer;
import static com.dudev.util.EntityGenerator.getUser;
import static org.assertj.core.api.Assertions.assertThat;

public class OfferIT extends TransactionManagementTestBase {


    @Test
    public void save() {
        Offer offer = saveOffer();

        assertThat(offer.getId()).isNotNull();
    }

    @Test
    public void get() {
        Offer offer = saveOffer();

        Offer actualOffer = session.get(Offer.class, offer.getId());

        assertThat(actualOffer).isEqualTo(offer);
    }

    @Test
    public void update() {
        Offer initialOffer = saveOffer();

        initialOffer.setChangeValue(200);
        session.update(initialOffer);
        session.flush();
        session.clear();
        Offer updatedOffer = session.get(Offer.class, initialOffer.getId());

        assertThat(updatedOffer).isEqualTo(initialOffer);
    }

    @Test
    public void delete() {
        Offer initialOffer = saveOffer();

        session.delete(initialOffer);
        session.flush();
        Offer deletedOffer = session.get(Offer.class, initialOffer.getId());

        assertThat(deletedOffer).isNull();
    }

    private Offer saveOffer() {
        ChangeType changeType = getChangeType();
        User buyer = getUser();
        User seller = User.builder()
                .username("Gosling333")
                .role(USER)
                .fullName("Ryan Gosling")
                .password("second_password")
                .phoneNumber("8920-122-22-23")
                .build();
        Offer offer = getOffer(buyer, seller, changeType);
        session.save(seller);
        session.save(buyer);
        session.save(changeType);
        session.save(offer);
        return offer;
    }
}
