# InterView-Topics

## 基本資訊
- Android系統最低需求為Android Lollipop (5.0)

## 功能需求

- 透過 <http://www.cwb.gov.tw/V7/service/eservice/rss.htm> 取得台中市 最近一週的天氣預報, 抓取日期, 溫度, 天氣 …
- 資料取得之後存入 local 資料庫
- 頁面 UI 提供資料列表瀏灠上列資料
- 提供刪除某一筆資料的功能
- 抓取 <https://tw.appledaily.com/index/dailyquote/> 網頁的每日一句，一併顯示於畫面中。

加分: (可不實作)

- 程式架構須符合 MVC-n
- <https://academy.realm.io/posts/slug-marcus-zarra-exploring-mvcn-swift/>
- Tips:建議使用 Broadcast在模組間傳遞資訊

## 專案技術

- 專案架構：MVP-N
- 資料庫：[Room](https://developer.android.com/topic/libraries/architecture/room.html)
- 第三方Library：
  - [OkHttp3](https://github.com/square/okhttp/tree/master/okhttp/src/main/java/okhttp3)
  - [Jsoup](https://github.com/jhy/jsoup)
  - [RssParser](https://github.com/prof18/RSS-Parser)
- Unit Test：[JUnit](https://developer.android.com/training/testing/unit-testing/local-unit-tests.html)
- UI Test：[Espresso](https://developer.android.com/training/testing/espresso/index.html)

## Unit Test

- EntityTest
  - 測試Room寫入資料
  - 測試Room讀取資料
  - 測試Room刪除資料

## UI Test

- MainViewTest 
  - 檢查1秒內取得一週天氣，且要有14筆資料
  - 檢查3秒內取得每日一句

## 目錄結構

- database
  - dao
    - WeatherDao.java
  - entities
    - Weather.java
  - service
    - DBJobService.java
  - AppDatabase.java
- main
  - model
    - DailyQuote.java
  - presenter
    - IMainPresenter.java
    - MainPresenter.java
  - view
    - IMainView.java
    - MainView.java
- network
  - NetwrokController.java
- utils
  - Utils.java
  - Constants.java

## 實機畫面

### 主畫面

## ![2018-03-07 23.29.20](https://github.com/jack24254029/Interview-Topics/blob/master/img/2018-03-07%2023.29.20.jpg)

### 長按列表可以刪除該筆資料

![2018-03-07 23.29.35](https://github.com/jack24254029/Interview-Topics/blob/master/img/2018-03-07%2023.29.35.jpg)

### 如果沒有網路會提示

![2018-03-07 23.29.41](https://github.com/jack24254029/Interview-Topics/blob/master/img/2018-03-07%2023.29.41.jpg)

### 右上方提供重新整理功能，會重新取得每日一句及一週天氣預報

![2018-03-07 23.29.46](https://github.com/jack24254029/Interview-Topics/blob/master/img/2018-03-07%2023.29.46.jpg)

