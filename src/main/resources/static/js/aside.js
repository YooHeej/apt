document.addEventListener("DOMContentLoaded", function() {
  // 맵 초기화 함수
  function initMap() {
      var container = document.getElementById('map');
      var options = {
          center: new kakao.maps.LatLng(37.5665, 126.9780), // 서울 시청 좌표
          level: 10 // 지도 확대 수준
      };
      var map = new kakao.maps.Map(container, options);

      // 위도와 경도 표시
      document.getElementById('latitude').innerHTML += options.center.getLat();
      document.getElementById('longitude').innerHTML += options.center.getLng();

      // 마커 생성
      var markerPosition = new kakao.maps.LatLng(options.center.getLat(), options.center.getLng());
      var marker = new kakao.maps.Marker({
          position: markerPosition
      });
      marker.setMap(map);
  }

  // 카카오 맵 API 로드
  kakao.maps.load(initMap);
});