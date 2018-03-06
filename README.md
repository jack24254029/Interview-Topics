# InterView Topic

## 規格需求

- 透過http://www.cwb.gov.tw/V7/service/eservice/rss.htm取得台中市最近一週的天氣預報(日期、溫度、天期...)
- 取得的天氣資料存進Locale DB
- 頁面UI必須提供資料列表可瀏覽天氣資料
- 資料列表必須提供刪除某一筆資料的功能
- 抓取 https://tw.appledaily.com/index/dailyquote/ 提供的每日一句，一併顯示於畫面中
- 程式架構須符合MVC-N
  https://academy.realm.io/posts/slug-marcus-zarra-exploring-mvcn-swift/
  Tips:
  建議使用 Broadcast在模組間傳遞資訊

## 專案使用技術

- 架構：MVP (先暫時保留網路層)
- Locale DB：Room
- 網路框架：OkHttp3

## 目錄結構

- main
  - database
  - model
  - presenter
    - IMainPresenter
    - MainPresenter
  - view
    - IMainView
    - MainView
- utils
  - Utils
  - Constants