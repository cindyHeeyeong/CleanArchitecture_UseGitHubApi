
2019/08/08
리팩토링 예정인 것 
1. favoriteuser에서 체크박스 문제
2. dummy 파일 지우기
3. ui material design에 맞게 고도화
4. data layer 수정 예정

2019/08/09 
<리팩토링 한 것 >
1. check 이벤트가 헷갈리게 되어 있어서 room구조 바꾸고 favoriteuser에서 checkbox 값이 null이 오는 현상 해결
2. room, data entity 구조 바꿈 
  -room entity에 check 칼럼 추가 
  -data layer의 UserEntity를 json과 room을 동시에 사용할 수 있는 entity로 수정
3. 불필요한 코드, 패키지, 클래스 
