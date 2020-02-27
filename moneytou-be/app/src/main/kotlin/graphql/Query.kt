package io.herain.moneytou.app.graphql

import com.coxautodev.graphql.tools.GraphQLQueryResolver

class Query(postDao: PostDao) : GraphQLQueryResolver {
    private val postDao: PostDao
    fun recentPosts(count: Int, offset: Int): List<Post> {
        return postDao.getRecentPosts(count, offset)
    }

    init {
        this.postDao = postDao
    }
}
