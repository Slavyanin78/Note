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
}