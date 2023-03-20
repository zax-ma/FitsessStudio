package com.example.productservice.service;

import com.example.productservice.dao.entity.IngredientEntity;
import com.example.productservice.dao.entity.ProductEntity;
import com.example.productservice.dao.entity.RecipeEntity;
import com.example.productservice.dao.repo.IRecipeRepository;
import com.example.productservice.dto.PageDTO;
import com.example.productservice.dto.recipe.IngredientDTO;
import com.example.productservice.dto.recipe.NewRecipeDTO;
import com.example.productservice.dto.recipe.RecipeDTO;
import com.example.productservice.service.api.IProductService;
import com.example.productservice.service.api.IRecipeService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class RecipeService implements IRecipeService {

    Converter<RecipeEntity, RecipeDTO> toDtoConverter;
    IRecipeRepository repository;

    IProductService productService;

    public RecipeService(Converter<RecipeEntity, RecipeDTO> toDtoConverter, IRecipeRepository repository, IProductService productService) {
        this.toDtoConverter = toDtoConverter;
        this.repository = repository;
        this.productService = productService;
    }

    @Override
    public void create(RecipeEntity recipe) {
        if(repository.existByTitle(recipe.getTitle())){
            throw new RuntimeException("Recipe is already created");
        }
            repository.save(recipe);
    }

    @Override
    public PageDTO<RecipeDTO> getPage(Pageable pageable) {

        Page<RecipeEntity> recipeEntityPage = repository.findAll(pageable);
        List<RecipeDTO> content = new ArrayList<>();

        for (RecipeEntity recipeEntity : recipeEntityPage){
            content.add(toDtoConverter.convert(recipeEntity));
        }
        return new PageDTO<>(
                recipeEntityPage.getNumber(),
                recipeEntityPage.getSize(),
                recipeEntityPage.getTotalPages(),
                recipeEntityPage.getTotalElements(),
                recipeEntityPage.isFirst(),
                recipeEntityPage.getNumberOfElements(),
                recipeEntityPage.isLast(),
                content);
    }

    @Override
    public void update(UUID uuid, Timestamp dtUpdate, NewRecipeDTO recipe) {
        RecipeEntity recipeUpdate = repository.findById(uuid)
                .orElseThrow(() -> new EntityNotFoundException("uuid"));

        if (Timestamp.valueOf(recipeUpdate.getDt_update()).equals(dtUpdate)){
            recipeUpdate.setTitle(recipe.getTitle());
            recipeUpdate.setComposition(productIngredients);
            List<IngredientEntity> ingredient = convertToProductIngredients(recipe.getComposition());
            repository.save(recipeUpdate);
        }
    }

    private List<IngredientEntity> convertToProduct(
            List<IngredientDTO> ingredients) {

        return ingredients.stream()
                .map(dto -> {
                    ProductEntity product = this.productService.getById(dto.getProduct());
                    IngredientEntity ingredient = new IngredientEntity();

                    ingredient.setProductEntity(product);
                    ingredient.setWeight(dto.getWeight());

                    return ingredient;
                })
                .collect(Collectors.toList());
    }
}
