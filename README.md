# Ktor Hash Calculator

Ktor Hash Calculator is a simple application that calculates the hash of the received string value in POST request. App is desinged to wotk with Android demo app Image Hash Remote Calculator

To calculate the hash, you need to send a POST request to
```sh
localhost:8080/base64tohash 
```
with JSON in body
```json
{
    "base64Image": "YOUR STRING"
}
```
Response is JSON
```json
{
    "imageHash": "YOUR STRING HASH"
}
```