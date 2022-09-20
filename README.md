# CGA2spring_Boot




作品說明
1. 使用自學的Spring_Boot框架改寫養成班專案的商城區部分，再利用GCP上的ubuntu作業系統，
安裝docker後，進行佈署。


2. 撰寫單元測試：分別測試Spring MVC架構，包含controller層、service層及modle層，使用H2DB搭配初始化的.sql檔進行測試。




資料夾說明
1. controller--依照RESTful API風格提供API給予前端(實作範圍：gameplatformtype、gametype、product、productPic)。
2. dao--藉由連接GCP上的MySQL，使用SQL語法或是spring data jpa進行資料處理(實作範圍：gameplatformtype、gametype、gamecompany、product、productPic、orderdetail)。
3. model--pojo部分。
4. service --接續model層級controller層。




測試資料夾說明
1. controller--利用MockMvc測試controller層的查詢 / 新增 / 修改 / 刪除 功能 API(實作範圍：gameplatformtype)。
2. service--利用Mockito進行service層的查詢 / 新增之測試(實作範圍：gameplatformtype)。
3. model--測試modle層的新增 / 修改 / 刪除功能。
