package com.epam.esm.tag;

import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;


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

    @GetMapping("/popular")
    public Tag findMostPopularTagOfUserWithHighestCostOfAllOrders(){
        return tagService.findMostPopularTagOfUserWithHighestCostOfAllOrders();
    }
}
