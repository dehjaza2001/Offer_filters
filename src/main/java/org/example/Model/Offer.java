package org.example.Model;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.*;
public class Offer implements Comparable<Offer> {
    private int id;
    private String title;
    private String description;

    private int category;
    private  List<Merchant> merchants;
    private  String valid_to;

    public Offer(int id, String title, String description, int category, List<Merchant> merchants, String valid_to) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.category = category;
        this.merchants = merchants;
        this.valid_to = valid_to;
    }
    public Offer(){
        this.id = 0;
        this.title = "";
        this.description = "";
        this.category = 0;
        this.merchants = new ArrayList<>();
        this.valid_to = "";

    }
    public Offer(JsonNode offerNode){
        this.id = offerNode.get("id").asInt();
        this.title = offerNode.get("title").asText();
        this.description = offerNode.get("description").asText();
        this.category = offerNode.get("category").asInt();
        this.valid_to = offerNode.get("valid_to").asText();
        this.merchants = new ArrayList<>();

        JsonNode merchantsNode = offerNode.get("merchants");
        if (merchantsNode.isArray()) {
            for (JsonNode merchantNode : merchantsNode) {
                int merchantId = merchantNode.get("id").asInt();
                String name = merchantNode.get("name").asText();
                double distance = merchantNode.get("distance").asDouble();
                Merchant merchant = new Merchant(merchantId,name,distance);
                this.merchants.add(merchant);
            }
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public List<Merchant> getMerchants() {
        return merchants;
    }

    public void setMerchants(List<Merchant> merchants) {
        this.merchants = merchants;
    }

    public String getValid_to() {
        return valid_to;
    }

    public void setValid_to(String valid_to) {
        this.valid_to = valid_to;
    }

    @Override
    public int compareTo(Offer compareOffer) {
        return 0;
    }
}
