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
        System.out.println("       날짜               매출        ");
        for (SalesVO e : list) {
            System.out.println("   " + e.getP_DateStr() + "           " +e.getSales());
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
        System.out.println("       날짜               매출        ");
        for (SalesVO e : list) {
            System.out.println("     " + e.getP_DateStr() + "            " +e.getSales());
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
        System.out.println("      날짜               매출        ");
        for (SalesVO e : list) {
            System.out.println("     " + e.getP_DateStr() + "              " +e.getSales());
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
            if (ret == 0) {
                System.out.println("존재하지 않는 주문번호 입니다.");
            }else System.out.println("Return : " + ret);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Common.close(stmt);
        Common.close(conn);
    }

    public void salesDelete() {
        System.out.print("지울 주문번호를 입력 하세요 : ");
        int order_no = sc.nextInt();

        String query = "DELETE FROM SALES_STATEMENT WHERE ORDER_NO =" + order_no ;
        try {
            conn = Common.getConnection();
            stmt = conn.createStatement();
            int ret = stmt.executeUpdate(query);
            if (ret == 0) {
                System.out.println("존재하지 않는 주문번호 입니다.");
            }else System.out.println("Return : " + ret);
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
            if (!rs.next()){
                System.out.println("매출이 존재하지 않습니다.");
            } else while (rs.next()) {
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
            if (!rs.next()){
                System.out.println("매출이 존재하지 않습니다.");
            } else while (rs.next()) {
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
            if (!rs.next()){
                System.out.println("매출이 존재하지 않습니다.");
            } else while (rs.next()) {
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
        System.out.println("  주문번호     회원번호     회원이름     상품이름     매출           구매날짜");
        for (SalesVO e : list) {
            System.out.println("   " + e.getOrder_No() + "       " + e.getMem_ID()  + "        " + e.getMName()+ "        "
                    + e.getPurchase()+ "     "  + e.getSales() + "     " + e.getP_DateStr());
        }
    }
}






