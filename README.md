
## Getting Started

### Prerequisites
In order to run the application you need to create a database and run it by code editor or from command line.

## Create PostgreSQL database
  ```
      docker pull postgres
      docker run -d --name postgres-zadanie -e POSTGRES_PASSWORD=password -p 5432:5432  postgres
              
      docker exec -ti <4 first letters from ID container> psql -U postgres
      create database user_db;
```

## Run the application from command line
Run the application:
```
      mvn spring-boot:run
```

### Startup
On startup application initialises the database and do injection.
After a startup, you will have next users in the database:

Admin:
```
    username: admin
    password: admin
```

Users:
```
    username: user
    password: user

    username: user2
    password: user
```



### Functionalities
The application allows you as an admin to do next:
```
- login
- logout
- create a gallery and assign to a user 
- upload additional photos to a gallery
- delete a gallery
- create a user
- delete a user
```

The application allows you as a user to do next:
```
- login
- logout
- check photos from galleris assigned to a user
```


