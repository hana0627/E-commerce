package msa.hana.userservice.api.domain;


import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String userId;
    @Column(nullable = false, length = 50, unique = true)
    private String email;
    @Column(nullable = false, length = 50)
    private String name;
    @Column(nullable = false)
    private String encryptedPassword;


    @Builder
    public UserAccount(String userId, String email, String name, String encryptedPassword) {
        this.userId = userId;
        this.email = email;
        this.name = name;
        this.encryptedPassword = encryptedPassword;
    }

}
