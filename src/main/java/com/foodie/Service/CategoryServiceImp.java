package com.foodie.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodie.Model.Category;
import com.foodie.Model.Restaurent;
import com.foodie.Repository.CategoryRepository;

@Service
public class CategoryServiceImp implements CategoryService {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private RestaurentService restaurentService;

	@Override
	public Category createCategory(String name, long userId) throws Exception {
		
		Restaurent restaurent= restaurentService.getRestaurentByUserId(userId);
		if(restaurent==null)
		{
			throw new Exception("Restaurent not found by id "+userId);
		}
		
		Category category=new Category();
		category.setName(name);
		category.setRestaurent(restaurent);
		
		return categoryRepository.save(category);
	}

	@Override
	public List<Category> findCategoryByRestaurentId(long id) throws Exception {
		Restaurent restaurent=restaurentService.getRestaurentByUserId(id);
		return categoryRepository.findByRestaurentId(restaurent.getId());
	}

	@Override
	public Category findCategoryById(long id) throws Exception {
		
		Optional<Category>optCategory= categoryRepository.findById(id);
		
		if(optCategory.isEmpty())
		{
			throw new Exception("Category not found by id "+id);
		}
		return optCategory.get();
	}

	@Override
	public void deleteCategory(long id) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Category updateCategory(long id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
