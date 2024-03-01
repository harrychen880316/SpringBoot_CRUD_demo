package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.bean.FoodInputBo;
import com.example.demo.bean.FoodOutputBo;
import com.example.demo.bean.FoodRelayDto;
import com.example.demo.bean.UserInputVO;
import com.example.demo.service.CRUDService;

@RestController
public class CRUDController {
	@Autowired
	private CRUDService Service;

	@PostMapping("/food/addFood")
	public String addFood(@RequestBody UserInputVO foodDto) {
		FoodInputBo foodInputBo = new FoodInputBo();
		foodInputBo.setFoodType(foodDto.getFoodType());
		foodInputBo.setName(foodDto.getName());
		foodInputBo.setPrice(foodDto.getPrice());
		return ("add:" + Service.add(foodInputBo));
	}

	@GetMapping("/food/chooseFood")
	public FoodOutputBo chooseFood(@RequestBody UserInputVO foodDto) {
		FoodInputBo foodInputBo = new FoodInputBo();
		foodInputBo.setFoodType(foodDto.getFoodType());
		FoodOutputBo foodOutputBo = Service.getFood(foodInputBo);
		return foodOutputBo;
	}

	@GetMapping("/food/allFood")
	public List<FoodRelayDto> allFood() {
		List<FoodRelayDto> list = new ArrayList<>();
		List<FoodOutputBo> outputList = Service.getAllFood();
		for (int i = 0; i < outputList.size(); i++) {
			FoodRelayDto foodRelayDto = new FoodRelayDto();
			foodRelayDto.setFoodType(outputList.get(i).getFoodType());
			foodRelayDto.setName(outputList.get(i).getName());
			foodRelayDto.setPrice(outputList.get(i).getPrice());
			list.add(foodRelayDto);
		}
		return list;
	}

	@PutMapping("/food/updateFood")
	public String updateFood(@RequestBody UserInputVO foodDto) {
		FoodInputBo foodInputBo = new FoodInputBo();
		foodInputBo.setFoodType(foodDto.getFoodType());
		foodInputBo.setName(foodDto.getName());
		foodInputBo.setPrice(foodDto.getPrice());
		return ("update:" + Service.update(foodInputBo));
	}

	@DeleteMapping("/food/deleteFood")
	public String deleteFood(@RequestBody UserInputVO foodDto) {
		FoodInputBo foodInputBo = new FoodInputBo();
		foodInputBo.setFoodType(foodDto.getFoodType());
		return ("delete:" + Service.delete(foodInputBo));
	}

}
