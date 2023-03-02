package com.kh.gym.method;

import com.kh.gym.dao.PtDao;
import com.kh.gym.vo.MemberInfoVO;

import java.util.List;
import java.util.Scanner;

public class Pt {
    Scanner sc = new Scanner(System.in);
    PtDao pDao = new PtDao();

    public void ptManagement(){
        while(true){
            System.out.println("===== PT 회원 관리 =====");
            System.out.println("[1] PT 회원 목록, [2] 특정 회원 조회, [3] PT횟수 설정 [4] PT 기록 확인, [5] PT 기록 입력, [6] PT 기록 수정, [7] PT 기록 삭제, [8] 종료");
            int sel = sc.nextInt();
            switch(sel){
                case 1: List<MemberInfoVO> list = pDao.ptMemberList();
                        pDao.ptMember(list);
                        break;
                case 2: List<MemberInfoVO> list1 = pDao.specificPtMemberList();
                        pDao.specificPtMember(list1);
                        break;
                case 3: pDao.setPtRemain();
                        break;
                case 4: pDao.ptDataChoice();
                        break;
                case 5: pDao.insertPtDataProgram();
                        break;
                case 6: pDao.updatePtData();
                        break;
                case 7: pDao.deleteChoice();
                        break;
                case 8:
                    System.out.println("PT관리를 종료합니다.");
                    return;
            }
        }

    }
}
