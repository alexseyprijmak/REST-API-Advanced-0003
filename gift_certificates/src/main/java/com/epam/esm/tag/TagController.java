package com.epam.esm.tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/tags")
public class TagController {
    @Autowired
    private TagService tagService;

    @GetMapping
    public List<Tag> findAll() {
        return tagService.findAll();
    }

    @GetMapping("/{tagId}")
    public Tag findById(@PathVariable("tagId") Integer tagId) {
        return tagService.findById(tagId);
    }

    @PostMapping("/add/{name}")
    public Tag addTag(@PathVariable("name") String name) {
          return tagService.addTag(name);
    }

    @DeleteMapping("/{tagId}")
    public ResponseEntity<HttpStatus> deleteTag(@PathVariable("tagId") Integer tagId) {
        tagService.deleteTagById(tagId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
