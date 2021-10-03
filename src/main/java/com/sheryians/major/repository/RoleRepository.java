package com.sheryians.major.repository;

import com.sheryians.major.model.Role;
import org.hibernate.event.service.spi.JpaBootstrapSensitive;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Integer> {


}
