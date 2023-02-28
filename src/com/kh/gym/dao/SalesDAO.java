package com.kh.gym.dao;

import com.kh.gym.util.Common;
import com.kh.gym.vo.SalesVO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class SalesDAO {
    Connection conn = null;
    Statement stmt = null;
    ResultSet rs = null;
    Scanner sc = new Scanner(System.in);

    public List<SalesVO> dailySalSel() {
        List<SalesVO> list = new ArrayList<>();
        try {
            conn = Common.getConnection();
            stmt = conn.createStatement();
            String sql = "SELECT SUM(SALES), TO_CHAR(P_DATE,'YYYY-MM-DD') FROM SALES_STATEMENT GROUP BY TO_CHAR(P_DATE,'YYYY-MM-DD') ORDER BY TO_CHAR(P_DATE,'YYYY-MM-DD')";
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                String pDateStr = rs.getString("TO_CHAR(P_DATE,'YYYY-MM-DD')");
                int sumSales = rs.getInt("SUM(SALES)");
                SalesVO vo = new SalesVO();
                vo.setP_DateStr(pDateStr);
                vo.setSales(sumSales);
                list.add(vo);
            }
            Common.close(rs);
            Common.close(stmt);
            Common.close(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public void dailySalSelPrint(List<SalesVO> list) {
        for (SalesVO e : list) {
            System.out.println("일 : " + e.getP_DateStr());
            System.out.println("매출 : " + e.getSales());
            System.out.println("--------------------------------------");
        }
    }


    public List<SalesVO> monthlySalSel() {
        List<SalesVO> list = new ArrayList<>();
        try {
            conn = Common.getConnection();
            stmt = conn.createStatement();
            String sql = "SELECT SUM(SALES), TO_CHAR(P_DATE, 'YYYY/MM') FROM SALES_STATEMENT GROUP BY TO_CHAR(P_DATE, 'YYYY/MM')";
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                String pDateStr = rs.getString("TO_CHAR(P_DATE,'YYYY/MM')");
                int sumSales = rs.getInt("SUM(SALES)");
                SalesVO vo = new SalesVO();
                vo.setP_DateStr(pDateStr);
                vo.setSales(sumSales);
                list.add(vo);
            }
            Common.close(rs);
            Common.close(stmt);
            Common.close(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public void monthlySalSelPrint(List<SalesVO> list) {
        for (SalesVO e : list) {
            System.out.println("월 : " + e.getP_DateStr());
            System.out.println("매출 : " + e.getSales());
            System.out.println("--------------------------------------");
        }
    }

    public List<SalesVO> annualSalSel() {
        List<SalesVO> list = new ArrayList<>();
        try {
            conn = Common.getConnection();
            stmt = conn.createStatement();
            String sql = "SELECT SUM(SALES), TO_CHAR(P_DATE, 'YYYY') FROM SALES_STATEMENT GROUP BY TO_CHAR(P_DATE, 'YYYY')";
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                String pDate = rs.getString("TO_CHAR(P_DATE,'YYYY')");
                int sumSales = rs.getInt("SUM(SALES)");
                SalesVO vo = new SalesVO();
                vo.setP_DateStr((pDate));
                vo.setSales(sumSales);
                list.add(vo);
            }
            Common.close(rs);
            Common.close(stmt);
            Common.close(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public void annualSalSelPrint(List<SalesVO> list) {
        for (SalesVO e : list) {
            System.out.println("년 : " + e.getP_DateStr());
            System.out.println("매출 : " + e.getSales());
            System.out.println("--------------------------------------");
        }
    }

    public void salesInsert() {
        System.out.print("주문번호 : ");
        int orNo = sc.nextInt();
        System.out.print("회원번호 : ");
        int memNo = sc.nextInt();
        System.out.print("회원이름 : ");
        String mName = sc.next();
        System.out.print("상품이름 : ");
        String purchase  = sc.next();
        System.out.print("매출 : ");
        int sales = sc.nextInt();
        System.out.print("구매날짜 : ");
        String pDate = sc.next();

        String sql = "INSERT INTO SALES_STATEMENT(ORDER_NO,MEM_ID,MNAME,PURCHASE,SALES,P_DATE) VALUES ("
                + orNo + ", " + memNo + ", " + "'" + mName + "'" + ", " + "'" + purchase + "'" + ", "
                + sales + ", " + "'" + pDate + "'" + ")";
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

    public void salesUpdate() {
        System.out.print("변경할 사원의 이름을 입력 하세요 : ");
        String name = sc.next();
        System.out.print("상품이름 : ");
        String purchase = sc.next();
        System.out.print("매출 : " );
        int sales = sc.nextInt();
        System.out.print("구매날짜 : " );
        String pDate = sc.next();

        String query = "UPDATE SALES_STATEMENT "
                + "SET PURCHASE = " + "'" + purchase + "', "
                + "SALES = " + sales + ", "
                + "P_DATE = " + "'" + pDate + "', "
                + "WHERE MNAME = " + "'" + name + "'";
        try {
            conn = Common.getConnection();
            stmt = conn.createStatement();
            int ret = stmt.executeUpdate(query);
            System.out.println("Return : " + ret);

        } catch (Exception e) {
            e.printStackTrace();
        }
        Common.close(stmt);
        Common.close(conn);
    }
}