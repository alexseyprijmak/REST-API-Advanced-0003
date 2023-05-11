package com.epam.esm.user;

import com.epam.esm.tag.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {


//    @Transactional(readOnly = true)
//    @Query(
////            "SELECT t FROM "
////                    + "GiftCertificate g INNER JOIN g.tags t WHERE g.id IN " +
////                    "(SELECT o.giftCertificate.id FROM Order o "
////                    + "WHERE o.user.id IN (SELECT o.user.id FROM Order o GROUP BY o.user.id ORDER BY SUM(o.price) DESC)) "
////                    + "GROUP BY t.id ORDER BY COUNT(t.id) DESC"
//    )
//    Long mostPopularTagOfUserWithHighestCostOfAllOrders();

    @Query("" +
            "select e from Users e where e.salary > :salary")
    Tag findTheMostUsedUsersTag(@Param("salry") Long salary
//            ,
//                                Sort sort
    );

}
