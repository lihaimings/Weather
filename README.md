# Weather
一款天气类项目app
1. 这个项目用到的开源库有Litepale、OkHttp、Gson、Glide
2. 工具android studio3.2 api28
- 项目中遇到了一个问题
1. 就是在使用okhttp的http请求网址得不到回应，谷歌了一下，说okhttp在api28以上是默认不请求Http的，只会请求https得数据。
要想使用，则要在main/res 中新建一个名为"xml"文件夹，并在这个文件夹新建一个xml文件，在里面写<network-security-config>
    <base-config cleartextTrafficPermitted="true"/>
</network-security-config>。最后再Manifest.xml中配置<application  android:networkSecurityConfig="@xml/xml_name" >