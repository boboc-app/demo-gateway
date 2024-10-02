# GateWay
### 사용자 인증
- Authorization: Bearer {JWT} 를 통해서 사용자 인증
- JWT에 해당하는 사용자 정보가 존재하는 지 확인

### API
- 사용자 별 전체 API 호출 Rate Limit 산정 필터 Header: `X-Boboc-Ratelimt`
  - 매 X시간 단위 초기화
  - 총 X 이상의 요청이 들어올 경우 429 Error 
- IP 별 API 호출 Rate Limit 산정 필터 Header: `X-Boboc-Ip-Ratelimit`
  - 매 X시간 단위 초기화
  - 총 X 이상의 요청이 들어올 경우 429 Error
- 사용자 별 API 호출 횟수 기록 필터
- 서버에서 동시에 처리 가능한 횟수 제한 필터