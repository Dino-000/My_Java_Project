package com.axonactive.demo2.services;

import com.axonactive.demo2.entities.Relatives;
import com.axonactive.demo2.exceptions.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;

public interface RelativesService {
    List<Relatives> getAll();

    void addRelatives(Relatives relatives);

    void deleteRelatives(Integer id);

    Optional<Relatives> findRelativesById(Integer id);
    void updateRelatives( Integer id, Relatives relatives) throws ResourceNotFoundException;

    List<Relatives> findByRelationShip(String relationship);

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
