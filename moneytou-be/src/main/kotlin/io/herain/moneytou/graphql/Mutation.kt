package io.herain.moneytou.graphql

import com.coxautodev.graphql.tools.GraphQLMutationResolver
import java.util.*

class Mutation(private val postDao: PostDao) : GraphQLMutationResolver {
    fun writePost(title: String?, text: String?, category: String?, author: String?): Post {
        val post = Post()
        post.id = UUID.randomUUID().toString()
        post.title = title
        post.text = text
        post.category = category
        post.authorId = author
        postDao.savePost(post)
        return post
    }

}