package com.test_module_04.repository;

import com.test_module_04.model.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface ICityRepository extends JpaRepository<City, Long> {
    void deleteAllByCountry_Id(Long id);
}
