package com.kh.gym.dao;

import com.kh.gym.util.Common;
import com.kh.gym.vo.ProductVO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
    Connection conn = null; // 자바와 오라클에 대한 연결 설정
    Statement stmt = null;  // SQL 문을 수행하기 위한 객체
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

    }

    public void productUpdate(){

    }

    public void productDelete(){

    }

}
