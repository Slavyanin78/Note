package ru.netology

class Note<T>(var id: Int, var content: T, var deleted: Boolean = false, var comments: MutableList<Comment> = mutableListOf()) {
    override fun toString(): String {
        return "Note(id=$id, content=$content, deleted=$deleted, comments=$comments)"
    }
}

data class Comment(val id: Int, var text: String, var deleted: Boolean = false)

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

    fun addComment(noteId: Int, commentId: Int, text: String) {
        val note = notes.find { it.id == noteId && !it.deleted }
        note?.comments?.add(Comment(commentId, text))
    }

    fun editComment(noteId: Int, commentId: Int, newText: String) {
        val note = notes.find { it.id == noteId && !it.deleted }
        val comment = note?.comments?.find { it.id == commentId }
        comment?.text = newText
    }

    fun deleteComment(noteId: Int, commentId: Int) {
        val note = notes.find { it.id == noteId && !it.deleted }
        val comment = note?.comments?.find { it.id == commentId }
        note?.comments?.remove(comment)
    }

    fun restoreComment(noteId: Int, commentId: Int) {
        val note = notes.find { it.id == noteId && !it.deleted }
        val comment = note?.comments?.find { it.id == commentId }
        comment?.deleted = false
    }

    override fun toString(): String {
        return "NoteService(notes=$notes)"
    }
}

fun main() {
    val noteService = NoteService<String>()

    noteService.create(1, "Первая заметка")
    noteService.create(2, "Вторая заметка")

    println(noteService.read(1))

    noteService.update(1, "Обновлённая первая заметка")

    println(noteService.read(1))

    noteService.delete(2)

    println(noteService.read(2))

    noteService.restore(2)

    println(noteService.read(2))

    // Добавляем комментарии
    noteService.addComment(1, 1, "Первый комментарий к первой заметке")
    noteService.addComment(1, 2, "Второй комментарий к первой заметке")

    // Редактируем комментарий
    noteService.editComment(1, 1, "Изменённый первый комментарий к первой заметке")

    // Удаляем комментарий
    noteService.deleteComment(1, 2)

    // Восстанавливаем комментарий
    noteService.restoreComment(1, 2)

    println(noteService.read(1))
}