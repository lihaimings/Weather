# Weather
一款天气类项目app
1. 这个项目用到的开源库有Litepale、OkHttp、Gson、Glide
2. 工具android studio3.2 api28
- 项目中遇到了一个问题
1. 就是在使用okhttp的http请求网址得不到回应，谷歌了一下，说okhttp在api28以上是默认不请求Http的，只会请求https得数据。
要想使用http请求看这篇文章: 
[Android 9.0/P http 网络请求的问题](https://blog.csdn.net/cgq1030699125/article/details/85157148)