package com.example.readinglist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import java.util.List;

@Controller
@RequestMapping("/")
public class ReadingListController {

    private ReadingListRepository readingListRepository;

    @Autowired
    public  ReadingListController(ReadingListRepository readingListRepository){
        this.readingListRepository = readingListRepository;
    }


    @RequestMapping("/")
    public String hello()
    {
        return "HelloTest";
    }
    @RequestMapping(value = "/{reader}",method = RequestMethod.GET)
    public  String readersBooks(@PathVariable("reader") String reader, Model model){
        List<Book> readingList = readingListRepository.findByReader(reader);
        if(readingList != null){
            model.addAttribute("books", readingList);
            model.addAttribute("reader",reader);
        }
        return "readingList";
    }

    @RequestMapping(value="/{reader}", method=RequestMethod.POST) public String addToReadingList(
            @PathVariable("reader") String reader, Book book) {
        book.setReader(reader);
        readingListRepository.save(book);
        return "redirect:/{reader}";
    }

//    @RequestMapping(value = "/addUser")
//    public String addUser(){
//        Reader reader = new Reader();
//        reader.setFullname("kmruan");
//        reader.setPassword("wwww");
//        reader.setUsername("user");
//        return "readingList";
//    }

    @RequestMapping("/login")
    public String login(){
        return "login";
    }



}
