package com.epam.esm.tag;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {

    @Transactional(readOnly = true)
//    @Query(
//            "SELECT t FROM "
//            + "GiftCertificate g INNER JOIN g.tags t WHERE g.id IN (SELECT o.giftCertificate.id FROM Order o "
//            + "WHERE o.user.id IN (SELECT o.user.id FROM Order o GROUP BY o.user.id ORDER BY SUM(o.price) DESC)) "
//            + "GROUP BY t.id ORDER BY COUNT(t.id) DESC"
//    )
    Tag mostPopularTagOfUserWithHighestCostOfAllOrders();



}
