#!/bin/bash

echo "CURL operation 1 - get average exchange rate (should return 5.348 value)"
echo "--location 'localhost:8080/nbp/exchanges/average-exchange-rate?date=2012-01-02&code=gbp'"

curl --location 'localhost:8080/nbp/exchanges/average-exchange-rate?date=2012-01-02&code=gbp'
echo -e "\n"

echo "CURL operation 2 - get min and max exchange rates  (should return json object with code = GBP, minMid = 5.2874 and maxMid = 5.1861 values)"
echo "curl --location 'localhost:8080/nbp/exchanges/min-max-rate?topCount=10&code=gbp'"
curl --location 'localhost:8080/nbp/exchanges/min-max-rate?topCount=10&code=gbp'
echo -e "\n"

echo "CURL operation 3 - get difference between the buy and ask rate (should return json object curerncy, code and rates)"
echo "Rates is a list, which has 5 json objects with diffAskBid values, which are our returned values for different dates."
echo "Following values should be: 0.10479975, 0.10440016, 0.10420036, 0.103999615, 0.10340023. Checked for 26.04.2022"
echo "curl --location 'localhost:8080/nbp/exchanges/difference?code=gbp&topCount=5'"
curl --location 'localhost:8080/nbp/exchanges/difference?code=gbp&topCount=5'