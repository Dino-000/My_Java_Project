package com.axonactive.demo2.services.impl;

import com.axonactive.demo2.entities.Relatives;
import com.axonactive.demo2.exceptions.ResourceNotFoundException;
import com.axonactive.demo2.repositories.RelativesRepository;
import com.axonactive.demo2.services.RelativesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RelativesServiceImpl implements RelativesService {
    private final RelativesRepository relativesRepository;

    @Override
    public List<Relatives> getAll() {
        return relativesRepository.findAll();
    }

    @Override
    public void addRelatives(Relatives relatives) {
        relativesRepository.save(relatives);
    }

    @Override
    public void deleteRelatives(Integer id) {
        relativesRepository.deleteById(id);
    }

    @Override
    public Optional<Relatives> findRelativesById(Integer id) {
        return relativesRepository.findById(id);
    }

    @Override
    public void updateRelatives(Integer id, Relatives updateDetail) throws ResourceNotFoundException {
        Relatives updateRelatives = findRelativesById(id).orElseThrow(()-> new ResourceNotFoundException("Can not found that Relatives"));
        updateRelatives.setEmployee(updateDetail.getEmployee());
        updateRelatives.setRelationship(updateDetail.getRelationship());
        updateRelatives.setFullName(updateDetail.getFullName());
        updateRelatives.setGender(updateDetail.getGender());
        updateRelatives.setPhoneNumber(updateDetail.getPhoneNumber());
        relativesRepository.save(updateRelatives);
    }

    @Override
    public List<Relatives> findByRelationShip(String relationship) {
        return relativesRepository.findByRelationship(relationship);
    }

    @Override
    public List<Relatives> findByFullNameContaining(String containingWord) {
        return relativesRepository.findByFullNameContaining(containingWord);
    }

    @Override
    public List<Relatives> findByGender(Integer gender) {
        return relativesRepository.findByGender(gender);
    }

    @Override
    public List<Relatives> findByGenderAndRelationship(Integer gender, String relationship) {
        return relativesRepository.findByGenderAndRelationship(gender,relationship);
    }

    @Override
    public List<Relatives> findByPhoneNumberContaining(String containingNumber) {
        return relativesRepository.findByPhoneNumberContaining(containingNumber);
    }

    @Override
    public List<Relatives> findByRelationshipNot(String relationship) {
        return relativesRepository.findByRelationshipNot(relationship);
    }

    @Override
    public List<Relatives> findByPhoneNumberEndingWithAndRelationship(String containingNumber, String relationship) {
        return relativesRepository.findByPhoneNumberEndingWithAndRelationship(containingNumber,relationship);
    }

    @Override
    public List<Relatives> findByFullNameStartingWithAndGender(String wordContaining, Integer gender) {
        return relativesRepository.findByFullNameStartingWithAndGender(wordContaining,gender);
    }

    @Override
    public List<Relatives> findByIdNot(Integer id) {
        return relativesRepository.findByIdNot(id);
    }

    @Override
    public List<Relatives> findByRelationshipNotAndGenderNot(String relationship, Integer gender) {
        return relativesRepository.findByRelationshipNotAndGenderNot(relationship,gender);
    }

    @Override
    public List<Relatives> findByEmployeeId(Integer empId) {
        return relativesRepository.findByEmployeeId(empId);
    }
}
