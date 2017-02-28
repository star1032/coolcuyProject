package com.coolcuy.dao;

import java.sql.Connection;
import java.sql.SQLException;

import com.coolcuy.dto.CouponDto;

public interface CouponDao extends GenericDao<CouponDto> {
	public int updateImage(CouponDto object, Connection conn) throws SQLException;
	public int getCount(Connection conn) throws SQLException;
//	public int buyCoupon(String email, String couponNumber , Connection conn) throws SQLException;
	// member가 구매한 쿠폰을 get 한다.
//	public List<CouponDto> getBuyCoupon(String eamil, Connection conn) throws SQLException;
//	public int useCoupon(String email, String luckyNumber, Connection conn) throws SQLException;
//	public int countCoupon(Connection conn) throws SQLException;
//	
}
