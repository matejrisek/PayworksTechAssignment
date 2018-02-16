# Payworks Tech Assignment
# Purpose
Project is a tech assignment that serves the purpose of showcasing developer's skills.
The idea is simple, app exposes three endpoints ( + login) where user can create a superhero, get details of one and get the list of all the superheroes. (More details in App usage section)

## Technologies used
* Spring Boot
* Spring MVC
* Spring Data JPA
* Spring Security
* JWT

## Security
In order to prevent anyone from creating superheroes, the create endpoint is secured with [JWT](https://jwt.io/introduction/) token based authentication.

Application provides following test account:
```
username: payworks
password: payworks
```

In order to obtain token one has to call /login endpoint first while providing the above credentials.
If authentication was successful, _Authorization_ header which contains the token is returned in response.
After which the obtained token should be sent in every request to the create endpoint.

## Docker
Note that docker build should be run from the project directory as Dockerfile ADDs jar from the target directory with relative path.

## App usage
### Superhero
Superhero has the following properties:

| Property | Description | Mandatory | Format |
| -------- | ----------- | --------- | ------ |
| name | Superhero's name | Yes | - |
| pseudonym | Superhero's pseudonym | No | - |
| publisher | Superhero's publisher | No | - |
| powers | List of superhero's powers | No | - |
| allies | List of superhero's allies | No | - |
| firstAppearance | First appearance of the superhero | No | YYYY-MM-DD |

__Example:__
```json
{
    "name": "Payworks",
    "pseudonym": "PW",
    "publisher": "Unkown",
    "powers": [
        "lasers",
        "flight",
        "superhuman strength"
    ],
    "allies": [
        "AliPay",
        "Visa"
    ],
    "firstAppearance": "2012-01-01"
}
```

### List all superheroes
`curl -k <HOST>/superheroes/`
> As an self-signed certificate was used for SSL connection, we have to use __-k__ flag with curl 
> to allow access to insecure sites.

__Example:__
```bash
curl -k https://85.90.245.217/superheroes/
```
### Get superhero details by name
`curl -k https://85.90.245.217/superheroes/{name}`
__Example:__
```bash
curl -k https://85.90.245.217/superheroes/Superman
```
> Please note that names are case-sensitive
### Login
```
curl -i -k -H "Content-Type: application/json" \
-X POST -d '{"username":"<USERNAME>", "password": "<PASSWORD>"}' \
<HOST>/login
```
__Example:__
```bash
curl -i -k -H "Content-Type: application/json" \
-X POST -d '{"username":"payworks", "password": "payworks"}' \
https://85.90.245.217/login
```
> An authorization token will be provided in __Authorization__ header
### Superhero creation
```
curl -k -H "Authorization: <TOKEN>" -H "Content-Type: application/json"\
-X POST -d '{<JSON_PROPERTIES>}' https://85.90.245.217/superheroes
```
__Example:__
```bash
curl -k -H "Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ\
wYXl3b3JrcyIsImV4cCI6MTUxODc0MTQ0MX0.T-DGfeDeGNG-1BsSTCZ-6-SQTCoxI\
TRLkN2ccCJiCWXgpig7B-c8904AdQVlKZkDS2b0QiqYQs2wjCJkL6t2qQ" \ 
-H "Content-Type: application/json" \ 
-X POST -d '{"name": "Payworks", \
"powers": ["flight", "lasers", "superhuman strength"], \
"firstAppearance": "2012-01-01", "allies":["Visa", "AliPay"]}' \
https://85.90.245.217/superheroes
```
> Note that token has to be provided with the leading Bearer string

### Superhero update
If you wish to update some of the fields, call the creation endpoint with the existing superhero name and with changed properties.

### Todos
* Code coverage
* Automated docker build and deploy to repository
* Prettify error messages (current ones are default ones from Spring Boot libs)
