# Cinema Room REST Service with Java

Welcome to the Cinema Room REST Service with Java project! This project simulates a virtual movie theater with features such as checking seat availability, purchasing tickets, and refunding tickets.

## Features

- View available seats in the cinema.
- Purchase tickets and receive a token.
- Refund tickets using the provided token.
- Obtain cinema statistics (for theater managers) with proper authentication.

## Usage

### Endpoints

- **GET /seats**
    - Description: Get information about available seats in the cinema.
    - Example Request: `GET /seats`

- **POST /purchase**
    - Description: Purchase a ticket for a specified seat.
    - Example Request: `POST /purchase`
    - Request Body:
      ```json
      {
          "row": 3,
          "column": 4
      }
      ```

- **POST /return**
    - Description: Refund a ticket using the provided token.
    - Example Request: `POST /return`
    - Request Body:
      ```json
      {
          "token": "e739267a-7031-4eed-a49c-65d8ac11f556"
      }
      ```

- **GET /stats**
    - Description: Get cinema statistics (for theater managers).
    - Example Request: `GET /stats?password=super_secret`

## Project Structure

- `src/cinema`: Contains the main application classes.
- `src/resources`: Contains configuration files.
- ...

## Dependencies

- [Spring Boot](https://spring.io/projects/spring-boot)
- [Hyperskill Test Framework](https://github.com/hyperskill/hs-test)
