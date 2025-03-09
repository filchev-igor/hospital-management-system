Project Documentation
This document provides an overview of key components and resources related to the hospital management system project. Below are the details for accessing various resources.

1. Actuator
   The Spring Boot Actuator provides production-ready features to help monitor and manage your application. You can access the Actuator endpoints as follows:

Base Endpoint:
URL: http://localhost:8081/actuator
Description: This endpoint lists all available Actuator endpoints for the application.
Health Endpoint:
URL: http://localhost:8081/actuator/health
Description: This endpoint provides the health status of the application (e.g., UP or DOWN) in a JSON format.
Example JSON Response:


```
{
"status": "UP",
"components": {
"diskSpace": { "status": "UP", "details": { "total": 107374182400, "free": 53687091200, "threshold": 10485760 } },
"ping": { "status": "UP" }
}
```

Location:
Files related to Actuator configuration can be found in the /files/actuator directory.
2. ER Diagram
   The Entity-Relationship (ER) diagram visually represents the database schema for the hospital management system.

Location:
File: /files/er_diagram.png
Description: This PNG file illustrates the relationships between entities such as Appointment, Doctor, and Patient.
How to View:
Open the er_diagram.png file in an image viewer to explore the database structure.
3. OpenAPI Specifications
   The OpenAPI specifications document the REST API endpoints, including paths, methods, parameters, and responses, for the hospital management system.

Location:
Directory: /OpenApi Specifications
Description: Contains detailed API documentation in a machine-readable format (e.g., OpenAPI YAML or JSON files).
How to Use:
Access the files in the /OpenApi Specifications directory to generate API documentation or integrate with tools like Swagger UI.
Notes
Ensure the application is running (http://localhost:8081) to access the Actuator endpoints.
Update the file paths or URLs if the project structure or port changes.
For further details, refer to the project source code or consult the development team.