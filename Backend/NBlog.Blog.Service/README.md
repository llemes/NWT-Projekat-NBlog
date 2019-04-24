# Blog microservice

<!-- toc -->
- [Functionalities](#functionalities)
- [API routes](#api-routes)
    * [/user](#user)
    * [/blogpost](#blogpost)
    * [/comment](#comment)
    * [/blogpostrating](#blogpostrating)
<!-- tocstop -->

## Functionalities

This microservice will provide:

* CRUD operations for blog posts
* Activity tracking on posts
* Post rating
* Integration with Imgur for image upload

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
	"username": "michaelbay",
	"password": "notmichaelbay",
	"email": "mock@mail.com"
}
```

### /blogpost

Sample object for POST/PUT:

```
{
    "content": "Lorem ipsum some content",
    "imageContent": "...",
    "author": {
        "userId": 1
    },
    "comments": [
        {
            "commentId": 5
        }
    ]
}
```

To specify an author, an existing user's id should be passed as a parameter. Comments are added in the same fashion.
**Important**: when creating a blog post, comments cannot be added automatically. This can only be achieved when updating an existing blog post.

### /comment

Sample object for POST/PUT: 

```
{
	"content": "this is a comment",
	"author": {
		"userId": 2
	}
}
```

To specify an author, an existing user's id should be passed as a parameter.

### /blogpostrating

Sample object for POST:

```
{
	"blogPost": {
		"blogPostId": 6
	},
	"user": {
		"userId": 3
	},
	"ratingOption": {
		"ratingCode": "UP"
	}
}
```

The parameters passed are as follows:
* `blogPost` specifies which post is being rated, by passing an object with an existing post's id
* `user` specifies which user is rating the post, by passing an object with the user's id.
* `ratingOption` specifies which type of rating is being submitted - UP for upvote or DWN for downvote

These options are used when calculating a blog post's rating. To get a post's rating, send a GET request to `/blogpostrating/id`, passing the blog post's id in the url.
