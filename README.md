# BaseAndroidApp
---
**此项目是作为开发新App的模板，包含一些基本的属性设置**


## 包含模块
### 1. 透明状态栏和导航栏适配
不同的Android版本适配方案都不一样，
- Android 4.4 (API 19) 之前：状态栏和导航栏都是黑色
- Android 4.4 (API 19) 及以上可以在Activity的style中添加以下属性：
    ``` xml
  <item name="android:windowTranslucentStatus">true</item>
  <item name="android:windowTranslucentNavigation">true</item>
    ```
    但在Android 5.0 (API 21) 之后，状态栏表现为半透明
- Android 5.0 (API 21) 及以上可以在Activity的style中添加以下属性：
    ``` xml
  <item name="android:windowDrawsSystemBarBackgrounds">true</item>
  <item name="android:statusBarColor">@color/transparent</item>
  <item name="android:windowTranslucentNavigation">true</item>
    ```
### 等待添加...
