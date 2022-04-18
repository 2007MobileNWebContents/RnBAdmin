package reservation.model.service;

import java.sql.Connection;

import common.JDBCTemplate;
import reservation.model.dao.ReservationDAO;
import reservation.model.vo.PageData;

public class ReservationService {

	private JDBCTemplate factory;
	private ReservationDAO reservationDAO;
	public ReservationService() {
		factory = JDBCTemplate.getConnection();
		reservationDAO = new ReservationDAO();
	}
	
	//그룹-승인 페이지 순번매기기
	public PageData groupAckSelectList(int currentPage) {
		Connection conn = null;
		
		int recordCountPerPage = 5;
		int naviCountPerPage = 5;
		PageData pd = new PageData();
		
		try {
			conn = factory.createConnection();
			pd.setGroupAckPageList(reservationDAO.groupAckSelectList(conn, currentPage, recordCountPerPage));
			pd.setGroupAckPageNavi(reservationDAO.getGroupAckPageNavi(conn, currentPage, recordCountPerPage, naviCountPerPage));
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			JDBCTemplate.close(conn);
		}
		return pd;
	}
	
	public PageData groupConfirmSelectList(int currentPage) {
		Connection conn = null;
		
		int recordCountPerPage = 5;
		int naviCountPerPage = 5;
		PageData pd = new PageData();
		
		try {
			conn = factory.createConnection();
			pd.setGroupConfirmPageList(reservationDAO.groupConfirmSelectList(conn, currentPage, recordCountPerPage));
			pd.setGroupConfirmPageNavi(reservationDAO.getGroupConfirmPageNavi(conn, currentPage, recordCountPerPage, naviCountPerPage));
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			JDBCTemplate.close(conn);
		}
		return pd;
	}
	
	public int groupReservationCancle(int gorupReservationNo) {
		Connection conn = null;
		int result = 0;
		
		try {
			conn = factory.createConnection();
			result = reservationDAO.groupReservationCancle(conn, gorupReservationNo);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			JDBCTemplate.close(conn);
		}
		
		return result;
	}
	
	public int groupReservationReject(int gorupReservationNo) {
		Connection conn = null;
		int result = 0;
		
		try {
			conn = factory.createConnection();
			result = reservationDAO.groupReservationReject(conn, gorupReservationNo);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			JDBCTemplate.close(conn);
		}
		
		return result;
	}
	
	public int groupReservationComplete(int groupReservationNo) {
		Connection conn = null;
		int result = 0;
		try {
			conn = factory.createConnection();
			result = reservationDAO.groupReservationComplete(conn, groupReservationNo);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			JDBCTemplate.close(conn);
		}
		return result;
	}
	
	public PageData personalReservationList(int currentPage) {
		Connection conn = null;
		
		int recordCountPerPage = 5;
		int naviCountPerPage = 5;
		PageData pd = new PageData();
		
		try {
			conn = factory.createConnection();
			pd.setPersonalPageList(reservationDAO.personalSelectList(conn, currentPage, recordCountPerPage));
			pd.setPersonalPageNavi(reservationDAO.personalTotalPageNavi(conn, currentPage, recordCountPerPage, naviCountPerPage));
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			JDBCTemplate.close(conn);
		}
		return pd;
	}
	
	public PageData groupAckNameSearchList(int currentPage, String search) {
		Connection conn = null;
		
		int recordCountPage = 5;
		int naviCountPage = 5;
		PageData pd = new PageData();
		
		try {
			conn = factory.createConnection();
			pd.setGroupAckPageList(reservationDAO.groupAckNameSearchList(conn, currentPage, recordCountPage, search));
			pd.setGroupAckPageNavi(reservationDAO.getGroupAckNamePageNavi(conn, currentPage, recordCountPage, naviCountPage, search));
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			JDBCTemplate.close(conn);
		}
		return pd;
	}
	
	public PageData groupAckIdSearchList(int currentPage, String search) {
		Connection conn = null;
		
		int recordCountPage = 5;
		int naviCountPage = 5;
		PageData pd = new PageData();
		
		try {
			conn = factory.createConnection();
			pd.setGroupAckPageList(reservationDAO.groupAckIdSearchList(conn, currentPage, recordCountPage, search));
			pd.setGroupAckPageNavi(reservationDAO.getGroupAckIdPageNavi(conn, currentPage, recordCountPage, naviCountPage, search));
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			JDBCTemplate.close(conn);
		}
		return pd;
	}
	
	public PageData groupConfirmNameSearchList(int currentPage, String search) {
		Connection conn = null;
		
		int recordCountPage = 5;
		int naviCountPage = 5;
		PageData pd = new PageData();
		
		try {
			conn = factory.createConnection();
			pd.setGroupConfirmPageList(reservationDAO.groupConfirmNameSearchList(conn, currentPage, recordCountPage, search));
			pd.setGroupConfirmPageNavi(reservationDAO.getGroupConfirmNamePageNavi(conn, currentPage, recordCountPage, naviCountPage, search));
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			JDBCTemplate.close(conn);
		}
		return pd;
	}
	
	public PageData groupConfirmIdSearchList(int currentPage, String search) {
		Connection conn = null;
		
		int recordCountPage = 5;
		int naviCountPage = 5;
		PageData pd = new PageData();
		
		try {
			conn = factory.createConnection();
			pd.setGroupConfirmPageList(reservationDAO.groupConfirmIdSearchList(conn, currentPage, recordCountPage, search));
			pd.setGroupConfirmPageNavi(reservationDAO.getGroupConfirmIdPageNavi(conn, currentPage, recordCountPage, naviCountPage, search));
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			JDBCTemplate.close(conn);
		}
		return pd;
	}
	
//	public int groupReservationAckUpdate() {
//		int result = 0;
//		Connection conn = null;
//		
//		try {
//			conn = factory.createConnection();
//			result = reservationDAO.groupReservationAckUpdate(conn);
//		}
//		catch(Exception e) {
//			e.printStackTrace();
//		}
//		finally {
//			JDBCTemplate.close(conn);
//		}
//		return result;
//	}
//	
	public int groupReservationWaitUpdate() {
		int result = 0;
		Connection conn = null;
		
		try {
			conn = factory.createConnection();
			result = reservationDAO.groupReservationWaitUpdate(conn);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			JDBCTemplate.close(conn);
		}
		
		return result;
	}
	
	public PageData personalReservationAckList(int currentAckPage) {
		Connection conn = null;
		
		int recordCountPerPage = 5;
		int naviCountPerPage = 5;
		PageData pd = new PageData();
		
		try {
			conn = factory.createConnection();
			pd.setPersonalAckPageList(reservationDAO.personalAckSelectList(conn, currentAckPage, recordCountPerPage));
			pd.setPersonalAckPageNavi(reservationDAO.getpersonalAckPageNavi(conn, currentAckPage, recordCountPerPage, naviCountPerPage));
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			JDBCTemplate.close(conn);
		}
		return pd;
	}
	
	public int personalReservationCancle(int personalReservationNo) {
		Connection conn = null;
		int result = 0;
		
		try {
			conn = factory.createConnection();
			result = reservationDAO.personalReservationCancle(conn, personalReservationNo);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			JDBCTemplate.close(conn);
		}
		
		return result;
	}
	
	public PageData personalSearchTotalNameList(int currentPage, String search) {
		Connection conn = null;
		
		int recordCountPage = 5;
		int naviCountPage = 5;
		PageData pd = new PageData();
		
		try {
			conn = factory.createConnection();
			pd.setPersonalPageList(reservationDAO.personalNameSearchList(conn, currentPage, recordCountPage, search));
			pd.setPersonalPageNavi(reservationDAO.getpersonalNamePageNavi(conn, currentPage, recordCountPage, naviCountPage, search));
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			JDBCTemplate.close(conn);
		}
		return pd;
	}
	
	public PageData personalSearchTotalIdList(int currentPage, String search){
		Connection conn = null;
		
		int recordCountPage = 5;
		int naviCountPage = 5;
		PageData pd = new PageData();
		
		try {
			conn = factory.createConnection();
			pd.setPersonalPageList(reservationDAO.personalIdSearchList(conn, currentPage, recordCountPage, search));
			pd.setPersonalPageNavi(reservationDAO.getpersonalIdPageNavi(conn, currentPage, recordCountPage, naviCountPage, search));
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			JDBCTemplate.close(conn);
		}
		return pd;
	}
	
	public PageData personalSearchAckNameList(int currentPage, String search) {
		Connection conn = null;
		
		int recordCountPage = 5;
		int naviCountPage = 5;
		PageData pd = new PageData();
		
		try {
			conn = factory.createConnection();
			pd.setPersonalAckPageList(reservationDAO.personalAckNameSearchList(conn, currentPage, recordCountPage, search));
			pd.setPersonalAckPageNavi(reservationDAO.getpersonalAckNamePageNavi(conn, currentPage, recordCountPage, naviCountPage, search));
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			JDBCTemplate.close(conn);
		}
		return pd;
	}
	
	public PageData personalSearchAckNaIdList(int currentPage, String search) {
		Connection conn = null;
		
		int recordCountPage = 5;
		int naviCountPage = 5;
		PageData pd = new PageData();
		
		try {
			conn = factory.createConnection();
			pd.setPersonalAckPageList(reservationDAO.personalAckIdSearchList(conn, currentPage, recordCountPage, search));
			pd.setPersonalAckPageNavi(reservationDAO.getpersonalAckIdPageNavi(conn, currentPage, recordCountPage, naviCountPage, search));
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			JDBCTemplate.close(conn);
		}
		return pd;
	}
	
	public int groupReservationFinish(int groupReservationNo) {
		Connection conn = null;
		int result = 0;
		
		try {
			conn = factory.createConnection();
			result = reservationDAO.groupReservationFinish(conn, groupReservationNo);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			JDBCTemplate.close(conn);
		}
		
		return result;
	}
	
	public int personalReservationFinish(int personalReservationNo) {
		int result = 0;
		Connection conn = null;
		
		try {
			conn = factory.createConnection();
			result = reservationDAO.personalReservetionFinish(conn, personalReservationNo);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			JDBCTemplate.close(conn);
		}
		return result;
	}
}
