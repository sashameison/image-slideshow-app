# image-slideshow-app

This is a image slideshow app built with Spring Boot, Gradle, and Java. It provides API for slideshows.
In current design one image can be linked to one slideshow.

## Setup and Installation

### 1. Clone the Repository

```bash
git clone https://github.com/sashameison/image-slideshow-app.git
cd image-slideshow-app
```

### 2. Setup dev services for application

```bash
./gradle build
docker-compose up
```

### 3. Build and run the app (in case your encounter permission denied use chmod 777 ./gradlew ) Or using IntellijIDEA UI button
```
./gradlew bootRun
```

### 4. Use postman collection in postman folder for using API.

### 5. API Documentation

For detailed API documentation, please visit the [Swagger UI](http://localhost:8080/swagger-ui/index.html).