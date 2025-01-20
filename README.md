# Toby Nichol - Experimental API

**Description:**

_This project is my sandbox for working with Spring and various libraries in my spare time._

## **Getting Started:**

1.  **Prerequisites:**
    *   Java 17
    * Maven
    

2.  **Installation:**
    *   Clone the repository: `git clone https://github.com/tobywritescode/starling_tech_test.git`
    * Use the docker-compose file to launch the relevant docker containers.

## **Test Usage:**
To run the tests, port 8081 needs to be available for wiremock. Because of this there are two profiles. "**Test**" for the E2E tests and "**wiremock**" for the mock testing.

## **Usage:**

*   To start the application you just need to have port 9091 available.
* The application uses Spring Security with a bearer token to access the API endpoints. Simply call the /token endpoint with basic auth header. 
* username: **user**
* password: **password**
* You will receive a bearer token to use in the header for the rest of the endpoints.


## **Endpoints:**

1. **/token** - call this endpoint with a basic auth header with the above username and password to be given a bearer token.

2. **/rickandmorty/***
   1. **/getcharacters/{ids}** - Endpoint accepts a list of Integer ID's and will call the Rick And Morty public api and return the relevant list of characters and info.
   2. **/getcharacterbyid/{id}** - Endpoint will return a single character object from the embedded h2 database.
   3. **/getcharacters** - Endpoint will return all characters stored in the embedded h2 database.
   4. **/getlivingearthdwellers** - Endpoint will find all rick and morty characters that are currently alive and were last seen on earth.
   5. **/getcharacterepisodecount** - Endpoint will return a list of every character and the number of episodes they were in.
   6. **/getreoccuringcharacters** - The endpoint will return a map of characters. Each entry in the map will contain the character's name, the character's ID as the key and a list of episode objects in which the character starred as the value.
   7. **/getgendercount** - Endpoint will return a count of each gender in the show e.g.
`   {
    "Female": 148,
    "Male": 610,
    "Genderless": 19,
    "unknown": 49
}`
   8. **/getricks** - Endpoint will return a Map of Ricks starring in the show. Each entry will have the name of each rick as the key and the value will be another nested map. The key being the episode count and the value being a list of links to those episodes. e.g     `"Zeta Alpha Rick389": {
      "4": [
      "https://rickandmortyapi.com/api/episode/10",
      "https://rickandmortyapi.com/api/episode/11",
      "https://rickandmortyapi.com/api/episode/22",
      "https://rickandmortyapi.com/api/episode/51"
      ]
      },`

3. **/kafka***
   1. **/go** - Endpoint generates 100 messages pushes them to a topic (userInfo-topic) and then consumes the messages using the kafka listener (UserInfoConsumer) class.
4. **/people/***
   1. **/getUsersOver/{age}** - Endpoint takes a list of users and an integer path variable (age) and returns all users over that age.
   2. **/getUsersInAgeGroups** - Endpoint takes a list of users and returns them in 3 different age groups.
5. **/telegram/***
   1. **/sendmessage** - Endpoint takes a string body (message) and uses the telegram API chatbot to send a message to a particular chat ID - I've used this to asynchronously alert me to anyone using one of the Rick And Morty Endpoints.

**Contact:**

[Toby Nichol] - [toby.s.nichol@gmail.com]
