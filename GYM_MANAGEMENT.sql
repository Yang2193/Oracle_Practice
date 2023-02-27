/*회원정보 테이블*/
DROP TABLE MEMBERINFO;

CREATE TABLE MEMBERINFO(
	MEM_ID NUMBER(4) PRIMARY KEY,
	MNAME VARCHAR2(20) NOT NULL,
	PNAME VARCHAR2(20) NOT NULL,
	PT_REMAIN NUMBER(2),
	DUE_DATE DATE,
	GENDER VARCHAR2(6) NOT NULL,
	PHONE_NUM VARCHAR2(20) NOT NULL,
	LOCKER VARCHAR2(4) UNIQUE,
	REG_DATE DATE
);

CREATE SEQUENCE MEM_ID1
INCREMENT BY 1
START WITH 1001
NOCYCLE 
NOCACHE;

SELECT * FROM MEMBERINFO ;

-- 데이터 입력
INSERT INTO MEMBERINFO VALUES(MEM_ID1.NEXTVAL, '박준하', 'H3', NULL, SYSDATE+90, '남', '010-7723-9785', NULL, SYSDATE );
INSERT INTO MEMBERINFO VALUES(MEM_ID1.NEXTVAL, '베니코', 'H6', NULL, SYSDATE + 180, '여', '010-1234-4321', NULL, SYSDATE);
INSERT INTO MEMBERINFO VALUES(MEM_ID1.NEXTVAL, '죠니', 'PT 30회', 30, SYSDATE + 120, '남', '010-1111-1111', NULL, SYSDATE);
INSERT INTO MEMBERINFO VALUES(MEM_ID1.NEXTVAL, '타오가카', 'H12', NULL, SYSDATE + 365, '여', '010-5555-5555', NULL, SYSDATE - 55);
INSERT INTO MEMBERINFO VALUES(MEM_ID1.NEXTVAL, '황용', 'H6', NULL, SYSDATE + 180, '여', '010-4836-1825', NULL, SYSDATE + 3);
INSERT INTO MEMBERINFO VALUES(MEM_ID1.NEXTVAL, '모리안', 'PT 10회', 10, SYSDATE + 90, '여', '010-8923-1258', NULL, SYSDATE + 12);
INSERT INTO MEMBERINFO VALUES(MEM_ID1.NEXTVAL, '일리악', 'PT 30회', 30, SYSDATE + 180, '남', '010-2348-4838', NULL, SYSDATE + 17);
INSERT INTO MEMBERINFO VALUES(MEM_ID1.NEXTVAL, '후청', 'H3', NULL, SYSDATE + 90, '여', '010-3461-3596', NULL, SYSDATE);
INSERT INTO MEMBERINFO VALUES(MEM_ID1.NEXTVAL, '니웁', 'H6', NULL,  SYSDATE + 60, '남', '010-8329-3857', NULL, SYSDATE);
INSERT INTO MEMBERINFO VALUES(MEM_ID1.NEXTVAL, '유비', 'PT 20회', 20, SYSDATE + 150, '남', '010-2873-4745', NULL, SYSDATE+30);
INSERT INTO MEMBERINFO VALUES(MEM_ID1.NEXTVAL, '관우', 'H12', NULL, SYSDATE + 395, '남', '010-3459-8245', NULL, SYSDATE+30);
INSERT INTO MEMBERINFO VALUES(MEM_ID1.NEXTVAL, '장비', 'H6', NULL, SYSDATE + 210, '남', '010-5251-3951', NULL, SYSDATE +30);
INSERT INTO MEMBERINFO VALUES(MEM_ID1.NEXTVAL, '조운', 'PT 10회', 10,  SYSDATE + 150, '남', '010-2315-2930', NULL, SYSDATE + 60););
INSERT INTO MEMBERINFO VALUES(MEM_ID1.NEXTVAL, '황충', 'H3',  NULL, SYSDATE + 180, '남', '010-2838-3827', NULL, SYSDATE + 90);
INSERT INTO MEMBERINFO VALUES(MEM_ID1.NEXTVAL, '강유', 'H6', NULL , SYSDATE + 300, '남', '010-6293-2875', NULL, SYSDATE + 120);
INSERT INTO MEMBERINFO VALUES(MEM_ID1.NEXTVAL, '장익', 'PT 20회', 20, SYSDATE + 60, '남', '010-2316-4382', NULL, SYSDATE -60);
INSERT INTO MEMBERINFO VALUES(MEM_ID1.NEXTVAL, '장억', 'H6', NULL, SYSDATE +30, '남', '010-2395-9283', NULL, SYSDATE -150 );
INSERT INTO MEMBERINFO VALUES(MEM_ID1.NEXTVAL, '제갈량', 'PT 30회', 30, SYSDATE + 234 , '남', '010-2070-2112', NULL, SYSDATE + 54);
INSERT INTO MEMBERINFO VALUES(MEM_ID1.NEXTVAL, '요화', 'H3', NULL, SYSDATE, '남', '010-4923-5812', NULL, SYSDATE - 90);
INSERT INTO MEMBERINFO VALUES(MEM_ID1.NEXTVAL, '손건', 'H6', NULL, SYSDATE + 90, '남', '010-5283-8932', NULL, SYSDATE-90);

/* 상품명 테이블 */

DROP TABLE PRODUCT ;

CREATE TABLE PRODUCT (
	PNAME VARCHAR2(20) PRIMARY KEY,
	PRICE NUMBER(10) NOT NULL,
	TERM NUMBER(4)
	);

INSERT INTO PRODUCT VALUES('H1', 70000, 30);
INSERT INTO PRODUCT VALUES('H3', 135000, 90);
INSERT INTO PRODUCT VALUES('H6', 250000, 180);
INSERT INTO PRODUCT VALUES('H12', 450000, 365);

INSERT INTO PRODUCT VALUES('PT 10회', 500000, 90);
INSERT INTO PRODUCT VALUES('PT 20회', 900000, 120);
INSERT INTO PRODUCT VALUES('PT 30회', 1200000, 180);

INSERT INTO PRODUCT VALUES('LOCKER RENT', 10000, 30); -- 라커 렌탈은 회원정보랑 기간 따로
INSERT INTO PRODUCT VALUES('일일권', 10000, NULL); -- 일일권은 회원 등록 안함
	
SELECT * FROM PRODUCT;

/*라커 테이블*/

DROP TABLE LOCKER;

CREATE TABLE LOCKER (
	LOCKNUM VARCHAR2(10) PRIMARY KEY,
 	DUE_DATE DATE
)


-- 라커 생성
INSERT INTO LOCKER(LOCKNUM) VALUES('복도1' );
INSERT INTO LOCKER(LOCKNUM)  VALUES('복도2' );
INSERT INTO LOCKER(LOCKNUM)  VALUES('복도3' );
INSERT INTO LOCKER(LOCKNUM)  VALUES('복도4' );
INSERT INTO LOCKER(LOCKNUM)  VALUES('복도5' );
INSERT INTO LOCKER(LOCKNUM)  VALUES('복도6' );
INSERT INTO LOCKER(LOCKNUM)  VALUES('복도7' );
INSERT INTO LOCKER(LOCKNUM)  VALUES('복도8' );
INSERT INTO LOCKER(LOCKNUM)  VALUES('복도9' );
INSERT INTO LOCKER(LOCKNUM)  VALUES('복도10' );
INSERT INTO LOCKER(LOCKNUM)  VALUES('복도11');
INSERT INTO LOCKER(LOCKNUM)  VALUES('복도12');
INSERT INTO LOCKER(LOCKNUM)  VALUES('복도13');
INSERT INTO LOCKER(LOCKNUM)  VALUES('복도14');
INSERT INTO LOCKER(LOCKNUM)  VALUES('복도15');
INSERT INTO LOCKER(LOCKNUM)  VALUES('복도16');
INSERT INTO LOCKER(LOCKNUM)  VALUES('복도17');
INSERT INTO LOCKER(LOCKNUM)  VALUES('복도18');
INSERT INTO LOCKER(LOCKNUM)  VALUES('복도19');
INSERT INTO LOCKER(LOCKNUM)  VALUES('복도20');
INSERT INTO LOCKER(LOCKNUM)  VALUES('남1');
INSERT INTO LOCKER(LOCKNUM)  VALUES('남2');
INSERT INTO LOCKER(LOCKNUM)  VALUES('남3');
INSERT INTO LOCKER(LOCKNUM)  VALUES('남4');
INSERT INTO LOCKER(LOCKNUM)  VALUES('남5');
INSERT INTO LOCKER(LOCKNUM)  VALUES('남6');
INSERT INTO LOCKER(LOCKNUM)  VALUES('남7');
INSERT INTO LOCKER(LOCKNUM)  VALUES('남8');
INSERT INTO LOCKER(LOCKNUM)  VALUES('남9');
INSERT INTO LOCKER(LOCKNUM)  VALUES('남10');
INSERT INTO LOCKER(LOCKNUM)  VALUES('여1');
INSERT INTO LOCKER(LOCKNUM)  VALUES('여2');
INSERT INTO LOCKER(LOCKNUM)  VALUES('여3');
INSERT INTO LOCKER(LOCKNUM)  VALUES('여4');
INSERT INTO LOCKER(LOCKNUM)  VALUES('여5');
INSERT INTO LOCKER(LOCKNUM)  VALUES('여6');
INSERT INTO LOCKER(LOCKNUM)  VALUES('여7');
INSERT INTO LOCKER(LOCKNUM)  VALUES('여8');
INSERT INTO LOCKER(LOCKNUM)  VALUES('여9');
INSERT INTO LOCKER(LOCKNUM)  VALUES('여10');

SELECT * FROM LOCKER;

/* 매출, 주문내역 테이블*/

DROP TABLE SALES_STATEMENT;

CREATE TABLE SALES_STATEMENT (
 ORDER_NO NUMBER PRIMARY KEY,
 MEM_ID NUMBER(4) REFERENCES MEMBERINFO(MEM_ID),
 MNAME VARCHAR2(20) NOT NULL,
 PURCHASE VARCHAR2(20) NOT NULL, -- Q. 상품정보 테이블을 참조할까요? 외부키는 안 받아도 상품명은 참조해서 작성하죠
 SALES NUMBER NOT NULL,
 P_DATE DATE
 );

SELECT * FROM SALES_STATEMENT;

CREATE SEQUENCE ORDER_NO
INCREMENT BY 1
START WITH 1
NOCYCLE 
NOCACHE;

INSERT INTO SALES_STATEMENT VALUES(ORDER_NO.NEXTVAL, 1001, '박준하', 'H3', 135000, (SELECT REG_DATE FROM MEMBERINFO WHERE MEM_ID = 1001) );
INSERT INTO SALES_STATEMENT VALUES(ORDER_NO.NEXTVAL, 1002, '베니코', 'H6 + L6', 300000, (SELECT REG_DATE FROM MEMBERINFO WHERE MEM_ID = 1002));
INSERT INTO SALES_STATEMENT VALUES(ORDER_NO.NEXTVAL, 1003, '죠니', 'PT 30회', 1200000, (SELECT REG_DATE FROM MEMBERINFO WHERE MEM_ID = 1003));
INSERT INTO SALES_STATEMENT VALUES(ORDER_NO.NEXTVAL, 1004, '타오가카', 'H12', 450000, (SELECT REG_DATE FROM MEMBERINFO WHERE MEM_ID = 1004));
INSERT INTO SALES_STATEMENT VALUES(ORDER_NO.NEXTVAL, 1005, '황용', 'H6', 250000, (SELECT REG_DATE FROM MEMBERINFO WHERE MEM_ID = 1005) );
INSERT INTO SALES_STATEMENT VALUES(ORDER_NO.NEXTVAL, 1006, '모리안', 'PT 10회', 500000, (SELECT REG_DATE FROM MEMBERINFO WHERE MEM_ID = 1006));
INSERT INTO SALES_STATEMENT VALUES(ORDER_NO.NEXTVAL, 1007, '일리악', 'PT 30회', 1200000, (SELECT REG_DATE FROM MEMBERINFO WHERE MEM_ID = 1007));
INSERT INTO SALES_STATEMENT VALUES(ORDER_NO.NEXTVAL, 1008, '후청', 'H3', 135000, (SELECT REG_DATE FROM MEMBERINFO WHERE MEM_ID = 1008));
INSERT INTO SALES_STATEMENT VALUES(ORDER_NO.NEXTVAL, 1009, '니웁', 'H6', 250000, (SELECT REG_DATE FROM MEMBERINFO WHERE MEM_ID = 1009));
INSERT INTO SALES_STATEMENT VALUES(ORDER_NO.NEXTVAL, 1010, '유비', 'PT 20회', 900000, (SELECT REG_DATE FROM MEMBERINFO WHERE MEM_ID = 1010));

DROP TABLE SALES_STATEMENT;

CREATE TABLE SALES_STATEMENT (
 ORDER_NO NUMBER PRIMARY KEY,
 MEM_ID NUMBER(4) REFERENCES MEMBERINFO(MEM_ID),
 MNAME VARCHAR2(20) NOT NULL,
 PURCHASE VARCHAR2(20) NOT NULL, -- Q. 상품정보 테이블을 참조할까요? 외부키는 안 받아도 상품명은 참조해서 작성하죠
 SALES NUMBER NOT NULL,
 P_DATE DATE
 );

SELECT * FROM SALES_STATEMENT;

CREATE SEQUENCE ORDER_NO
INCREMENT BY 1
START WITH 1
NOCYCLE 
NOCACHE;

INSERT INTO SALES_STATEMENT VALUES(ORDER_NO.NEXTVAL, 1001, '박준하', 'H3', 135000, (SELECT REG_DATE FROM MEMBERINFO WHERE MEM_ID = 1001) );
INSERT INTO SALES_STATEMENT VALUES(ORDER_NO.NEXTVAL, 1002, '베니코', 'H6 + L6', 300000, (SELECT REG_DATE FROM MEMBERINFO WHERE MEM_ID = 1002));
INSERT INTO SALES_STATEMENT VALUES(ORDER_NO.NEXTVAL, 1003, '죠니', 'PT 30회', 1200000, (SELECT REG_DATE FROM MEMBERINFO WHERE MEM_ID = 1003));
INSERT INTO SALES_STATEMENT VALUES(ORDER_NO.NEXTVAL, 1004, '타오가카', 'H12', 450000, (SELECT REG_DATE FROM MEMBERINFO WHERE MEM_ID = 1004));
INSERT INTO SALES_STATEMENT VALUES(ORDER_NO.NEXTVAL, 1005, '황용', 'H6', 250000, (SELECT REG_DATE FROM MEMBERINFO WHERE MEM_ID = 1005) );
INSERT INTO SALES_STATEMENT VALUES(ORDER_NO.NEXTVAL, 1006, '모리안', 'PT 10회', 500000, (SELECT REG_DATE FROM MEMBERINFO WHERE MEM_ID = 1006));
INSERT INTO SALES_STATEMENT VALUES(ORDER_NO.NEXTVAL, 1007, '일리악', 'PT 30회', 1200000, (SELECT REG_DATE FROM MEMBERINFO WHERE MEM_ID = 1007));
INSERT INTO SALES_STATEMENT VALUES(ORDER_NO.NEXTVAL, 1008, '후청', 'H3', 135000, (SELECT REG_DATE FROM MEMBERINFO WHERE MEM_ID = 1008));
INSERT INTO SALES_STATEMENT VALUES(ORDER_NO.NEXTVAL, 1009, '니웁', 'H6', 250000, (SELECT REG_DATE FROM MEMBERINFO WHERE MEM_ID = 1009));
INSERT INTO SALES_STATEMENT VALUES(ORDER_NO.NEXTVAL, 1010, '유비', 'PT 20회', 900000, (SELECT REG_DATE FROM MEMBERINFO WHERE MEM_ID = 1010));

INSERT INTO SALES_STATEMENT VALUES(ORDER_NO.NEXTVAL, 1011, '관우', 'H12', 450000, (SELECT REG_DATE FROM MEMBERINFO WHERE MEM_ID = 1011));
INSERT INTO SALES_STATEMENT VALUES(ORDER_NO.NEXTVAL, 1012, '장비', 'H6', 250000, (SELECT REG_DATE FROM MEMBERINFO WHERE MEM_ID = 1012));
INSERT INTO SALES_STATEMENT VALUES(ORDER_NO.NEXTVAL, 1013, '조운', 'PT 10회', 500000, (SELECT REG_DATE FROM MEMBERINFO WHERE MEM_ID = 1013));
INSERT INTO SALES_STATEMENT VALUES(ORDER_NO.NEXTVAL, 1014, '황충', 'H3', 135000, (SELECT REG_DATE FROM MEMBERINFO WHERE MEM_ID = 1014));
INSERT INTO SALES_STATEMENT VALUES(ORDER_NO.NEXTVAL, 1015, '강유', 'H6', 250000, (SELECT REG_DATE FROM MEMBERINFO WHERE MEM_ID = 1015));
INSERT INTO SALES_STATEMENT VALUES(ORDER_NO.NEXTVAL, 1016, '장익', 'PT 20회', 900000, (SELECT REG_DATE FROM MEMBERINFO WHERE MEM_ID = 1016));
INSERT INTO SALES_STATEMENT VALUES(ORDER_NO.NEXTVAL, 1017, '장억', 'H6', 250000, (SELECT REG_DATE FROM MEMBERINFO WHERE MEM_ID = 1017));
INSERT INTO SALES_STATEMENT VALUES(ORDER_NO.NEXTVAL, 1018, '제갈량', 'PT 30회', 1200000, (SELECT REG_DATE FROM MEMBERINFO WHERE MEM_ID = 1018));
INSERT INTO SALES_STATEMENT VALUES(ORDER_NO.NEXTVAL, 1019, '요화', 'H3', 135000, (SELECT REG_DATE FROM MEMBERINFO WHERE MEM_ID = 1019));
INSERT INTO SALES_STATEMENT VALUES(ORDER_NO.NEXTVAL, 1020, '손건', 'H6', 250000, (SELECT REG_DATE FROM MEMBERINFO WHERE MEM_ID = 1020));

SELECT * FROM SALES_STATEMENT ;


/* PT 기록 테이블 */
DROP TABLE PT_DATA;

CREATE TABLE PT_DATA (
	MEM_ID 		NUMBER(4), -- 중복이 되어야만 함
	MNAME 		VARCHAR2(20) NOT NULL,
	PTNAME 		VARCHAR2(20) NOT NULL,
	PT_DATE 		DATE
);

--데이터 입력
INSERT INTO PT_DATE VALUES();

/*회원 입장 테이블*/


CREATE TABLE ENTRANCE_DATA ( -- 개인별로 만료일 되면 데이터를 지울 지 그냥 통합으로 몇 개월에 한번씩 지울 지 고민 중.
	MEM_ID 		NUMBER(4),  -- 중복이 되어야만 함
	E_DATE 			DATE NOT NULL
);

--데이터 입력
INSERT INTO ENTRANCE_DATA VALUES();


SELECT * FROM MEMBERINFO M ;
SELECT * FROM PRODUCT P ;
SELECT * FROM LOCKER;
SELECT * FROM SALES_STATEMENT;
SELECT * FROM PT_DATA PD ;

DELETE FROM MEMBERINFO M WHERE MEM_ID = 1021;
SELECT * FROM ENTRANCE_DATA ED ;

UPDATE MEMBERINFO SET MNAME = '당태종' WHERE MEM_ID = 1021;
