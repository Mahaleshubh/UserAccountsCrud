package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entity.UserAccount;

import jakarta.transaction.Transactional;


public interface UserAccountRepo extends JpaRepository<UserAccount, Integer>{
	@Modifying
	@Transactional
	@Query("update UserAccount set activateSe=:status where userId=:userId")
	public void updateUserAccStatus(Integer userId, String status);

}
