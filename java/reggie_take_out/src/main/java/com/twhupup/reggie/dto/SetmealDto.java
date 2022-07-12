package com.twhupup.reggie.dto;

import com.twhupup.reggie.entity.Setmeal;
import com.twhupup.reggie.entity.SetmealDish;
import lombok.Data;

import java.util.List;

@Data
public class SetmealDto extends Setmeal {

    private List<SetmealDish> setmealDishes;

    private String categoryName;
}
