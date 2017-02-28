package com.coolcuy.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.coolcuy.dao.CouponDao;
import com.coolcuy.dao.CouponDaoImpl;
import com.coolcuy.dto.CouponDto;
import com.coolcuy.jdbc.connection.ConnectionProvider;
import com.coolcuy.util.JdbcUtil;

public class CouponServiceImpl implements CouponService {
	
	CouponDao dao = new CouponDaoImpl();

	@Override
	public int add(CouponDto object) {
		Connection conn = null;
		int x = -1;
		
		try{
			conn = ConnectionProvider.getConnection();
			x = dao.add(object, conn);
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}finally {
			JdbcUtil.close(conn);
		}
		
		return x;
	}

	@Override
	public int delete(String element) {
		Connection conn = null;
		int x = -1;
		
		try {
			conn = ConnectionProvider.getConnection();
			x = dao.delete(element, conn);
			
		} catch (SQLException e) {
			throw new RuntimeException();
		} finally {
			JdbcUtil.close(conn);
		} 
			
		return x;
	}

	@Override
	public int deleteAll() {
		Connection conn = null;
		int x = -1;
		
		try {
			conn = ConnectionProvider.getConnection();
			x = dao.deleteAll(conn);
		} catch (SQLException e) {
			throw new RuntimeException();
		} finally {
			JdbcUtil.close(conn);
		}

		return x;
	}

	@Override
	public CouponDto update(CouponDto object) {
		String couponNumber = object.getCouponNumber();
		
		Connection conn = null;
		CouponDto updatedCoupon = null;
		int x = -1;
		
		CouponDto getCoupon = get(couponNumber);
		
		try {
			conn = ConnectionProvider.getConnection();
			
			if(getCoupon != null){
				x = dao.update(object, conn);
				
				if(x == 1)
					updatedCoupon = get(couponNumber);
			}
			
		} catch (SQLException e) {
			throw new RuntimeException();
		} finally {
			JdbcUtil.close(conn);
		}
		
		return updatedCoupon;
	}

	@Override
	public CouponDto get(String element) {
		Connection conn = null;
		CouponDto getCoupon = null;
		
		try{
			conn = ConnectionProvider.getConnection();
			
			getCoupon = dao.get(element, conn);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn);
		}
				
		return getCoupon;
	}

	@Override
	public List<CouponDto> getAll() {
		Connection conn = null;
		List<CouponDto> getCoupons = null;
		
		try {
			conn = ConnectionProvider.getConnection();
			getCoupons = dao.getAll(conn);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn);
		}
		
		return getCoupons;
	}

	@Override
	public CouponDto updateImage(CouponDto object) {
		String couponNumber = object.getCouponNumber();
		
		Connection conn = null;
		CouponDto updateImage = null;
		int x = -1;
		
		CouponDto getCoupon = get(couponNumber);
		
		try{
			conn = ConnectionProvider.getConnection();
			
			if(getCoupon != null){
				x = dao.updateImage(object, conn);
				
				if(x == 1)
					updateImage = get(couponNumber);
			}
			
		} catch (SQLException e) {
			throw new RuntimeException();
		} finally {
			JdbcUtil.close(conn);
		}
		
		return updateImage;
	}

	@Override
	public int getCount() {
		Connection conn = null;
		
		int x = -1;
		
		try{
			conn = ConnectionProvider.getConnection();
			
			x = dao.getCount(conn);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn);
		}
		
		return x;
	}

}
