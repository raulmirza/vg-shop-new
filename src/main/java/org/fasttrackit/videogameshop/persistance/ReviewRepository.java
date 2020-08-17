package org.fasttrackit.videogameshop.persistance;

import org.fasttrackit.videogameshop.domain.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    Page<Review> findByProductId(long productId, Pageable pageable);
}
