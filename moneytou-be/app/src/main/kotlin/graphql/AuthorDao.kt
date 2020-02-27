package io.herain.moneytou.app.graphql

import java.util.*

class AuthorDao(authors: List<Author>) {
    private val authors: List<Author>

    fun getAuthor(id: String): Optional<Author> {
        return authors.stream()
                .filter { author: Author -> id == author.id }
                .findFirst()
    }

    init {
        this.authors = authors
    }
}
