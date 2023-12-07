package org.example;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.Model.Offer;
import org.example.Model.OfferList;

public class Main {
    public static void main(String[] args) {
            if(args.length < 1){
                System.out.println("Please provide the check-in date (format : YYYY-MM-DD).");
                return;
            }
            String checkInDateString = args[0];
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            dateFormat.setLenient(false);
            Date checkInDate;
            try {
                checkInDate = dateFormat.parse(checkInDateString);
            } catch (ParseException e) {
                System.out.println("Invalid syntax, please use YYYY-MM-DD.");
                return;
            }

            ObjectMapper mapper = new ObjectMapper();
            try {
                OfferList offerList = mapper.readValue(new File("src/main/resources/input.json"), OfferList.class);
                List<Offer> offers = offerList.getOffers();
                IOfferFilterService offerFilterService = new OfferFilterService();

                List<Offer> filteredOffers = offerFilterService.filterOffersCategory(checkInDate,offers);
                filteredOffers = offerFilterService.filter2closestOffer(filteredOffers);

                mapper.writeValue(new File("output.json"), new OfferList(filteredOffers));
                System.out.println("Filtered offers saved to output.json.");
            } catch (IOException e) {
                System.out.println("Error reading or writing files.");
                throw new RuntimeException(e);
            }

    }
}