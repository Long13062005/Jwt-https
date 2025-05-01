package com.hunglevi.jwthttpscookie.repository;

import com.hunglevi.jwthttpscookie.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    // Tìm kiếm người dùng theo tên đăng nhập
    User findByUsername(String username);

    // Kiểm tra xem người dùng đã bị xóa hay chưa
    boolean existsByUsernameAndDeletedFalse(String username);
}
