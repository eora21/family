package com.example.family.repository;

import com.example.family.config.RootConfig;
import com.example.family.config.WebConfig;
import com.example.family.domain.family_relationship.repository.FamilyRelationshipRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.transaction.Transactional;

@Transactional
@WebAppConfiguration
@ExtendWith(SpringExtension.class)
@ContextHierarchy(
        @ContextConfiguration(classes = {
                RootConfig.class,
                WebConfig.class
        }))
class FamilyFamilyRelationshipRepositoryTestCode {
    @Autowired
    private FamilyRelationshipRepository repository;

    @Test
    @DisplayName("모든 관계 조회")
    void findAll() throws Exception {
        repository.findAll().forEach(System.out::println);
    }
}