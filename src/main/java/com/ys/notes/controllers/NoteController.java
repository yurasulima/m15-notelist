package com.ys.notes.controllers;

import com.ys.notes.services.NoteService;
import com.ys.notes.models.Note;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;


@RequiredArgsConstructor
@Controller()
@RequestMapping("/note")
public class NoteController {


    @Autowired
    private final NoteService noteService;


    @GetMapping("/list")
    public ModelAndView list() {
        ModelAndView model = new ModelAndView("note/all");
        model.addObject("notes", noteService.listAll());
        return model;
    }

    @PostMapping(value = "/delete")
    public RedirectView delete(@RequestParam(name = "id") long id) {
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("/note/list");
        noteService.deleteById(id);
        return redirectView;
    }

    @GetMapping(value = "/edit")
    public String editPage(Model model, @RequestParam(name = "id") long id) {
        Note note = noteService.getById(id);
        model.addAttribute("note", note);
        return ("note/edit");
    }

    @PostMapping(value = "/edit")
    public RedirectView edit(@ModelAttribute Note note) {
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("/note/list");
        noteService.update(note);
        return redirectView;
    }


    //for tests
    @GetMapping("/add")
    public ModelAndView add() {
        Note testNote = new Note();
        testNote.setContent("Content");
        testNote.setTitle("Title");
        noteService.add(testNote);
        return new ModelAndView("test");
    }

}
