package com.wildcodeschool.festivalorleansjoue.services;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import com.wildcodeschool.festivalorleansjoue.entity.Address;
import com.wildcodeschool.festivalorleansjoue.entity.Society;
import com.wildcodeschool.festivalorleansjoue.entity.User;
import com.wildcodeschool.festivalorleansjoue.repository.AddressRepository;
import com.wildcodeschool.festivalorleansjoue.repository.SocietyRepository;
import com.wildcodeschool.festivalorleansjoue.repository.UserRepository;
import com.wildcodeschool.festivalorleansjoue.repository.UserRoleRepository;


@Repository
public class UserService implements IUserService, UserDetailsService{

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserRoleRepository userRoleRepository;
	@Autowired
	private SocietyRepository societyRepository;
	@Autowired
	private AddressRepository addressRepository;
	
	
	@Override
	public User returnConnectedUser() {
		User connectedUser = userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
		if (connectedUser == null) {
			return new User();
		}
		if (connectedUser.getProfilePicture() == null || connectedUser.getProfilePicture().contentEquals("")) {
			connectedUser.setProfilePicture("pictures/default_avatar.jpg");
		}
		return connectedUser;
	}


	public User createEmptyUser() {
		
		return new User();
	}


	@Override
	public UserDetails loadUserByUsername(String email) {		
		
		User user = userRepository.findByEmail(email);
		if (user == null) {
            throw new UsernameNotFoundException(email);
        }
        return user;
	}

	
	@Override
	public void registrateUser(User user, String userRole) {
		
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setUserRole(userRoleRepository.findByWording(userRole));
		userRepository.save(user);
	}

	
	@Override
	public User findByEmail(String email) {
		
		return userRepository.findByEmail(email);
	}


	@Override
	public void saveUser(User user) {
		
		User connectedUser = returnConnectedUser();
		Address userAddress = user.getAddress();
		Society userSociety;
		String role = "VOLUNTEER, PROTOZONE";
		if (!role.contains(connectedUser.getUserRole().getWording())) {
			user.getAddress().setWording(user.getSociety().getName()); 
			userSociety = user.getSociety();
			if (checkIfAddressExist(userAddress)) {
				userAddress = updateAddress(userAddress);
			} else { 
				userAddress = addressRepository.save(userAddress);
			}			
			user.getSociety().setAddress(userAddress);
			if (checkIfSocietyExist(userSociety)) {
				userSociety = updateSociety(userSociety);
			} else {
				userSociety = societyRepository.save(userSociety);
			}
			connectedUser.setSociety(userSociety);
		}
		connectedUser.setAddress(userAddress);
		connectedUser.setFirstname(user.getFirstname());
		connectedUser.setLastname(user.getLastname());
		connectedUser.setEmail(user.getEmail());
		if (user.getPassword().length() >= 4) {
			connectedUser.setPassword(passwordEncoder.encode(user.getPassword()));
		}			
		if (user.getProfilePicture() !=null)
			connectedUser.setProfilePicture(user.getProfilePicture());
		
		connectedUser.setCompletedUserInfos(true);
		userRepository.save(connectedUser);
	}
	
	public void updateUser(User user) {
		if (user.getId() != null) {
			//Update User
			User originalUser = userRepository.findById(user.getId()).get();					
			if (user.getProfilePicture() != null)
				originalUser.setProfilePicture(user.getProfilePicture());
			if (user.getFirstname()!= null)
				originalUser.setFirstname(user.getFirstname());
			if (user.getLastname() != null)
				originalUser.setLastname(user.getLastname());
			if (user.getPassword() != null && user.getPassword().length() >= 8)
				originalUser.setPassword(passwordEncoder.encode(user.getPassword()));
			originalUser.setAccountActive(user.isAccountActive());
			if (!originalUser.isCompletedUserInfos())
				originalUser.setCompletedUserInfos(user.isCompletedUserInfos());
			
			//Update Address from User
			Address address = user.getAddress();
			if (address != null) {
				if (originalUser.getAddress() == null)
					originalUser.setAddress(new Address());
				Address originalAddress = originalUser.getAddress();
				if (address.getPhoneNumber1() != null)
					originalAddress.setPhoneNumber1(address.getPhoneNumber1());					
			}
			
			
			String userWithSociety = "EDITOR SHOP";
			if (userWithSociety.contains(originalUser.getUserRole().getWording())) {
				
				//Update Society From User
				Society society = user.getSociety();
				if (society != null) {
					if (originalUser.getSociety() == null)
						originalUser.setSociety(new Society());						
					Society originalSociety = originalUser.getSociety();
					if (society.getName() != null)
						originalSociety.setName(society.getName());
					if (society.getSiret() != null)
						originalSociety.setSiret(society.getSiret());
					if (society.getTva() != null)
						originalSociety.setTva(society.getTva());
					
					//Update Address from Society from User
					if (society.getAddress() != null) {
						Address societyAddress = society.getAddress();
						if (originalSociety.getAddress() == null)
							originalSociety.setAddress(new Address());
						Address originalSocietyAddress = originalSociety.getAddress();
						if (societyAddress.getAddress1() != null)
							originalSocietyAddress.setAddress1(societyAddress.getAddress1());
						if (societyAddress.getPostalCode() != null)
							originalSocietyAddress.setPostalCode(societyAddress.getPostalCode());
						if (societyAddress.getCity() != null)
							originalSocietyAddress.setCity(societyAddress.getCity());
						if (societyAddress.getContactEmail() != null)
							originalSocietyAddress.setContactEmail(societyAddress.getContactEmail());
						if (societyAddress.getPhoneNumber2() != null)
							originalSocietyAddress.setPhoneNumber2(societyAddress.getPhoneNumber2());						
					}				
				}				
			}			
			userRepository.save(originalUser);
		}
	}
	
	
	public boolean checkIfAddressExist(Address address) {
		
		Address searchedAddress = addressRepository.findByWording(address.getWording());
		if (searchedAddress != null) {
			return true;
		}
		return false;
	}
	
	
	public boolean checkIfSocietyExist(Society society) {
		
		Society searchedSociety = societyRepository.findByName(society.getName());
		if (searchedSociety != null) {
			return true;
		}
		return false;
	}
	
	
	public Address updateAddress(Address address) {
		
		Address oldAddress = addressRepository.findByWording(address.getWording());
		address.setId(oldAddress.getId());
		return addressRepository.save(address);
	}
	
	
	public Society updateSociety(Society society) {
		
		Society oldSociety = societyRepository.findByName(society.getName());
		society.setId(oldSociety.getId());
		return societyRepository.save(society);
	}
  
	
	public List<User> findAllUsers() {
		
		return userRepository.findAll();
	}
	
	
	public List<User> findByRole(String roleWording) {
		
		return userRepository.findByUserRole(userRoleRepository.findByWording(roleWording));
	}
	
	public String userType() {
		return null;
	}


	public User findByName(String firstname) {
		userRepository.findByFirstname(firstname);
		return null;
	}
}
