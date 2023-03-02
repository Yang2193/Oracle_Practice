package com.kh.gym.dao;

import com.kh.gym.util.Common;
import com.kh.gym.vo.MemberInfoVO;
import com.kh.gym.vo.PtVO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PtDao {
    Connection conn = null; // 자바와 오라클에 대한 연결 설정
    Statement stmt = null;  // SQL 문을 수행하기 위한 객체
    PreparedStatement pStmt = null;
    ResultSet rs = null; // statement 동작에 대한 결과로 전달되는 DB의 내용

    public List<MemberInfoVO> ptMemberList(){
        List<MemberInfoVO> list = new ArrayList<>();
        try {
            conn = Common.getConnection();
            stmt = conn.createStatement();
            String sql = "SELECT * FROM MEMBERINFO WHERE SUBSTR(PNAME, 1, 2) = 'PT'";
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                int id = rs.getInt("MEM_ID");
                String mName = rs.getString("MNAME");
                String pName = rs.getString("PNAME");
                int ptRemain = rs.getInt("PT_REMAIN");
                Date dDate = rs.getDate("DUE_DATE");
                String gender = rs.getString("GENDER");
                String pNum = rs.getString("PHONE_NUM");
                String lNum = rs.getString("LOCKER");
                Date rDate = rs.getDate("REG_DATE");

                MemberInfoVO vo = new MemberInfoVO(id, mName, pName, ptRemain, dDate, gender, pNum, lNum, rDate);
                list.add(vo);
            }
            Common.close(rs); // 연결과 역순으로 해제
            Common.close(stmt);
            Common.close(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public void ptMember(List<MemberInfoVO> list){
        System.out.println("회원번호  회원성함  이용 중인 상품  남은 PT횟수  만료일        성별        전화번호      라커번호      등록일");

        for (MemberInfoVO e : list) {
            System.out.print(" " + e.getMem_Id());
            System.out.print("    " + e.getMname());
            System.out.print("      " + e.getPname());
            System.out.print("        " + e.getPtRemain());
            System.out.print("      " + e.getDue_Date());
            System.out.print("    " + e.getGender());
            System.out.print("    " + e.getPhoneNum());
            System.out.print("    " + e.getLockNum());
            System.out.print("    " + e.getReg_Date());
            System.out.println();
        }
    }

    public List<MemberInfoVO> specificPtMemberList(){
        List<MemberInfoVO> list = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        System.out.print("회원번호 입력 : ");
        int num = sc.nextInt();

        String sql = "SELECT * FROM MEMBERINFO WHERE SUBSTR(PNAME, 1, 2) = 'PT' AND MEM_ID = ?";

        try {
            conn = Common.getConnection();
            pStmt = conn.prepareStatement(sql);
            pStmt.setInt(1, num);
            rs = pStmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("MEM_ID");
                String mName = rs.getString("MNAME");
                String pName = rs.getString("PNAME");
                int ptRemain = rs.getInt("PT_REMAIN");
                Date dDate = rs.getDate("DUE_DATE");
                String gender = rs.getString("GENDER");
                String pNum = rs.getString("PHONE_NUM");
                String lNum = rs.getString("LOCKER");
                Date rDate = rs.getDate("REG_DATE");

                MemberInfoVO vo = new MemberInfoVO(id, mName, pName, ptRemain, dDate, gender, pNum, lNum, rDate);
                list.add(vo);
            }
            Common.close(rs); // 연결과 역순으로 해제
            Common.close(stmt);
            Common.close(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;

    }

    public void specificPtMember(List<MemberInfoVO> list){
        for (MemberInfoVO e : list) {
            System.out.println("회원번호 : " + e.getMem_Id());
            System.out.println("회원성함 : " + e.getMname());
            System.out.println("이용 중인 상품 : " + e.getPname());
            System.out.println("남은 PT횟수 : " + e.getPtRemain());
            System.out.println("만료일 : " + e.getDue_Date());
            System.out.println("성별 : " + e.getGender());
            System.out.println("전화번호 : " + e.getPhoneNum());
            System.out.println("라커번호 : " + e.getLockNum());
            System.out.println("등록일 : " + e.getReg_Date());
            System.out.println("===============");
        }
    }

    public List<PtVO> showAllPtDataList(){
        List<PtVO> list = new ArrayList<>();
        try {
            conn = Common.getConnection();
            stmt = conn.createStatement();
            String sql = "SELECT ROWNUM, MEM_ID, MNAME, PT_DATE  FROM (SELECT  M.MEM_ID, M.MNAME, PD.PT_DATE FROM MEMBERINFO M JOIN PT_DATA PD ON M.MEM_ID = PD.MEM_ID)";
            rs = stmt.executeQuery(sql);
            while(rs.next()){
                int rownum = rs.getInt("ROWNUM");
                int id = rs.getInt("MEM_ID");
                String name = rs.getString("MNAME");
                Date date = rs.getDate("PT_DATE");

                PtVO vo = new PtVO();
                vo.setRownum(rownum);
                vo.setId(id);
                vo.setName(name);
                vo.setDate(date);
                list.add(vo);
            }
            Common.close(rs);
            Common.close(stmt);
            Common.close(conn);
        } catch(Exception e){
            e.printStackTrace();
        }
     return list;
    }

    public void showAllPtData(List<PtVO> list){
        System.out.println("번호   회원번호   회원성함     날짜");
        for(PtVO e : list){
            System.out.print(" " + e.getRownum());
            System.out.print("      " + e.getId());
            System.out.print("     " + e.getName());
            System.out.print("    "  + e.getDate());
            System.out.println();

        }

    }

    public List<PtVO> showSpecificPtDataList(){
        List<PtVO> list = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        System.out.print("회원번호 입력 : ");
        int num = sc.nextInt();
        try {
            conn = Common.getConnection();
            stmt = conn.createStatement();
            String sql = "SELECT ROWNUM, MEM_ID, MNAME, PT_DATE  FROM (SELECT  M.MEM_ID, M.MNAME, PD.PT_DATE FROM MEMBERINFO M JOIN PT_DATA PD ON M.MEM_ID = PD.MEM_ID WHERE PD.MEM_ID =" + num +"ORDER BY PT_DATE)";
            rs = stmt.executeQuery(sql);
            while(rs.next()){
                int rownum = rs.getInt("ROWNUM");
                int id = rs.getInt("MEM_ID");
                String name = rs.getString("MNAME");
                Date date = rs.getDate("PT_DATE");

                PtVO vo = new PtVO();
                vo.setRownum(rownum);
                vo.setId(id);
                vo.setName(name);
                vo.setDate(date);
                list.add(vo);
            }
            Common.close(rs);
            Common.close(stmt);
            Common.close(conn);
        } catch(Exception e){
            e.printStackTrace();
        }
        return list;
    }

    public void showSpecificPtData(List<PtVO> list){
        System.out.println("회차   회원번호   회원성함     날짜");
        for(PtVO e : list){
            System.out.print(" " + e.getRownum());
            System.out.print("      " + e.getId());
            System.out.print("     " + e.getName());
            System.out.print("    "  + e.getDate());
            System.out.println();
        }

    }

    public void ptDataChoice(){
        Scanner sc = new Scanner(System.in);
        System.out.println("[1] 전체 조회, [2] 특정 회원 조회");
        int sel = sc.nextInt();
        switch (sel){
            case 1: List<PtVO> list= showAllPtDataList();
                    showAllPtData(list);
                    break;
            case 2: List<PtVO> list1 = showSpecificPtDataList();
                    showSpecificPtData(list1);
                    break;
            default: break;
        }

    }
    public void setPtRemain(){
        Scanner sc = new Scanner(System.in);
        System.out.print("회원번호 입력 : ");
        int id = sc.nextInt();
        System.out.print("설정할 PT횟수 입력(*주의 : 입력 전에 이전 PT기록 초기화 필요) : ");
        int num = sc.nextInt();
        String sql = "UPDATE MEMBERINFO SET PT_REMAIN = ? WHERE MEM_ID = ?";

        try{
            conn = Common.getConnection();
            pStmt = conn.prepareStatement(sql);
            pStmt.setInt(1, num);
            pStmt.setInt(2, id);
            pStmt.executeUpdate();
            System.out.println("설정 완료.");

        }catch(Exception e){
            e.printStackTrace();
        }
        Common.close(pStmt);
        Common.close(conn);
        updatePtRemain(id);

    }
    public void insertPtData(int id){
        Scanner sc = new Scanner(System.in);
        System.out.print("날짜 입력 : ");
        Date date = Date.valueOf(sc.next());
        String sql = "INSERT INTO PT_DATA VALUES(?, ?)";
        try{
            conn = Common.getConnection();
            pStmt = conn.prepareStatement(sql);
            pStmt.setInt(1, id);
            pStmt.setDate(2, date);
            pStmt.executeUpdate();
            System.out.println("PT기록 입력 완료");
        } catch(Exception e){
            e.printStackTrace();
        }
        Common.close(pStmt);
        Common.close(conn);
    }

    public void updatePtRemain(int id){
        String sql = "UPDATE MEMBERINFO SET PT_REMAIN = PT_REMAIN - (SELECT COUNT(*) FROM PT_DATA WHERE MEM_ID = ?) WHERE MEM_ID = ?";
        try{
            conn = Common.getConnection();
            pStmt = conn.prepareStatement(sql);
            pStmt.setInt(1, id);
            pStmt.setInt(2, id);
            pStmt.executeUpdate();

        } catch(Exception e){
            e.printStackTrace();
        }
        Common.close(pStmt);
        Common.close(conn);
    }

    public void insertPtDataProgram(){
        Scanner sc = new Scanner(System.in);
        System.out.print("회원번호 입력 : ");
        int id = sc.nextInt();
        insertPtData(id);
        updatePtRemain(id);

    }

    public void updatePtData(){
        Scanner sc = new Scanner(System.in);
        System.out.print("회원번호 입력 : ");
        int id = sc.nextInt();
        System.out.print("날짜 입력 : ");
        Date date = Date.valueOf(sc.next());
        System.out.print("수정할 회원번호 입력 : ");
        int id2 = sc.nextInt();
        System.out.print("수정할 날짜 입력 : ");
        String date2 = sc.next();
        String sql = "UPDATE PT_DATA SET MEM_ID = ?, PT_DATE = ? WHERE TO_CHAR(PT_DATE,'YYYY-MM-DD') = ? AND MEM_ID = ?";
        try{
            conn = Common.getConnection();
            pStmt = conn.prepareStatement(sql);
            pStmt.setInt(1, id2);
            pStmt.setString(2, date2);
            pStmt.setDate(3, date);
            pStmt.setInt(4, id);
            pStmt.executeUpdate();
            System.out.println("PT기록 수정 완료");
        } catch(Exception e){
            e.printStackTrace();
        }
        Common.close(pStmt);
        Common.close(conn);

    }

    public void deleteAllPtData(){
        Scanner sc = new Scanner(System.in);
        System.out.println("회원 번호 입력 시 해당 회원의 모든 PT기록이 지워집니다.");
        System.out.println("회원 번호 입력 : ");
        int id = sc.nextInt();
        String sql = "DELETE FROM PT_DATA WHERE MEM_ID = ?";
        try{
            conn = Common.getConnection();
            pStmt = conn.prepareStatement(sql);
            pStmt.setInt(1, id);
            pStmt.executeUpdate();
            System.out.println(id + "번 회원님 기록 삭제 완료");
        } catch(Exception e){
            e.printStackTrace();
        }
        Common.close(pStmt);
        Common.close(conn);
    }

    public void deleteSomePtData(){
        Scanner sc = new Scanner(System.in);
        System.out.println("회원 번호와 날짜 입력 시 해당 날짜의 PT기록이 지워집니다.");
        System.out.println("회원 번호 입력 : ");
        int id = sc.nextInt();
        System.out.println("날짜 입력 : ");
        String date = sc.next();
        String sql = "DELETE FROM PT_DATA WHERE MEM_ID = ? AND TO_CHAR(PT_DATE, 'YYYY-MM-DD') = ?";

        try{
            conn = Common.getConnection();
            pStmt = conn.prepareStatement(sql);
            pStmt.setInt(1, id);
            pStmt.setString(2, date);
            pStmt.executeUpdate();
            System.out.println(id + "번 회원님의 " + date + "의 기록 삭제 완료");
        } catch(Exception e){
            e.printStackTrace();
        }
        Common.close(pStmt);
        Common.close(conn);
    }

    public void deleteChoice() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("[1] 전체 삭제, [2] 하나만 삭제, [3] 이전 메뉴로");
            int sel = sc.nextInt();
            switch (sel) {
                case 1: deleteAllPtData();
                        break;
                case 2: deleteSomePtData();
                        break;
                case 3: System.out.println("이전 메뉴로 돌아갑니다.");
                return;
            }

        }
    }
}
