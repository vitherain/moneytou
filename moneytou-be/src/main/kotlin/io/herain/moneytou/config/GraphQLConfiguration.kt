package io.herain.moneytou.config

import io.herain.moneytou.graphql.*
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.util.ArrayList

@Configuration
class GraphqlConfiguration {
    @Bean
    fun postDao(): PostDao {
        val posts: MutableList<Post> = ArrayList()
        for (postId in 0..9) {
            for (authorId in 0..9) {
                val post = Post()
                post.id = "Post$authorId$postId"
                post.title = "Post $authorId:$postId"
                post.text = "Post $postId + by author $authorId"
                post.authorId = "Author$authorId"
                posts.add(post)
            }
        }
        return PostDao(posts)
    }

    @Bean
    fun authorDao(): AuthorDao {
        val authors: MutableList<Author> = ArrayList()
        for (authorId in 0..9) {
            val author = Author()
            author.id = "Author$authorId"
            author.name = "Author $authorId"
            author.thumbnail = "http://example.com/authors/$authorId"
            authors.add(author)
        }
        return AuthorDao(authors)
    }

    @Bean
    fun postResolver(authorDao: AuthorDao): PostResolver {
        return PostResolver(authorDao)
    }

    @Bean
    fun authorResolver(postDao: PostDao): AuthorResolver {
        return AuthorResolver(postDao)
    }

    @Bean
    fun query(postDao: PostDao): Query {
        return Query(postDao)
    }

    @Bean
    fun mutation(postDao: PostDao): Mutation {
        return Mutation(postDao)
    }
}
