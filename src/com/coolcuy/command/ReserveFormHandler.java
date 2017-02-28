package com.coolcuy.command;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ReserveFormHandler implements CommandHandler{
//	private SpotService spotService = new SpotServiceImpl();
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
//		try{
//			List<SpotNameDto> getSpotName = spotService.getAllSpotName();
//			
//			JSONArray array = new JSONArray();
//			
//			for(SpotNameDto spotName : getSpotName){
//				JSONObject obj = new JSONObject();
//				
//				obj.put("spotName", spotName.getSpotName());
//				obj.put("spotArea", spotName.getSpotArea());
//				
//				array.put(obj);
//			}
//				System.out.println(array);
//			
//			request.setAttribute("spotName", getSpotName);
//		}catch(RuntimeException e){
//			e.printStackTrace();
//		}
		
		return "member/reserveForm.jsp";
	}
	
}
