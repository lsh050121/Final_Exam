# StudyGruop Matching

## 목차
1. [개요](#개요)
    1. [목적](#1-목적)
    2. [대상](#2-대상)
   

2. [프로그램의 중요성 및 필요성](#프로그램의-중요성-및-필요성) 
    1. [중요성](#1-중요성)
    2. [필요성](#2-필요성)


3. [프로그램 수행 절차](#프로그램-수행-절차)
    1. [다이어그램](#1-다이어그램)
    2. [클래스 다이어그램](#2-클래스-다이어그램)
    3. [절차 설명](#3-절차-설명)


4. [느낀점](#느낀점)
   1. [느낀점](#1-느낀점)


## 개요
### 1. 목적
스터디그룹을 **등록 및 검색**하여 원하는 스터디 그룹에 **참가**하고, 인원을 **모집**할 수 있는 프로그램입니다.    
사용자들이 스터디 그룹을 쉽게 등록하고, 검색하며 참가할 수 있도록 돕기 위해 설계되었습니다.        
편하고 직관적인 UI를 제공함으로써 스터디 관리의 편리성을 극대화하는 것을 목표로 합니다.    
이 프로그램을 통해 스터디 그룹 활동을 활발하게 하여 **많은 사람들과의 교류 및 학습역량 발전**을 목표로합니다.


### 2. 대상
기본적으로 스터디 그룹에 관심있는 **청주대 학생들**을 대상으로합니다.  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ● 학습이나 프로젝트를 위한 그룹 활동을 계획중인 학생  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ● 특정 요일에 맞는 스터디를 가입하고자 하는 학생

---

## 프로그램의 중요성 및 필요성
### 1. 중요성  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ● 효율적인 생성 및 참가 : 복잡성을 줄이고, 그룹 생성 및 참여 과정을 간소화합니다.   
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ● 시간 절약 : 등록된 스터디 그룹 정보를 한눈에 파악하고, 검색 기능을 통해 원하는 요일의 스터디 그룹을 빠르게 찾을수 있습니다.      
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ● 사용자 친화적 : 직관적인 UI와 명확한 동작 흐름을 제공하여 쉽게 사용하는 것을 목표 개발하였습니다.

### 2. 필요성
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ● 

---

## 프로그램 수행 절차
### 1. 다이어그램
```mermaid
graph TD
   A[시스템 시작] --> B[메인 화면 표시]
   B --> C[스터디 리스트 표시]
   B --> D[스터디 등록 버튼 클릭]
   B --> E[스터디 검색 버튼 클릭]
   D --> F[스터디 등록 다이얼로그 열기;]
   F --> G[주제, 요일, 인원 정보 입력]
   G --> H{현재 인원이 최대 인원 초과?}
   H -- 예 --> I[오류 메시지 표시] 
   H -- 아니오 --> J[새로운 스터디 그룹 파일에 저장]
   J --> K[스터디 리스트에 추가]
   E --> L[검색 다이얼로그 열기]
   L --> M[요일 선택]
   M --> N[검색 버튼 클릭]
   N --> O[리스트에서 해당 요일 그룹 필터링]
   O --> C
   C --> P[참가 버튼 클릭]
   P --> Q{최대 인원 도달 여부 확인}
   Q -- 예 --> R[참가 실패 메시지 표시]
   Q -- 아니오 --> S[현재 인원 증가 및 파일 업데이트]
   S --> T[참가 성공 메시지 표시]
   T --> C
   P --> U[취소 버튼 클릭]
```

### 2. 클래스 다이어그램
```mermaid
classDiagram
    class CJU_Study_Group {
        +main(String[] args)
    }

    class MainFrame {
        -JFrame mainFrame
        -JLabel title
        -JButton registerButton
        -JButton searchButton
        -JButton resetButton
        -JPanel studyListPanel
        +MainFrame()
        +createFrame() : JFrame
        +createLabel() : JLabel
        +createStudyListPanel() : JPanel
        +createRegisterButton() : JButton
        +createSearchButton() : JButton
        +createResetButton() : JButton
    }

    class RegisterDialog {
        +openRegistrationDialog(JFrame parent)
        -playErrorSound()
    }

    class SearchDialog {
        +openSearchDialog(JFrame parent, JPanel studyListPanel)
        -filterStudyGroupsByDay(String day) : List<String>
    }

    class ReadFile {
        +populateStudyList(String filePath, JPanel studyListPanel) : void
        +writeToFile(String filePath, String content) : void
        +addStudyGroup(String infoText, JPanel studyListPanel) : void
        +joinStudyGroup(String groupInfo, String studentNumber, JPanel studyListPanel) : boolean
    }

    CJU_Study_Group --> MainFrame : Creates
    MainFrame --> RegisterDialog : Opens
    MainFrame --> SearchDialog : Opens
    MainFrame --> ReadFile : Uses
    RegisterDialog --> ReadFile : Uses
    SearchDialog --> ReadFile : Uses
```
### 3. 절차 설명

---

## 느낀점
### 1. 느낀점
