package io.herain.moneytou.app.graphql

import com.coxautodev.graphql.tools.GraphQLResolver
import java.util.*

class PostResolver(authorDao: AuthorDao) : GraphQLResolver<Post?> {
    private val authorDao: AuthorDao
    fun getAuthor(post: Post): Optional<Author> {
        return authorDao.getAuthor(post.authorId!!)
    }

    init {
        this.authorDao = authorDao
    }
}
