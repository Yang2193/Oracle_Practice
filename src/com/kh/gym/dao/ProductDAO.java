package com.kh.gym.dao;

import com.kh.gym.util.Common;
import com.kh.gym.vo.ProductVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProductDAO {
    Connection conn = null; // 자바와 오라클에 대한 연결 설정
    Statement stmt = null;  // SQL 문을 수행하기 위한 객체
    PreparedStatement pStmt = null;
    ResultSet rs = null; // statement 동작에 대한 결과로 전달되는 DB의 내용

    public List<ProductVO> productSel(){
        List<ProductVO> list = new ArrayList<>();
        try{
            conn = Common.getConnection();
            stmt = conn.createStatement();
            String sql = "SELECT * FROM PRODUCT";
            rs = stmt.executeQuery(sql);

            while(rs.next()){
                String pName = rs.getString("PNAME");
                int price = rs.getInt("PRICE");
                int term = rs.getInt("TERM");

                ProductVO vo = new ProductVO(pName, price, term);
                list.add(vo);
            }
            Common.close(rs);
            Common.close(stmt);
            Common.close(conn);
        }catch(Exception e){
            e.printStackTrace();
        }

        return list;
    }

    public void productView(List<ProductVO> list){
        for(ProductVO e : list){
            System.out.println("상품명 : " + e.getpName());
            System.out.println("가격 : " + e.getPrice());
            System.out.println("기간 : " + e.getTerm());
            System.out.println("=============");
        }
    }

    public void productInsert(){
        Scanner sc = new Scanner(System.in);
        System.out.println("===== 상품 추가 =====");
        System.out.print("상품 이름 : ");
        String pName = sc.nextLine();
        System.out.print("가격 설정 : ");
        int price = sc.nextInt();
        System.out.print("기간 설정 : ");
        int term = sc.nextInt();

        String sql = "INSERT INTO PRODUCT VALUES(?,?,?)";

        try{
            conn = Common.getConnection();
            pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, pName);
            pStmt.setInt(2, price);
            pStmt.setInt(3, term);
            pStmt.executeUpdate();
            System.out.print("상품 추가 완료");

        } catch(Exception e){
            e.printStackTrace();
        }
        Common.close(pStmt);
        Common.close(conn);
    }

    public void productUpdate(){
        Scanner sc = new Scanner(System.in);
        System.out.print("수정할 상품명을 입력하세요 : ");
        String pName = sc.nextLine();
        System.out.print("상품명 수정 : ");
        String newPName = sc.nextLine();
        System.out.print("가격 수정 : ");
        int price = sc.nextInt();
        System.out.print("기간 수정 : ");
        int term = sc.nextInt();

        String sql = "UPDATE PRODUCT SET PNAME = ?, PRICE = ?, TERM = ? WHERE PNAME = ?";

        try{
            conn = Common.getConnection();
            pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, newPName);
            pStmt.setInt(2, price);
            pStmt.setInt(3, term);
            pStmt.setString(4, pName);
            pStmt.executeUpdate();
            System.out.println("상품 수정 완료");
        }catch(Exception e){
            e.printStackTrace();
        }
        Common.close(pStmt);
        Common.close(conn);

    }

    public void productDelete(){
        Scanner sc = new Scanner(System.in);
        System.out.print("삭제할 상품명을 입력하세요. :  ");
        String pName = sc.nextLine();

        String sql = "DELETE FROM PRODUCT WHERE PNAME = ?";

        try{
            conn = Common.getConnection();
            pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, pName);
            pStmt.executeUpdate();
            System.out.println("삭제 완료");
        }catch(Exception e){
            e.printStackTrace();
        }
        Common.close(pStmt);
        Common.close(conn);
    }

}
