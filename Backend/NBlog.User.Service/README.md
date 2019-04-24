# User microservice

<!-- toc -->
- [Functionalities](#functionalities)
- [API routes](#api-routes)
    * [/user](#user)
<!-- tocstop -->

## Functionalities

This microservice will provide:

* Authentication and authorization for users
* User-related CRUD operations

## API routes

All routes listed below, except rating, follow the same pattern:
/route
* GET: gets all entries
* POST: creates an entry
/route/id
* GET: gets entry with provided ID
* PUT: updates entry with provided ID
* DELETE: deletes entry with provided ID

### /user

Sample object for POST/PUT:

```
{
	"username": "johndoe",
	"password": "johndoespassword",
	"email": "mock@mail.com"
}
```
