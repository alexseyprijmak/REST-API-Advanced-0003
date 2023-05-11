package com.epam.esm.giftCertificate;


import com.epam.esm.exeptions.NoSuchEntityException;
import com.epam.esm.tag.Tag;
import com.epam.esm.tag.TagRepository;


import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class GiftCertificateService {

    private final GiftCertificateRepository giftCertificateRepository;

    private final TagRepository tagRepository;

    public GiftCertificateService(GiftCertificateRepository giftCertificateRepository, TagRepository tagRepository) {
        this.giftCertificateRepository = giftCertificateRepository;
        this.tagRepository = tagRepository;
    }

    public GiftCertificate getGiftCertificateById(Long id) {
        Optional<GiftCertificate> giftCertificate = giftCertificateRepository.findById(id);
        if (giftCertificate.isPresent()) {
            return giftCertificate.get();
        } else {
            throw new NoSuchEntityException(String.format("GiftCertificate with id : %d wasn't found", id));
        }
    }


    public GiftCertificate addGiftCertificate(String name, String description, BigDecimal price, Integer duration) {

        List<GiftCertificate> giftCertificates = giftCertificateRepository.findAll().stream()
                .filter(giftCertificateInDb -> name.equals(giftCertificateInDb.getName()))
                .toList();
        // TODO what we do when we  already have this certificate ? Custom exe
        if (giftCertificates.size() > 0) {
            throw new NoSuchEntityException(String.format("Gift certificate with name : %s already exists", giftCertificates.get(0).getName()));
        } else {
            GiftCertificate giftCertificate = new GiftCertificate(
                    name, description, price, duration, LocalDateTime.now(), LocalDateTime.now()
            );
            return giftCertificateRepository.save(giftCertificate);
        }
    }

    public GiftCertificate addGiftCertificatesToTags(Long giftCertificateId, Long tagId) {

        List<GiftCertificate> giftCertificatesListWithIdFilter = giftCertificateRepository.findAll().stream()
                .filter(giftCertificateIDInDb -> giftCertificateId.equals(giftCertificateIDInDb.getId()))
                .toList();
        // TODO what we do when we  already have this certificate ? Custom exe
        if (giftCertificatesListWithIdFilter.size() == 0) {
            throw new NoSuchEntityException(String.format("Gift certificate with id : %s is not exists", giftCertificateId));
        } else {
            GiftCertificate giftCertificate = giftCertificateRepository.getById(giftCertificateId);
//            List<GiftCertificate> giftCertificatesListWithTagFilter = giftCertificateRepository.findAll().stream()
//                    .filter(giftCertificateIDInDb -> giftCertificateIDInDb.getTags().contains(tagRepository.getById(tagId)))
//                    .toList();

            List<Tag> tagListWithIdFilter = tagRepository.findAll().stream()
                    .filter(tagId::equals)
                    .toList();

//            if (
//                    g
//            iftCertificatesListWithTagFilter.size() == 0 ||
//                    tagListWithIdFilter.size() == 0) {
//                // TODO exeption? msg
//                throw new NoSuchEntityException(String.format("Tag  with id : %s is not exists", tagId));
//            } else {
            Set<Tag> tagSet = giftCertificate.getTags();
            tagSet.add(tagRepository.getById(tagId));
            giftCertificate.setTags(tagSet);

            return giftCertificateRepository.save(giftCertificate);
//            }
        }
    }


    public GiftCertificate updateGiftCertificate(Long id, String name, String description, BigDecimal price, Integer duration) {
        List<GiftCertificate> giftCertificates = giftCertificateRepository.findAll().stream()
                .filter(giftCertificateInDb -> id.equals(giftCertificateInDb.getId()))
                .toList();
        if (giftCertificates.size() > 0) {
            throw new RuntimeException(String.format("Gift certificate with id : %s already exists", giftCertificates.get(0).getName()));
        } else {
            GiftCertificate giftCertificate = giftCertificateRepository.getById(id);
            giftCertificate.setName(name);
            giftCertificate.setDescription(description);
            giftCertificate.setPrice(price);
            giftCertificate.setDuration(duration);
            giftCertificate.setLastUpdateDate(LocalDateTime.now());
            return giftCertificateRepository.save(giftCertificate);
        }

    }

    public List<GiftCertificate> getGiftCertificateByTagName(String tagName) {
        List<Tag> tagsList = tagRepository.findAll().stream().
                filter(tagInDB -> tagInDB.getName().equals(tagName)).toList();
        if (tagsList.size() == 0) {
            throw new RuntimeException(String.format("Tag with name : %s already exists", tagName));
        } else {
            Tag tag = tagsList.get(0);
            return giftCertificateRepository.findAll().stream().
                    filter(tagsSet -> tagsSet.getTags().contains(tag)).
                    toList();
        }

    }


    public List<GiftCertificate> findAllGiftCertificate() {
        return giftCertificateRepository.findAll();
    }


    public void deleteGiftCertificateById(Long giftCertificateId) {
        giftCertificateRepository.delete(giftCertificateRepository.getById(giftCertificateId));
    }

}
