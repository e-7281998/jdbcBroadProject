----------------------------------------------------------------�ʱ�ȭ
 drop table apperence;
 drop table Celebrity;
 drop table BroadProgram;
 drop table BroadStation;

drop sequence cele_seq;
drop sequence bp_seq;
drop sequence bs_seq;

-----------------------------------------------------------------------
---------------------------------���̺� ����-----------------------------
-----------------------------------------------------------------------
-- ��۱� ���̺�
create table BroadStation (
    bid NUMBER primary key,
    broadcaster VARCHAR(45) not null unique
);

-- ��� ���̺�
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

 
-- �ι� ���̺�
CREATE TABLE Celebrity (
  cid NUMBER PRIMARY KEY,
  name VARCHAR(12) NOT NULL,
  gender VARCHAR(3) NOT NULL,
  appfee NUMBER NOT NULL,
  birth DATE NOT NULL,
  
  CONSTRAINT ck_Celebrity_gender CHECK (gender in('F','M'))
);


-- �⿬ ���̺�
create table apperence(
    pid NUMBER,
    cid NUMBER,

   constraint pk_id primary key(pid, cid) ,
   constraint fk_apperence_BroadProgram Foreign key (pid) references BroadProgram(pid) on DELETE CASCADE,
   constraint fk_apperence_Celebrity Foreign key (cid) references Celebrity(cid) on DELETE CASCADE
);

-----------------------------------------------------------------------
--------------------------------- ������   -----------------------------
-----------------------------------------------------------------------
--------1) �ι� ������
create sequence cele_seq
    increment by 1
    start with 1;
    
--------2) ��� ������
create sequence bp_seq
    increment by 1
    start with 1;

--------3) ��۱� ������
create sequence bs_seq
    increment by 1
    start with 1;

-----------------------------------------------------------------------
---------------------------------�� �ֱ�---------------------------------
-----------------------------------------------------------------------

--��ۻ� �� �ֱ�
insert into BroadStation values (bs_seq.nextval, 'KBS');
insert into BroadStation values (bs_seq.nextval, 'MBC');
insert into BroadStation values (bs_seq.nextval, 'SBS');

----���α׷� �� �ֱ�
insert into BroadProgram values (bp_seq.nextval, 3, '18:20', '���׸�', '������ ��Ÿ�� ������� �Բ� �̼��� �Ѵ�.', '��');
insert into BroadProgram values (bp_seq.nextval, 2, '18:20', '��鹹�ϴ�?', '���缮�� �������� ����Ȯ�� ���Ϲ���(YOONIVERSE) �����̾�Ƽ.', '��');
insert into BroadProgram values (bp_seq.nextval, 2, '23:10', '�� ȥ�� ���', ' ȥ�� ��� �����ε��� �ϻ��� ���� ī�޶� ���·� ���� ��ť���͸� ������ ���� ���α׷�', '��');
insert into BroadProgram values (bp_seq.nextval, 1, '23:10', '���� �Ǹ��ϴ�', ' �ݷ��߰� ����� �ູ�ϰ� ��췯�� ��� ���� �Բ� ����غ��� ���α׷�', '��');
insert into BroadProgram values (bp_seq.nextval, 2, '22:45', '������ Ȩ��', '�ٻ� �����ε��� �� ã�⸦ ���� ���� ���� ��Ÿ��! �׵��� ������ ��ǰ �߰� ��Ʋ', '��');
insert into BroadProgram values (bp_seq.nextval, 1, '18:15', '1��2��', '�츮�� �˰� �ִ�, �츮�� ���� ���ϴ� �Ƹ��ٿ� �츮����. ������ ���� ���ڿ� �Բ� 1�� 2���� ������ ������!', '��');
insert into BroadProgram values (bp_seq.nextval, 2, '18:05', '���鰡��', '����, �ź�, ������ ���� ��Ÿ���� ��Ҹ������� �Ƿ��� �˳��� ���� �����̾�Ƽ ���α׷�', '��');
insert into BroadProgram values (bp_seq.nextval, 2, '22:30', '������Ÿ', '���� ƥ�� �𸣴� ��ö������ �Դ����� �Խ�Ʈ���� �������� ���� ��¥ �̾߱⸦ ������� ������ ��ũ��', '��');
insert into BroadProgram values (bp_seq.nextval, 2, '21:00', '�Ƚο�� �����̾�','������ ���� �߻��� ã�ư� ������ ��ǥ ��ģ���� �ڿ����� ���� �״�� ��ƺ��� ���α׷�', '��');
insert into BroadProgram values (bp_seq.nextval, 1, '20:30', '�����̵� �����', '�� ���� ���� Ȯ!! �վ��� ������� �ذ�å�� �ִ� ���α׷�', '��');
insert into BroadProgram values (bp_seq.nextval, 3, '23:10', '�����̸�2', '�پ��� �о��� Ŀ�õ��� �Բ� ��� ���� ��ġ�� ���캸�� ���α׷�', '��');
insert into BroadProgram values (bp_seq.nextval, 1, '20:30', 'ȫ�赿��', 'ȫ �� �� ���� �������� ����� ü���� �Ǵ� �Ƕ� ������ �� ���� �����̾�Ƽ', '��');
insert into BroadProgram values (bp_seq.nextval, 3, '15:50', '�αⰡ��', 'SBS ���� ���� ���α׷����� �α� �������� �ֽ� ������ ���� �� �ִ� ���α׷�', '��');




----������ �� �ֱ�
insert into Celebrity values (cele_seq.nextval, '���ҹ�', 'F', 5000, '1986/04/07' );
insert into Celebrity values (cele_seq.nextval, '������', 'M', 8000, '1976/04/25' );
insert into Celebrity values (cele_seq.nextval, '���缮', 'M', 10000, '1972/08/14' );
insert into Celebrity values (cele_seq.nextval, '������', 'M', 8000, '1977/11/07' );
insert into Celebrity values (cele_seq.nextval, '�ڳ���', 'F', 8000, '1985/10/25' );
insert into Celebrity values (cele_seq.nextval, '�����', 'M', 6300, '1965/12/2' );
insert into Celebrity values (cele_seq.nextval, 'Ű', 'M', 7000, '1991/09/23' );
insert into Celebrity values (cele_seq.nextval, '����', 'M', 6900, '1979/8/20' );
insert into Celebrity values (cele_seq.nextval, '�̹���', 'F', 4900, '1994/09/23' );
insert into Celebrity values (cele_seq.nextval, 'ȭ��', 'F', 5000, '1995/07/23' );
insert into Celebrity values (cele_seq.nextval, '����ȿ', 'F', 6300, '1991/08/15' );
insert into Celebrity values (cele_seq.nextval, '������', 'M', 6900, '1966/02/10' );
insert into Celebrity values (cele_seq.nextval, '�缼��', 'M', 6000, '1986/12/08' );
insert into Celebrity values (cele_seq.nextval, '������', 'M', 6600, '1978/11/06' );
insert into Celebrity values (cele_seq.nextval, '������', 'M', 6800, '1982/05/11' );
insert into Celebrity values (cele_seq.nextval, '������', 'M', 6900, '1979/09/24' );
insert into Celebrity values (cele_seq.nextval, '���ο�', 'M', 6500, '1994/09/17' );
insert into Celebrity values (cele_seq.nextval, '�嵵��', 'F', 7000, '1985/03/10' );
insert into Celebrity values (cele_seq.nextval, '�̰��', 'M', 8000, '1960/09/21' );
insert into Celebrity values (cele_seq.nextval, '����ȯ', 'M', 6500, '1976/01/27' );
insert into Celebrity values (cele_seq.nextval, '��', 'M', 6500, '1982/05/10' );
insert into Celebrity values (cele_seq.nextval, '�ȿ���', 'F', 6800, '1983/11/05' );
insert into Celebrity values (cele_seq.nextval, '������', 'M', 7000, '1980/09/12' );
insert into Celebrity values (cele_seq.nextval, '�豹��', 'M', 7100, '1965/02/06' );
insert into Celebrity values (cele_seq.nextval, '�豸��', 'M', 7000, '1970/10/03' );
insert into Celebrity values (cele_seq.nextval, '�輺��', 'M', 7000, '1972/10/10' );
insert into Celebrity values (cele_seq.nextval, '�̼���', 'M', 7000, '1974/06/03' );
insert into Celebrity values (cele_seq.nextval, '������', 'M', 7000, '1975/02/10' );
insert into Celebrity values (cele_seq.nextval, '���', 'F', 7100, '1975/07/06' );
insert into Celebrity values (cele_seq.nextval, '��ȣ��', 'M', 8300, '1970/06/11' );
insert into Celebrity values (cele_seq.nextval, '������', 'F', 6500, '1983/03/23' );
insert into Celebrity values (cele_seq.nextval, '�ֿ���', 'M', 6300, '1986/11/28' );
insert into Celebrity values (cele_seq.nextval, 'ȫ����', 'F', 6500, '1977/12/23' );
insert into Celebrity values (cele_seq.nextval, '����ȣ', 'M', 7000, '1982/09/09' );
insert into Celebrity values (cele_seq.nextval, '��쿵', 'M', 6800, '1989/04/30' );
insert into Celebrity values (cele_seq.nextval, '����', 'M', 6000, '1999/09/13' );
insert into Celebrity values (cele_seq.nextval, '������', 'F', 6000, '2001/07/31' );
insert into Celebrity values (cele_seq.nextval, '�̿���', 'M', 7000, '1985/11/18' );
insert into Celebrity values (cele_seq.nextval, '�缼��', 'M', 7000, '1985/08/18' );





----�⿬�̷� �� �ֱ�
----���׸�
insert into apperence (pid, cid) values (1, 1);
insert into apperence (pid, cid) values (1, 2);
insert into apperence (pid, cid) values (1, 3);
insert into apperence (pid, cid) values (1, 8);
insert into apperence (pid, cid) values (1, 11);
insert into apperence (pid, cid) values (1, 12);
insert into apperence (pid, cid) values (1, 13);
--�
insert into apperence (pid, cid) values (2, 3);
insert into apperence (pid, cid) values (2, 8);
insert into apperence (pid, cid) values (2, 9);
--��ȥ��
insert into apperence (pid, cid) values (3, 4);
insert into apperence (pid, cid) values (3, 5);
insert into apperence (pid, cid) values (3, 6);
insert into apperence (pid, cid) values (3, 7);
insert into apperence (pid, cid) values (3, 10);
--���Ǹ�
insert into apperence (pid, cid) values (4, 18);
insert into apperence (pid, cid) values (4, 19);

--r������Ȩ��
insert into apperence (pid, cid) values (5, 5);
insert into apperence (pid, cid) values (5, 13);
insert into apperence (pid, cid) values (5, 21);
--1��
insert into apperence (pid, cid) values (6, 14);
insert into apperence (pid, cid) values (6, 15);
insert into apperence (pid, cid) values (6, 16);
insert into apperence (pid, cid) values (6, 17);
---���鰡��
insert into apperence (pid, cid) values (7, 26);
insert into apperence (pid, cid) values (7, 25);
--������Ÿ
insert into apperence (pid, cid) values (8, 22);
insert into apperence (pid, cid) values (8, 23);
insert into apperence (pid, cid) values (8, 24);
insert into apperence (pid, cid) values (8, 25);

--�ȴ���
insert into apperence (pid, cid) values (9, 21);
insert into apperence (pid, cid) values (9, 20);

--�����̵繰���
insert into apperence (pid, cid) values (10, 27);
insert into apperence (pid, cid) values (10, 28);
--
----�����̸�
insert into apperence (pid, cid) values (11, 29);
insert into apperence (pid, cid) values (11, 25);
----ȫ�赿��
insert into apperence (pid, cid) values (12, 32);
insert into apperence (pid, cid) values (12, 33);
insert into apperence (pid, cid) values (12, 34);
insert into apperence (pid, cid) values (12, 35);
--�αⰡ��
insert into apperence (pid, cid) values (13, 36);
insert into apperence (pid, cid) values (13, 37);

commit;
