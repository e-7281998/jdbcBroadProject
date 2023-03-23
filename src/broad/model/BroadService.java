package broad.model;

import java.util.List;
import java.util.Stack;

import broad.controller.BroadContoller;
import broad.vo.BroadVO;

public class BroadService {
	BroadDAO broadDao = new BroadDAO();

	/*-------------- 관리자 ------------------------*/

	public boolean insertBroadcaster(String broadcaster) {
		return broadDao.insertBroadcaster(broadcaster);
	}

	public boolean insertBroad(Stack<String> st) {
		return broadDao.insertBroad(st);
	}

	public boolean insertApperence(String title, String name) {
		return broadDao.insertApperence(title, name);
	}

	public boolean deleteApperence(String title, String name) {
		return broadDao.deleteApperence(title, name);
	}

	public boolean DeleteBroad(String title) {
		return broadDao.DeleteBroad(title);

	}
	
	public boolean UpdateBroad(Stack<String> st) {
		return broadDao.UpdateBroad(st);
	}

	/*-------------- 유저 ------------------------*/
	public List<BroadVO> selectName(String name) {
		return broadDao.selectName(name);
	}

	public List<BroadVO> selectTitle(String title) {
		return broadDao.selectTitle(title);
	}

	public List<BroadVO> selectBroadcaster(String broadcaster) {
		return broadDao.selectBroadcaster(broadcaster);
	}

	public List<BroadVO> selectBroadDay(String broadday) {
		return broadDao.selectBroadDay(broadday);
	}

	public List<BroadVO> selectGender(int gender) {
		return broadDao.selectGender(gender);
	}

	public List<BroadVO> selectAppearance(String title) {
		return broadDao.selectAppearance(title);
	}

	

}
