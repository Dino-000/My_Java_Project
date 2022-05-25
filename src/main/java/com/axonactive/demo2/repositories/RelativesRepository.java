package com.axonactive.demo2.repositories;

import com.axonactive.demo2.entities.Relatives;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RelativesRepository extends JpaRepository<Relatives,Integer> {
    List<Relatives> findByRelationship(String relationship);
    List<Relatives> findByFullNameContaining(String containingWord);
    List<Relatives> findByGender(Integer gender);
    List<Relatives> findByGenderAndRelationship(Integer gender,String relationship);
    List<Relatives> findByPhoneNumberContaining(String containingNumber);
    List<Relatives> findByRelationshipNot(String relationship);
    List<Relatives> findByPhoneNumberEndingWithAndRelationship(String containingNumber,String relationship);
    List<Relatives> findByFullNameStartingWithAndGender(String wordContaining, Integer gender);
    List<Relatives> findByIdNot(Integer id);
    List<Relatives> findByRelationshipNotAndGenderNot(String relationship,Integer gender);
    List<Relatives> findByEmployeeId(Integer empId);
}
