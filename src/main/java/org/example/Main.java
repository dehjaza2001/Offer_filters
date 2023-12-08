package org.example;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.Model.Offer;
import org.example.Model.OfferList;

public class Main {
    public static void main(String[] args) {
            if(args.length != 2){
                System.out.println("Usage: java -jar YourJarName.jar <inputFileName> <checkInDate>");
                System.exit(1);
            }
            String inputFilePath = args[0];
            String checkInDateString = args[1];
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
            try (FileInputStream fileInputStream = new FileInputStream(inputFilePath )) {
                if (fileInputStream != null) {
                    OfferList offerList = mapper.readValue(fileInputStream, OfferList.class);
                    List<Offer> offers = offerList.getOffers();
                    IOfferFilterService offerFilterService = new OfferFilterService();

                    List<Offer> filteredOffers = offerFilterService.filterOffersCategory(checkInDate, offers);
                    filteredOffers = offerFilterService.filter2closestOffer(filteredOffers);

                    mapper.writeValue(new File("output.json"), new OfferList(filteredOffers));
                    System.out.println("Filtered offers saved to output.json.");
                } else {
                    System.out.println("File not found in resources directory.");
                }
            } catch (JsonParseException e) {
                System.out.println("Error parsing JSON file.");
                e.printStackTrace();
            } catch (IOException e) {
                System.out.println("Error reading or writing files.");
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
}