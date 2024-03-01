package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.bean.FoodDo;
import com.example.demo.bean.FoodInputBo;
import com.example.demo.bean.FoodOutputBo;
import com.example.demo.dataAccessor.CRUDDataAccessor;

@Service
public class CRUDService {

	@Autowired
	private CRUDDataAccessor dataAcc;

	public boolean add(FoodInputBo foodInputBo) {

		FoodDo foodDo = new FoodDo();
		foodDo.setFoodType(foodInputBo.getFoodType());
		foodDo.setName(foodInputBo.getName());
		foodDo.setPrice(foodInputBo.getPrice());

		return dataAcc.addFood(foodDo);
	}

	public FoodOutputBo getFood(FoodInputBo foodInputBo) {

		FoodOutputBo outputBo = new FoodOutputBo();

		FoodDo foodDo = dataAcc.findByFoodType(foodInputBo);
		outputBo.setFoodType(foodDo.getFoodType());
		outputBo.setName(foodDo.getName());
		outputBo.setPrice(foodDo.getPrice());

		return outputBo;
	}

	public List<FoodOutputBo> getAllFood() {

		List<FoodOutputBo> list = new ArrayList<>();
		List<FoodDo> outputList = dataAcc.findAll();
		for (int i = 0; i < outputList.size(); i++) {
			FoodOutputBo outputBo = new FoodOutputBo();
			outputBo.setFoodType(outputList.get(i).getFoodType());
			outputBo.setName(outputList.get(i).getName());
			outputBo.setPrice(outputList.get(i).getPrice());
			list.add(outputBo);
		}

		return list;
	}

	public boolean update(FoodInputBo foodInputBo) {
		FoodDo foodDo = new FoodDo();
		foodDo.setFoodType(foodInputBo.getFoodType());
		foodDo.setName(foodInputBo.getName());
		foodDo.setPrice(foodInputBo.getPrice());

		return dataAcc.updateFood(foodDo);
	}

	public boolean delete(FoodInputBo foodInputBo) {
		FoodDo foodDo = new FoodDo();
		foodDo.setFoodType(foodInputBo.getFoodType());

		return dataAcc.deleteFood(foodDo);
	}
}
