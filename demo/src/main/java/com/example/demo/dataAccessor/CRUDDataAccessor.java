package com.example.demo.dataAccessor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import com.example.demo.bean.FoodDo;
import com.example.demo.bean.FoodInputBo;

@Repository
public class CRUDDataAccessor {

	private String tableName = "TB_HW_FOOD";
	@Autowired
	private JdbcTemplate jdbcTemplate;

	/*
	 * Create
	 */
	public boolean addFood(FoodDo inputDo) {
		String sql = "INSERT INTO " + tableName + " (FOOD_TYPE, FOOD_NAME, FOOD_PRICE) \r\n VALUES (?,?,?)";
		try {
			return jdbcTemplate.update(sql, inputDo.getFoodType(), inputDo.getName(), inputDo.getPrice()) > 0;

		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
	}

	/*
	 * Read
	 */
	public FoodDo findByFoodType(FoodInputBo foodInputBo) {
		String foodType = foodInputBo.getFoodType();
		String sql = "SELECT * FROM " + tableName + " WHERE FOOD_TYPE = \'" + foodType + "\'";
		FoodDo foodDo = jdbcTemplate.query(sql, new ResultSetExtractor<FoodDo>() {
			@Override
			public FoodDo extractData(ResultSet resultSet) throws SQLException, DataAccessException {
				resultSet.next();
				String foodType = resultSet.getString("FOOD_TYPE");
				String name = resultSet.getString("FOOD_NAME");
				int price = resultSet.getInt("FOOD_PRICE");
				FoodDo foodDo = new FoodDo();
				foodDo.setFoodType(foodType);
				foodDo.setName(name);
				foodDo.setPrice(price);
				return foodDo;
			}
		});
		return foodDo;

	}

	public List<FoodDo> findAll() {
		String sql = "SELECT * FROM " + tableName;
		List<FoodDo> list = jdbcTemplate.query(sql, new ResultSetExtractor<List<FoodDo>>() {

			@Override
			public List<FoodDo> extractData(ResultSet resultSet) throws SQLException, DataAccessException {
				List<FoodDo> list = new ArrayList<>();
				while (resultSet.next()) {
					String foodType = resultSet.getString("FOOD_TYPE");
					String name = resultSet.getString("FOOD_NAME");
					int price = resultSet.getInt("FOOD_PRICE");
					FoodDo foodDo = new FoodDo();
					foodDo.setFoodType(foodType);
					foodDo.setName(name);
					foodDo.setPrice(price);
					list.add(foodDo);
				}
				return list;
			}
		});
		return list;
	}

	/*
	 * Update
	 */
	public boolean updateFood(FoodDo inputDo) {
		String sql = "UPDATE " + tableName + " SET FOOD_NAME = ? , FOOD_PRICE = ?\r\n WHERE FOOD_TYPE = ?";
		try {
			return jdbcTemplate.update(sql, inputDo.getName(), inputDo.getPrice(), inputDo.getFoodType()) > 0;

		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
	}

	/*
	 * Delete
	 */
	public boolean deleteFood(FoodDo inputDo) {
		String sql = "DELETE FROM \r\n" + tableName + " WHERE FOOD_TYPE = ?";
		try {
			return jdbcTemplate.update(sql, inputDo.getFoodType()) > 0;

		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
	}

}
