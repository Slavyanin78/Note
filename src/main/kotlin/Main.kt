package ru.netology



class Note<T>(var id: Int, var content: T, var deleted: Boolean = false) {
    override fun toString(): String {
        return "Note(id=$id, content=$content, deleted=$deleted)"
    }
}

class NoteService<T> {
    private val notes = mutableListOf<Note<T>>()

    fun create(id: Int, content: T) {
        notes.add(Note(id, content))
    }

    fun read(id: Int): Note<T>? {
        return notes.find { it.id == id && !it.deleted }
    }

    fun update(id: Int, newContent: T) {
        val note = notes.find { it.id == id && !it.deleted }
        note?.content = newContent
    }

    fun delete(id: Int) {
        val note = notes.find { it.id == id && !it.deleted }
        note?.deleted = true
    }

    fun restore(id: Int) {
        val note = notes.find { it.id == id && it.deleted }
        note?.deleted = false
    }

    override fun toString(): String {
        return "NoteService(notes=$notes)"
    }
}

fun main() {
    val noteService = NoteService<String>()

    noteService.create(1, "First note")
    noteService.create(2, "Second note")

    println(noteService.read(1))

    noteService.update(1, "Updated first note")

    println(noteService.read(1))

    noteService.delete(2)

    println(noteService.read(2))

    noteService.restore(2)

    println(noteService.read(2))
}
