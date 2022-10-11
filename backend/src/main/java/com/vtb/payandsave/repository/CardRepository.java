package com.vtb.payandsave.repository;

import com.vtb.payandsave.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Card, Long> {

}
