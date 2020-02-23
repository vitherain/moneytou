package io.herain.moneytou.graphql

import com.coxautodev.graphql.tools.GraphQLResolver


class AuthorResolver(private val postDao: PostDao) : GraphQLResolver<Author?> {
    fun getPosts(author: Author): List<Post> {
        return postDao.getAuthorPosts(author.id!!)
    }
}
