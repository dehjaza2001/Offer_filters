package org.example.Model;

import org.example.Model.Offer;
import java.util.List;

public class OfferList {
    private List<Offer> offers;

    public OfferList(List<Offer> new_offers){
        this.offers = new_offers;
    }

    public OfferList(){
        this.offers = null;
    }

    public List<Offer> getOffers() {
        return offers;
    }

    public void setOffers(List<Offer> offers) {
        this.offers = offers;
    }
}