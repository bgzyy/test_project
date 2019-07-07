# test_project
Hand 摸底考核（从新浪网上爬取近十年的股票历史信息）
1. 开发环境：IDEA + Docker + MySQL + SSM + Jsoup

介绍：
  1. 使用 Jsoup 爬取网站源码中爬取历史数据，并将数据批量保存到 MySQL 数据库（下载入口为 Main 类）；
  2. 提供所有股票信息的展示、查询入口，可以使用股票代码或者股票名称进行查询，以及展示每支股票的历史数据信息；
  3. 该项目使用 Jsoup 从源码爬取数据相比于爬取接口返回的 JSON 数据效率较低，因为送页面爬取程序需要渲染页面；
  4. 项目中使用蘑菇代理防止 IP 被封；
  5. 项目中下载数据时没能下载涨幅等更加全面的信息，将所有的数据（81w）全部存在一张表中，导致全表查询很慢；
  6. 下载的数据由于没有涨幅数据，所以自己使用涨幅计算公式计算了涨幅再次存入数据库然后在页面上进行了展示；
  7. 对于项目需求，定时任务还没有完成，打算使用 quartz 定时任务框架完成，此次爬取数据时将会使用抓包工具进行分析，爬取 JSON 数据；
  8. 后面对项目进行迭代，通过接口爬取历史数据，然后按照区域分表插入数据，可以减少查询时对数据库的压力；
 
 步骤：
  1. 克隆当前到项目到本地开发环境，部署好所需环境开始下载数据
  2. 启动 Main 类开始下载数据，在这之前需要更改 PageInfo 类中的 appkey（从蘑菇代理网站获取）以及对于数据库的配置（db.properties）
  3. 下载完收据之后进入项目启动 WEB 应用程序即可
