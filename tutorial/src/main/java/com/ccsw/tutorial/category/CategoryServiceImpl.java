package com.ccsw.tutorial.category;


import com.ccsw.tutorial.category.model.Category;
import com.ccsw.tutorial.category.model.CategoryDto;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ccsw
 *
 */
@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryRepository categoryRepository;



    /**
     * {@inheritDoc}
     */
    @Override
    public Category get(Long id) {

          return this.categoryRepository.findById(id).orElse(null);
    }




    /**
     * {@inheritDoc}
     */
    @Override
    public List<Category> findAll() {

          return (List<Category>) this.categoryRepository.findAll();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(Long id, CategoryDto dto) {

          Category category;

          if (id == null) {
             category = new Category();
          } else {
             //category = this.categoryRepository.findById(id).orElse(null);
             category = this.get(id);
          }

          category.setName(dto.getName());

          this.categoryRepository.save(category);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(Long id) throws Exception {

          //if(this.categoryRepository.findById(id).orElse(null) == null){
            if(this.get(id) == null){
             throw new Exception("Not exists");
          }

          this.categoryRepository.deleteById(id);
    }

}










/* 
/**
 * @author ccsw
 *
 */
/* 
@Service
public class CategoryServiceImpl implements CategoryService {

    private long SEQUENCE = 1;
    private Map<Long, CategoryDto> categories = new HashMap<Long, CategoryDto>();

    /**
     * {@inheritDoc}
     */

/* 
    public List<CategoryDto> findAll() {

        return new ArrayList<CategoryDto>(this.categories.values());
    }

    /**
     * {@inheritDoc}
     */

/* 
    public void save(Long id, CategoryDto dto) {

        CategoryDto category;

        if (id == null) {
            category = new CategoryDto();
            category.setId(this.SEQUENCE++);
            this.categories.put(category.getId(), category);
        } else {
            category = this.categories.get(id);
        }

        category.setName(dto.getName());
    }

    /**
     * {@inheritDoc}
     */

/*
    public void delete(Long id) {

        this.categories.remove(id);
    }

}
    */
