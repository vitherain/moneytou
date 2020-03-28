package io.herain.moneytou.test.repository

import io.herain.moneytou.shared.domain.IdentifiedEntity
import org.springframework.data.repository.CrudRepository
import java.util.Collections
import java.util.HashMap
import java.util.Optional
import java.util.UUID
import kotlin.streams.toList

abstract class InMemoryCrudRepository<T : IdentifiedEntity>(
    existingData: List<T>?
) : CrudRepository<T, UUID> {

    protected val db: MutableMap<UUID, T> = HashMap()

    init {
        existingData?.forEach { db.put(it.id, it) }
    }

    override fun <S : T> save(entity: S): S {
        db[entity.id] = entity
        return entity
    }

    override fun <S : T> saveAll(entities: Iterable<S>): Iterable<S> {
        return entities.toList()
            .map { s: S -> save(s) }
            .toList()
    }

    override fun findById(id: UUID): Optional<T> {
        return Optional.ofNullable(db[id])
    }

    override fun existsById(id: UUID): Boolean {
        return db.values.stream()
            .anyMatch { t: T -> t.id == id }
    }

    override fun findAll(): Iterable<T> {
        return Collections.unmodifiableCollection(db.values)
    }

    override fun findAllById(ids: Iterable<UUID>): Iterable<T> {
        return db.values.stream()
            .filter { t: T -> ids.toList().contains(t.id) }
            .toList()
    }

    override fun count(): Long {
        return db.values.size.toLong()
    }

    override fun deleteById(id: UUID) {
        db.remove(id)
    }

    override fun delete(entity: T) {
        db.remove(entity.id, entity)
    }

    override fun deleteAll(entities: Iterable<T>) {
        entities.toList().forEach { t: T -> delete(t) }
    }

    override fun deleteAll() {
        db.clear()
    }
}
