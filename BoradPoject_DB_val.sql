----------------------------------------------------------------초기화
 drop table apperence;
 drop table Celebrity;
 drop table BroadProgram;
 drop table BroadStation;

drop sequence cele_seq;
drop sequence bp_seq;
drop sequence bs_seq;

-----------------------------------------------------------------------
---------------------------------테이블 생성-----------------------------
-----------------------------------------------------------------------
-- 방송국 테이블
create table BroadStation (
    bid NUMBER primary key,
    broadcaster VARCHAR(45) not null unique
);

-- 방송 테이블
CREATE TABLE  BroadProgram(
    pid NUMBER,
    bid NUMBER not null,
    airtime VARCHAR(12),
    title varchar(45) not null,
    introduction varchar(150),
    broadday varchar(3),
    
    primary key(pid),
    unique(bid, airtime, broadday),
    
    CONSTRAINT fk_Broadcast_BroadStation FOREIGN KEY (bid) REFERENCES BroadStation(bid)
    on delete cascade
 );

 
-- 인물 테이블
CREATE TABLE Celebrity (
  cid NUMBER PRIMARY KEY,
  name VARCHAR(12) NOT NULL,
  gender VARCHAR(3) NOT NULL,
  appfee NUMBER NOT NULL,
  birth DATE NOT NULL,
  
  CONSTRAINT ck_Celebrity_gender CHECK (gender in('F','M'))
);


-- 출연 테이블
create table apperence(
    pid NUMBER,
    cid NUMBER,

   constraint pk_id primary key(pid, cid) ,
   constraint fk_apperence_BroadProgram Foreign key (pid) references BroadProgram(pid) on DELETE CASCADE,
   constraint fk_apperence_Celebrity Foreign key (cid) references Celebrity(cid) on DELETE CASCADE
);

-----------------------------------------------------------------------
--------------------------------- 시퀀스   -----------------------------
-----------------------------------------------------------------------
--------1) 인물 시퀀스
create sequence cele_seq
    increment by 1
    start with 1;
    
--------2) 방송 시퀀스
create sequence bp_seq
    increment by 1
    start with 1;

--------3) 방송국 시퀀스
create sequence bs_seq
    increment by 1
    start with 1;

-----------------------------------------------------------------------
---------------------------------값 넣기---------------------------------
-----------------------------------------------------------------------

--방송사 값 넣기
insert into BroadStation values (bs_seq.nextval, 'KBS');
insert into BroadStation values (bs_seq.nextval, 'MBC');
insert into BroadStation values (bs_seq.nextval, 'SBS');

----프로그램 값 넣기
insert into BroadProgram values (bp_seq.nextval, 3, '18:20', '런닝맨', '수많은 스타와 멤버들이 함께 미션을 한다.', '일');
insert into BroadProgram values (bp_seq.nextval, 2, '18:20', '놀면뭐하니?', '유재석을 괴롭히는 무한확장 유니버스(YOONIVERSE) 버라이어티.', '토');
insert into BroadProgram values (bp_seq.nextval, 2, '23:10', '나 혼자 산다', ' 혼자 사는 유명인들의 일상을 관찰 카메라 형태로 담은 다큐멘터리 형식의 예능 프로그램', '금');
insert into BroadProgram values (bp_seq.nextval, 1, '23:10', '개는 훌륭하다', ' 반려견과 사람이 행복하게 어우러져 사는 법을 함께 고민해보는 프로그램', '월');
insert into BroadProgram values (bp_seq.nextval, 2, '22:45', '구해줘 홈즈', '바쁜 현대인들의 집 찾기를 위해 직접 나선 스타들! 그들의 리얼한 발품 중개 배틀', '일');
insert into BroadProgram values (bp_seq.nextval, 1, '18:15', '1박2일', '우리가 알고 있는, 우리가 알지 못하는 아름다운 우리나라. 유쾌한 여섯 남자와 함께 1박 2일의 여행을 떠난다!', '일');
insert into BroadProgram values (bp_seq.nextval, 2, '18:05', '복면가왕', '나이, 신분, 직종을 숨긴 스타들이 목소리만으로 실력을 뽐내는 음악 버라이어티 프로그램', '일');
insert into BroadProgram values (bp_seq.nextval, 2, '22:30', '라디오스타', '어디로 튈지 모르는 촌철살인의 입담으로 게스트들을 무장해제 시켜 진짜 이야기를 끄집어내는 독보적 토크쇼', '수');
insert into BroadProgram values (bp_seq.nextval, 2, '21:00', '안싸우면 다행이야','극한의 리얼 야생을 찾아간 연예계 대표 절친들이 자연인의 삶을 그대로 살아보는 프로그램', '월');
insert into BroadProgram values (bp_seq.nextval, 1, '20:30', '무엇이든 물어보살', '꽉 막힌 속을 확!! 뚫어줄 신통방통 해결책을 주는 프로그램', '월');
insert into BroadProgram values (bp_seq.nextval, 3, '23:10', '동상이몽2', '다양한 분야의 커플들이 함께 사는 것의 가치를 살펴보는 프로그램', '월');
insert into BroadProgram values (bp_seq.nextval, 1, '20:30', '홍김동전', '홍 씨 김 씨의 동전으로 운명이 체인지 되는 피땀 눈물의 구 개념 버라이어티', '목');
insert into BroadProgram values (bp_seq.nextval, 3, '15:50', '인기가요', 'SBS 가요 순위 프로그램으로 인기 가수들의 최신 음악을 들을 수 있는 프로그램', '일');




----연예인 값 넣기
insert into Celebrity values (cele_seq.nextval, '전소민', 'F', 5000, '1986/04/07' );
insert into Celebrity values (cele_seq.nextval, '김종국', 'M', 8000, '1976/04/25' );
insert into Celebrity values (cele_seq.nextval, '유재석', 'M', 10000, '1972/08/14' );
insert into Celebrity values (cele_seq.nextval, '전현무', 'M', 8000, '1977/11/07' );
insert into Celebrity values (cele_seq.nextval, '박나래', 'F', 8000, '1985/10/25' );
insert into Celebrity values (cele_seq.nextval, '이장우', 'M', 6300, '1965/12/2' );
insert into Celebrity values (cele_seq.nextval, '키', 'M', 7000, '1991/09/23' );
insert into Celebrity values (cele_seq.nextval, '하하', 'M', 6900, '1979/8/20' );
insert into Celebrity values (cele_seq.nextval, '이미주', 'F', 4900, '1994/09/23' );
insert into Celebrity values (cele_seq.nextval, '화사', 'F', 5000, '1995/07/23' );
insert into Celebrity values (cele_seq.nextval, '송지효', 'F', 6300, '1991/08/15' );
insert into Celebrity values (cele_seq.nextval, '지석진', 'M', 6900, '1966/02/10' );
insert into Celebrity values (cele_seq.nextval, '양세찬', 'M', 6000, '1986/12/08' );
insert into Celebrity values (cele_seq.nextval, '연정훈', 'M', 6600, '1978/11/06' );
insert into Celebrity values (cele_seq.nextval, '문세윤', 'M', 6800, '1982/05/11' );
insert into Celebrity values (cele_seq.nextval, '김종민', 'M', 6900, '1979/09/24' );
insert into Celebrity values (cele_seq.nextval, '나인우', 'M', 6500, '1994/09/17' );
insert into Celebrity values (cele_seq.nextval, '장도연', 'F', 7000, '1985/03/10' );
insert into Celebrity values (cele_seq.nextval, '이경규', 'M', 8000, '1960/09/21' );
insert into Celebrity values (cele_seq.nextval, '안정환', 'M', 6500, '1976/01/27' );
insert into Celebrity values (cele_seq.nextval, '붐', 'M', 6500, '1982/05/10' );
insert into Celebrity values (cele_seq.nextval, '안영미', 'F', 6800, '1983/11/05' );
insert into Celebrity values (cele_seq.nextval, '유세윤', 'M', 7000, '1980/09/12' );
insert into Celebrity values (cele_seq.nextval, '김국진', 'M', 7100, '1965/02/06' );
insert into Celebrity values (cele_seq.nextval, '김구라', 'M', 7000, '1970/10/03' );
insert into Celebrity values (cele_seq.nextval, '김성주', 'M', 7000, '1972/10/10' );
insert into Celebrity values (cele_seq.nextval, '이수근', 'M', 7000, '1974/06/03' );
insert into Celebrity values (cele_seq.nextval, '서장훈', 'M', 7000, '1975/02/10' );
insert into Celebrity values (cele_seq.nextval, '김숙', 'F', 7100, '1975/07/06' );
insert into Celebrity values (cele_seq.nextval, '강호동', 'M', 8300, '1970/06/11' );
insert into Celebrity values (cele_seq.nextval, '한혜진', 'F', 6500, '1983/03/23' );
insert into Celebrity values (cele_seq.nextval, '주우재', 'M', 6300, '1986/11/28' );
insert into Celebrity values (cele_seq.nextval, '홍진경', 'F', 6500, '1977/12/23' );
insert into Celebrity values (cele_seq.nextval, '조세호', 'M', 7000, '1982/09/09' );
insert into Celebrity values (cele_seq.nextval, '장우영', 'M', 6800, '1989/04/30' );
insert into Celebrity values (cele_seq.nextval, '연준', 'M', 6000, '1999/09/13' );
insert into Celebrity values (cele_seq.nextval, '노정의', 'F', 6000, '2001/07/31' );
insert into Celebrity values (cele_seq.nextval, '이용진', 'M', 7000, '1985/11/18' );
insert into Celebrity values (cele_seq.nextval, '양세형', 'M', 7000, '1985/08/18' );





----출연이력 값 넣기
----런닝맨
insert into apperence (pid, cid) values (1, 1);
insert into apperence (pid, cid) values (1, 2);
insert into apperence (pid, cid) values (1, 3);
insert into apperence (pid, cid) values (1, 8);
insert into apperence (pid, cid) values (1, 11);
insert into apperence (pid, cid) values (1, 12);
insert into apperence (pid, cid) values (1, 13);
--놀뭐
insert into apperence (pid, cid) values (2, 3);
insert into apperence (pid, cid) values (2, 8);
insert into apperence (pid, cid) values (2, 9);
--나혼자
insert into apperence (pid, cid) values (3, 4);
insert into apperence (pid, cid) values (3, 5);
insert into apperence (pid, cid) values (3, 6);
insert into apperence (pid, cid) values (3, 7);
insert into apperence (pid, cid) values (3, 10);
--개훌륭
insert into apperence (pid, cid) values (4, 18);
insert into apperence (pid, cid) values (4, 19);

--r구해줘홈즈
insert into apperence (pid, cid) values (5, 5);
insert into apperence (pid, cid) values (5, 13);
insert into apperence (pid, cid) values (5, 21);
--1박
insert into apperence (pid, cid) values (6, 14);
insert into apperence (pid, cid) values (6, 15);
insert into apperence (pid, cid) values (6, 16);
insert into apperence (pid, cid) values (6, 17);
---복면가왕
insert into apperence (pid, cid) values (7, 26);
insert into apperence (pid, cid) values (7, 25);
--라디오스타
insert into apperence (pid, cid) values (8, 22);
insert into apperence (pid, cid) values (8, 23);
insert into apperence (pid, cid) values (8, 24);
insert into apperence (pid, cid) values (8, 25);

--안다행
insert into apperence (pid, cid) values (9, 21);
insert into apperence (pid, cid) values (9, 20);

--무엇이든물어보살
insert into apperence (pid, cid) values (10, 27);
insert into apperence (pid, cid) values (10, 28);
--
----동상이몽
insert into apperence (pid, cid) values (11, 29);
insert into apperence (pid, cid) values (11, 25);
----홍김동전
insert into apperence (pid, cid) values (12, 32);
insert into apperence (pid, cid) values (12, 33);
insert into apperence (pid, cid) values (12, 34);
insert into apperence (pid, cid) values (12, 35);
--인기가요
insert into apperence (pid, cid) values (13, 36);
insert into apperence (pid, cid) values (13, 37);

commit;
