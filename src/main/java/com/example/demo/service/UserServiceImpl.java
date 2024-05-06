package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.UserAccount;
import com.example.demo.repo.UserAccountRepo;
@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserAccountRepo userRepo;

	@Override
	public String saveOrUpdateUserAcc(UserAccount userAcc) {
		Integer user_ID = userAcc.getUserId();
		if(user_ID==null) {
			userAcc.setActivateSe("Y");
		}
		userRepo.save(userAcc);
		if(user_ID==null) {
			return "user saved";
		}else {
			return "user updated";
		}
	}

	@Override
	public List<UserAccount> getAllUserAccount() {
		//List<UserAccount> findAll = userRepo.findAll();
		
		return userRepo.findAll() ;
	}

	@Override
	public UserAccount getUserAcc(Integer userId) {
		/*
		 * Optional<UserAccount> findById = userRepo.findById(userId);
		 * if(findById.isPresent()) { findById.get(); }return null;
		 */
		return userRepo.findById(userId).orElse(null);	
	}

	@Override
	public boolean deleteUserAcc(Integer userId) {
		if(userRepo.existsById(userId)) {
			userRepo.deleteById(userId);
			return true;
		}else {
		return false;
		}
	}

	@Override
	public boolean updateUserAccStatus(Integer userId, String status) {
		try {
			userRepo.updateUserAccStatus(userId, status);
			return true;
		}catch(Exception e){
			e.printStackTrace();
		}
		return false;
	}

}
