# Social Media Platform API

This is a backend API for a social media platform built with Spring Boot and PostgreSQL.

The idea is simple — authors can sign up and create posts. You can't create a post without an author existing first.

---

## How to run it

You need PostgreSQL running on your machine. Create a database called `socialmedia`, then update the `application.properties` file with your database username and password.



It starts on port 8080 cause it is the one i am using ,so port will depend.

---

## What was built

### Authors

- Create a new author
- Retrieve all authors
- Update an author
- Delete an author

### Posts

- Create a post
- Retrieve posts dependingly
- Delete a post

---

## Validation

Authors need a full name with at least 5 characters, a unique username, and a valid email. Posts need a title between 10 and 100 characters and content between 50 and 2000 characters.
