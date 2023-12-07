package org.example;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.Model.Merchant;
import org.example.Model.Offer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class OfferFilterService implements IOfferFilterService{
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    @Override
    public List<Offer> filterOffersCategory(Date checkIndate, List<Offer> offers) {
        List<Offer> filteredOffers = new ArrayList<>();
        Map<Integer,Double> categoryCount = new HashMap<>();
        for(Offer offer : offers){
            if(isValidOffer(offer,checkIndate) && !offer.getMerchants().isEmpty()){
                Merchant closetMerchant = Collections.max(offer.getMerchants());
                List<Merchant> filteredMerchant = new ArrayList<>();
                filteredMerchant.add(closetMerchant);
                offer.setMerchants(filteredMerchant);

                if (!categoryCount.containsKey(offer.getCategory())) {
                    categoryCount.put(offer.getCategory(), closetMerchant.getDistance());
                } else {
                    if(closetMerchant.getDistance() < categoryCount.get(offer.getCategory())){
                        filteredOffers.removeIf(filteredOffer -> filteredOffer.getCategory() == offer.getCategory());
                        categoryCount.put(offer.getCategory(),closetMerchant.getDistance());
                    } else continue;
                }
                filteredOffers.add(offer);
            }
        }

        return filteredOffers;
    }

    @Override
    public List<Offer> filter2closestOffer(List<Offer> offers) {
        if(offers.size() > 2){
            Offer offer1 = new Offer();
            Offer offer2 = new Offer();
            double minDistance1 = Double.MAX_VALUE;
            double minDistance2 = Double.MAX_VALUE;

            for(Offer offer : offers){
                double currentDistance = offer.getMerchants().get(0).getDistance();
                if(currentDistance < minDistance1){
                    minDistance2 = minDistance1;
                    minDistance1 = currentDistance;
                    offer2 = offer1;
                    offer1 = offer;
                } else if ( currentDistance < minDistance2 && currentDistance != minDistance1){
                    minDistance2 = currentDistance;
                    offer2 = offer;
                }
            }

            List<Offer> filteredCategoryOffers = new ArrayList<>();
            filteredCategoryOffers.add(offer1);
            filteredCategoryOffers.add(offer2);

            return filteredCategoryOffers;
        }

        return offers;
    }


    @Override
    public boolean isValidOffer(Offer offer, Date checkinDate) {
        try {
            Date validToDate = dateFormat.parse(offer.getValid_to());
            Calendar cal = Calendar.getInstance();
            cal.setTime(checkinDate);
            cal.add(Calendar.DATE, 5);
            if(!validToDate.before(cal.getTime()) && offer.getCategory() != 3){
                return true;
            }
        } catch (ParseException e){
            e.printStackTrace();
        }
        return false;
    }


}
