package com.cailou.basicRegisterLoginAPI.repository;

import com.cailou.basicRegisterLoginAPI.model.RoleModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RoleRepository extends JpaRepository<RoleModel, UUID> {
}
