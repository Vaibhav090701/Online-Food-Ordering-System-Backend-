package com.foodie.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodie.DTO.RestaurentDTO;
import com.foodie.Model.Address;
import com.foodie.Model.Restaurent;
import com.foodie.Model.User;
import com.foodie.Repository.AddressRepository;
import com.foodie.Repository.RestaurentRepository;
import com.foodie.Repository.UserRepository;
import com.foodie.request.CreateRestaurantRequest;

@Service
public class RestaurentServiceImp implements RestaurentService {
	
	@Autowired
	private RestaurentRepository restaurentRepository;
	
	@Autowired
	private AddressRepository addressRepository;

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public Restaurent createRestaurent(CreateRestaurantRequest req, User user) {
		
		Address address=addressRepository.save(req.getAddress());
		
		Restaurent restaurent=new Restaurent();
		restaurent.setAddress(address);
		restaurent.setContactInformation(req.getContactInformation());
		restaurent.setCuisineType(req.getCuisineType());
		restaurent.setDescription(req.getDescription());
		restaurent.setImages(req.getImages());
		restaurent.setName(req.getName());
		restaurent.setOpeningHours(req.getOpeningHours());
		restaurent.setRegistrationDate(LocalDateTime.now());
		restaurent.setUser(user);
		
		return restaurentRepository.save(restaurent);
	}

	@Override
	public Restaurent updateRestaurent(long restaurentId, CreateRestaurantRequest updateRestaurent) throws Exception {
		
		Restaurent restaurent=findRestaurentById(restaurentId);
		if(restaurent.getCuisineType()!=null)
		{
			restaurent.setCuisineType(updateRestaurent.getCuisineType());
		}
		
		if(restaurent.getDescription()!=null)
		{
			restaurent.setDescription(updateRestaurent.getDescription());
		}
		
		if(restaurent.getName()!=null)
		{
			restaurent.setName(updateRestaurent.getName());
		}
		
		if(restaurent.getAddress()!=null)
		{
			restaurent.setAddress(updateRestaurent.getAddress());
		}

		return restaurentRepository.save(restaurent);
	}

	@Override
	public void deleteRestaurent(long restaurentId) throws Exception {
		
		Restaurent restaurent= findRestaurentById(restaurentId);

		restaurentRepository.delete(restaurent);
		
	}

	@Override
	public List<Restaurent> getAllRestaurent() {
		return restaurentRepository.findAll();
	}

	@Override
	public List<Restaurent> searchRestaurent(String keyword) {
		
		return restaurentRepository.findBySearchQuery(keyword);
	}

	@Override
	public Restaurent findRestaurentById(long id) throws Exception {
	  	Optional<Restaurent> opt= restaurentRepository.findById(id);
	  	
	  	if(opt.isEmpty())
	  	{
	  		throw new Exception("Restaurent not found with id "+id);
	  	}
		return opt.get();
	}

	@Override
	public Restaurent getRestaurentByUserId(long userId) throws Exception {
		Restaurent restaurent= restaurentRepository.findByUserId(userId);
		
		if(restaurent==null)
		{
			throw new Exception("Restaurent not found with user id "+userId);
		}
		
		return restaurent;
	}

	@Override
	public RestaurentDTO addFavourites(long restaurentId, User user) throws Exception {
		
		Restaurent restaurent= findRestaurentById(restaurentId);
		
		RestaurentDTO dto=new RestaurentDTO();
		dto.setId(restaurentId);
		dto.setTitle(restaurent.getName());
		dto.setDescription(restaurent.getDescription());
		dto.setImages(restaurent.getImages());
		
		//to add and once added then after clicking on same restaurent it has to remove from favourite list
		boolean isFavourite=false;
		List<RestaurentDTO> favourites=user.getFavourites();
		for(RestaurentDTO favourite : favourites)
		{
			if(favourite.getId().equals(restaurentId))
			{
				isFavourite=true;
				break;
			}
		}
		
		if(isFavourite)
		{
			favourites.removeIf(favourite -> favourite.getId().equals(restaurentId));
		}
		else {
			favourites.add(dto);
		}
		
		userRepository.save(user);
		return dto;
	}

	@Override
	public Restaurent updatesRestaurentStatus(long id) throws Exception {
		
		Restaurent restaurent= findRestaurentById(id);
		restaurent.setOpen(!restaurent.isOpen());
		
		return restaurentRepository.save(restaurent);
	}

}
