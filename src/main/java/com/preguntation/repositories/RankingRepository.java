package com.preguntation.repositories;

import com.preguntation.models.ranking;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RankingRepository extends CrudRepository<ranking, Long> {
}
