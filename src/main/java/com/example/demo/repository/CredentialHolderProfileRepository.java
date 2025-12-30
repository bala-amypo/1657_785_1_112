
// package com.example.demo.repository;

// import com.example.demo.entity.CredentialHolderProfile;
// import org.springframework.data.jpa.repository.JpaRepository;

// public interface CredentialHolderProfileRepository
//         extends JpaRepository<CredentialHolderProfile, Long> {
// }
package com.example.demo.repository;

import com.example.demo.entity.CredentialHolderProfile;
import java.util.Optional;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface CredentialHolderProfileRepository {
    Optional<CredentialHolderProfile> findById(Long id);
    CredentialHolderProfile save(CredentialHolderProfile profile);
}
