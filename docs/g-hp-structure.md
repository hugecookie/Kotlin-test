# G-HP Reference Structure

`https://greencatsoft.com/g-hp`를 참고해 정리한 랜딩/소개 페이지 구조안.

## 1. Page Goal

- 제품이 무엇인지 5초 안에 이해시킨다.
- 어떤 사용자에게 어떤 효율을 주는지 빠르게 설득한다.
- 상세 기능을 "업무 흐름" 기준으로 보여준다.
- 마지막에 문의 또는 데모 요청으로 자연스럽게 연결한다.

## 2. Recommended Information Architecture

### A. Hero

- 제품명: `G-HP (Home Planner)`
- 한 줄 설명: `클라우드 기반 3D 공간 설계 솔루션`
- 보조 설명:
  - 홈 퍼니싱 설계/상담 업무 개선
  - 정확한 설계, 주문 연결, 오생산/오시공 감소
  - 계약률 및 고객 만족도 향상
- CTA:
  - `데모 요청`
  - `기능 보기`

### B. Product Value

- 핵심 가치 1: `설계 속도 향상`
- 핵심 가치 2: `정확한 주문 연계`
- 핵심 가치 3: `고객 커뮤니케이션 강화`
- 핵심 가치 4: `지속 가능한 운영 기반`

이 섹션은 카드 3~4개로 간단히 보여주고, 수치가 있다면 함께 배치한다.

### C. Service Positioning

- 바로홈(Barohom)과의 관계 설명
- SaaS 서비스 브랜드인지, 엔진/플랫폼인지 구분
- 사용 대상:
  - 가구/주방/수납 설계 영업 조직
  - 브랜드 관리자
  - 운영/주문 담당자

### D. Feature Split

#### User Features

- 설치 없는 웹 사용 환경
- 도면 기반 공간 구성
- 2D/3D 배치
- 주방/수납 설계
- BOM 전개
- 고급 렌더링
- 입면도/평면도 산출
- ERP 연동 주문 처리

#### Admin Features

- 브랜드/모델별 3D 제품 정보 관리
- 설계 기능 속성 관리
- 제품 모델/플랜 라이브러리 운영
- 사용 로그 분석
- 사용자/그룹 권한 관리

이 구간은 `사용자 / 관리자` 2열 비교 구조가 가장 적합하다.

### E. Detailed Capabilities

#### Common Design Tools

- 벽면 배치 시 자동 회전
- 거리 자동 표시
- 수치 입력 기반 위치 조정
- 기즈모 기반 이동/회전/정렬

#### Design Convenience

- 준비된 플랜으로 시작
- 복사/붙여넣기
- 멀티 선택 및 정렬
- 색상/모델 일괄 변경

#### Storage Planning

- 수납장 스냅 배치
- 크기/도어/다릿발/손잡이 세부 조정
- 몸통 구성 변경

#### Kitchen Planning

- 상판/걸레받이/서라운딩 자동 배치
- 싱크볼/인덕션 매립
- 빌트인 기기 스냅 배치
- 뒷턱/뒷선반/마감재 설계

이 구간은 단순 나열보다 `업무 시나리오` 순서로 배치하는 편이 읽기 쉽다.

### F. Process Section

추천 추가 섹션. 원본 사이트에는 약하지만, 실제 설득력은 이 섹션이 높다.

1. 공간 정의
2. 제품 배치
3. 세부 설계
4. 렌더링/도면 생성
5. BOM/주문 연계

### G. Proof / Outcomes

추천 추가 섹션.

- 계약률 향상
- 설계 시간 단축
- 주문 오류 감소
- 시공 커뮤니케이션 개선

실제 수치가 없으면 정성 문장으로만 두고, 향후 데이터 확보 후 교체한다.

### H. Contact / CTA

- 데모 요청
- 도입 문의
- 회사 연락처
- 이메일

페이지 마지막 CTA는 `기능 보기`보다 `도입 상담`이 더 적합하다.

## 3. Recommended Navigation

- `제품 소개`
- `핵심 기능`
- `사용자/관리자`
- `설계 프로세스`
- `문의하기`

싱글 페이지라면 상단 앵커 네비게이션으로 충분하다.

## 4. Recommended Screen Composition

### Top to Bottom Order

1. Hero
2. 핵심 가치
3. 사용자/관리자 기능 비교
4. 설계 상세 특장점
5. 설계 프로세스
6. 기대 효과
7. 문의 CTA

원본 사이트는 텍스트 비중이 높다. 실제 구현 시에는 각 섹션마다 이미지, 다이어그램, UI 캡처가 반드시 필요하다.

## 5. Component-Level Structure

웹 페이지 또는 앱 소개 화면으로 옮길 때의 컴포넌트 예시:

- `Header`
- `HeroSection`
- `ValueCardsSection`
- `AudienceSplitSection`
- `FeatureGridSection`
- `WorkflowSection`
- `OutcomeSection`
- `ContactSection`
- `Footer`

## 6. Content Tone

- 기술 설명보다 업무 개선 효과를 먼저 말한다.
- 기능명보다 사용 장면을 먼저 보여준다.
- 관리자 기능은 운영 통제, 사용자 기능은 설계 생산성 중심으로 분리한다.
- 같은 내용을 반복 설명하지 말고, 각 섹션 역할을 분명히 나눈다.

## 7. If Building This In Kotlin Project

현재 프로젝트가 웹 UI가 아니라면, 먼저 아래 3단계로 분리하는 편이 좋다.

1. `content`
   - 페이지 섹션 데이터 모델
   - 카피/문구 관리
2. `application`
   - 페이지 조립 로직
   - 사용자 유형별 노출 규칙
3. `presentation`
   - HTML/템플릿 또는 Compose 렌더링

즉, 지금은 "기능 구현"보다 "섹션 구조와 카피 구조"를 먼저 코드로 분리하는 것이 맞다.
