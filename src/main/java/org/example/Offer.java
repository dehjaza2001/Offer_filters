package org.example;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;

import java.util.*;
public class Offer {
    private int id;
    private String title;
    private String description;

    private int category;
    private  List<Map<String, Object>> merchants;
    private  String validTo;

    public Offer(int id, String title, String description, int category, List<Map<String, Object>> merchants, String validTo) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.category = category;
        this.merchants = merchants;
        this.validTo = validTo;
    }
    public Offer(JsonNode offerNode){
        this.id = offerNode.get("id").asInt();
        this.title = offerNode.get("title").asText();
        this.description = offerNode.get("description").asText();
        this.category = offerNode.get("category").asInt();
        this.validTo = offerNode.get("valid_to").asText();
        this.merchants = new ArrayList<>();

        JsonNode merchantsNode = offerNode.get("merchants");
        if (merchantsNode.isArray()) {
            for (JsonNode merchant : (ArrayNode) merchantsNode) {
                int merchantId = merchant.get("id").asInt();
                String name = merchant.get("name").asText();
                double distance = merchant.get("distance").asDouble();
                Map<String, Object> merchantMap = Map.of(
                        "id", merchantId,
                        "name", name,
                        "distance", distance
                );
                this.merchants.add(merchantMap);
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

    public List<Map<String, Object>> getMerchants() {
        return merchants;
    }

    public void setMerchants(List<Map<String, Object>> merchants) {
        this.merchants = merchants;
    }

    public String getValidTo() {
        return validTo;
    }

    public void setValidTo(String validTo) {
        this.validTo = validTo;
    }
}
