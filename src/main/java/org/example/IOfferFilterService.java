package org.example;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.Date;
import java.util.List;

public interface IOfferFilterService {
     List<Offer> filterOffers(Date checkIndate, JsonNode input);

     boolean isValidOffer(Offer offer, Date checkinDate);

     JsonNode convertToJsonNode(List<Offer> offers) ;
}