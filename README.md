# Juniorica Recruitment Task

## Setup
This project requires JDK 17 and Docker installed.

### Building modules and creating container images
In order to build microservices and create container images to run in Docker, please run in project root:
* on Windows:
```shell script
mvnw.cmd clean install
```
* on Linux/macOS:
```shell script
./mvnw clean install
```
### Running application
After creating container images, please run in project root:
```shell script
docker compose up
```
That will deploy microservices Docker containers and setup MySQL database container.

Credit service will then be available under `http://localhost:8081/credit/`.

## Sample calls
### Creating new credit
Creating new credit is accessible via POST request to this endpoint:
```shell script
http://localhost:8081/credit/create
```
Sample data sent in POST's request body:
```shell script
{
    "creditName": "testCredit",
    "customerFirstName": "Jan",
    "customerLastName": "Kowalski",
    "customerPESEL": "12345678901",
    "productName": "testProduct",
    "productValue": 123
}
```
Sample response:
```shell script
{
    "creditID": 1835050629
}
```

### Getting all credits
Creating new credit is accessible via GET request to this endpoint:
```shell script
http://localhost:8081/getAll
```
Sample response:
```shell script
{
    "allCredits": [
        {
            "creditName": "anotherCredit",
            "clientFirstName": "Kowal",
            "clientLastName": "Janowski",
            "clientPESEL": "98765432101",
            "productName": "anotherProduct",
            "productValue": 256
        },
        {
            "creditName": "testCredit",
            "clientFirstName": "Jan",
            "clientLastName": "Kowalski",
            "clientPESEL": "12345678901",
            "productName": "testProduct",
            "productValue": 123
        }
    ]
}
```