package com.epam.esm.giftCertificate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping(value = "/certificates")
public class GiftCertificateController {

    @Autowired
    private GiftCertificateService giftCertificateService;

    @GetMapping("/{id}")
    public GiftCertificate getGiftCertificateById(@PathVariable("id") Long giftCertificateId) {
        return giftCertificateService.getGiftCertificateById(giftCertificateId);
    }


    @GetMapping
    public List<GiftCertificate> findAll() {
        return giftCertificateService.findAllGiftCertificate();
    }


    @PostMapping("/add")
    public GiftCertificate addGiftCertificate(@RequestParam(name = "name") String name,
                                              @RequestParam(name = "description") String description,
                                              @RequestParam(name = "price") BigDecimal price,
                                              @RequestParam(name = "duration") Integer duration) {

        return giftCertificateService.addGiftCertificate(name, description, price, duration);
    }

    @PostMapping("/update")
    public GiftCertificate updateGiftCertificate(@RequestParam(name = "id") Long id,
                                                 @RequestParam(name = "name") String name,
                                                 @RequestParam(name = "description") String description,
                                                 @RequestParam(name = "price") BigDecimal price,
                                                 @RequestParam(name = "duration") Integer duration) {

        return giftCertificateService.updateGiftCertificate(id, name, description, price, duration);
    }

    @PostMapping("/addTag")

    public GiftCertificate addTagToGiftCertificate(@RequestParam(name = "certificate_id") Long giftCertificateId,
                                                   @RequestParam(name = "tag_id") Long tagId) {
        return giftCertificateService.addGiftCertificatesToTags(giftCertificateId, tagId);
    }

    @GetMapping("/getCertificatesByTagName")
    public List<GiftCertificate> getCertificatesByTagName(@RequestParam(name = "name") String name) {
        return giftCertificateService.getGiftCertificateByTagName(name);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteGiftCertificate(@PathVariable("id") Long certificateId) {
        giftCertificateService.deleteGiftCertificateById(certificateId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

