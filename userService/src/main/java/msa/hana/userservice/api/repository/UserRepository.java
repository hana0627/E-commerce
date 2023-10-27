package msa.hana.userservice.api.repository;

import msa.hana.userservice.api.domain.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
public interface UserRepository extends JpaRepository<UserAccount, Long> {
}
