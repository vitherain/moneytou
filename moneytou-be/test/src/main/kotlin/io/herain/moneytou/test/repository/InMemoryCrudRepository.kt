package io.herain.moneytou.test.repository

import io.herain.moneytou.shared.domain.IdentifiedEntity
import io.herain.moneytou.shared.repository.SimpleFetchingOperation
import java.util.Collections
import java.util.HashMap
import java.util.UUID
import kotlin.streams.toList

abstract class InMemoryCrudRepository<T : IdentifiedEntity>(
    existingData: List<T>?
) : SimpleFetchingOperation<T, UUID> {

    protected val db: MutableMap<UUID, T> = HashMap()

    init {
        existingData?.forEach { db.put(it.id, it) }
    }

    fun <S : T> save(entity: S): S {
        db[entity.id] = entity
        return entity
    }

    fun <S : T> saveAll(entities: Iterable<S>): Iterable<S> {
        return entities.toList()
            .map { s: S -> save(s) }
            .toList()
    }

    override fun findById(id: UUID): T? {
        return db[id]
    }

    fun existsById(id: UUID): Boolean {
        return db.values.stream()
            .anyMatch { t: T -> t.id == id }
    }

    fun findAll(): Iterable<T> {
        return Collections.unmodifiableCollection(db.values)
    }

    fun findAllById(ids: Iterable<UUID>): Iterable<T> {
        return db.values.stream()
            .filter { t: T -> ids.toList().contains(t.id) }
            .toList()
    }

    fun count(): Long {
        return db.values.size.toLong()
    }

    fun deleteById(id: UUID) {
        db.remove(id)
    }

    fun delete(entity: T) {
        db.remove(entity.id, entity)
    }

    fun deleteAll(entities: Iterable<T>) {
        entities.toList().forEach { t: T -> delete(t) }
    }

    fun deleteAll() {
        db.clear()
    }
}
