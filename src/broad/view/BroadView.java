package broad.view;

import java.util.List;

import broad.util.SearchType;
import broad.vo.BroadVO;

public class BroadView {
	//프로그램 정보 보기
	public static void print(List<BroadVO> broadlist, SearchType type) {
		System.out.println("======================================");
		
		if (broadlist.size() == 0) {
			System.out.println(">>>>>>>>>> 조회 결과가 없습니다.<<<<<<<<");
			return;
		}
		System.out.println(">>>>>>>>>>>>>>> 조회 결과 <<<<<<<<<<<<<<<");
		
		switch(type) {
		case TITLE:
		case BROADCASTER:
		case AIRTIME:
		case BROADDAY:
			printBroad(broadlist, type);
			break;
		default :
			printCele(broadlist, type);
			
		}
		
	}
	//인물로 조회
	private static void printCele(List<BroadVO> broadlist, SearchType type) {
		for (BroadVO broad : broadlist) {
			System.out.println("---------------------------------------");
			System.out.println("이름 : "+ broad.getName());
			if(!(type.equals(SearchType.GENDER)))
				System.out.println("성별 : "+ broad.getGender());
			System.out.println("출생: " + broad.getBirth() + " ("+broad.getAge()+"세)");
			if(!(type.equals(SearchType.APPEARANCE)))
				System.out.println("출연작 : " + broad.getTitle());
		}		
	}
	
	//방송으로 조회
	private static void printBroad(List<BroadVO> broadlist, SearchType type) {
		for (BroadVO broad : broadlist) {
			System.out.println("---------------------------------------");
			System.out.println("프로그램명 : "+ broad.getTitle());
			if(!(type.equals(SearchType.BROADCASTER)))
				System.out.println("방송사 : "+ broad.getBroadcaster());
			if(!(type.equals(SearchType.BROADDAY)))
				System.out.println("방영 요일 : "+ broad.getBroadday() + "요일 ");
			System.out.println("방영 시간 : "+ broad.getAirtime());
			System.out.println("프로그램 소개 : " + broad.getIntroduction());
			System.out.println("출연진 : " + broad.getName());
		}		
	}

	public static void print(String message) {
		System.out.println("[알림]" + message);
	}
}
