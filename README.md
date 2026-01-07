# 🍚 밥친구 (BFriend)

📌 팀 프로젝트이며, 본 README는
포트폴리오 목적의 개인 기여를 중심으로 작성되었습니다.

> **근처의 식사 친구를 연결하는 게시판형 플랫폼**  
> Spring Boot 기반 백엔드 설계를 중심으로,  
> DB → API → 프론트엔드 연동까지 구현한 팀 프로젝트입니다.

---

## 📌 프로젝트 개요
- **프로젝트 기간**: 2025년  
- **형태**: 팀 프로젝트  
- **역할**: 팀장 / 백엔드 중심 풀스택 개발  
- **목표**:  
  위치 기반으로 식사 동행을 모집하고 참여할 수 있는 커뮤니티 플랫폼 구현

---

## 👤 담당 역할
- 프로젝트 **기획 및 일정 관리**, 팀 협업 구조 설계
- **Spring Boot 기반 REST API 설계 및 구현**
- **DB 스키마 설계 및 Entity 중심 도메인 모델링**
- API 명세 작성으로 프론트–백엔드 협업 효율 개선
- Git-flow 브랜치 전략 및 커밋 컨벤션 도입

---

## 🛠 기술 스택
### Backend
- Java
- Spring Boot
- Spring Data JPA

### Frontend
- React
- JavaScript

### Database
- RDB (요구사항 기반 직접 설계)

### Collaboration
- Git / GitHub
- Git-flow
- Figma

---

## 🧩 주요 기능
### 모임방
- 모임방 생성
- 모임방 목록 조회
- 키워드 기반 모임방 검색
- 모임방 상세 조회 (내가 참여한 모임 포함)

### 사용자 흐름
1. 모임방 목록 조회
2. 모임방 생성 또는 참여
3. 참여한 모임방 상세 확인

---

## 🧱 백엔드 구조
- **Controller – Service – Repository 계층 분리**
- Entity 중심 도메인 설계
- Request / Response DTO 분리
- 공통 오류 응답 형식 정의

```text
Controller
  └ Service
      └ Repository
          └ Entity
