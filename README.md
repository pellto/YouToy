# YouToy

유튜브의 기능들을 살펴보고 해당 기능들을 구현하는 백엔드 클론 코딩 프로젝트 

## 프로젝트 목표
- 유튜브의 다양한 기능들을 사용할 수 있어야 한다.
- JDBC -> JPA로 코드 변환을 시도한다.
- 모든 서비스(또는 유스케이스)에 대해 테스트 코드를 작성한다.
- traffic 병목이 일어나는 곳을 테스트하고 개선한다.
- CI / CD를 적용한다.

## Docs
- [기능 정의 및 요구사항 문서]()
- [DB 설계 문서]()
- [API 문서]()
- [프로젝트 설계 문서]()

## TODO List
- [x] 기능 정의
- [x] 각 기능의 요구사항 정리
- [x] v1
  - [x] 기능에 따라 DB Schema 설계
  - [x] 도메인 설계
  - [x] 기능 -> API로 변환
  - [x] API 문서 작성
  - [x] 프로젝트 코드 아키텍쳐 설계
  - [x] 프로젝트 아키텍쳐 설계
  - [x] 기능 구현 (with JDBC)
  - [x] 유닛 테스트 구현
  - [x] 통합 테스트 구현
  - [x] Github Actions를 이용해 feature 브랜치 CI
  - [x] Jenkins를 이용한 CI / CD 구현
- [ ] v2
  - [x] 기능에 따라 DB Schema 설계
  - [ ] 도메인 설계
  - [ ] 기능 -> API로 변환
  - [ ] API 문서 작성
  - [ ] 프로젝트 코드 아키텍쳐 설계
  - [ ] 프로젝트 아키텍쳐 설계
  - [ ] v2로 코드 리팩토링
  - [ ] Code Coverage 측정 및 보완
  - [ ] JDBC -> JPA 변환
  - [ ] 서비스 traffic 측정 및 보완