package com.kh.gym.dao;
import com.kh.gym.util.Common;
import com.kh.gym.vo.SalesVO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class SalesDAO {
    Connection conn = null;
    Statement stmt = null;
    PreparedStatement pStmt = null;
    ResultSet rs = null;
    Scanner sc = new Scanner(System.in);

    public List<SalesVO> dailySalSel() {
        List<SalesVO> list = new ArrayList<>();
        try {
            conn = Common.getConnection();
            stmt = conn.createStatement();
            String sql = "SELECT SUM(SALES), TO_CHAR(P_DATE,'YYYY/MM/DD') FROM SALES_STATEMENT GROUP BY TO_CHAR(P_DATE,'YYYY/MM/DD') ORDER BY TO_CHAR(P_DATE,'YYYY/MM/DD')";
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                String pDate = rs.getString("TO_CHAR(P_DATE,'YYYY/MM/DD')");
                int sumSales = rs.getInt("SUM(SALES)");
                SalesVO vo = new SalesVO();
                vo.setP_DateStr(pDate);
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
            String sql = "SELECT SUM(SALES), TO_CHAR(P_DATE,'YYYY/MM') FROM SALES_STATEMENT GROUP BY TO_CHAR(P_DATE,'YYYY/MM') ORDER BY TO_CHAR(P_DATE,'YYYY/MM')";
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
            String sql = "SELECT SUM(SALES),TO_CHAR(P_DATE,'YYYY') FROM SALES_STATEMENT GROUP BY TO_CHAR(P_DATE,'YYYY') ORDER BY TO_CHAR(P_DATE,'YYYY')";
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
        String purchase = sc.next();
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
        System.out.print("수정할 주문번호를 입력 하세요 : ");
        int order_no = sc.nextInt();
        System.out.print("회원번호 : ");
        int no = sc.nextInt();
        System.out.print("회원이름 : ");
        String name = sc.next();
        System.out.print("상품이름 : ");
        String purchase = sc.next();
        System.out.print("매출 : ");
        int sales = sc.nextInt();
        System.out.print("구매날짜 : ");
        String pDate = sc.next();

        String query = "UPDATE SALES_STATEMENT "
                + "SET MEM_ID = " + no + ","
                + "MNAME = " + "'" + name + "'" + ","
                + "PURCHASE = " + "'" + purchase + "'" + ","
                + "SALES = " + sales + ","
                + "P_DATE = " + "'" + pDate + "'"
                + "WHERE ORDER_NO = " + order_no ;
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

    public List<SalesVO> somDaySalSel() {
        List<SalesVO> list = new ArrayList<>();
        try {
            conn = Common.getConnection();
            System.out.print("원하는 날을 입력 하세요 : ");
            String wtDate = sc.nextLine();

            String sql = "SELECT * FROM SALES_STATEMENT WHERE TO_CHAR(P_DATE,'YYYY/MM/DD') = ? ORDER BY P_DATE";
            pStmt = conn.prepareStatement(sql);
            pStmt.setString(1,wtDate);
            rs = pStmt.executeQuery();
            while (rs.next()) {
                int no = rs.getInt("ORDER_NO");
                int id = rs.getInt("MEM_ID");
                String name = rs.getString("MNAME");
                String purchase = rs.getString("PURCHASE");
                int sales = rs.getInt("SALES");
                String pDate = rs.getString("P_DATE");

                SalesVO vo = new SalesVO();
                vo.setOrder_No(no);
                vo.setMem_ID(id);
                vo.setMName(name);
                vo.setPurchase(purchase);
                vo.setSales(sales);
                vo.setP_DateStr((pDate));
                list.add(vo);
            }
            Common.close(rs);
            Common.close(pStmt);
            Common.close(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<SalesVO> somMonthSalSel() {
        List<SalesVO> list = new ArrayList<>();
        try {
            conn = Common.getConnection();
            System.out.print("원하는 월을 입력 하세요 : ");
            String wtDate = sc.nextLine();

            String sql = "SELECT * FROM SALES_STATEMENT WHERE TO_CHAR(P_DATE,'YYYY/MM') = ? ORDER BY P_DATE";
            pStmt = conn.prepareStatement(sql);
            pStmt.setString(1,wtDate);
            rs = pStmt.executeQuery();
            while (rs.next()) {
                int no = rs.getInt("ORDER_NO");
                int id = rs.getInt("MEM_ID");
                String name = rs.getString("MNAME");
                String purchase = rs.getString("PURCHASE");
                int sales = rs.getInt("SALES");
                String pDate = rs.getString("P_DATE");

                SalesVO vo = new SalesVO();
                vo.setOrder_No(no);
                vo.setMem_ID(id);
                vo.setMName(name);
                vo.setPurchase(purchase);
                vo.setSales(sales);
                vo.setP_DateStr((pDate));
                list.add(vo);
            }
            Common.close(rs);
            Common.close(pStmt);
            Common.close(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<SalesVO> somYearSalSel() {
        List<SalesVO> list = new ArrayList<>();

        try {
            conn = Common.getConnection();
            System.out.print("원하는 년도를 입력 하세요 : ");
            String wtDate = sc.nextLine();
            String sql = "SELECT * FROM SALES_STATEMENT WHERE TO_CHAR(P_DATE,'YYYY') = ? ORDER BY P_DATE";
            pStmt = conn.prepareStatement(sql);
            pStmt.setString(1,wtDate);
            rs = pStmt.executeQuery();
            while (rs.next()) {
                int no = rs.getInt("ORDER_NO");
                int id = rs.getInt("MEM_ID");
                String name = rs.getString("MNAME");
                String purchase = rs.getString("PURCHASE");
                int sales = rs.getInt("SALES");
                String pDate = rs.getString("P_DATE");

                SalesVO vo = new SalesVO();
                vo.setOrder_No(no);
                vo.setMem_ID(id);
                vo.setMName(name);
                vo.setPurchase(purchase);
                vo.setSales(sales);
                vo.setP_DateStr((pDate));
                list.add(vo);
            }
            Common.close(rs);
            Common.close(pStmt);
            Common.close(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public void SalSelPrint(List<SalesVO> list) {
        for (SalesVO e : list) {
            System.out.println("주문번호 : " + e.getOrder_No());
            System.out.println("회원번호 : " + e.getMem_ID());
            System.out.println("회원이름 : " + e.getMName());
            System.out.println("상품이름 : " + e.getPurchase());
            System.out.println("매출 : " + e.getSales());
            System.out.println("구매날짜 : " + e.getP_DateStr());
            System.out.println("--------------------------------------");
        }
    }
}






