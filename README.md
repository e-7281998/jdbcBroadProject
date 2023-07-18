# 신한DS 금융SW 아카데미 - 1차 개인 프로젝트

### 1️⃣목적
- 프로젝트 기획, 설계 역량을 향상한다.
- JDBC를 활용해 CRUD를 구현할 수 있다.

### 2️⃣개발 환경
<img alt="java" src ="https://img.shields.io/badge/java-FF7800.svg?&style=for-the-badge&logo=java&logoColor=white"/> <img alt="oracle" src ="https://img.shields.io/badge/oracle-F80000.svg?&style=for-the-badge&logo=oracle&logoColor=white"/> <img alt="jdbc" src ="https://img.shields.io/badge/jdbc-6528F7.svg?&style=for-the-badge&logo=jdbc&logoColor=white"/>

### 3️⃣설명
접근 유형에 따라 방송정보를 조회하고, 관리한다.

### 4️⃣요구사항
| 분류 | | 
|:---|:---|
|관계자|관계자는 프로그램을 제작 / 수정 / 폐지할 수 있어야 한다. |
|시청자| - 방송 프로그램, 연예인에 대한 정보를 조회할 수 있다.</br> - 프로그램은 프로그램명, 방송사, 요일로 조회할 수 있어야 한다.<br/> - 연예인은 이름, 성별, 프로그램으로 조회할 수 있어야 한다.<br/> - 연예인을 조회할 때 선택한 조회정보는 출력하지 않는다.(이름 제외)</P>|
|방송국|방송국 명칭은 동일할 수 없다.  |
|연예인| - 현재 출연중인 프로그램이 없을 수 있다. | 
|프로그램| - 하나의 방송국에서 여러 프로그램을 제작할 수는 있지만, 프로그램이 동시간에 방영(시작)할 수는 없다.<br/> -  프로그램명, 시간, 요일, 방송국은 필수로 값이 들어가야 하지만 프로그램 소개는 하지 않아도 된다. <br/> - 하나의 프로그램에 다수의 연예인이 출연할 수 있지만, 중복해서 들어갈 수 없다.<br/> - 프로그램이 삭제되면 해당 프로그램의 출연정보도 같이 삭제된다.|

### 5️⃣ERD
<p align="center">
  <img src="https://github.com/e-7281998/jdbcBroadProject/assets/78722497/d5bcbd88-6347-4145-91b7-ea961359dbcd">
</p>

### 6️⃣로직
<p align="center">
  <img src="https://github.com/e-7281998/jdbcBroadProject/assets/78722497/a98ea1ca-074a-4b63-887b-6267a07012f0">
</p>
 
### 7️⃣시연
|시청자로 접근|관계자로 접근| 
|:---|:---|
|<img width="600px" src="https://github.com/e-7281998/jdbcBroadProject/assets/78722497/87bcc275-f57b-4095-ac57-038860776722">|<img width="600px" src="https://github.com/e-7281998/jdbcBroadProject/assets/78722497/f19c5194-d2e2-45ad-89e9-ba08974a9306">|
