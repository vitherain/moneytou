package io.herain.moneytou.graphql

import kotlin.streams.toList

class PostDao(posts: List<Post>) {
    private val posts: List<Post>
    fun getRecentPosts(count: Int, offset: Int): List<Post> {
        return posts.stream()
                .skip(offset.toLong())
                .limit(count.toLong())
                .toList()
    }

    fun getAuthorPosts(author: String): List<Post> {
        return posts.stream()
                .filter { post: Post -> author == post.id }
                .toList()
    }

    fun savePost(post: Post) {
    }

    init {
        this.posts = posts
    }
}
