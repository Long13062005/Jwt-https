package com.hunglevi.jwthttpscookie.repository;

import com.hunglevi.jwthttpscookie.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    // Tìm kiếm quyền theo tên
    Role findByName(String name);

    // Kiểm tra xem quyền đã bị xóa hay chưa
    boolean existsByNameAndDeletedFalse(String name);
}
