# Notes for those reviewing this project

## Process
I took an API design first approach to this project, this is the process I followed:

1. Used IntelliJ to create a SpringBoot project.
2. Created an Open API definition for the Widget API see "src/main/api-definition/widget-api.yml".
3. I used the code generation tools on the swagger site(https://editor.swagger.io/) to generate the API models and controller from the definition.
4. Created the API tests. 
5. Individually implemented the required changes to the API Controller functions to make the API tests work, creating the service, repository tests and repository functions as needed.

This is my first SpringBoot project so I focussed on learning the basics of Spring Boot and leveraging features supported by the framework.
 
I implemented a repository extending the CrudRepository interface as I planned to do the SQL implementation too and felt that would best be done by creating a drop in replacement for WidgetRepostitoryImpl

I have implemented all the requirements for the test and for the first complication.

I did not implement any security and minimal work on error handling. If this was a real application being deployed to production I would have secured it and implemented more complete and robust error handling. 