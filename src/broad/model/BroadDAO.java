package broad.model;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

import broad.util.OracleUtil;
import broad.util.SearchType;
import broad.vo.BroadVO;

public class BroadDAO {
	Connection conn;
	Statement st;
	ResultSet rs;
	PreparedStatement pst;// ?지원
	CallableStatement cst; // sp지원
	int resultCount;// insert, update, delete건수
	
	//-------------------------------관리자 접속
	//1) 신규 방송사 등록
	public boolean insertBroadcaster(String broadcaster) {
		String sql = """
				insert into broadstation(bid, broadcaster) select bs_seq.nextval, upper(?) from dual
				where not exists ( select bid from broadstation where broadcaster = upper(?))
				""";
		boolean result = false;
		
		conn = OracleUtil.getConnection();
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, broadcaster);
			pst.setString(2, broadcaster);

			if(pst.executeUpdate() > 0) {
				result = true;
			}
		
		} catch (SQLException e) {
			if(e.getErrorCode() == 1)
				System.out.println("*** 해당 방송사가 존재합니다.");
			else
				e.printStackTrace();
		} finally {
			OracleUtil.dbDisconnect(rs, pst, conn);
		}
		return result; 
	}
	//2) 신규 프로그램 등록
	public boolean insertBroad(Stack<String> st) {
		String sql = """
				insert into BroadProgram( PID, BID, AIRTIME, TITLE, INTRODUCTION, BROADDAY)
				select bp_seq.nextval, (select bid
				            from broadstation
				            where BROADCASTER = upper(?) ),
				            ?,
				            ?,
				            ?,
				            ?
				from dual
				where exists ( select bid from broadstation where BROADCASTER = upper(?))
				""";
		boolean result = false;
		
		conn = OracleUtil.getConnection();
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, st.pop());
			pst.setString(2, st.pop());
			pst.setString(3, st.pop());
			pst.setString(4, st.pop());
			pst.setString(5, st.pop());
			pst.setString(6, st.pop());
			
			if(pst.executeUpdate() > 0)
				result = true;
			
		} catch (SQLException e) {
			if(e.getErrorCode() == 1)
				System.out.println("***해당 시간에 방송되는 프로그램이 있습니다.");
			else if(e.getErrorCode() == 12899)
				System.out.println("***프로그램 제작 조건을 맞춰주세요.");
			else if(e.getErrorCode() == 1400)
				System.out.println("***프로그램 소개를 제외하고는 필수입력입니다.");
			else {
				System.out.println(e.getErrorCode());
				e.printStackTrace();

			}
		} finally {
			OracleUtil.dbDisconnect(rs, pst, conn);
		}
		return result; 
	}
	
	//출연정보 등록하기
	public boolean insertApperence(String title, String name) {
		String sql = """
				insert into apperence(pid, cid) select (select pid
                                        from BroadProgram
                                        where title= ?),
                                        (select cid
                                        from celebrity
                                        where name = ?)
				from dual
				where exists (select pid, title from BroadProgram where title= ?)
				and exists (select name from celebrity where name = ?)
				""";
		boolean result = false;
		
		conn = OracleUtil.getConnection();
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, title);
			pst.setString(2, name);
			pst.setString(3, title);
			pst.setString(4, name);
			
			if(pst.executeUpdate() > 0) {
				result = true;
			}
		
		} catch (SQLException e) {
			if(e.getErrorCode() == 1)
				System.out.println("*** 이미 해당 프로그램에 출연중 입니다.");
			else
				e.printStackTrace();
		} finally {
			OracleUtil.dbDisconnect(rs, pst, conn);
		}
		return result; 	
	}
	
	//출연진 삭제하기
	public boolean deleteApperence(String title, String name) {
		String sql = """
				delete
				from apperence
				where pid = (select pid from BroadProgram where title = ? )
				and cid = (select cid from celebrity where name = ? )
				""";
		boolean result = false;
		
		conn = OracleUtil.getConnection();
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, title);
			pst.setString(2, name);
			
			if(pst.executeUpdate() > 0) {
				result = true;
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			OracleUtil.dbDisconnect(rs, pst, conn);
		}
		return result; 
	}
	
	//프로그램 삭제하기
	public boolean DeleteBroad(String title) {
		String sql = """
				delete
				from BroadProgram
				where title = ?
				""";
		boolean result = false;
		
		conn = OracleUtil.getConnection();
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, title);
			
			if(pst.executeUpdate() > 0) {
				result = true;
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			OracleUtil.dbDisconnect(rs, pst, conn);
		}
		return result; 
	}
	
	//프로그램정보 수정하기
	public boolean UpdateBroad(Stack<String> st) {
		String sql = "update BroadProgram SET " + st.pop()+ " = ? WHERE title = ?";
		boolean result = false;
		
		conn = OracleUtil.getConnection();
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, st.pop());
			pst.setString(2, st.pop());

			if(pst.executeUpdate() > 0) {
				result = true;
			}
		
		} catch (SQLException e) {
			if(e.getErrorCode() == 1)
				System.out.println("*** 해당 시간에 다른 프로그램이 방송되고 있습니다.");
			else if(e.getErrorCode() == 12899)
				System.out.println("*** 프로그램 제작 조건을 맞춰주세요.");
			else
				e.printStackTrace();
		} finally {
			OracleUtil.dbDisconnect(rs, pst, conn);
		}
		return result; 	
	}

	//-------------------------------인물 접속
	//1) 이름으로 조회
	public List<BroadVO> selectName(String name){
		String sql = """
				select name, decode(gender, 'F', '여자','남자') gender, trunc(MONTHS_BETWEEN(SYSDATE,birth)/12) age, birth, listagg(title, ', ')
                               within group(order by pid) as broad
				from celebrity join  apperence using(cid)
				                join BroadProgram using(pid)
				where name like ?
				group by (name, gender , birth)
				""";
		conn = OracleUtil.getConnection();
		List<BroadVO> broadlist = new ArrayList<>();
		conn = OracleUtil.getConnection();
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, name);

			rs = pst.executeQuery();
			while (rs.next()) {
				BroadVO br = makeCele(rs, SearchType.NAME);
				broadlist.add(br);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			OracleUtil.dbDisconnect(rs, pst, conn);
		}
		return broadlist; 
	}
	
	//2) 성별로 조회
	public List<BroadVO> selectGender(int gender){
		String sql = """
				select name,  trunc(MONTHS_BETWEEN(SYSDATE,birth)/12) age, birth, listagg(title, ', ')
                               within group(order by pid) as broad
				from celebrity join  apperence using(cid)
				                join BroadProgram using(pid)
				where gender = ?
				group by (name, birth)
				order by name
				""";
		conn = OracleUtil.getConnection();
		List<BroadVO> broadlist = new ArrayList<>();
		conn = OracleUtil.getConnection();
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, gender == 1 ? "F" : "M");

			rs = pst.executeQuery();
			while (rs.next()) {
				BroadVO br = makeCele(rs, SearchType.GENDER);
				broadlist.add(br);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			OracleUtil.dbDisconnect(rs, pst, conn);
		}
		return broadlist; 
	}
	//3) 프로그램으로 조회
	public List<BroadVO> selectAppearance(String title){
		String sql = """
				select name, decode(gender, 'F', '여자','남자') gender, trunc(MONTHS_BETWEEN(SYSDATE,birth)/12) age, birth
				from celebrity join  apperence using(cid)
				                join BroadProgram using(pid)
                where title = ?
				group by (name, gender , birth)
				""";
		conn = OracleUtil.getConnection();
		List<BroadVO> broadlist = new ArrayList<>();
		conn = OracleUtil.getConnection();
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, title);

			rs = pst.executeQuery();
			while (rs.next()) {
				BroadVO br = makeCele(rs, SearchType.APPEARANCE);
				broadlist.add(br);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			OracleUtil.dbDisconnect(rs, pst, conn);
		}
		return broadlist; 
	}
	
	// * 인물 - 쿼리로 받아온 정보 넣기
	private BroadVO makeCele(ResultSet rs2, SearchType type) throws SQLException {
		BroadVO br = new BroadVO();

		// 공통으로 출력해야 할 정보
		br.setName(rs.getString("name"));
		br.setAge(rs.getInt("age"));
		if(!(type.equals(SearchType.GENDER)))
			br.setGender(rs.getString("gender"));
		br.setBirth(rs.getDate("birth"));
		if(!(type.equals(SearchType.APPEARANCE)))
			br.setTitle(rs.getString("broad"));
		
		return br;
	}

	//-------------------------------프로그램 관련
	// 1,5)프로그램 명으로 보기, 모든 프로그램 보기
	public List<BroadVO> selectTitle(String title) {
		String sql = """
				select  title, broadcaster, broadday, airtime, introduction,  listagg(name, ', ')
                within group(order by cid) as cele
				from celebrity join  apperence using(cid)
				                join broadprogram using(pid)
				                join BroadStation using(bid)
				where title like ?
				group by (title, broadcaster, broadday, airtime, introduction)
				order by broadcaster, broadday, airtime
				""";
		conn = OracleUtil.getConnection();
		List<BroadVO> broadlist = new ArrayList<>();
		conn = OracleUtil.getConnection();
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, title);

			rs = pst.executeQuery();
			while (rs.next()) {
				BroadVO br = makeBroad(rs, SearchType.TITLE);
				broadlist.add(br);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			OracleUtil.dbDisconnect(rs, pst, conn);
		}
		return broadlist;
	}

	// 2)방송사로 보기
	public List<BroadVO> selectBroadcaster(String broadcaster) {
		String sql = """
				select title, broadday, airtime, introduction, listagg(name, ', ')
                               within group(order by cid) as cele
				from celebrity join  apperence using(cid)
				                join BroadProgram using(pid)
				                join BroadStation using(bid)
				where broadcaster like upper(?)
				group by (title, broadday, airtime, introduction)
				order by title, airtime
				""";

		List<BroadVO> broadlist = new ArrayList<>();
		conn = OracleUtil.getConnection();
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, broadcaster);

			rs = pst.executeQuery();
			while (rs.next()) {
				BroadVO br = makeBroad(rs, SearchType.BROADCASTER);
				broadlist.add(br);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			OracleUtil.dbDisconnect(rs, pst, conn);
		}
		return broadlist;
	}

	
	// 4)요일로 보기
		public List<BroadVO> selectBroadDay(String broadday) {
			String sql = """
					select title, broadcaster, airtime, introduction, listagg(name, ', ')
                               within group(order by cid) as cele
					from celebrity join  apperence using(cid)
					                join BroadProgram using(pid)
					                join BroadStation using(bid)
					where broadday = ?
					group by (title, broadcaster, airtime, introduction)
					order by broadcaster, title
					""";
			System.out.println(broadday);
			List<BroadVO> broadlist = new ArrayList<>();
			conn = OracleUtil.getConnection();
			try {
				pst = conn.prepareStatement(sql);
				pst.setString(1, broadday);

				rs = pst.executeQuery();
				while (rs.next()) {
					BroadVO br = makeBroad(rs, SearchType.BROADDAY);
					broadlist.add(br);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				OracleUtil.dbDisconnect(rs, pst, conn);
			}
			return broadlist;
		}
		
	
	// * 방송 - 쿼리로 받아온 정보 넣기
	private BroadVO makeBroad(ResultSet rs, SearchType type) throws SQLException {
		BroadVO br = new BroadVO();

		// 공통으로 출력해야 할 정보
		br.setTitle(rs.getString("title"));
		br.setAirtime(rs.getString("airtime"));
		br.setIntroduction(rs.getString("introduction"));
		br.setName(rs.getString("cele"));
		// 방송사로 조회할 경우 넣지 않음
		if (!(type.equals(SearchType.BROADCASTER))) {
			br.setBroadcaster(rs.getString("broadcaster"));
		}
		// 방송요일로 조회할 경우 넣지 않음
		if (!(type.equals(SearchType.BROADDAY))) {
			br.setBroadday(rs.getString("broadday"));
		}

		return br;
	}
	
	
	
	

}
