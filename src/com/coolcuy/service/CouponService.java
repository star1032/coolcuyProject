package com.coolcuy.service;

import com.coolcuy.dto.CouponDto;

public interface CouponService extends GenericService<CouponDto> {
	
	public CouponDto updateImage(CouponDto object);
	public int getCount();

}
