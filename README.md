## COWAY-APP-SYSTEM- REST API 구현




# 🙌 계기 
* 코웨이 제품 특성상 지속적인 관리가 필요한 상품들이 많다. (필터 교체, 수질관리 등등)
* 카톡으로 제품에 대한 상품관리 서비스를 받는것이 불편함
* 앱서비스 내에서 상품을 관리하는 기능을 제공
<br/>


# 🛠 사용 기술
* Spring boot 2.7.5
* Spring data jpa
* Swagger 2.9.2
* AWS RDS MYSQL 8.0.28
* AWS EC2
* Kotlin
<br/>




# 👉 내가 만든 앱 소개
* UI는 코드로 작성하지 않았습니다.

|               메인 페이지                |               상품 페이지                |
| :---------------------------------:   |    :-------------------------------:   |
| <img src="https://user-images.githubusercontent.com/64251951/206711006-1569537c-e885-4112-9caa-cd3d22dc1693.png" width="350" height="450"/>|<img src="https://user-images.githubusercontent.com/64251951/206712660-d4c0c0ee-667f-4f88-91be-d5596d7881d5.png" width="350" height="450"/>
|1. 상품의 이름과 카테고리 이름으로 상품을 검색할 수 있습니다.<br/>2. 사용자들이 자주 찾는 인기상품의 카테고리는 미리 지정하여 <br/>카테고리 이름으로 상품을 검색할 수 있습니다.|              3. 카테고리 이름이나 상품이름을 통해 검색한 결과를 보여준다.|
|               상품 주문 페이지                |               주문 상품 확인 페이지               |
| <img src="https://user-images.githubusercontent.com/64251951/206713670-f1f649db-5db8-4bdf-ba89-ff5c772999d0.png" width="350" height="450"/>|<img src="https://user-images.githubusercontent.com/64251951/206715821-dca38d68-d9bc-4522-a8c9-5c4880b1b02b.png" width="350" height="450"/>
|4. 원하는 상품을 구매하기 위해 클릭했을때의 모습입니다.<br/> 5. 정기방문서비스 신청시 사용자 마이페이지에서 정기 방문을<br/>신청한 상품 목록을 한눈에 볼 수 있고 3개월(정기 기간)이 지나면 <br/>자동으로 방문서비스를 요청할 수 있는 탭이 생성되어 원하는 <br/> 방문 날짜와 주소를 입력하면 편리하게 방문 서비스를 이용할 수 있다.<br/> 6. 상품의 색상과 개수를 선택하여 구매 할 수 있다. | 7. 회원이 주문한 상품의 목록을 확인 할 수 있다. <br/> 8. 주문을 취소하고 싶은 상품에 대해 주문을 취소 할 수있다.|
|               배달 완료 확인 페이지                |               방문 서비스 신청 상품 목록 페이지              |
|<img src="https://user-images.githubusercontent.com/64251951/206716102-53a2230f-52f7-40b7-a777-c36398a2d725.png" width="350" height="450"/>|<img src="https://user-images.githubusercontent.com/64251951/206717282-64adac5a-da15-47a5-b83b-a5ca7eda5521.png" width="350" height="450"/>
|9. 배송이 완료된 주문에 대해 상품들을 보여준다. | 10. 이용중인 정기 방문서비스에 대해 보여주는 페이지<br/> 11. 3개월(정기 기간)이 지나지 않았지만 해당 상품에 대해 방문서비스를 이용할 수 있다. (별도 비용이 차감)<br/>12. 3개월(정기 기간)이 지난 상품에 대해 상품 목록을 조회 할 수 있다.|
|정기 방문서비스 신청 대상 상품 확인 페이지 |
|<img src="https://user-images.githubusercontent.com/64251951/206717833-9cba0563-e8b1-4f30-b6b6-8c0fd8bb04a1.png" width="350" height="450"/>|
|13. 구매 상품 중 3개월이 지난 상품에 대해 정기 방문 서비스를 이용할 수 있다.<br/>14. 원하는 방문 날짜와 주소를 기입 후 손쉽게 방문서비스를 이용할 수 있다.|
----------------------



# 🙋‍♂️ 배포 API 문서
* http://3.37.65.27:8080/swagger-ui.html





 # 🎹 ERD
 * https://www.erdcloud.com/d/kL6LwTHqD6BB99Cti


