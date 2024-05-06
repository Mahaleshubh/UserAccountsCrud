package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.UserAccount;
@Service
public interface UserService {
	public String saveOrUpdateUserAcc(UserAccount userAcc);

    public List<UserAccount> getAllUserAccount();

    public UserAccount getUserAcc(Integer userId);

    public boolean deleteUserAcc(Integer userId);

    public boolean updateUserAccStatus(Integer userId,String status);

}
