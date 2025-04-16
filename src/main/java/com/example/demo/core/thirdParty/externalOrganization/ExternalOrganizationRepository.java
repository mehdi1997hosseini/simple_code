package com.example.demo.core.thirdParty.externalOrganization;

import com.example.demo.core.repository.BasicRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExternalOrganizationRepository extends BasicRepository<ExternalOrganizationEntity,String> {
}
