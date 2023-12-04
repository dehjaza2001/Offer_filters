package org.example;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class OfferFilterService implements IOfferFilterService{
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    @Override
    public List<Offer> filterOffers(Date checkIndate, JsonNode input) {
        List<Offer> offers = new ArrayList<>();
        for(JsonNode offerNode : input.get("offers")){
            Offer offer = new Offer(offerNode);
            if(isValidOffer(offer,checkIndate)){
                offers.add(offer);
            }
        }
        return offers;
    }

    @Override
    public boolean isValidOffer(Offer offer, Date checkinDate) {
        return false;
    }



    @Override
    public JsonNode convertToJsonNode(List<Offer> offers) {
        ObjectMapper mapper = new ObjectMapper();
        return null;
    }
}
