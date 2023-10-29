package msa.hana.userservice.api.repository;

import msa.hana.userservice.api.domain.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserAccount, Long> {
    Optional<UserAccount> findByUserId(String userId);
}
