package reservation.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import common.JDBCTemplate;
import reservation.model.vo.GroupReservation;
import reservation.model.vo.PersonalReservation;

public class ReservationDAO {
	
	public ReservationDAO() {
		
	}
	
	public ArrayList<GroupReservation> groupAckSelectList(Connection conn, int currentPage, int recordCountPage) {
		ArrayList<GroupReservation> gList = null;
		PreparedStatement pstmt = null;
//		Statement stmt = null;
		ResultSet rset = null;
		String query = "SELECT * FROM (SELECT RES_GROUP.*, ROW_NUMBER() OVER(ORDER BY RG_NO DESC) AS NUM FROM RES_GROUP WHERE RG_STATE = '대기') WHERE (NUM BETWEEN ? AND ?)";
		int start = currentPage * recordCountPage - (recordCountPage - 1);
		int end = currentPage * recordCountPage;
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rset = pstmt.executeQuery();
			gList = new ArrayList<GroupReservation>();
			while(rset.next()) {
				GroupReservation groupReservation = new GroupReservation();
				groupReservation.setRgNo(rset.getInt("RG_NO"));
				groupReservation.setRgDate(rset.getString("RG_DATE"));
				groupReservation.setRgPlace(rset.getString("RG_PLACE"));
				groupReservation.setRgTime(rset.getString("RG_TIME"));
				groupReservation.setRgState(rset.getString("RG_STATE"));
				groupReservation.setRgPeople(rset.getString("RG_PEOPLE"));
				groupReservation.setUserId(rset.getString("USER_ID"));
				gList.add(groupReservation);
				
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return gList;
	}
	
	public ArrayList<GroupReservation> groupConfirmSelectList(Connection conn, int currentPage, int recordCountPage) {
		ArrayList<GroupReservation> gcList = null;
		PreparedStatement pstmt = null;
//		Statement stmt = null;
		ResultSet rset = null;
		String query = "SELECT * FROM (SELECT RES_GROUP.*, ROW_NUMBER() OVER(ORDER BY RG_NO DESC) AS NUM FROM RES_GROUP WHERE RG_STATE!= '대기')WHERE (NUM BETWEEN ? AND ?)";
		int start = currentPage * recordCountPage - (recordCountPage - 1);
		int end = currentPage * recordCountPage;
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rset = pstmt.executeQuery();
			gcList = new ArrayList<GroupReservation>();
			while(rset.next()) {
				GroupReservation groupReservation = new GroupReservation();
				groupReservation.setRgNo(rset.getInt("RG_NO"));
				groupReservation.setRgDate(rset.getString("RG_DATE"));
				groupReservation.setRgPlace(rset.getString("RG_PLACE"));
				groupReservation.setRgTime(rset.getString("RG_TIME"));
				groupReservation.setRgState(rset.getString("RG_STATE"));
				groupReservation.setRgPeople(rset.getString("RG_PEOPLE"));
				groupReservation.setUserId(rset.getString("USER_ID"));
				gcList.add(groupReservation);
				
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return gcList;
	}
	
	public String getGroupAckPageNavi(Connection conn, int currentPage, int recordCountPerPage, int naviCountPerPage) {
		int recordTotalCount = totalAckCount(conn);
		
		int pageTotalCount = 0;
		if(recordTotalCount % recordCountPerPage > 0) {
			pageTotalCount = recordTotalCount / recordCountPerPage + 1;
		}
		else {
			pageTotalCount = recordTotalCount / recordCountPerPage;
		}
		
		if(currentPage < 1) {
			currentPage = 1;
		}
		else if(currentPage > pageTotalCount) {
			currentPage = pageTotalCount;
		}
		
		int startNavi = ((currentPage - 1) / naviCountPerPage) * naviCountPerPage + 1;
		int endNavi = startNavi + naviCountPerPage - 1;
		
		if(endNavi > pageTotalCount) {
			endNavi = pageTotalCount;
		}
		
		boolean needPrev = true;
		boolean needNext = true;
		
		if(startNavi == 1) {
			needPrev = false;
		}
		if(endNavi == pageTotalCount) {
			needNext = false;
		}
		
		StringBuilder sb = new StringBuilder();
		
		if(needPrev) {
			sb.append("<a style='text-decoration: none;color: black;' href='/group/reservation?currentAckPage="+(startNavi-1)+"'> < </a>");
		}

		//1 ~ 10까지의 숫자를 for문을 이용해서 생성하고 만들어 주는 부분

		for(int i = startNavi; i <= endNavi; i++) {
			if(i == currentPage) {
				sb.append("<a style='text-decoration: none;color: black;' href='/group/reservation?currentAckPage="+i+"'><b>"+i+"</b></a>");
			}
			else {
				sb.append("<a style='text-decoration: none;color: black;' href='/group/reservation?currentAckPage="+i+"'>"+i+"</a>");
			}
		}
		//다음 버튼을 누를 수 있을 때 다음 버튼을 생성하는 부분
		if(needNext) {
			sb.append("<a style='text-decoration: none;color: black;' href='/group/reservation?currentAckPage="+(endNavi+1)+"'> > </a>");
		}
		//태그를 StringBuilder의 append()메소드를 이용해서 붙인 후에 toString()메소드를 이용하여 String으로 만들어저 리턴
		return sb.toString();
	}
	
	public String getGroupConfirmPageNavi(Connection conn, int currentPage, int recordCountPerPage, int naviCountPerPage) {
		int recordTotalCount = totalConfirmCount(conn);
		
		int pageTotalCount = 0;
		if(recordTotalCount % recordCountPerPage > 0) {
			pageTotalCount = recordTotalCount / recordCountPerPage + 1;
		}
		else {
			pageTotalCount = recordTotalCount / recordCountPerPage;
		}
		
		if(currentPage < 1) {
			currentPage = 1;
		}
		else if(currentPage > pageTotalCount) {
			currentPage = pageTotalCount;
		}
		
		int startNavi = ((currentPage - 1) / naviCountPerPage) * naviCountPerPage + 1;
		int endNavi = startNavi + naviCountPerPage - 1;
		
		if(endNavi > pageTotalCount) {
			endNavi = pageTotalCount;
		}
		
		boolean needPrev = true;
		boolean needNext = true;
		
		if(startNavi == 1) {
			needPrev = false;
		}
		if(endNavi == pageTotalCount) {
			needNext = false;
		}
		
		StringBuilder sb = new StringBuilder();
		
		if(needPrev) {
			sb.append("<a style='text-decoration: none;color: black;' href='/group/reservation?currentConfirmPage="+(startNavi-1)+"'> < </a>");
		}

		//1 ~ 10까지의 숫자를 for문을 이용해서 생성하고 만들어 주는 부분

		for(int i = startNavi; i <= endNavi; i++) {
			if(i == currentPage) {
				sb.append("<a style='text-decoration: none;color: black;' href='/group/reservation?currentConfirmPage="+i+"'><b> "+i+" </b></a>");
			}
			else {
				sb.append("<a style='text-decoration: none;color: black;' href='/group/reservation?currentConfirmPage="+i+"'> "+i+" </a>");
			}
		}
		//다음 버튼을 누를 수 있을 때 다음 버튼을 생성하는 부분
		if(needNext) {
			sb.append("<a style='text-decoration: none;color: black;' href='/group/reservation?currentConfirmPage="+(endNavi+1)+"'> > </a>");
		}
		//태그를 StringBuilder의 append()메소드를 이용해서 붙인 후에 toString()메소드를 이용하여 String으로 만들어저 리턴
		return sb.toString();
	}
	
	public int totalAckCount(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "SELECT COUNT(*) AS TOTALCOUNT FROM RES_GROUP WHERE RG_STATE='대기'";
		int recourdTotalCount = 0;
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				recourdTotalCount = rset.getInt("TOTALCOUNT");
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return recourdTotalCount;
		
	}
	
	public int totalConfirmCount(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "SELECT COUNT(*) AS TOTALCOUNT FROM RES_GROUP WHERE RG_STATE!='대기'";
		int recourdTotalCount = 0;
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				recourdTotalCount = rset.getInt("TOTALCOUNT");
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return recourdTotalCount;
		
	}
	
	
	public int groupReservationCancle(Connection conn, int gorupReservationNo) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = "UPDATE RES_GROUP SET RG_STATE='취소' WHERE RG_NO=?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, gorupReservationNo);
			result = pstmt.executeUpdate();
			
			if(result > 0) {
				JDBCTemplate.commit(conn);
			}
			else {
				JDBCTemplate.rollback(conn);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}
	
	public int groupReservationReject(Connection conn, int gorupReservationNo) {
		int result = 0;
		
		PreparedStatement pstmt = null;
		String query = "UPDATE RES_GROUP SET RG_STATE='거절' WHERE RG_NO=?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, gorupReservationNo);
			result = pstmt.executeUpdate();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public int groupReservationComplete(Connection conn, int groupReservationNo) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = "UPDATE RES_GROUP SET RG_STATE='승인' WHERE RG_NO=?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, groupReservationNo);
			result = pstmt.executeUpdate();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}

	public ArrayList<PersonalReservation> personalSelectList(Connection conn, int currentPage, int recordCountPage) {
		ArrayList<PersonalReservation> pList = null;
		PreparedStatement pstmt = null;
//		Statement stmt = null;
		ResultSet rset = null;
		String query = "SELECT * FROM (SELECT RES_PRIV.*, ROW_NUMBER() OVER(ORDER BY RP_NO DESC) AS NUM FROM RES_PRIV) WHERE (NUM BETWEEN ? AND ?)";
		int start = currentPage * recordCountPage - (recordCountPage - 1);
		int end = currentPage * recordCountPage;
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rset = pstmt.executeQuery();
			pList = new ArrayList<PersonalReservation>();
			while(rset.next()) {
				PersonalReservation personalReservation = new PersonalReservation();
				personalReservation.setRpNo(rset.getInt("RP_NO"));
				personalReservation.setRpDate(rset.getString("RP_DATE"));
				personalReservation.setRpPlace(rset.getString("RP_PLACE"));
				personalReservation.setRpTime(rset.getString("RP_TIME"));
				personalReservation.setRpKind(rset.getString("RP_KIND"));
				personalReservation.setRpState(rset.getString("RP_STATE"));
				personalReservation.setRpOn(rset.getString("RP_ON"));
				personalReservation.setUserId(rset.getString("USER_ID"));
				pList.add(personalReservation);
				
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return pList;
	}
	
	public String personalTotalPageNavi(Connection conn, int currentPage, int recordCountPerPage, int naviCountPerPage) {
		int recordTotalCount = totalPersonalCount(conn);
		
		int pageTotalCount = 0;
		if(recordTotalCount % recordCountPerPage > 0) {
			pageTotalCount = recordTotalCount / recordCountPerPage + 1;
		}
		else {
			pageTotalCount = recordTotalCount / recordCountPerPage;
		}
		
		if(currentPage < 1) {
			currentPage = 1;
		}
		else if(currentPage > pageTotalCount) {
			currentPage = pageTotalCount;
		}
		
		int startNavi = ((currentPage - 1) / naviCountPerPage) * naviCountPerPage + 1;
		int endNavi = startNavi + naviCountPerPage - 1;
		
		if(endNavi > pageTotalCount) {
			endNavi = pageTotalCount;
		}
		
		boolean needPrev = true;
		boolean needNext = true;
		
		if(startNavi == 1) {
			needPrev = false;
		}
		if(endNavi == pageTotalCount) {
			needNext = false;
		}
		
		StringBuilder sb = new StringBuilder();
		
		if(needPrev) {
			sb.append("<a style='text-decoration: none;color: black;' href='/personal/reservation?currentTotalPage="+(startNavi-1)+"'> < </a>");
		}

		//1 ~ 10까지의 숫자를 for문을 이용해서 생성하고 만들어 주는 부분

		for(int i = startNavi; i <= endNavi; i++) {
			if(i == currentPage) {
				sb.append("<a style='text-decoration: none;color: black;' href='/personal/reservation?currentTotalPage="+i+" '> <b>"+i+"</b> </a>");
			}
			else {
				sb.append("<a style='text-decoration: none;color: black;' href='/personal/reservation?currentTotalPage="+i+" '> "+i+" </a>");
			}
		}
		//다음 버튼을 누를 수 있을 때 다음 버튼을 생성하는 부분
		if(needNext) {
			sb.append("<a style='text-decoration: none;color: black;' href='/personal/reservation?currentTotalPage="+(endNavi+1)+"'> > </a>");
		}
		//태그를 StringBuilder의 append()메소드를 이용해서 붙인 후에 toString()메소드를 이용하여 String으로 만들어저 리턴
		return sb.toString();
	}
	
	public int totalPersonalCount(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "SELECT COUNT(*) AS TOTALCOUNT FROM RES_PRIV";
		int recourdTotalCount = 0;
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				recourdTotalCount = rset.getInt("TOTALCOUNT");
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return recourdTotalCount;
		
	}
	
	
	//승인 이름검색
	public ArrayList<GroupReservation> groupAckNameSearchList(Connection conn, int currentPage, int recordCountPage, String search){
		ArrayList<GroupReservation> gList = null;
		PreparedStatement pstmt = null;
//		Statement stmt = null;
		ResultSet rset = null;
		String query = "SELECT * FROM (SELECT RES_GROUP.*, ROW_NUMBER() OVER(ORDER BY RG_NO DESC) AS NUM FROM RES_GROUP WHERE USER_ID=(SELECT MEMBER.USER_ID FROM MEMBER WHERE USER_NAME=?) AND RG_STATE='대기') WHERE (NUM BETWEEN ? AND ?)";
		int start = currentPage * recordCountPage - (recordCountPage - 1);
		int end = currentPage * recordCountPage;
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, search);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			rset = pstmt.executeQuery();
			gList = new ArrayList<GroupReservation>();
			while(rset.next()) {
				GroupReservation groupReservation = new GroupReservation();
				groupReservation.setRgNo(rset.getInt("RG_NO"));
				groupReservation.setRgDate(rset.getString("RG_DATE"));
				groupReservation.setRgPlace(rset.getString("RG_PLACE"));
				groupReservation.setRgTime(rset.getString("RG_TIME"));
				groupReservation.setRgState(rset.getString("RG_STATE"));
				groupReservation.setRgPeople(rset.getString("RG_PEOPLE"));
				groupReservation.setUserId(rset.getString("USER_ID"));
				gList.add(groupReservation);
				
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return gList;
	}
	
	public String getGroupAckNamePageNavi(Connection conn, int currentPage, int recordCountPage, int naviCountPage, String search) {
		int recordTotalCount = totalAckNameCount(conn, search);
//		System.out.println(search);
		
		int pageTotalCount = 0;
		if(recordTotalCount % recordCountPage > 0) {
			pageTotalCount = recordTotalCount / recordCountPage + 1;
		}
		else {
			pageTotalCount = recordTotalCount / recordCountPage;
		}
		
		if(currentPage < 1) {
			currentPage = 1;
		}
		else if(currentPage > pageTotalCount) {
			currentPage = pageTotalCount;
		}
		
		int startNavi = ((currentPage - 1) / naviCountPage) * naviCountPage + 1;
		int endNavi = startNavi + naviCountPage - 1;
		
		if(endNavi > pageTotalCount) {
			endNavi = pageTotalCount;
		}
		
		boolean needPrev = true;
		boolean needNext = true;
		
		if(startNavi == 1) {
			needPrev = false;
		}
		if(endNavi == pageTotalCount) {
			needNext = false;
		}
		
		StringBuilder sb = new StringBuilder();
		
		if(needPrev) {
			sb.append("<a style='text-decoration: none;color: black;' href='/group/search/ack/name?currentPage="+(startNavi-1)+"&search="+search+"'> < </a>");
		}

		//1 ~ 10까지의 숫자를 for문을 이용해서 생성하고 만들어 주는 부분

		for(int i = startNavi; i <= endNavi; i++) {
			if(i == currentPage) {
				sb.append("<a style='text-decoration: none;color: black;' href='/group/search/ack/name?currentPage="+i+"&search="+search+"'><b> "+i+" </b></a>");
			}
			else {
				sb.append("<a style='text-decoration: none;color: black;' href='/group/search/ack/name?currentPage="+i+"&search="+search+"'> "+i+" </a>");
			}
		}
		//다음 버튼을 누를 수 있을 때 다음 버튼을 생성하는 부분
		if(needNext) {
			sb.append("<a style='text-decoration: none;color: black;' href='/group/search/ack/name?currentPage="+(endNavi+1)+"&search="+search+"'> > </a>");
		}
		//태그를 StringBuilder의 append()메소드를 이용해서 붙인 후에 toString()메소드를 이용하여 String으로 만들어저 리턴
		return sb.toString();
	}
	
	public int totalAckNameCount(Connection conn, String search) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "SELECT COUNT(*) AS TOTALCOUNT FROM RES_GROUP WHERE RG_STATE='대기' AND USER_ID=(SELECT MEMBER.USER_ID FROM MEMBER WHERE USER_NAME=?)";
		int recourdTotalCount = 0;
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, search);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				recourdTotalCount = rset.getInt("TOTALCOUNT");
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return recourdTotalCount;
		
	}
	
	//단체승인 아이디검색
	public ArrayList<GroupReservation> groupAckIdSearchList(Connection conn, int currentPage, int recordCountPage, String search){
		ArrayList<GroupReservation> gList = null;
		PreparedStatement pstmt = null;
//		Statement stmt = null;
		ResultSet rset = null;
		String query = "SELECT * FROM (SELECT RES_GROUP.*, ROW_NUMBER() OVER(ORDER BY RG_NO DESC) AS NUM FROM RES_GROUP WHERE RG_STATE = '대기' AND USER_ID=?) WHERE (NUM BETWEEN ? AND ?)";
		int start = currentPage * recordCountPage - (recordCountPage - 1);
		int end = currentPage * recordCountPage;
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, search);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			rset = pstmt.executeQuery();
			gList = new ArrayList<GroupReservation>();
			while(rset.next()) {
				GroupReservation groupReservation = new GroupReservation();
				groupReservation.setRgNo(rset.getInt("RG_NO"));
				groupReservation.setRgDate(rset.getString("RG_DATE"));
				groupReservation.setRgPlace(rset.getString("RG_PLACE"));
				groupReservation.setRgTime(rset.getString("RG_TIME"));
				groupReservation.setRgState(rset.getString("RG_STATE"));
				groupReservation.setRgPeople(rset.getString("RG_PEOPLE"));
				groupReservation.setUserId(rset.getString("USER_ID"));
				gList.add(groupReservation);
				
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return gList;
	}
	
	public String getGroupAckIdPageNavi(Connection conn, int currentPage, int recordCountPage, int naviCountPage, String search) {
		int recordTotalCount = totalAckIdCount(conn, search);
		
		int pageTotalCount = 0;
		if(recordTotalCount % recordCountPage > 0) {
			pageTotalCount = recordTotalCount / recordCountPage + 1;
		}
		else {
			pageTotalCount = recordTotalCount / recordCountPage;
		}
		
		if(currentPage < 1) {
			currentPage = 1;
		}
		else if(currentPage > pageTotalCount) {
			currentPage = pageTotalCount;
		}
		
		int startNavi = ((currentPage - 1) / naviCountPage) * naviCountPage + 1;
		int endNavi = startNavi + naviCountPage - 1;
		
		if(endNavi > pageTotalCount) {
			endNavi = pageTotalCount;
		}
		
		boolean needPrev = true;
		boolean needNext = true;
		
		if(startNavi == 1) {
			needPrev = false;
		}
		if(endNavi == pageTotalCount) {
			needNext = false;
		}
		
		StringBuilder sb = new StringBuilder();
		
		if(needPrev) {
			sb.append("<a style='text-decoration: none;color: black;' href='/group/search/ack/id?currentPage="+(startNavi-1)+"&search="+search+"'> < </a>");
		}

		//1 ~ 10까지의 숫자를 for문을 이용해서 생성하고 만들어 주는 부분

		for(int i = startNavi; i <= endNavi; i++) {
			if(i == currentPage) {
				sb.append("<a style='text-decoration: none;color: black;' href='/group/search/ack/id?currentPage="+i+"&search="+search+"'><b> "+i+" </b></a>");
			}
			else {
				sb.append("<a style='text-decoration: none;color: black;' href='/group/search/ack/id?currentPage="+i+"&search="+search+"'> "+i+" </a>");
			}
		}
		//다음 버튼을 누를 수 있을 때 다음 버튼을 생성하는 부분
		if(needNext) {
			sb.append("<a style='text-decoration: none;color: black;' href='/group/search/ack/id?currentPage="+(endNavi+1)+"&search="+search+"'> > </a>");
		}
		//태그를 StringBuilder의 append()메소드를 이용해서 붙인 후에 toString()메소드를 이용하여 String으로 만들어저 리턴
		return sb.toString();
	}
	
	public int totalAckIdCount(Connection conn, String search) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "SELECT COUNT(*) AS TOTALCOUNT FROM RES_GROUP WHERE RG_STATE='대기' AND USER_ID=?";
		int recourdTotalCount = 0;
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, search);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				recourdTotalCount = rset.getInt("TOTALCOUNT");
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return recourdTotalCount;
		
	}
	
	//단체 확인 이름검색
	public ArrayList<GroupReservation> groupConfirmNameSearchList(Connection conn, int currentPage, int recordCountPage, String search){
		ArrayList<GroupReservation> gcList = null;
		PreparedStatement pstmt = null;
//		Statement stmt = null;
		ResultSet rset = null;
		String query = "SELECT * FROM (SELECT RES_GROUP.*, ROW_NUMBER() OVER(ORDER BY RG_NO DESC) AS NUM FROM RES_GROUP WHERE USER_ID=(SELECT MEMBER.USER_ID FROM MEMBER WHERE USER_NAME=?) AND RG_STATE!='대기') WHERE (NUM BETWEEN ? AND ?)";
		int start = currentPage * recordCountPage - (recordCountPage - 1);
		int end = currentPage * recordCountPage;
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, search);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			rset = pstmt.executeQuery();
			gcList = new ArrayList<GroupReservation>();
			while(rset.next()) {
				GroupReservation groupReservation = new GroupReservation();
				groupReservation.setRgNo(rset.getInt("RG_NO"));
				groupReservation.setRgDate(rset.getString("RG_DATE"));
				groupReservation.setRgPlace(rset.getString("RG_PLACE"));
				groupReservation.setRgTime(rset.getString("RG_TIME"));
				groupReservation.setRgState(rset.getString("RG_STATE"));
				groupReservation.setRgPeople(rset.getString("RG_PEOPLE"));
				groupReservation.setUserId(rset.getString("USER_ID"));
				gcList.add(groupReservation);
				
			}
			System.out.println(gcList.isEmpty()); // false
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return gcList;
	}
	
	public String getGroupConfirmNamePageNavi(Connection conn, int currentPage, int recordCountPage, int naviCountPage, String search) {
		int recordTotalCount = totalConfirmNameCount(conn, search);
		
		int pageTotalCount = 0;
		if(recordTotalCount % recordCountPage > 0) {
			pageTotalCount = recordTotalCount / recordCountPage + 1;
		}
		else {
			pageTotalCount = recordTotalCount / recordCountPage;
		}
		
		if(currentPage < 1) {
			currentPage = 1;
		}
		else if(currentPage > pageTotalCount) {
			currentPage = pageTotalCount;
		}
		
		int startNavi = ((currentPage - 1) / naviCountPage) * naviCountPage + 1;
		int endNavi = startNavi + naviCountPage - 1;
		
		if(endNavi > pageTotalCount) {
			endNavi = pageTotalCount;
		}
		
		boolean needPrev = true;
		boolean needNext = true;
		
		if(startNavi == 1) {
			needPrev = false;
		}
		if(endNavi == pageTotalCount) {
			needNext = false;
		}
		
		StringBuilder sb = new StringBuilder();
		
		if(needPrev) {
			sb.append("<a style='text-decoration: none;color: black;' href='/group/search/confirm/name?currentPage="+(startNavi-1)+"&search="+search+"'> < </a>");
		}

		//1 ~ 10까지의 숫자를 for문을 이용해서 생성하고 만들어 주는 부분

		for(int i = startNavi; i <= endNavi; i++) {
			if(i == currentPage) {
				sb.append("<a style='text-decoration: none;color: black;' href='/group/search/confirm/name?currentPage="+i+"&search="+search+"'><b> "+i+" </b></a>");
			}
			else {
				sb.append("<a style='text-decoration: none;color: black;' href='/group/search/confirm/name?currentPage="+i+"&search="+search+"'> "+i+" </a>");
			}
		}
		//다음 버튼을 누를 수 있을 때 다음 버튼을 생성하는 부분
		if(needNext) {
			sb.append("<a style='text-decoration: none;color: black;' href='/group/search/confirm/name?currentPage="+(endNavi+1)+"&search="+search+"'> > </a>");
		}
		//태그를 StringBuilder의 append()메소드를 이용해서 붙인 후에 toString()메소드를 이용하여 String으로 만들어저 리턴
		return sb.toString();
	}
	
	public int totalConfirmNameCount(Connection conn, String search) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "SELECT COUNT(*) AS TOTALCOUNT FROM RES_GROUP WHERE RG_STATE!='대기' AND USER_ID=(SELECT MEMBER.USER_ID FROM MEMBER WHERE USER_NAME=?)";
		int recourdTotalCount = 0;
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, search);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				recourdTotalCount = rset.getInt("TOTALCOUNT");
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return recourdTotalCount;
		
	}
	
	//단체확인 아이디 검색
	public ArrayList<GroupReservation> groupConfirmIdSearchList(Connection conn, int currentPage, int recordCountPage, String search){
		ArrayList<GroupReservation> gcList = null;
		PreparedStatement pstmt = null;
//		Statement stmt = null;
		ResultSet rset = null;
		String query = "SELECT * FROM (SELECT RES_GROUP.*, ROW_NUMBER() OVER(ORDER BY RG_NO DESC) AS NUM FROM RES_GROUP WHERE USER_ID=? AND RG_STATE!='대기') WHERE (NUM BETWEEN ? AND ?)";
		int start = currentPage * recordCountPage - (recordCountPage - 1);
		int end = currentPage * recordCountPage;
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, search);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			rset = pstmt.executeQuery();
			gcList = new ArrayList<GroupReservation>();
			while(rset.next()) {
				GroupReservation groupReservation = new GroupReservation();
				groupReservation.setRgNo(rset.getInt("RG_NO"));
				groupReservation.setRgDate(rset.getString("RG_DATE"));
				groupReservation.setRgPlace(rset.getString("RG_PLACE"));
				groupReservation.setRgTime(rset.getString("RG_TIME"));
				groupReservation.setRgState(rset.getString("RG_STATE"));
				groupReservation.setRgPeople(rset.getString("RG_PEOPLE"));
				groupReservation.setUserId(rset.getString("USER_ID"));
				gcList.add(groupReservation);
				
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return gcList;
	}
	
	public String getGroupConfirmIdPageNavi(Connection conn, int currentPage, int recordCountPage, int naviCountPage, String search) {
		int recordTotalCount = totalConfirmIdCount(conn, search);
		
		int pageTotalCount = 0;
		if(recordTotalCount % recordCountPage > 0) {
			pageTotalCount = recordTotalCount / recordCountPage + 1;
		}
		else {
			pageTotalCount = recordTotalCount / recordCountPage;
		}
		
		if(currentPage < 1) {
			currentPage = 1;
		}
		else if(currentPage > pageTotalCount) {
			currentPage = pageTotalCount;
		}
		
		int startNavi = ((currentPage - 1) / naviCountPage) * naviCountPage + 1;
		int endNavi = startNavi + naviCountPage - 1;
		
		if(endNavi > pageTotalCount) {
			endNavi = pageTotalCount;
		}
		
		boolean needPrev = true;
		boolean needNext = true;
		
		if(startNavi == 1) {
			needPrev = false;
		}
		if(endNavi == pageTotalCount) {
			needNext = false;
		}
		
		StringBuilder sb = new StringBuilder();
		
		if(needPrev) {
			sb.append("<a style='text-decoration: none;color: black;' href='/group/search/confirm/id?currentPage="+(startNavi-1)+"&search="+search+"'> < </a>");
		}

		//1 ~ 10까지의 숫자를 for문을 이용해서 생성하고 만들어 주는 부분

		for(int i = startNavi; i <= endNavi; i++) {
			if(i == currentPage) {
				sb.append("<a style='text-decoration: none;color: black;' href='/group/search/confirm/id?currentPage="+i+"&search="+search+"'><b> "+i+" </b></a>");
			}
			else {
				sb.append("<a style='text-decoration: none;color: black;' href='/group/search/confirm/id?currentPage="+i+"&search="+search+"'> "+i+" </a>");
			}
		}
		//다음 버튼을 누를 수 있을 때 다음 버튼을 생성하는 부분
		if(needNext) {
			sb.append("<a style='text-decoration: none;color: black;' href='/group/search/confirm/id?pageConfirmNavi="+(endNavi+1)+"&search="+search+"'> > </a>");
		}
		//태그를 StringBuilder의 append()메소드를 이용해서 붙인 후에 toString()메소드를 이용하여 String으로 만들어저 리턴
		return sb.toString();
	}
	
	public int totalConfirmIdCount(Connection conn, String search) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "SELECT COUNT(*) AS TOTALCOUNT FROM RES_GROUP WHERE RG_STATE!='대기' AND USER_ID=?";
		int recourdTotalCount = 0;
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, search);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				recourdTotalCount = rset.getInt("TOTALCOUNT");
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return recourdTotalCount;
		
	}
	
//	public int groupReservationAckUpdate(Connection conn) {
//		int result = 0;
//		PreparedStatement pstmt = null;
//		String query = "UPDATE RES_GROUP SET RG_STATE='완료' WHERE RG_DATE<TO_CHAR(SYSDATE, 'YYYY-MM-DD') AND RG_STATE IN('승인')";
//		
//		try {
//			pstmt = conn.prepareStatement(query);
//			result = pstmt.executeUpdate();
//			if(result > 0) {
//				JDBCTemplate.commit(conn);
//			}
//			else {
//				JDBCTemplate.rollback(conn);
//			}
//		}
//		catch (Exception e) {
//			e.printStackTrace();
//		}
//		finally {
//			JDBCTemplate.close(pstmt);
//		}
//		return result;
//	}
//	
	public int groupReservationWaitUpdate(Connection conn) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = "UPDATE RES_GROUP SET RG_STATE='거절' WHERE RG_DATE<TO_CHAR(SYSDATE, 'YYYY-MM-DD') AND RG_STATE IN('대기')";
		
		try {
			pstmt = conn.prepareStatement(query);
			result = pstmt.executeUpdate();
			if(result > 0) {
				JDBCTemplate.commit(conn);
			}
			else {
				JDBCTemplate.rollback(conn);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}
	
	public ArrayList<PersonalReservation> personalAckSelectList(Connection conn, int currentPage, int recordCountPage){
		ArrayList<PersonalReservation> paList = null;
		PreparedStatement pstmt = null;
//		Statement stmt = null;
		ResultSet rset = null;
		String query = "SELECT * FROM (SELECT RES_PRIV.*, ROW_NUMBER() OVER(ORDER BY RP_NO DESC) AS NUM FROM RES_PRIV WHERE RP_STATE='승인') WHERE (NUM BETWEEN ? AND ?)";
		int start = currentPage * recordCountPage - (recordCountPage - 1);
		int end = currentPage * recordCountPage;
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rset = pstmt.executeQuery();
			paList = new ArrayList<PersonalReservation>();
			while(rset.next()) {
				PersonalReservation personalReservation = new PersonalReservation();
				personalReservation.setRpNo(rset.getInt("RP_NO"));
				personalReservation.setRpDate(rset.getString("RP_DATE"));
				personalReservation.setRpPlace(rset.getString("RP_PLACE"));
				personalReservation.setRpTime(rset.getString("RP_TIME"));
				personalReservation.setRpKind(rset.getString("RP_KIND"));
				personalReservation.setRpState(rset.getString("RP_STATE"));
				personalReservation.setRpOn(rset.getString("RP_ON"));
				personalReservation.setUserId(rset.getString("USER_ID"));
				paList.add(personalReservation);
				
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return paList;
	}
	
	public String getpersonalAckPageNavi(Connection conn, int currentPage, int recordCountPerPage, int naviCountPerPage) {
		int recordTotalCount = AckPersonalCount(conn);
		
		int pageTotalCount = 0;
		if(recordTotalCount % recordCountPerPage > 0) {
			pageTotalCount = recordTotalCount / recordCountPerPage + 1;
		}
		else {
			pageTotalCount = recordTotalCount / recordCountPerPage;
		}
		
		if(currentPage < 1) {
			currentPage = 1;
		}
		else if(currentPage > pageTotalCount) {
			currentPage = pageTotalCount;
		}
		
		int startNavi = ((currentPage - 1) / naviCountPerPage) * naviCountPerPage + 1;
		int endNavi = startNavi + naviCountPerPage - 1;
		
		if(endNavi > pageTotalCount) {
			endNavi = pageTotalCount;
		}
		
		boolean needPrev = true;
		boolean needNext = true;
		
		if(startNavi == 1) {
			needPrev = false;
		}
		if(endNavi == pageTotalCount) {
			needNext = false;
		}
		
		StringBuilder sb = new StringBuilder();
		
		if(needPrev) {
			sb.append("<a style='text-decoration: none;color: black;' href='/personal/reservation?currentAckPage="+(startNavi-1)+"'> < </a>");
		}

		//1 ~ 10까지의 숫자를 for문을 이용해서 생성하고 만들어 주는 부분

		for(int i = startNavi; i <= endNavi; i++) {
			if(i == currentPage) {
				sb.append("<a style='text-decoration: none;color: black;' href='/personal/reservation?currentAckPage="+i+" '> <b>"+i+"</b> </a>");
			}
			else {
				sb.append("<a style='text-decoration: none;color: black;' href='/personal/reservation?currentAckPage="+i+" '> "+i+" </a>");
			}
		}
		//다음 버튼을 누를 수 있을 때 다음 버튼을 생성하는 부분
		if(needNext) {
			sb.append("<a style='text-decoration: none;color: black;' href='/personal/reservation?currentAckPage="+(endNavi+1)+"'> > </a>");
		}
		//태그를 StringBuilder의 append()메소드를 이용해서 붙인 후에 toString()메소드를 이용하여 String으로 만들어저 리턴
		return sb.toString();
	}
	
	public int AckPersonalCount(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "SELECT COUNT(*) AS TOTALCOUNT FROM RES_PRIV WHERE RP_STATE='승인'";
		int recourdTotalCount = 0;
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				recourdTotalCount = rset.getInt("TOTALCOUNT");
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return recourdTotalCount;
		
	}
	
	public int personalReservationCancle(Connection conn, int personalReservationNo) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = "UPDATE RES_PRIV SET RP_STATE='취소' WHERE RP_NO=?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, personalReservationNo);
			result = pstmt.executeUpdate();
			
			if(result > 0) {
				JDBCTemplate.commit(conn);
			}
			else {
				JDBCTemplate.rollback(conn);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}
	
	public ArrayList<PersonalReservation> personalNameSearchList(Connection conn, int currentPage, int recordCountPage, String search){
		ArrayList<PersonalReservation> pList = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "SELECT * FROM (SELECT RES_PRIV.*, ROW_NUMBER() OVER(ORDER BY RP_NO DESC) AS NUM FROM RES_PRIV WHERE USER_ID=(SELECT MEMBER.USER_ID FROM MEMBER WHERE USER_NAME=?)) WHERE (NUM BETWEEN ? AND ?)";
		int start = currentPage * recordCountPage - (recordCountPage - 1);
		int end = currentPage * recordCountPage;
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, search);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			rset = pstmt.executeQuery();
			pList = new ArrayList<PersonalReservation>();
			
			while(rset.next()) {
				PersonalReservation personalReservation = new PersonalReservation();
				personalReservation.setRpNo(rset.getInt("RP_NO"));
				personalReservation.setRpDate(rset.getString("RP_DATE"));
				personalReservation.setRpPlace(rset.getString("RP_PLACE"));
				personalReservation.setRpTime(rset.getString("RP_TIME"));
				personalReservation.setRpKind(rset.getString("RP_KIND"));
				personalReservation.setRpState(rset.getString("RP_STATE"));
				personalReservation.setRpOn(rset.getString("RP_ON"));
				personalReservation.setUserId(rset.getString("USER_ID"));
				pList.add(personalReservation);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return pList;
	}
	
	public String getpersonalNamePageNavi(Connection conn, int currentPage, int recordCountPage, int naviCountPage, String search){
		int recordTotalCount = totalNameCount(conn, search);
//		System.out.println(search);
		
		int pageTotalCount = 0;
		if(recordTotalCount % recordCountPage > 0) {
			pageTotalCount = recordTotalCount / recordCountPage + 1;
		}
		else {
			pageTotalCount = recordTotalCount / recordCountPage;
		}
		
		if(currentPage < 1) {
			currentPage = 1;
		}
		else if(currentPage > pageTotalCount) {
			currentPage = pageTotalCount;
		}
		
		int startNavi = ((currentPage - 1) / naviCountPage) * naviCountPage + 1;
		int endNavi = startNavi + naviCountPage - 1;
		
		if(endNavi > pageTotalCount) {
			endNavi = pageTotalCount;
		}
		
		boolean needPrev = true;
		boolean needNext = true;
		
		if(startNavi == 1) {
			needPrev = false;
		}
		if(endNavi == pageTotalCount) {
			needNext = false;
		}
		
		StringBuilder sb = new StringBuilder();
		
		if(needPrev) {
			sb.append("<a style='text-decoration: none;color: black;' href='/personal/search/total/name?currentPage="+(startNavi-1)+"&search="+search+"'> < </a>");
		}

		//1 ~ 10까지의 숫자를 for문을 이용해서 생성하고 만들어 주는 부분

		for(int i = startNavi; i <= endNavi; i++) {
			if(i == currentPage) {
				sb.append("<a style='text-decoration: none;color: black;' href='/personal/search/total/name?currentPage="+i+"&search="+search+"'><b> "+i+" </b></a>");
			}
			else {
				sb.append("<a style='text-decoration: none;color: black;' href='/personal/search/total/name?currentPage="+i+"&search="+search+"'> "+i+" </a>");
			}
		}
		//다음 버튼을 누를 수 있을 때 다음 버튼을 생성하는 부분
		if(needNext) {
			sb.append("<a style='text-decoration: none;color: black;' href='/personal/search/total/name?currentPage="+(endNavi+1)+"&search="+search+"'> > </a>");
		}
		//태그를 StringBuilder의 append()메소드를 이용해서 붙인 후에 toString()메소드를 이용하여 String으로 만들어저 리턴
		return sb.toString();
	}
	
	public int totalNameCount(Connection conn, String search) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "SELECT COUNT(*) AS TOTALCOUNT FROM RES_PRIV WHERE USER_ID=(SELECT MEMBER.USER_ID FROM MEMBER WHERE USER_NAME=?)";
		int recourdTotalCount = 0;
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, search);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				recourdTotalCount = rset.getInt("TOTALCOUNT");
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return recourdTotalCount;
		
	}
	
	public ArrayList<PersonalReservation> personalIdSearchList(Connection conn, int currentPage, int recordCountPage, String search){
		ArrayList<PersonalReservation> pList = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "SELECT * FROM (SELECT RES_PRIV.*, ROW_NUMBER() OVER(ORDER BY RP_NO DESC) AS NUM FROM RES_PRIV WHERE USER_ID=?) WHERE (NUM BETWEEN ? AND ?)";
		int start = currentPage * recordCountPage - (recordCountPage - 1);
		int end = currentPage * recordCountPage;
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, search);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			rset = pstmt.executeQuery();
			pList = new ArrayList<PersonalReservation>();
			
			while(rset.next()) {
				PersonalReservation personalReservation = new PersonalReservation();
				personalReservation.setRpNo(rset.getInt("RP_NO"));
				personalReservation.setRpDate(rset.getString("RP_DATE"));
				personalReservation.setRpPlace(rset.getString("RP_PLACE"));
				personalReservation.setRpTime(rset.getString("RP_TIME"));
				personalReservation.setRpKind(rset.getString("RP_KIND"));
				personalReservation.setRpState(rset.getString("RP_STATE"));
				personalReservation.setRpOn(rset.getString("RP_ON"));
				personalReservation.setUserId(rset.getString("USER_ID"));
				pList.add(personalReservation);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return pList;
	}
	
	public String getpersonalIdPageNavi(Connection conn, int currentPage, int recordCountPage, int naviCountPage, String search){
		int recordTotalCount = totalIdCount(conn, search);
//		System.out.println(search);
		
		int pageTotalCount = 0;
		if(recordTotalCount % recordCountPage > 0) {
			pageTotalCount = recordTotalCount / recordCountPage + 1;
		}
		else {
			pageTotalCount = recordTotalCount / recordCountPage;
		}
		
		if(currentPage < 1) {
			currentPage = 1;
		}
		else if(currentPage > pageTotalCount) {
			currentPage = pageTotalCount;
		}
		
		int startNavi = ((currentPage - 1) / naviCountPage) * naviCountPage + 1;
		int endNavi = startNavi + naviCountPage - 1;
		
		if(endNavi > pageTotalCount) {
			endNavi = pageTotalCount;
		}
		
		boolean needPrev = true;
		boolean needNext = true;
		
		if(startNavi == 1) {
			needPrev = false;
		}
		if(endNavi == pageTotalCount) {
			needNext = false;
		}
		
		StringBuilder sb = new StringBuilder();
		
		if(needPrev) {
			sb.append("<a style='text-decoration: none;color: black;' href='/personal/search/total/id?currentPage="+(startNavi-1)+"&search="+search+"'> < </a>");
		}

		//1 ~ 10까지의 숫자를 for문을 이용해서 생성하고 만들어 주는 부분

		for(int i = startNavi; i <= endNavi; i++) {
			if(i == currentPage) {
				sb.append("<a style='text-decoration: none;color: black;' href='/personal/search/total/id?currentPage="+i+"&search="+search+"'><b> "+i+" </b></a>");
			}
			else {
				sb.append("<a style='text-decoration: none;color: black;' href='/personal/search/total/id?currentPage="+i+"&search="+search+"'> "+i+" </a>");
			}
		}
		//다음 버튼을 누를 수 있을 때 다음 버튼을 생성하는 부분
		if(needNext) {
			sb.append("<a style='text-decoration: none;color: black;' href='/personal/search/total/id?currentPage="+(endNavi+1)+"&search="+search+"'> > </a>");
		}
		//태그를 StringBuilder의 append()메소드를 이용해서 붙인 후에 toString()메소드를 이용하여 String으로 만들어저 리턴
		return sb.toString();
	}
	
	public int totalIdCount(Connection conn, String search) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "SELECT COUNT(*) AS TOTALCOUNT FROM RES_PRIV WHERE USER_ID=?";
		int recourdTotalCount = 0;
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, search);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				recourdTotalCount = rset.getInt("TOTALCOUNT");
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return recourdTotalCount;
		
	}
	
	public ArrayList<PersonalReservation> personalAckNameSearchList(Connection conn, int currentPage, int recordCountPage, String search){
		ArrayList<PersonalReservation> paList = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "SELECT * FROM (SELECT RES_PRIV.*, ROW_NUMBER() OVER(ORDER BY RP_NO DESC) AS NUM FROM RES_PRIV WHERE USER_ID=(SELECT MEMBER.USER_ID FROM MEMBER WHERE USER_NAME=?) AND RP_STATE='승인') WHERE (NUM BETWEEN ? AND ?)";
		int start = currentPage * recordCountPage - (recordCountPage - 1);
		int end = currentPage * recordCountPage;
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, search);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			rset = pstmt.executeQuery();
			paList = new ArrayList<PersonalReservation>();
			
			while(rset.next()) {
				PersonalReservation personalReservation = new PersonalReservation();
				personalReservation.setRpNo(rset.getInt("RP_NO"));
				personalReservation.setRpDate(rset.getString("RP_DATE"));
				personalReservation.setRpPlace(rset.getString("RP_PLACE"));
				personalReservation.setRpTime(rset.getString("RP_TIME"));
				personalReservation.setRpKind(rset.getString("RP_KIND"));
				personalReservation.setRpState(rset.getString("RP_STATE"));
				personalReservation.setRpOn(rset.getString("RP_ON"));
				personalReservation.setUserId(rset.getString("USER_ID"));
				paList.add(personalReservation);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return paList;
	}
	
	public String getpersonalAckNamePageNavi(Connection conn, int currentPage, int recordCountPage, int naviCountPage, String search){
		int recordTotalCount = AckNameCount(conn, search);
//		System.out.println(search);
		
		int pageTotalCount = 0;
		if(recordTotalCount % recordCountPage > 0) {
			pageTotalCount = recordTotalCount / recordCountPage + 1;
		}
		else {
			pageTotalCount = recordTotalCount / recordCountPage;
		}
		
		if(currentPage < 1) {
			currentPage = 1;
		}
		else if(currentPage > pageTotalCount) {
			currentPage = pageTotalCount;
		}
		
		int startNavi = ((currentPage - 1) / naviCountPage) * naviCountPage + 1;
		int endNavi = startNavi + naviCountPage - 1;
		
		if(endNavi > pageTotalCount) {
			endNavi = pageTotalCount;
		}
		
		boolean needPrev = true;
		boolean needNext = true;
		
		if(startNavi == 1) {
			needPrev = false;
		}
		if(endNavi == pageTotalCount) {
			needNext = false;
		}
		
		StringBuilder sb = new StringBuilder();
		
		if(needPrev) {
			sb.append("<a style='text-decoration: none;color: black;' href='/personal/search/ack/name?currentPage="+(startNavi-1)+"&search="+search+"'> < </a>");
		}

		//1 ~ 10까지의 숫자를 for문을 이용해서 생성하고 만들어 주는 부분

		for(int i = startNavi; i <= endNavi; i++) {
			if(i == currentPage) {
				sb.append("<a style='text-decoration: none;color: black;' href='/personal/search/ack/name?currentPage="+i+"&search="+search+"'><b> "+i+" </b></a>");
			}
			else {
				sb.append("<a style='text-decoration: none;color: black;' href='/personal/search/ack/name?currentPage="+i+"&search="+search+"'> "+i+" </a>");
			}
		}
		//다음 버튼을 누를 수 있을 때 다음 버튼을 생성하는 부분
		if(needNext) {
			sb.append("<a style='text-decoration: none;color: black;' href='/personal/search/ack/name?currentPage="+(endNavi+1)+"&search="+search+"'> > </a>");
		}
		//태그를 StringBuilder의 append()메소드를 이용해서 붙인 후에 toString()메소드를 이용하여 String으로 만들어저 리턴
		return sb.toString();
	}
	
	public int AckNameCount(Connection conn, String search) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "SELECT COUNT(*) AS TOTALCOUNT FROM RES_PRIV WHERE USER_ID=(SELECT MEMBER.USER_ID FROM MEMBER WHERE USER_NAME=?) AND RP_STATE='승인'";
		int recourdTotalCount = 0;
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, search);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				recourdTotalCount = rset.getInt("TOTALCOUNT");
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return recourdTotalCount;
		
	}
	
	public ArrayList<PersonalReservation> personalAckIdSearchList(Connection conn, int currentPage, int recordCountPage, String search){
		ArrayList<PersonalReservation> paList = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "SELECT * FROM (SELECT RES_PRIV.*, ROW_NUMBER() OVER(ORDER BY RP_NO DESC) AS NUM FROM RES_PRIV WHERE USER_ID=? AND RP_STATE='승인') WHERE (NUM BETWEEN ? AND ?)";
		int start = currentPage * recordCountPage - (recordCountPage - 1);
		int end = currentPage * recordCountPage;
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, search);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			rset = pstmt.executeQuery();
			paList = new ArrayList<PersonalReservation>();
			
			while(rset.next()) {
				PersonalReservation personalReservation = new PersonalReservation();
				personalReservation.setRpNo(rset.getInt("RP_NO"));
				personalReservation.setRpDate(rset.getString("RP_DATE"));
				personalReservation.setRpPlace(rset.getString("RP_PLACE"));
				personalReservation.setRpTime(rset.getString("RP_TIME"));
				personalReservation.setRpKind(rset.getString("RP_KIND"));
				personalReservation.setRpState(rset.getString("RP_STATE"));
				personalReservation.setRpOn(rset.getString("RP_ON"));
				personalReservation.setUserId(rset.getString("USER_ID"));
				paList.add(personalReservation);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return paList;
	}
	
	public String getpersonalAckIdPageNavi(Connection conn, int currentPage, int recordCountPage, int naviCountPage, String search){
		int recordTotalCount = AckIdCount(conn, search);
//		System.out.println(search);
		
		int pageTotalCount = 0;
		if(recordTotalCount % recordCountPage > 0) {
			pageTotalCount = recordTotalCount / recordCountPage + 1;
		}
		else {
			pageTotalCount = recordTotalCount / recordCountPage;
		}
		
		if(currentPage < 1) {
			currentPage = 1;
		}
		else if(currentPage > pageTotalCount) {
			currentPage = pageTotalCount;
		}
		
		int startNavi = ((currentPage - 1) / naviCountPage) * naviCountPage + 1;
		int endNavi = startNavi + naviCountPage - 1;
		
		if(endNavi > pageTotalCount) {
			endNavi = pageTotalCount;
		}
		
		boolean needPrev = true;
		boolean needNext = true;
		
		if(startNavi == 1) {
			needPrev = false;
		}
		if(endNavi == pageTotalCount) {
			needNext = false;
		}
		
		StringBuilder sb = new StringBuilder();
		
		if(needPrev) {
			sb.append("<a style='text-decoration: none;color: black;' href='/personal/search/ack/id?currentPage="+(startNavi-1)+"&search="+search+"'> < </a>");
		}

		//1 ~ 10까지의 숫자를 for문을 이용해서 생성하고 만들어 주는 부분

		for(int i = startNavi; i <= endNavi; i++) {
			if(i == currentPage) {
				sb.append("<a style='text-decoration: none;color: black;' href='/personal/search/ack/id?currentPage="+i+"&search="+search+"'><b> "+i+" </b></a>");
			}
			else {
				sb.append("<a style='text-decoration: none;color: black;' href='/personal/search/ack/id?currentPage="+i+"&search="+search+"'> "+i+" </a>");
			}
		}
		//다음 버튼을 누를 수 있을 때 다음 버튼을 생성하는 부분
		if(needNext) {
			sb.append("<a style='text-decoration: none;color: black;' href='/personal/search/ack/id?currentPage="+(endNavi+1)+"&search="+search+"'> > </a>");
		}
		//태그를 StringBuilder의 append()메소드를 이용해서 붙인 후에 toString()메소드를 이용하여 String으로 만들어저 리턴
		return sb.toString();
	}
	
	public int AckIdCount(Connection conn, String search) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String query = "SELECT COUNT(*) AS TOTALCOUNT FROM RES_PRIV WHERE USER_ID=? AND RP_STATE='승인'";
		int recourdTotalCount = 0;
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, search);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				recourdTotalCount = rset.getInt("TOTALCOUNT");
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return recourdTotalCount;
		
	}
	
	public int groupReservationFinish(Connection conn, int groupReservationNo) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = "UPDATE RES_GROUP SET RG_STATE='완료' WHERE RG_NO=?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, groupReservationNo);
			result = pstmt.executeUpdate();
			
			if(result > 0) {
				JDBCTemplate.commit(conn);
			}
			else {
				JDBCTemplate.rollback(conn);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}
	
	public int personalReservetionFinish(Connection conn, int personalReservationNo) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = "UPDATE RES_PRIV SET RP_STATE='완료' WHERE RP_NO=?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, personalReservationNo);
			result = pstmt.executeUpdate();
			if(result > 0) {
				JDBCTemplate.commit(conn);
			}
			else {
				JDBCTemplate.rollback(conn);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}
}
