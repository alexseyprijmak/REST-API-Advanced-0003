package com.epam.esm.tag;

import com.epam.esm.user.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping(value = "/tags")
public class TagController {
    private final TagService tagService;

    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @GetMapping
    public List<Tag> findAll() {
        return tagService.findAll();
    }

    @GetMapping("/{tagId}")
    public Tag findById(@PathVariable("tagId") Long tagId) {
        return tagService.findById(tagId);
    }

    @PostMapping("/add/{name}")
    public Tag addTag(@PathVariable("name") String name) {
        return tagService.addTag(name);
    }

    @DeleteMapping("/{tagId}")
    public ResponseEntity<HttpStatus> deleteTag(@PathVariable("tagId") Long tagId) {
        tagService.deleteTagById(tagId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //



}
