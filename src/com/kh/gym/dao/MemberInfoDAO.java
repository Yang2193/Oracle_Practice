package com.kh.gym.dao;

import com.kh.gym.util.Common;
import com.kh.gym.vo.MemberInfoVO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;

public class MemberInfoDAO {

    Connection conn = null; // 자바와 오라클에 대한 연결 설정
    Statement stmt = null;  // SQL 문을 수행하기 위한 객체
    ResultSet rs = null; // statement 동작에 대한 결과로 전달되는 DB의 내용


    public List<MemberInfoVO> M_InfoSelect() {
        List<MemberInfoVO> list = new ArrayList<>();
        try {
            conn = Common.getConnection();
            stmt = conn.createStatement();
            String sql = "SELECT * FROM MEMBERINFO";
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

    public void M_InfoView(List<MemberInfoVO> list) {
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

    //특정 회원 조회 쿼리
    public List<MemberInfoVO> M_SelectSomeone() {
        List<MemberInfoVO> list = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        try {
            conn = Common.getConnection();
            stmt = conn.createStatement();
            System.out.print("조회하실 회원의 회원번호를 입력하세요. : ");
            int num = sc.nextInt();
            String sql = "SELECT * FROM MEMBERINFO WHERE MEM_ID = " + num;
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

    public void M_SomeoneView(List<MemberInfoVO> list) {
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

    public void M_InfoInsert() {
        Scanner sc = new Scanner(System.in);
        System.out.println("===== 회원 정보를 입력하세요. =====");
        System.out.print("회원 번호 : ");
        int mem_Id = sc.nextInt();
        System.out.print("회원 성함 : ");
        String name = sc.next();
        sc.nextLine();
        System.out.print("이용하시는 상품 : ");
        String pName = sc.nextLine();

        System.out.print("PT 남은 횟수 : ");
        String pt_Remain = sc.next();
        if (pt_Remain.equals("0")) pt_Remain = "";
        System.out.print("성별 : ");
        String gender = sc.next();
        System.out.print("전화번호 : ");
        String pNum = sc.next();
        System.out.print("라커 설정 : ");
        String lockNum = sc.next();
        if (lockNum.equals("0")) lockNum = "";
        System.out.print("등록일 설정 : ");
        String rDate = sc.next();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        //만료일 설정
        System.out.print("기간 설정 : ");
        int term = sc.nextInt();

        Calendar cal = null;
        try {
            Date date = Date.valueOf(rDate);
            cal = Calendar.getInstance();
            cal.setTime(date);
            cal.add(Calendar.DATE, term);

        } catch (Exception e) {
            e.printStackTrace();
        }

        Date dDate = Date.valueOf(sdf.format(cal.getTime()));


        String sql = "INSERT INTO MEMBERINFO VALUES(" +
                mem_Id + "," + "'" + name + "'" + "," + "'" + pName + "'" + "," + "'" + pt_Remain + "'" + ","
                + "'" + dDate + "'" + "," + "'" + gender + "'" + "," + "'" + pNum + "'" + "," +
                "'" + lockNum + "'" + "," + "'" + rDate + "'" + ")";
        try {
            conn = Common.getConnection();
            stmt = conn.createStatement();
            int ret = stmt.executeUpdate(sql);
            System.out.println("Return : " + ret);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Common.close(stmt);
        Common.close(conn);

    }

    public void m_infoModify() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("[1] 회원번호 입력, [2] 종료");
            int choice = sc.nextInt();
            if(choice == 2){
                System.out.println ("회원정보 수정을 종료합니다.");
                break;
            }
            System.out.print("회원 번호 : ");
            int mem_Id = sc.nextInt();
            while(true) {
                System.out.println("어떤 회원 정보를 수정하시겠습니까?");
                System.out.print("[1]성함, [2]이용 중인 상품, [3]남은 PT횟수, [4] 만료일, [5] 성별, [6] 전화번호, [7] 라커, [8] 마침");
                int sel = sc.nextInt();
                String sql = null;
                switch (sel) {
                    case 1: {
                        System.out.print("바꿀 성함 : ");
                        String name = sc.next();
                        sql = "UPDATE MEMBERINFO SET MNAME =" + "'" + name +"'"+
                                "WHERE MEM_ID =" + mem_Id;
                        break;
                    }
                    case 2: {
                        System.out.print("변경할 상품 입력 : ");
                        String pName = sc.nextLine();
                        sql = "UPDATE MEMBERINFO SET PNAME =" + "'" + pName +"'"+
                                "WHERE MEM_ID =" + mem_Id;
                        break;
                    }
                    case 3: {
                        System.out.print("변경할 PT횟수 입력 : ");
                        int ptR = sc.nextInt();
                        sql = "UPDATE MEMBERINFO SET PT_REMAIN =" + ptR +
                                "WHERE MEM_ID =" + mem_Id;
                        break;
                    }
                    case 4: {
                        System.out.print("변경할 기간 입력 : ");
                        int term = sc.nextInt();
                        sql = "UPDATE MEMBERINFO SET DUE_DATE = DUE_DATE"  +"+"+ term +
                                "WHERE MEM_ID =" + mem_Id;
                        break;
                    }
                    case 5: {
                        System.out.print("변경할 성별 입력 : ");
                        String gender = sc.next();
                        sql = "UPDATE MEMBERINFO SET GENDER =" + "'" + gender +"'"+
                                "WHERE MEM_ID =" + mem_Id;
                        break;
                    }
                    case 6: {
                        System.out.print("변경할 전화번호 입력 : ");
                        String pNum = sc.next();
                        sql = "UPDATE MEMBERINFO SET PHONE_NUM =" + "'" + pNum +"'"+
                                "WHERE MEM_ID =" + mem_Id;
                        break;
                    }
                    case 7: {
                        System.out.print("변경할 라커번호 입력 : ");
                        String lNum = sc.next();
                        sql = "UPDATE MEMBERINFO SET LOCKER =" + "'" + lNum +"'"+
                                "WHERE MEM_ID =" + mem_Id;
                        break;
                    }
                    case 8:
                        System.out.println(mem_Id + "번 회원님의 정보 수정을 마칩니다.");
                        break;
                }
                if(sql == null) break;
                try {
                    conn = Common.getConnection();
                    stmt = conn.createStatement();
                    int ret = stmt.executeUpdate(sql);
                    System.out.println("Return : " + ret);
                    System.out.println("수정 완료");
                }catch(Exception e){
                    e.printStackTrace();
                }
                Common.close(stmt);
                Common.close(conn);

            }
        }
    }

    public void m_InfoDelete(){
        Scanner sc = new Scanner(System.in);
        System.out.print("삭제하실 회원의 회원번호를 입력해주세요. : ");
        int mem_Id = sc.nextInt();
        String sql = "DELETE FROM MEMBERINFO WHERE MEM_ID = " + mem_Id;
        try {
            conn = Common.getConnection();
            stmt = conn.createStatement();
            int ret = stmt.executeUpdate(sql);
            System.out.println("Return : " + ret);
            System.out.println("삭제 완료");
        }catch(Exception e){
            e.printStackTrace();
        }
        Common.close(stmt);
        Common.close(conn);
    }
}
