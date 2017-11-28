# customerprofile
For Customer Profile homework I have developed a microservice using SpringBoot.

/api/profile api is exposed for CRUD operations.

Application Monitoring
- Actuator [I have enabled all endpoints] 

Caching
- Spring Caching is used

API Docs
- Swagger2 is used

Security
- I had started on JWT. API to receive JWT token and allow/deny customer operation based on the received token.

Further Work 
- Use Feign http client to communicate to CRM.
- Write consumer contract tests
- Implement Hystrix 
- Finish JWT



How to run :
1) Clone the repo. git clone --recursive https://github.com/swereddy/customerprofile.git
2) If on linux machine run the command ./gradlew bootRun
3) If on windows machinie run the commad gradlew bootRun

Swagger is exposed @ http://localhost:8080/swagger-ui.html

Actuator is exposed @ http://localhost:8080/private
