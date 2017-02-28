package com.coolcuy.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.coolcuy.charge.ChargeCalculator;
import com.coolcuy.charge.EventCalculator;
import com.coolcuy.dao.EventDao;
import com.coolcuy.dao.EventDaoImpl;
import com.coolcuy.dao.MemberDao;
import com.coolcuy.dao.MemberDaoImpl;
import com.coolcuy.dao.PriceDao;
import com.coolcuy.dao.PriceDaoImpl;
import com.coolcuy.dao.RentDao;
import com.coolcuy.dao.RentDaoImpl;
import com.coolcuy.dto.CarDto;
import com.coolcuy.dto.EventDto;
import com.coolcuy.dto.PriceDto;
import com.coolcuy.dto.RentDto;
import com.coolcuy.exception.NotSupportedException;
import com.coolcuy.jdbc.connection.ConnectionProvider;
import com.coolcuy.util.JdbcUtil;

public class RentServiceImpl implements RentService{
	private RentDao dao = new RentDaoImpl();
		
	@Override
	public int add(RentDto object) {
		Connection conn = null;
		int x = -1;
		
		try {
			conn = ConnectionProvider.getConnection();
			x = dao.add(object, conn);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally{
			JdbcUtil.close(conn);
		}
		
		return x;
	}

	@Override
	public int delete(int element) {
		Connection conn = null;
		int x = -1;
		
		try {
			conn = ConnectionProvider.getConnection();
			x = dao.delete(element, conn);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}finally{
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
			throw new RuntimeException(e);
		}finally{
			JdbcUtil.close(conn);
		}
		
		return x;
	}

	@Override
	public RentDto get(int element) {
		Connection conn = null;
		RentDto getRent = null;
		
		try {
			conn = ConnectionProvider.getConnection();
			getRent = dao.get(element, conn);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}finally{
			JdbcUtil.close(conn);
		}
		
		return getRent;
	}

	@Override
	public List<RentDto> getAll() {
		Connection conn = null;
		List<RentDto> getRents = null;
		
		try {
			conn = ConnectionProvider.getConnection();
			getRents = dao.getAll(conn);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}finally{
			JdbcUtil.close(conn);
		}
		
		return getRents;
	}
	
	@Override
	public List<CarDto> getRentAbleCar(String spot, String type, String startDate, String endDate, String email) {
		Connection conn = null;
		List<CarDto> getCars = null;
		MemberDao memberDao = null;
		PriceDao priceDao = null;
		EventDao eventDao = null;
		
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			// �젋�듃 媛��뒫�븳 �옄�룞李⑤�� 遺덈윭�삩�떎.
			getCars = dao.getRentAbleCar(spot, startDate, endDate, type, conn);
			
			// �듃�옖�옲�뀡 泥섎━瑜� �쐞�빐 Dao�뱾�쓣 �뿬湲곗꽌 �꽑�뼵/�깮�꽦�븯���떎. 由ы럺�넗留� �슂留�..
			priceDao = new PriceDaoImpl();
			// �슂湲� �뜲�씠�꽣瑜� 遺덈윭�삩�떎.
			List<PriceDto> getPrice = priceDao.getAll(conn);
						
			// 留대쾭�쓽 �벑湲됱쓣 遺덈윭�삩�떎.
			memberDao = new MemberDaoImpl();
			int rating = memberDao.getRating(email, conn);
			
			// �슂湲� 怨꾩궛�쓣 �쐞�븳 珥덇린�솕 Map<CarName, PriceDto> List濡� 媛��졇�룄 �긽愿��뾾吏�留�.. Map�궗�슜 �빐遊�..
			Map<String, PriceDto> priceMap = new HashMap<String, PriceDto>();
			for(PriceDto list : getPrice){
				priceMap.put(list.getCarName(), list);
			}
			
			// �슂湲� 怨꾩궛 Object
			ChargeCalculator chargeCal = new ChargeCalculator(priceMap, getCars, rating, startDate, endDate);
			chargeCal.operator();
			
			// �씠踰ㅽ듃 �뿰�궛.
			eventDao = new EventDaoImpl();
			List<EventDto> eventList = eventDao.getByCondition(spot, startDate, endDate, conn);
			if(eventList.size() != 0){
				EventCalculator eventCal = new EventCalculator();				
				eventCal.operator(eventList, getCars);
			}
			
			conn.commit();
			System.out.println("서비스오키");
			return getCars;
		} catch (SQLException e) {
			try {conn.rollback();} catch (SQLException e1) {e1.printStackTrace();}
			e.printStackTrace();
			throw new RuntimeException(e);
		}finally{
			JdbcUtil.close(conn);
		}
	}
		
	@Override
	public RentDto update(RentDto object) {
		throw new NotSupportedException();
	}
}
