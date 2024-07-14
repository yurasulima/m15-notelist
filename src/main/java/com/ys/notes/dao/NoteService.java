package com.ys.notes.dao;

import com.ys.notes.dao.model.Note;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class NoteService {

    private final List<Note> notes = new ArrayList<>();


    public List<Note> listAll() {
        return notes;
    }

    public Note add(Note note){
        int id = notes.size() + 1;
        note.setId(id);
        notes.add(note);
        return note;
    }

    public void deleteById(long id) {
        for (int i = 0; i < notes.size(); i++) {
            if (notes.get(i).getId() == id) {
                notes.remove(i);
                return;
            }
        }
        throw new RuntimeException();
    }

    public void update(Note note) {
        for (int i = 0; i < notes.size(); i++) {
            if (notes.get(i).getId() == note.getId()) {
                notes.set(i, note);
                return;
            }
        }
        throw new RuntimeException();
    }

    public Note getById(long id) {
        for (Note note : notes) {
            if (note.getId() == id) {
                return note;
            }
        }
        throw new RuntimeException();
    }

}
