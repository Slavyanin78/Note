import org.junit.Test

import org.junit.Assert.*
import ru.netology.NoteService

class NoteServiceTest {
    @Test
    fun testCreate() {
        val noteService = NoteService<String>()
        noteService.create(1, "Первая заметка")
        assertEquals("NoteService(notes=[Note(id=1, content=Первая заметка, deleted=false)])", noteService.toString())
    }

    @Test
    fun testRead() {
        val noteService = NoteService<String>()
        noteService.create(1, "Первая заметка")
        val readNote = noteService.read(1)
        assertNotNull(readNote)
        assertEquals("Note(id=1, content=Первая заметка, deleted=false)", readNote.toString())
    }

    @Test
    fun testUpdate() {
        val noteService = NoteService<String>()
        noteService.create(1, "Первая заметка")
        noteService.update(1, "Обновление первой заметки")
        val updatedNote = noteService.read(1)
        assertNotNull(updatedNote)
        assertEquals("Note(id=1, content=Обновление первой заметки, deleted=false)", updatedNote.toString())
    }

    @Test
    fun testDelete() {
        val noteService = NoteService<String>()
        noteService.create(1, "Первая заметка")
        noteService.delete(1)
        assertNull(noteService.read(1))
    }

    @Test
    fun testRestore() {
        val noteService = NoteService<String>()
        noteService.create(1, "Первая заметка")
        noteService.delete(1)
        noteService.restore(1)
        val restoredNote = noteService.read(1)
        assertNotNull(restoredNote)
        assertEquals("Note(id=1, content=Первая заметка, deleted=false)", restoredNote.toString())
    }

    @Test
    fun addComment() {
        val noteService = NoteService<String>()
        noteService.create(1, "Первая заметка")
        noteService.addComment(1, 1, "Первый комментарий к первой заметке")
        noteService.addComment(1, 2, "Второй комментарий к первой заметке")

        val note = noteService.read(1)
        assertNotNull(note)
        assertEquals(2, note?.comments?.size)
    }

    @Test
    fun editComment() {
        val noteService = NoteService<String>()
        noteService.create(1, "Первая заметка")
        noteService.addComment(1, 1, "Первый комментарий к первой заметке")

        noteService.editComment(1, 1, "Редактированный первый комментарий к первой заметке")

        val note = noteService.read(1)
        assertNotNull(note)
        assertEquals("Редактированный первый комментарий к первой заметке", note?.comments?.get(0)?.text)
    }

    @Test
    fun deleteComment() {
        val noteService = NoteService<String>()
        noteService.create(1, "Первая заметка")
        noteService.addComment(1, 1, "Первый комментарий к первой заметке")
        noteService.addComment(1, 2, "Второй комментарий к первой заметке")

        noteService.deleteComment(1, 2)

        val note = noteService.read(1)
        assertNotNull(note)
        assertEquals(1, note?.comments?.size)
    }

    @Test
    fun restoreComment() {
        val noteService = NoteService<String>()
        noteService.create(1, "Первая заметка")
        noteService.addComment(1, 1, "Первый комментарий к первой заметке")
        val commentId = 2
        noteService.addComment(1, commentId, "Второй комментарий к первой заметке")
        noteService.deleteComment(1, commentId)

        noteService.restoreComment(1, commentId)

        val note = noteService.read(1)
        assertNotNull(note)
        assertEquals(false, note?.comments?.get(0)?.deleted)
    }
}