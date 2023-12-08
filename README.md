# Offer_filters

## Description
Part of Ascenda travel platform is to find nearby offers for our customers. When a user books a hotel using Ascenda travel platform, we find nearby offers around the hotel customer booked and send those offers in the booking confirmation email. So when the customer stays at the hotel, he can enjoy nearby offers from Restaurants, Retail stores & Tourist Activity places.

For that purpose, we are using an external partner API to fetch offers. For this external API, we can provide the latitude & longitude values of the location & radius by kilometers to filter the offers.

For example: https://61c3deadf1af4a0017d990e7.mockapi.io/offers/near_by?lat=1.313492&lon=103.860359&rad=20

And this API returns us a JSON response including offer details & merchant details.

Merchants giving these offers are categorized by the external partner. In our travel platform, we only show a few selected categories even though API returns us more.

## Setup Instructions
### Prerequisites
Java JDK (version 1.11.0 or later)

### Clone the Repository
+ ``` git clone https://github.com/dehjaza2001/Offer_filters.git ```
+ ``` cd Offer_filters ```

### Run the .jar file
+ ``` cd out/artifacts/Ascenda_Luong_Thien_Tri_jar ```
+ Provide the input.json file with your data
+ Run the command _java -jar Ascenda_Luong_Thien_Tri.jar <input.json> <checkInDate>_ . For example

 ``` java -jar Ascenda_Luong_Thien_Tri.jar input.json 2019-12-25 ```
+ Then the output.json file will be in the same file as .jar file
  
