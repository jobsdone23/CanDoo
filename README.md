# 대구 특별 안전 지도

	
 ----------------------------------------------------------------------------------------------------------------------

팀 정보

  #PGSR
	
  2019111103 최형우
  
  2019114780 최병준
  
  2019110647 장락영
  
  2019116048 김현수


----------------------------------------------------------------------------------------------------------------------

제출 세션 및 주제

	*자유 세션
	->대구 지역 구성원의 삶을 윤택하게 하는 SW 개발 : 안전 문제 상황에서 신속한 대처를 위한 '공공재의 정보 접근 어려움'

----------------------------------------------------------------------------------------------------------------------


프로젝트 한 줄 설명

	대구 내 다양한 안전 문제들의 신속한 대처를 위해, 찾기 힘든 공공 장비 및 기관의 위치 정보를 제공하는 어플리케이션 

----------------------------------------------------------------------------------------------------------------------


프로젝트에 대한 설명


	*상황 분석 및 영향

	1. 다양한 정부의 안전정책과 공공재등의 지원과는 별개로 매년 크고 작은 사건사고들이 발생함. 그중에서는 빠른 대처만 가능했다면 큰 피해 없이 해결될 문제들도 있었다고 생각함.

	2. 위기 발생 시 경찰서 등의 기관들은 네이버 지도와 같은 위치를 알려주는 어플이나 검색을 통해 쉽게 위치 파악이 가능함. 
	
	   이에 반해 안전을 위해 배치된 장비들이나 잘 알려지지 않은 민간 비상 대피소 등의 기관은 위기상황 발생 시 바로 찾아내기에 어려움을 느낌.

	
	-> 이러한 상황들을 고려해 봤을 때, 위급한 상황에서 활용할 수 있는 장비 및 기관들의 위치정보를 한눈에 파악할 수 있는 어플을 제작하여 사용한다면, 대구시 구성원들이 다양한 안전문제들에 발빠른 대처를 통해 보다 안전한 대구시를 만들어 나갈 수 있다고 생각함.
	
	
	


<응급>

	AED(자동심장충격기) - 주위 사람이 심정지가 왔을 때 119호출 만으로는 오는데 물리적인 시간이 소요. 현장에서 빠른 대처를 위해 선정함 
	
	심야약국 / 24시간 약국 – 응급처치가 필요한 상황에 시간에 한정되지 않고 갈 수 있는 약국들의 정보 제공을 위해 선정함


<화재>

	소화전 – 무엇보다 화재는 빠른 대처가 중요하기에 주변 소화전을 이용하기 위해 선정함


<재난>

	비상급수시설, 비상대피시설, 지진옥외대피소 – 전쟁, 집중호우 등의 혹시 모를 재난 상황 발생시 신속한 대피를 위해 선정함


<치안>

	아동안전지킴이집 – 아이들의 위험한 상황이 발생시, 경찰청에서 지정한 공식적으로 안전을 제공하는 곳으로 찾아갈 수 있도록 하기 위해 선정함
	
	아동센터 - 지역사회 아동의 보호 · 교육, 건전한 놀이와 오락의 제공, 보호자와 지역사회의 연계 등 아동의 건전 육성을 제공하는 곳임으로 선정함 
	
	치안센터 – 지구대 및 파출소에 비해 상대적으로 알려지지 않아서 선정함 
	
	성범죄자 거주지 – 안전을 위해 사람들이 해당 지역에 있는 성범죄자의 위치를 파악할 수 있도록 하기위해 선정함

----------------------------------------------------------------------------------------------------------------------


프로젝트 활용된 기술(3가지 이내)

	1. Google My Maps의 DB화를 통한 안전 위치 정보 저장 
	   
	   공공데이터 csv 파일을 활용하여 AED 위치등 검색해서 쉽게 나오지 않는 안전 정보들을 My Maps에 응급, 화재, 재난, 치안 지도들로 나누고 데이터베이스화 하였음.
	
	2. LocationListener,Geocoder를 활용하여 사용자의 위치 정보 확인 
	   
	   locationlistener, Geocoder를 통해 사용자의 현위치 혹은 검색한 위치의 위도와 경도 정보를 받아와서 원하는 위치를 화면상에 표시할 수 있도록 하였음.
	
	
	3. ProgressDialog를 통한 로딩창 구현

	Webview를 활용하여 Google map을 띄우기 위해 어느 정도의 대기시간이 필요한데 이를 UI상에서 표현하기 위해 로딩화면을 구현하여 사용자가 이를 인지하도록 하였음.
	
	
----------------------------------------------------------------------------------------------------------------------



시연 영상

	*  유튜브 링크 : https://www.youtube.com/watch?v=NmlIt3hBlDU


----------------------------------------------------------------------------------------------------------------------
	

