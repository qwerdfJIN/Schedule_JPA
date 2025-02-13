package com.example.schedule.repository;

import com.example.schedule.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

//사용자 관련 DB Repository
public interface UserRepository extends JpaRepository<User, Long> {

    //이름으로 사용자 찾기
    Optional<User> findUserByName(String name);

    //이름으로 사용자 찾기 (없으면 예외 발생)
    default User findUserByNameOrElseThrow(String name) {
        return findUserByName(name).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist name = " + name));
    }

    //ID로 사용자 찾기 (없으면 에러 발생)
    default User findByIdOrElseThrow(Long id) {
        return findById(id).
                orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND, "Does not exist id = " + id
                        )
                );
    }

    //이메일로 사용자 찾기
    Optional<User> findByEmail(String email);

    //이메일로 사용자 찾기 (없으면 예외 발생)
    default User findByEmailOrElseThrow(String email) {
        return findByEmail(email).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist email = " + email)
        );
    }
}







