package com.epam.esm.tag;

import com.epam.esm.exeptions.NoSuchEntityException;
import com.epam.esm.giftCertificate.GiftCertificateRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TagService {
    private final TagRepository tagRepository;
    private final GiftCertificateRepository giftCertificateRepository;
    public TagService(TagRepository tagRepository, GiftCertificateRepository giftCertificateRepository) {
        this.tagRepository = tagRepository;
        this.giftCertificateRepository = giftCertificateRepository;
    }

    public Tag addTag(String name) {
        List<Tag> tags = tagRepository.findAll().stream()
                .filter(tagInBd -> name.equals(tagInBd.getName())).toList();

        if (tags.size() > 0) {
            throw new NoSuchEntityException(String.format("Tag have by name: %s already exists", name));
        } else {
            Tag tag = new Tag();
            tag.setName(name);
            return tagRepository.save(tag);
        }
    }

    public List<Tag> findAll() {
        return tagRepository.findAll();
    }

    public Tag findById(Long tagId) {
        Optional<Tag> tag = tagRepository.findById(tagId);
        if (tag.isPresent()) {
            return tag.get();
        } else {
            throw new NoSuchEntityException(String.format("Tag with id : %d wasn't found", tagId));
        }

    }

    public Tag findByName(String tagName) {
        Optional<Tag> product = tagRepository.findAll().stream().filter(tag -> tag.getName().equals(tagName)).findFirst();
        if (product.isPresent()) {
            return product.get();
        } else {
            throw new NoSuchEntityException(String.format("Tag with name : %d wasn't found", tagName));
        }

    }
    // TODO add delete in MtM connection
    public void deleteTagById(Long tagId) {
        Tag tag = tagRepository.getById(tagId);
        giftCertificateRepository.findAll().forEach(item -> item.getTags().remove(tag));
        tagRepository.delete(tagRepository.getById(tagId));
    }


    public Tag findMostPopularTagOfUserWithHighestCostOfAllOrders() {
        Tag  tag = tagRepository.mostPopularTagOfUserWithHighestCostOfAllOrders();
        return tag;
    }
}
