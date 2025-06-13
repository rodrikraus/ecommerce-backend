# ecommerce-template

Template backend for an ecommerce

## Technologies Used

* Java 17
* Spring Boot
* Maven
* Spring Data JPA
* Spring Web
* Thymeleaf
* PostgreSQL
* Lombok

## Build and Run

To build the project, run the following command in your terminal:

```bash
mvn clean install
```

To run the application, use the following Maven command:

```bash
mvn spring-boot:run
```

## Project Structure

The main directories and files in the project are:

* `src/main/java`: Contains the Java source code for the application.
* `src/main/resources`: Contains application resources, such as configuration files and templates.
* `pom.xml`: The Project Object Model (POM) file, which defines the project configuration, dependencies, and build settings for Maven.

## API Endpoints

This section lists the main API endpoints and their functionalities.

### Web Endpoints (HTML Views)

These endpoints typically return HTML pages for user interaction.

*   **Login Controller (`LoginController.java`)**
    *   `GET /login`: Displays the login page.
    *   `POST /login`: Handles user login attempt.
    *   `GET /logout`: Logs out the current user and invalidates the session.

*   **Product Controller (`ProductController.java` - base path `/productos`)**
    *   `GET /`: Displays a list of all products.
    *   `GET /agregarProducto`: Shows a form to add a new product.
    *   `POST /guardarProductoNuevo`: Submits the form to create a new product.
    *   `GET /modificarProducto/{id}`: Shows a form to edit an existing product identified by `id`.
    *   `POST /guardarModificacionProducto`: Submits the form to update an existing product.
    *   `GET /borrar/{id}`: Deletes a product identified by `id`.

*   **Purchase Controller (`CompraController.java` - base path `/compras`)**
    *   `GET /`: Displays a list of all purchases.
    *   `GET /modificarCompra/{id}`: Shows a form to edit an existing purchase identified by `id`.
    *   `POST /guardarModificacionCompra`: Submits the form to update an existing purchase.
    *   `GET /borrar/{id}`: Deletes a purchase identified by `id`.

### REST API Endpoints (JSON)

These endpoints are designed for programmatic access and typically return JSON data.

*   **Product REST Controller (`ProductRestController.java` - base path `/api/productos`)**
    *   `POST /`: Creates a new product. Expects product data in the request body.
    *   `GET /`: Retrieves a list of all products.
    *   `DELETE /borrar_producto/{id}`: Deletes a product identified by `id`.
    *   `GET /{id}`: Retrieves a specific product identified by `id`.

*   **Purchase REST Controller (`CompraRestController.java` - base path `/api/compras`)**
    *   `POST /`: Creates a new purchase. Expects purchase data in the request body.

## Database Setup

The application uses PostgreSQL as its database.

1.  **Install PostgreSQL**: Ensure you have PostgreSQL installed and running on your system.
2.  **Create a Database**: Create a new database for the application (e.g., `ecommerce_db`).
3.  **Configure Connection**: The application connects to the database using the following environment variables. You need to set these variables in your environment or in the `application.properties` file (though environment variables are recommended for security).
    *   `DB_URL`: The JDBC URL for your PostgreSQL database (e.g., `jdbc:postgresql://localhost:5432/ecommerce_db`).
    *   `DB_USERNAME`: The username for your database.
    *   `DB_PASSWORD`: The password for your database user.

    Alternatively, you can configure these properties directly in `src/main/resources/application.properties`:
    ```properties
    spring.datasource.url=${DB_URL:jdbc:postgresql://localhost:5432/ecommerce_db}
    spring.datasource.username=${DB_USERNAME:your_username}
    spring.datasource.password=${DB_PASSWORD:your_password}
    ```
    Replace `your_username` and `your_password` with your actual database credentials if not using environment variables.

4.  **Schema Management**: The application uses Hibernate, and it's configured to automatically update the database schema based on the JPA entities. This is controlled by the following property in `application.properties`:
    ```properties
    spring.jpa.hibernate.ddl-auto=update
    ```
    This means that when the application starts, Hibernate will attempt to update the schema to match the entity definitions. For a fresh database, it will create the necessary tables.

    **Note**: While `update` is convenient for development, for production environments, it's generally recommended to use a database migration tool like Flyway or Liquibase for more control over schema changes.
