package com.coolcuy.calendar;

//Map of key    : CarNumber
//''     value : CarDto 
//CarDto    : Car and date[][][]
//date       : char type

//1. Dao Load Cars to DB by Server boot 
//2. 
public class MyCalendar {
	static int years = 10;			// 년
	static int months = 12;		// 월
	static int days = 31;			// 일
	int date[][][] = new int[years][months][days]; // 렌트 대여 date
	int MonthPerYears = years * months;	// 년당 개월 수
	
	int MonthCnt = 0;	// 월 카운트
	int initDayOfWeek = 6;	// 초기 월 요일
	
	boolean yundal = false;
	int firstDayOfWeek[] = new int[MonthPerYears];		// 그 달의 첫 요일
	
	public MyCalendar() {
		setUp();
	}
	
	enum DayOfWeek {
		SUN(0), MON(1), TUE(2), WED(3), THR(4), FRI(5), SAT(6);
		int num;

		DayOfWeek(int num) {
			this.num = num;
		}

		public static String vlaueOf(int num) throws IllegalAccessException {
			switch (num) {
				case 0:return "SUN";
				case 1:return "MON";
				case 2:return "TUE";
				case 3:return "WEB";
				case 4:return "THR";
				case 5:return "FRI";
				case 6:return "SAT";
				default:throw new IllegalAccessException();
			}
		} 

		public int getNum() {
			return this.num;
		}
	}
	
	public int getDayOfWeek(){
		return initDayOfWeek;
	}
	
	public int[][][] getDate() {
		return date;
	}
	
	

	public void setUp() {
		int year = -1;

		for (int i = 0; i < 10; i++) {
			year = i + 2000;

			for (int j = 0; j < 12; j++) {
				if (j == 1 && yunMonth(year)) {
					inputDate(i, j, 29);
				} else if (j == 1 && !(yunMonth(year))) {
					inputDate(i, j, 28);
				} else if (j == 0 || j == 2 || j == 4 || j == 6 || j == 7 || j == 9 || j == 11) {
					inputDate(i, j, 31);
				} else {
					inputDate(i, j, 30);
				}

				System.out.println("\n\n");
			}
		}
	}
	
	public static void main(String[] args) {
		MyCalendar cal = new MyCalendar();
		
		cal.setUp();
	}
	
	public void inputDate(int i, int j, int endDay) {
		int cnt = 0;
				
		for (int k = 0; k < endDay; k++) {
			date[i][j][k] = 0;
			cnt++;
		}
		
		firstDayOfWeek[++MonthCnt] = (initDayOfWeek + cnt) % 7;
	}

	public boolean yunMonth(int year) {
		if ((year % 4) == 0 && (year % 100) != 0 || (year % 400) == 0)
			return true;
		else
			return false;
	}

}
