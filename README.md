# CGA2spring_Boot




作品說明：

1.使用自學的Spring_Boot框架改寫養成班專案的商城區部分與會員區，再利用GCP上的ubuntu作業系統，
安裝docker後，進行佈署。

1. 會員功能：登入出、註冊、修改會員資料、訂單查詢、買後商品評論。

2. 商品區功能：商品放入購物車、商品頁面查詢與篩選、成立訂單。

2.撰寫單元測試：分別測試Spring MVC架構，包含controller層、service層及modle層，使用H2DB搭配初始化的.sql檔進行測試。

3.作品網站連結: http://35.221.248.179:8999/CGA101G1/frontend/Product/HomePageinshop.html

4.版本：JDK-17


資料夾說明

    |-- src
        |-- main // 程式資料夾
            |-- java
                |-- com.cj.cga101g1
                    |-- 服務名稱
                        |--controller
                        |--dao
                        |--service
                        |--util


1. controller--依照RESTful API風格提供API給予前端。
2. dao       --藉由連接GCP上的MySQL，使用spring data jpa進行資料處理。
3. service   --接續dao層及controller層，處理商業邏輯。
4. util      --放置pojo。




測試資料夾說明
1. controller--利用MockMvc測試controller層的查詢 / 新增 / 修改 / 刪除 功能 API(實作範圍：gameplatformtype)。
2. service--利用Mockito進行service層的查詢 / 新增/ 修改 / 刪除之測試(實作範圍：gameplatformtype)。
3. model--測試modle層的新增 / 修改 / 刪除功能。
