package org.example;

import com.fasterxml.jackson.databind.JsonNode;
import org.example.Model.Offer;

import java.util.Date;
import java.util.List;

public interface IOfferFilterService {
     List<Offer> filterOffersCategory(Date checkIndate, List<Offer> offers);

     List<Offer> filter2closestOffer(List<Offer> offers);

     boolean isValidOffer(Offer offer, Date checkinDate);

}