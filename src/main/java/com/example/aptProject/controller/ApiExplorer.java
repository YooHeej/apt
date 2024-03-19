//package com.example.aptProject.controller;
//
//import com.example.aptProject.entity.APIResult;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.w3c.dom.Document;
//import org.w3c.dom.Element;
//import org.w3c.dom.Node;
//import org.w3c.dom.NodeList;
//
//import javax.xml.parsers.DocumentBuilder;
//import javax.xml.parsers.DocumentBuilderFactory;
//import java.io.*;
//import java.net.HttpURLConnection;
//import java.net.MalformedURLException;
//import java.net.URL;
//import java.net.URLEncoder;
//
//public class ApiExplorer {
//    public static void main(String[] args) throws IOException {
//
//        String serviceKey = "rlpbGR9EbYg8iu0YftsAGmUeblmq9qJenXIk7WsVg0qr%2FRXALrab9zfstv0OkO5A15gR4aKR5aO%2FVFtjV6dkfA%3D%3D";
//        String pageNo = "1";
//        String numOfRows = "10000000";
//        String LAWD_CD = "11110";
//        String DEAL_YMD = "201512";
//
//        StringBuilder sb = getAPIResult(serviceKey, pageNo, numOfRows, LAWD_CD, DEAL_YMD);
//
//        try {
//            // XML 문자열
//            String xmlString = sb.toString();
//
//            // XML 파서 생성
//            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
//            DocumentBuilder builder = factory.newDocumentBuilder();
//            InputStream inputStream = new ByteArrayInputStream(xmlString.getBytes());
//
//            // XML 파싱
//            Document doc = builder.parse(inputStream);
//            doc.getDocumentElement().normalize();
//
//            Node totalCountNode = doc.getElementsByTagName("totalCount").item(0);
//            String totalCount = totalCountNode.getTextContent().trim();
//
//            // 각 항목 추출
//            NodeList itemList = doc.getElementsByTagName("item");
//            for (int i = 0; i < itemList.getLength(); i++) {
//                Node itemNode = itemList.item(i);
//                if (itemNode.getNodeType() == Node.ELEMENT_NODE) {
//                    Element itemElement = (Element) itemNode;
//                    String 거래금액 = itemElement.getElementsByTagName("거래금액").item(0).getTextContent().trim();
////                    String 거래유형 = itemElement.getElementsByTagName("거래유형").item(0).getTextContent().trim();
//                    String 건축년도 = itemElement.getElementsByTagName("건축년도").item(0).getTextContent().trim();
//                    String 년 = itemElement.getElementsByTagName("년").item(0).getTextContent().trim();
//                    String 도로명 = itemElement.getElementsByTagName("도로명").item(0).getTextContent().trim();
////                    String 도로명건물본번호코드 = itemElement.getElementsByTagName("도로명건물본번호코드").item(0).getTextContent().trim();
////                    String 도로명건물부번호코드 = itemElement.getElementsByTagName("도로명건물부번호코드").item(0).getTextContent().trim();
////                    String 도로명시군구코드 = itemElement.getElementsByTagName("도로명시군구코드").item(0).getTextContent().trim();
////                    String 도로명일련번호코드 = itemElement.getElementsByTagName("도로명일련번호코드").item(0).getTextContent().trim();
////                    String 도로명지상지하코드 = itemElement.getElementsByTagName("도로명지상지하코드").item(0).getTextContent().trim();
////                    String 도로명코드 = itemElement.getElementsByTagName("도로명코드").item(0).getTextContent().trim();
////                    String 동 = itemElement.getElementsByTagName("동").item(0).getTextContent().trim();
////                    String 등기일자 = itemElement.getElementsByTagName("등기일자").item(0).getTextContent().trim();
////                    String 매도자 = itemElement.getElementsByTagName("매도자").item(0).getTextContent().trim();
//                    String 법정동 = itemElement.getElementsByTagName("법정동").item(0).getTextContent().trim();
////                    String 법정동본번코드 = itemElement.getElementsByTagName("법정동본번코드").item(0).getTextContent().trim();
////                    String 법정동부번코드 = itemElement.getElementsByTagName("법정동부번코드").item(0).getTextContent().trim();
////                    String 법정동시군구코드 = itemElement.getElementsByTagName("법정동시군구코드").item(0).getTextContent().trim();
////                    String 법정동읍면동코드 = itemElement.getElementsByTagName("법정동읍면동코드").item(0).getTextContent().trim();
////                    String 법정동지번코드 = itemElement.getElementsByTagName("법정동지번코드").item(0).getTextContent().trim();
//                    String 아파트 = itemElement.getElementsByTagName("아파트").item(0).getTextContent().trim();
//                    String 월 = itemElement.getElementsByTagName("월").item(0).getTextContent().trim();
//                    String 일 = itemElement.getElementsByTagName("일").item(0).getTextContent().trim();
////                    String 일련번호 = itemElement.getElementsByTagName("일련번호").item(0).getTextContent().trim();
//                    String 전용면적 = itemElement.getElementsByTagName("전용면적").item(0).getTextContent().trim();
////                    String 중개사소재지 = itemElement.getElementsByTagName("중개사소재지").item(0).getTextContent().trim();
////                    String 지번 = itemElement.getElementsByTagName("지번").item(0).getTextContent().trim();
//                    String 지역코드 = itemElement.getElementsByTagName("지역코드").item(0).getTextContent().trim();
//                    String 층 = itemElement.getElementsByTagName("층").item(0).getTextContent().trim();
////                    String 해제사유발생일 = itemElement.getElementsByTagName("해제사유발생일").item(0).getTextContent().trim();
////                    String 해제여부 = itemElement.getElementsByTagName("해제여부").item(0).getTextContent().trim();
//
//                    APIResult apiResult = new APIResult(년,월,일,지역코드,법정동, 도로명,아파트,층,전용면적,건축년도,거래금액,);
//
//
//                    // 추출한 항목 출력년금액);
//                    System.out.println("이거 안나오지??????????????@@@@@@@@@@@@@@@@@");
//                    System.out.println("년: " + 년);
//                    System.out.println("월: " + 월);
//                    System.out.println("일: " + 일);
//                    System.out.println("지역코드: " + 지역코드);
//                    System.out.println("법정동: " + 법정동);
//                    System.out.println("도로명: " + 도로명);
//                    System.out.println("아파트: " + 아파트);
//                    System.out.println("층: " + 층);
//                    System.out.println("전용면적(m^2): " + 전용면적);
//                    System.out.println("건축년도: " + 건축년도);
//                    System.out.println("거래금액(만): " + 거래금액);
//                    System.out.println("totalCount: " + totalCount);
//                    // 필요한 나머지 항목들도 출력합니다.
//                    System.out.println("---");
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//    }
//    public static StringBuilder getAPIResult(String serviceKey, String pageNo, String numOfRows, String LAWD_CD, String DEAL_YMD) throws IOException {
//
//        StringBuilder urlBuilder = new StringBuilder("http://openapi.molit.go.kr/OpenAPI_ToolInstallPackage/service/rest/RTMSOBJSvc/getRTMSDataSvcAptTradeDev"); /*URL*/
//        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=" + serviceKey); /*Service Key*/
//        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + pageNo); /*페이지번호*/
//        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + numOfRows); /*한 페이지 결과 수*/
//        urlBuilder.append("&" + URLEncoder.encode("LAWD_CD","UTF-8") + "=" + LAWD_CD); /*지역코드*/
//        urlBuilder.append("&" + URLEncoder.encode("DEAL_YMD","UTF-8") + "=" + DEAL_YMD); /*계약월*/
//        URL url = new URL(urlBuilder.toString());
//        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//
//        conn.setRequestMethod("GET");
//        conn.setRequestProperty("Content-type", "application/json");
//        System.out.println("Response code: " + conn.getResponseCode());
//
//        BufferedReader rd;
//        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
//            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//        } else {
//            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
//        }
//        StringBuilder sb = new StringBuilder();
//        String line;
//        while ((line = rd.readLine()) != null) {
//            sb.append(line);
//        }
//        rd.close();
//        conn.disconnect();
//        return sb;
//    }
//}