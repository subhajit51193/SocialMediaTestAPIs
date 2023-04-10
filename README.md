
# Social Media API Test

Creating a simple social media platform consisting of a backend and analytics pages. The platform should support creating,
reading, updating, and deleting operations for user profiles and posts. In addition,
users should be able to "like" and "unlike" posts. The analytics pages should display
insights on user engagement and content popularity.


## Features

- Create User
- Create Post for User
- Update User
- Delete User
- Update Post
- Delete Post
- Like Post
- Unlike Post


## API Reference

- POST /users: Create a new user.
- GET /users/{id}: Retrieve a user by id.
- PUT /users/{id}: Update a user's name or bio by id.
- DELETE /users/{id}: Delete a user by id.
- GET /analytics/users: Retrieve the total number of users.
- GET /analytics/users/top-active: Retrieve the top 5 most active users, based on the number of posts.
- POST /posts: Create a new post. The request should include the user_id.
- GET /posts/{id}: Retrieve a post by id.
- PUT /posts/{id}: Update a post's content by id.
- DELETE /posts/{id}: Delete a post by id.
- POST /posts/{id}/like: Increment the like count of a post by id.
- POST /posts/{id}/unlike: Decrement the like count of a post by id.
- GET /analytics/posts: Retrieve the total number of posts.
- GET /analytics/posts/top-liked: Retrieve the top 5 most liked posts.


## Optimizations

NA


## Installation

After donwloading the file project can be run locally with swagger.
Install and run with following properties if want to run locally

```bash
#db specific properties
spring.datasource.url=jdbc:mysql://${DB_HOST:localhost}:${DB_PORT:3306}/${DB_NAME:demodb}
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.username=${DB_USERNAME:root}
spring.datasource.password=${DB_PASSWORD:sql@subhajit51193}

#ORM s/w specific properties
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```
http://localhost:8080/swagger-ui.html.
    
## Deployment

Project is deployed on Railway Cloud

```bash
  socialmediatestapis-production.up.railway.app
```


## Tech Stack

**Client:** Java,SpringBoot,MySQL,Swagger,Maven

**Server:** Tomcat

## Authors

- [@Subhajit Saha](https://github.com/subhajit51193)


## Feedback

If you have any feedback, please reach out to us at nnorth87@gmail.com


## 🔗 Links
[![portfolio](https://img.shields.io/badge/my_portfolio-000?style=for-the-badge&logo=ko-fi&logoColor=white)](https://subhajit51193.github.io/)
[![linkedin](https://img.shields.io/badge/linkedin-0A66C2?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/subhajit-saha-103110185/)
