package com.kh.gym.method;

import com.kh.gym.dao.PTDataDAO;
import com.kh.gym.vo.PTDataVO;

import java.util.List;
import java.util.Scanner;

public class PTData {

    Scanner sc = new Scanner(System.in);
    PTDataDAO dao = new PTDataDAO();
    public void PTDataManagement() {
        System.out.println("[1]전체조회, [2]회원번호조회 [3]PT 기록");
        System.out.print("메뉴를 선택하세요 : ");

        int sel = sc.nextInt();
        List<PTDataVO> ptData = null;
        switch(sel) {
            case 1:
                ptData = dao.getPTInfoList();
                dao.PTDataView(ptData);
                break;
            case 2:
                System.out.print("회원번호 입력 : ");
                int memberID = sc.nextInt();
                ptData = dao.getPTInfoList(memberID);
                dao.PTDataView(ptData);
                break;
            case 3:
                dao.PTDataInsert();
                break;
        }

    }
}
