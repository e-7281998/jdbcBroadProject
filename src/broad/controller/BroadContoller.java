package broad.controller;

import java.util.Scanner;
import java.util.Stack;

import broad.model.BroadService;
import broad.util.SearchType;
import broad.view.BroadView;

public class BroadContoller {
	public static void main(String[] args) throws InterruptedException {
		System.out.println("---------------------------------------");
		System.out.println("              반갑습니다 ^^             ");
		System.out.println("        방송 정보 조회 프로그램입니다!      ");
		System.out.println("                - 은정 -               ");
		System.out.println("---------------------------------------\n");
		Thread.sleep(1500);

		AccessUser();

	}

	// 유저 선택
	private static void AccessUser() {
		Scanner sc = new Scanner(System.in);

		while (true) {
			System.out.println("======================================");
			System.out.println(">>>>>>>>>접속 형태를 선택하세요<<<<<<<<<<");
			System.out.println("======================================");
			System.out.println("\t 1.방송국 관계자로 접속하기");
			System.out.println("\t 2.시청자로 접속하기");
			System.out.println("\t 3.종료하기");
			System.out.print("=====> ");

			String num = sc.nextLine();
			switch (num) {
			case "1":
				AccessAdmin(sc);
				break;
			case "2":
				BroadcastSearch(sc);
				break;
			case "3":
				ExitProgram();
			default:
				break;
			}
		}
	}

	/* =========================== 관리자 =========================== */

	// 관리자 접속 - p2 - 실행할 작업 선택
	private static void AccessAdmin(Scanner sc) {

		while (true) {
			System.out.println("======================================");
			System.out.println(">>>>>>>>>진행할 업무를 선택하세요.<<<<<<<<<<");
			System.out.println("======================================");
			System.out.println("\t 1.신규 프로그램 제작하기");
			System.out.println("\t 2.기존 프로그램 정보 수정하기");
			System.out.println("\t 3.뒤로가기");
			System.out.println("\t 4.종료하기");
			System.out.print("=====> ");

			String num = sc.nextLine();
			switch (num) {
			case "1":
				IsertBroadcast(sc);
				break;
			case "2":
				ModifyInfo(sc);
				break;
			case "3":
				return;
			case "4":
				ExitProgram();
			default:
				System.out.println(">>>>>>>>>> 잘못된 입력 입니다. <<<<<<<<");
				break;
			}
		}
	}

	// 관리자 접속 - p2.1.1 - 신규 프로그램 등록
	private static void IsertBroadcast(Scanner sc) {
		BroadService bService = new BroadService();

		while (true) {
			System.out.println("======================================");
			System.out.println(">>>>>>>>>>방송국을 선택해주세요<<<<<<<<<<<<");
			System.out.println("======================================");
			System.out.println("\t 1.신규 방송국 프로그램");
			System.out.println("\t 2.기존 방송국 프로그램");
			System.out.println("\t 3.뒤로가기");
			System.out.println("\t 4.종료하기");
			System.out.print("=====> ");

			String num = sc.nextLine();
			switch (num) {
			case "1":
				InsertBroadcaster(sc);
				break;
			case "2":
				InsertBroad(sc, "");
				break;
			case "3":
				return;
			case "4":
				ExitProgram();
			default:
				System.out.println(">>>>>>>>>> 잘못된 입력 입니다. <<<<<<<<");
				break;
			}
		}
	}

	// 신규방송사 등록
	private static void InsertBroadcaster(Scanner sc) {
		BroadService bService = new BroadService();
		while (true) {
			System.out.println("======================================");
			System.out.println(">>>>>>>>>신규 방송국을 입력하세요.<<<<<<<<<");
			System.out.println("======================================");
			System.out.print("=====> ");
			String broadcaster = sc.nextLine();

			if (bService.insertBroadcaster(broadcaster)) {
				BroadView.print("신규 방송국 등록을 완료했습니다.");
				InsertBroad(sc, broadcaster);
				return;
			} else {
				BroadView.print("신규 방송국 등록을 실패했습니다.");
				System.out.println("\t 1.다시 시도하기");
				System.out.println("\t 2.신규 방송사 등록 끝내기");
				System.out.print("=====> ");
				if (sc.nextLine().equals("2"))
					break;
			}
		}

	}

	// 신규 프로그램 등록
	private static void InsertBroad(Scanner sc, String broadcaster) {
		BroadService bService = new BroadService();
		while (true) {
			Stack<String> st = new Stack<>();

			if (broadcaster.equals("")) {
				System.out.println("======================================");
				System.out.println(">>>>프로그램이 방송될 방송국을 입력하세요.<<<<");
				System.out.println("======================================");
				System.out.print("=====> ");
				broadcaster = sc.nextLine();
			}

			System.out.println("======================================");
			System.out.println(">>>>프로그램명을 입력하세요.<<<<");
			System.out.println("======================================");
			System.out.print("=====> ");
			String title = sc.nextLine();

			System.out.println("======================================");
			System.out.println(">>프로그램 방송 시간을 입력하세요.(ex: 16:30)<<");
			System.out.println("======================================");
			System.out.print("=====> ");
			String aritime = sc.nextLine();

			System.out.println("======================================");
			System.out.println(">>>프로그램 방송 요일을 입력하세요.(ex: 월)<<<");
			System.out.println("======================================");
			System.out.print("=====> ");
			String broadDay = sc.nextLine();

			System.out.println("======================================");
			System.out.println(">>프로그램 내용을 입력하세요. (최대 50자)<<");
			System.out.println("======================================");
			System.out.print("=====> ");
			String introduction = sc.nextLine();

			st.push(broadcaster);
			st.push(broadDay);
			st.push(introduction);
			st.push(title);
			st.push(aritime);
			st.push(broadcaster);

			if (bService.insertBroad(st)) {
				BroadView.print("신규 프로그램 제작을 완료했습니다.");
				InsertApperence(sc, title);
				return;
			} else {
				BroadView.print("신규 프로그램 제작을 실패했습니다.");
				System.out.println("\t 1.다시 제작하기");
				System.out.println("\t 2.신규 프로그램 제작 끝내기");
				System.out.print("=====> ");
				if (sc.nextLine().equals("2"))
					break;
			}
			broadcaster = "";
		}

	}

	// 출연자 추가
	private static void InsertApperence(Scanner sc, String title) {
		BroadService bService = new BroadService();
		Stack<String> st = new Stack<>();
		if (title.equals("")) {
			System.out.println("======================================");
			System.out.println(">>>>프로그램명을 입력하세요.<<<<");
			System.out.println("======================================");
			System.out.print("=====> ");
			title = sc.nextLine();
		}

		while (true) {
			System.out.println("======================================");
			System.out.println(">>>>출연진과 계약을 진행하세요.<<<<");
			System.out.println("======================================");
			System.out.println("\t 1.계약진행 그만하기");
			System.out.print("=====> ");
			String name = sc.nextLine();

			if (name.equals("1"))
				break;

			if (bService.insertApperence(title, name))
				BroadView.print(name+"님과 계약을 완료했습니다.");
			else
				BroadView.print(name+"님과 계약을 실패했습니다.");

		}

	}

	// 기존 프로그램 정보 수정하기 p2.2.1
	private static void ModifyInfo(Scanner sc) {
		BroadService bService = new BroadService();

		while (true) {
			System.out.println("======================================");
			System.out.println(">>>>진행할 업무를 선택하세요.<<<<");
			System.out.println("======================================");
			System.out.println("\t 1.새로운 출연자와 계약하기");
			System.out.println("\t 2.출연자와 계약 끝내기");
			System.out.println("\t 3.방송시간 수정하기");
			System.out.println("\t 4.방송요일 수정하기");
			System.out.println("\t 5.프로그램 소개 수정하기");
			System.out.println("\t 6.프로그램 폐지하기");
			System.out.println("\t 7.뒤로가기");
			System.out.println("\t 8.종료하기");
			System.out.print("=====> ");

			String num = sc.nextLine();
			switch (num) {
			case "1":
				InsertApperence(sc,"");
				break;
			case "2":
				DeleteApperence(sc);
				break;
			case "3":
				UpdateBroad(sc,SearchType.AIRTIME );
				break;
			case "4":
				UpdateBroad(sc,SearchType.BROADDAY );
				break;
			case "5":
				UpdateBroad(sc,SearchType.INTRODUCTIION );
				break;
			case "6":
				DeleteBroad(sc);
				break;
			case "7":
				return;
			case "8":
				ExitProgram();
			default:
				System.out.println(">>>>>>>>>> 잘못된 입력 입니다. <<<<<<<<");
				break;
			}
		}
	}
	
	
	//프로그램 정보 수정하기
	private static void UpdateBroad(Scanner sc, SearchType type) {
		BroadService bService = new BroadService();
		Stack<String> st = new Stack<>();
		String val = "";
		
		System.out.println("======================================");
		System.out.println(">>>>프로그램명을 입력하세요.<<<<");
		System.out.println("======================================");
		System.out.print("=====> ");
		String title = sc.nextLine();
		st.push(title);
		
		while(true) {
			switch(type) {
			case AIRTIME:
				System.out.println("======================================");
				System.out.println(">>수정할 방송 시간을 입력하세요.(ex: 16:30)<<");
				System.out.println("======================================");
				System.out.print("=====> ");
				val = sc.nextLine();
				st.push(val);
				st.push("airtime");
				break;
			case BROADDAY:
				System.out.println("======================================");
				System.out.println(">>>수정할 방송 요일을 입력하세요.(ex: 월)<<<");
				System.out.println("======================================");
				System.out.print("=====> ");
				val = sc.nextLine();
				st.push(val);
				st.push("broadDay");
				break;
			case INTRODUCTIION:
				System.out.println("======================================");
				System.out.println(">>수정할 프로그램 소개를 입력하세요. (최대 50자)<<");
				System.out.println("======================================");
				System.out.print("=====> ");
				val = sc.nextLine();
				st.push(val);
				st.push("introduction");
				break;
			}
			if(val.trim().equals("") && !type.equals(SearchType.INTRODUCTIION)) {
				st.pop();
				st.pop();
				System.out.println("*** 빈값으로 수정할 수 없습니다.");
			}
			else
				break;
		}
		
		
		if(bService.UpdateBroad(st)) 
			BroadView.print("프로그램 정보 수정을 완료했습니다.");
		else
			BroadView.print("프로그램 정보 수정을 실패했습니다.");
		
		
	}

	//출연자 삭제하기
	private static void DeleteApperence(Scanner sc) {
		BroadService bService = new BroadService();
		
		System.out.println("======================================");
		System.out.println(">>>>출연진과 계약을 해지할 프로그램을 입력하세요.<<<<");
		System.out.println("======================================");
		System.out.print("=====> ");
		String title = sc.nextLine();
		
		System.out.println("======================================");
		System.out.println(">"+title+" 프로그램에서 계약을 해지할 출연진을 입력하세요.<");
		System.out.println("======================================");
		System.out.print("=====> ");
		String name = sc.nextLine();
		
		System.out.println("======================================");
		System.out.println("["+title+"]에서 "+name+ "님과의 계약을 정말로 해지하시겠습니까?");
		System.out.println("======================================");
		System.out.println("\t 1.계속 진행하기");
		System.out.println("\t 2.중단하기");
		System.out.print("=====> ");

		if(sc.nextLine().equals("1")) {
			if(bService.deleteApperence(title, name)) 
				BroadView.print(name+"님과 계약을 해지했습니다.");
			else
				BroadView.print(name+"님과 계약 해지에 실패했습니다.");
		}
	}
	
	//프로그램 삭제하기 
		private static void DeleteBroad(Scanner sc) {
			BroadService bService = new BroadService();
			
			System.out.println("======================================");
			System.out.println(">>>>폐지할 프로그램을 입력하세요.<<<<");
			System.out.println("======================================");
			System.out.print("=====> ");
			String title = sc.nextLine();
			
			System.out.println("======================================");
			System.out.println("["+title+"]을 정말로 폐지하시겠습니까?");
			System.out.println("======================================");
			System.out.println("\t 1.계속 진행하기");
			System.out.println("\t 2.중단하기");
			System.out.print("=====> ");

			if(sc.nextLine().equals("1")) {
				if(bService.DeleteBroad(title)) 
					BroadView.print(title+" 프로그램이 폐지되었습니다.");
				else
					BroadView.print(title+" 프로그램 폐지에 실패했습니다.");
			}
				
			
		}

		
		
	/* =========================== 이용자 =========================== */

	// 이용자 접속 - p1 - 조회할 정보 선택
	public static void BroadcastSearch(Scanner sc) {
		while (true) {
			System.out.println("======================================");
			System.out.println(">>>>>>>>>조회할 정보를 선택하세요.<<<<<<<<<<");
			System.out.println("======================================");
			System.out.println("\t 1.프로그램 정보 조회");
			System.out.println("\t 2.연예인 정보 조회");
			System.out.println("\t 3.뒤로가기");
			System.out.println("\t 4.종료하기");
			System.out.print("=====> ");

			String num = sc.nextLine();
			switch (num) {
			case "1":
				BroadcastSearchT(sc);
				break;
			case "2":
				CelebritySearchT(sc);
				break;
			case "3":
				return;
			case "4":
				ExitProgram();
			default:
				System.out.println(">>>>>>>>>> 잘못된 입력 입니다. <<<<<<<<");
				break;
			}
		}
	}

	// 이용자 접속 - p1.1.1 - 프로그램 조회 방법 선택
	public static void BroadcastSearchT(Scanner sc) {
		BroadService bService = new BroadService();

		while (true) {
			System.out.println("======================================");
			System.out.println(">>>>>>>프로그램 조회 방법을 선택하세요.<<<<<<");
			System.out.println("======================================");
			System.out.println("\t 1.프로그램명으로 조회하기");
			System.out.println("\t 2.방송국으로 조회하기");
			System.out.println("\t 3.요일로 프로그램 조회하기");
			System.out.println("\t 4.모든 프로그램 조회하기");
			System.out.println("\t 5.뒤로가기");
			System.out.println("\t 6.종료하기");
			System.out.print("=====> ");

			String num = sc.nextLine();

			switch (num) {
			case "1":
				System.out.println("======================================");
				System.out.print("\t 조회할 프로그램 : ");
				String title = sc.nextLine();
				BroadView.print(bService.selectTitle(title), SearchType.TITLE);
				break;

			case "2":
				System.out.println("======================================");
				System.out.print("\t 조회할 방송국 : ");
				String broadcaster = sc.nextLine();
				BroadView.print(bService.selectBroadcaster(broadcaster), SearchType.BROADCASTER);
				break;

			case "3":
				System.out.println("======================================");
				System.out.print("\t 조회할 요일 : ");
				String broadday = sc.nextLine();
				BroadView.print(bService.selectBroadDay(broadday), SearchType.BROADDAY);
				break;

			case "4":
				BroadView.print(bService.selectTitle("%"), SearchType.TITLE);
				break;

			case "5":
				return;
			case "6":
				ExitProgram();
			default:
				System.out.println(">>>>>>>>>> 잘못된 입력 입니다. <<<<<<<<");
				break;
			}
		}
	}

	// 이용자 접속 - p1.1.2 - 인물 조회 방법 선택
	private static void CelebritySearchT(Scanner sc) {
		BroadService bService = new BroadService();

		while (true) {
			System.out.println("======================================");
			System.out.println(">>>>>>>>연예인 조회 방법을 선택하세요.<<<<<<<<");
			System.out.println("======================================");
			System.out.println("\t 1.이름으로 조회하기");
			System.out.println("\t 2.성별로 조회하기");
			System.out.println("\t 3.프로그램으로 조회하기");
			System.out.println("\t 4.활동중인 모든 연예인 조회하기");
			System.out.println("\t 5.뒤로가기");
			System.out.println("\t 6.종료하기");
			System.out.print("=====> ");

			String num = sc.nextLine();
			switch (num) {
			case "1":
				System.out.println("======================================");
				System.out.print("\t 조회할 이름 : ");
				String name = sc.nextLine();
				BroadView.print(bService.selectName(name), SearchType.NAME);
				break;

			case "2":
				System.out.println("======================================");
				System.out.println("\t 1. 여자 연예인 ");
				System.out.println("\t 2. 남자 연예인 ");
				System.out.print("=====> ");
				int gender = sc.nextInt();
				sc.nextLine();
				BroadView.print(bService.selectGender(gender), SearchType.GENDER);
				break;

			case "3":
				System.out.println("======================================");
				System.out.print("\t 조회할 프로그램 : ");
				String title = sc.nextLine();
				BroadView.print(bService.selectAppearance(title), SearchType.APPEARANCE);
				break;

			case "4":
				BroadView.print(bService.selectName("%"), SearchType.NAME);
				break;
			case "5":
				return;
			case "6":
				ExitProgram();
			default:
				System.out.println(">>>>>>>>>> 잘못된 입력 입니다. <<<<<<<<");
				break;
			}
		}
	}

	// 프로그램 종료
	public static void ExitProgram() {
		System.out.println("======================================");
		System.out.println("----방송 정보 조회 프로그램을 종료합니다.----");
		System.out.println("======================================");

		System.exit(0);
	}
}
