package com.todolist.backend;

import com.todolist.backend.persistence.entity.SysUserEntity;
import com.todolist.backend.persistence.jpa.SysUserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@RequiredArgsConstructor
@SpringBootApplication

public class BackendApplication {


    //    private final PasswordEncoder passwordEncoder;
    public static void main(String[] args) {

        SpringApplication.run(BackendApplication.class, args);

    }

//        @Bean
    public CommandLineRunner commandLineRunner(SysUserJpaRepository sysUserRepository) {
        return args -> {
            SysUserEntity user1 = new SysUserEntity();
            user1.setUsername("user1");
            user1.setPassword("123"); // Encode the password
            user1.setEmail("user1@test.com");
            user1.setBio("just a test user am i?");
            user1.setNumberOfTasks(0L);
            user1.setProfileImage(null);
            sysUserRepository.save(user1);

            SysUserEntity user2 = new SysUserEntity();
            user2.setUsername("user2");
            user2.setPassword("123"); // Encode the password
            user2.setEmail("user2@test.com");
            user2.setBio("just a test user am i?");
            user2.setNumberOfTasks(0L);
            user2.setProfileImage(null);
            sysUserRepository.save(user2);
        };
    }
}
