# REST API Backend


This is a backend application built using Java 11 and Spring Boot version 2.3.1 which provides REST API for both saving and retrieving the weather of a city.
It also simulates a buggy endpoint returning 500 error status code. 

## Table Of Content

- [Endpoints](#endpoints)
    - [1. Auth](#auth)
    - [2. Get Weather](#get-weather)
        - [By city](#by-city)
        - [By date](#by-date)
    - [2. Save Weather](#save-weather)
    - [3. Delete Weather](#delete)
    - [4. 500 status code simulation](#500)
- [Usage](#usage)
    - [Docker hub](#docker-hub)
    - [Environment variables](#environment-variables)
    - [How to run](#how-to-run)
- [API Documentation](#api-documentation)
    - [API Reference](#api-reference)
    - [Swagger Docs](#swagger-docs)  
- [List of handled exceptions](#handled-exceptions)
- [Known issues/limitations](#known-issues)

##
## Endpoints

Below are the possible endpoints.
### Auth 
(/auth) POST call to authenticate. More details in [API Documentation](#api-documentation)
### Get Weather 
(/weather) GET call - More details in [API Documentation](#api-documentation)

This is used to fetch the list of weatherstatus(es) based on either city or date (or both) based on the query string parameter.

**Note:** Authentication is required, or else 403 is returned!

**Note:** Atleast one request parameter is required, or else 422 is returned!


* #### By city

In this case, the temperature of all dates for this city is returned.

* #### By date

In this case, the temperature of all the cities for this date is returned.


### Save weather
(/weather) POST call - More details in [API Documentation](#api-documentation)

**Note:** Authentication is required, or else 403 is returned!

This is used to save the temperature of a city for a specific date.
### Delete
(/weather/{city}) DELETE call - More details in [API Documentation](#api-documentation)

**Note:** Authentication is required, or else 403 is returned!
This endpoint is developed for test automation scripts so that the data related to the city can be deleted after the test is completed.

### 500 
More details in [API Documentation](#api-documentation)

This is a buggy endpoint which responds 500 status code.




##
## Usage
### Docker Hub
Repository - https://hub.docker.com/repository/docker/arunaneja/dcs-rest-api-backend/

### Environment Variables
DCS_USER_NAME - This sets the username needed for authentication.

DCS_PASSWORD - This sets the password needed for authentication.

### How to run
```bash
docker run arunaneja/dcs-rest-api-backend:<tag-name> -p <host-port>:8080 -e DCS_USERNAME=<user-name> -e DCS_PASSWORD=<password>
```
or 

The container orchestration file can be seen here - https://github.com/dcs-challenge/container-orchestration/blob/main/docker-compose-only-api.yml
Then, use the following command
```bash
docker-compose -f docker-compose-only up
```
##
## API Documentation

### API Reference

#### Authentication

```http
  POST /auth
```

| Request Body | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `username` | `string` | **Required**. Username|
| `password` | `string` | **Required**. password|



#### Get weather by city

```http
  GET /weather?city=<city>
```

| Request param | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `city` | `string` | City for which weather is needed |

| Request header | Value     | Description                |
| :-------- | :------- | :------------------------- |
| `Authorizarion` | Bearer token | Bearer token returned in the auth call |

#### Get weather by date

```http
  GET /weather?date<date>
```

| Request param | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `date` | `string` | Date for which weather is needed |

| Request header | Value     | Description                |
| :-------- | :------- | :------------------------- |
| `Authorizarion` | Bearer token | Bearer token returned in the auth call |

#### Save weather

```http
  POST /weather
```

| Request Body | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `city` | `string` | **Required**. city|
| `date` | `string` | **Required**. date, to be given in the format yyyy-mm-dd |
| `temperature` | `string` | **Required**. temperature|

| Request header | Value     | Description                |
| :-------- | :------- | :------------------------- |
| `Authorizarion` | Bearer token | Bearer token returned in the auth call |

```python
  Success status code : 201
```
```python
Response body
{ "city": <string>,
  "date": <string>
  "temperature": <string>
 }
 ```


#### Delete weather by city
```http
  DELETE /weather/{city}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `city`      | `string` | **Required**. city for which data to be deleted. developed to help test automation scripts to delete data |

| Request header | Value     | Description                |
| :-------- | :------- | :------------------------- |
| `Authorizarion` | Bearer token | Bearer token returned in the auth call |



### Swagger Docs

First, bring the container up by either of the following commands.
```bash
docker run arunaneja/dcs-rest-api-backend:<tag-name> -p <host-port>:8080 -e DCS_USERNAME=<user-name> -e DCS_PASSWORD=<password>
```
or

```bash
docker-compose -f docker-compose-only up
```

Then, access the url - 
```bash
http://localhost:<host-port>//swagger-ui.html
```

For e.g. if the port mapping used while running the docker container is 9081, the the url for Swagger docs would be 

Then, access the url - 
```bash
http://localhost:9081//swagger-ui.html
```

### Handled Exceptions (and respective status codes)
* 422 - If trying to get weather status without any parameter (city or date)
* 409 - If trying to sace weather for an existing combination of city and date.
* 403 - If trying to save, get or delete without authentication.
* 404 - Bad exception in various case, like saving weather without city in request body. 

