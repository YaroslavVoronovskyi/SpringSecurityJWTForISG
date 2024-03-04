Run the application (it should run on 8080), and open Postman, you should use Java 17.

Hit the following API: http://localhost:8080/api/v1/auth/signup. 
Remember to include the required request body. 
Afterwards, examine the response: a successful registration 
message along with the user token will be returned.

Example RequestBody:
{
"firstName": "Yaroslav",
"lastName": "Voronovskyi",
"email": "yaroslav.voronovskyi@gmail.com",
"password": "12345"
}

Example Response:

{
"token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ5YXJvc2xhdi52b3Jvbm92c2t5aUBnbWFpbC5jb20iLCJpYXQiOjE3MDk1NDU3MzQsImV4cCI6MTcwOTU0NzE3NH0.eoIyqtemIa0ImE-7sbwl3mrLmSmrl42m_V3qGc2uYQo"
}

Hit the Sign-In API URL: http://localhost:8080/api/v1/auth/signin, 
ensuring that the request body contains an invalid password. 
Subsequently, examine the response: the authentication process will fail, 
and the HTTP status code will be 403.

Example RequestBody:
{
"email": "yaroslav.voronovskyi@gmail.com",
"password": "1234"
}

Example Response:

403 Forbidden

Hit the Sign-In API UR again, this time including a valid password in the request body. 
Proceed to examine the response: 
the authentication process will be successful, and the user token will be returned.

Example RequestBody:
{
"email": "yaroslav.voronovskyi@gmail.com",
"password": "12345"
}

{
"token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ5YXJvc2xhdi52b3Jvbm92c2t5aUBnbWFpbC5jb20iLCJpYXQiOjE3MDk1NDU3NjgsImV4cCI6MTcwOTU0NzIwOH0.O8f74JKabSVwLapa-p7Uh3lGwXeVPnx70DnR-CsPaO4"
}

Hit this http://localhost:8080/api/v1/resource without including the required authorization. 
As this API is protected, the resource cannot be accessed without a token. 
Verify the response for further details.

Copy the user token generated during the sign-up process and include it as an authorization header 
(Bearer Token type). Send another request to the Resource API URL and examine the response: 
we should now have successful access to the desired resource. 

Example Token: "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ5YXJvc2xhdi52b3Jvbm92c2t5aUBnbWFpbC5jb20iLCJpYXQiOjE3MDk1NDU3NjgsImV4cCI6MTcwOTU0NzIwOH0.O8f74JKabSVwLapa-p7Uh3lGwXeVPnx70DnR-CsPaO4"
Example Response: "Here is your resource"