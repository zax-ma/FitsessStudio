package com.example.productservice.service.api;
;
import com.example.productservice.dto.PageDTO;
import com.example.productservice.dto.recipe.NewRecipeDTO;
import com.example.productservice.dto.recipe.RecipeDTO;
import org.springframework.data.domain.Pageable;

import java.sql.Timestamp;
import java.util.UUID;


public interface IRecipeService {

    void create(NewRecipeDTO recipe);
    PageDTO<RecipeDTO> getPage(Pageable pageable);
    void update(UUID uuid, Timestamp dtUpdate, NewRecipeDTO recipe);

}