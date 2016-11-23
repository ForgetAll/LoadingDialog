# LoadingDialog

[![](https://jitpack.io/v/ForgetAll/LoadingDialog.svg)](https://jitpack.io/#ForgetAll/LoadingDialog)

### 如何使用
Step 1. 把这玩意加到你的build.gradle里:

	allprojects {
		repositories {
			...
			maven { url "https://jitpack.io" }
		}
	}
Step 2. 加上这个依赖

	dependencies {
	        compile 'com.github.ForgetAll:LoadingDialog:v1.0.4'
	}



### 使用方法与效果图
如何设置默认的样式，并使之影响全局：
提供了一个StyleManager类，通过提供的方法设置一些属性，请在application里进行这个初始化工作：
```java
StyleManager s = new StyleManager();

//在这里调用方法设置s的属性
//code here...
s.Anim(false).repeatTime(0).contentSize(-1).intercept(true);

LoadingDialog.initStyle(s);
```
更多的属性设置可以参考类->[StyleManager](https://github.com/ForgetAll/LoadingDialog/blob/master/LoadingDialog/src/main/java/com/xiasuhuei321/loadingdialog/manager/StyleManager.java)


展示一个loading dialog：
```java
new LoadingDialog(this)
    .setLoadingText("加载中...")//设置loading时显示的文字
	.show();
```
效果图：
![loading.gif](http://upload-images.jianshu.io/upload_images/1976147-6ec5b30b9fd59023.gif?imageMogr2/auto-orient/strip)

展示一个loading dialog并在合适的时机返回正确或者错误的反馈给用户：
```java
LoadingDialog ld = new LoadingDialog(this);
ld.setLoadingText("加载中")
         .setSuccessText("加载成功")//显示加载成功时的文字
         //.setFailedText("加载失败")
         .setInterceptBack(intercept_back_event)
         .setLoadSpeed(speed)
         .setRepeatCount(repeatTime)
         .setDrawColor(color)
         .show();

//在你代码中合适的位置调用反馈
ld.loadSuccess();
//ld.loadFailed();
```
效果图：

![loadSuccess.gif](https://github.com/ForgetAll/LoadingDialog/blob/master/screen/loadSuccess.gif)
![loadFailed.gif](https://github.com/ForgetAll/LoadingDialog/blob/master/screen/loadFailed.gif)

还是同样的蜜汁小圆点。。。因为不摸他 加载结束他就不录了，辣鸡AS。。。

如果你不想要这个动态画出来的效果，你也可以通过closeSuccessAnim()或者closeFailedAnim()关闭它：
```java
LoadingDialog ld = new LoadingDialog(this);
ld.setLoadingText("加载中")
         .setSuccessText("加载成功")
         .setInterceptBack(intercept_back_event)
         .setLoadSpeed(speed)
         .closeSuccessAnim()
         .setDrawColor(color)
         .setRepeatCount(repeatTime)
         .show();
```
![fail_no_anim.gif](https://github.com/ForgetAll/LoadingDialog/blob/master/screen/fail_no_anim.gif)

### 如何与Rx搭配使用？
项目的sample里有一个使用Rx的例子，各位可以参考一下，这个例子来自于热心的前辈——[猫哥](http://blog.csdn.net/neverwoods/article/category/6368309)， 感谢猫哥对于我的大力支持~还有[越越](http://www.jianshu.com/users/8c4757fd3c5e/latest_articles)的一些建议，恩，感觉还有一些事没做完，留在以后的版本吧~（flag已立）

提供你使用的一些方法：
* setSize(int size)：可以通过这个来设置弹框的尺寸，首先我这要求长宽相等的，所以只给一个参数设置他的尺寸就行了。再者我在自定义View里也会把不同的长宽处理成一样的。在项目中有一个SizeUtils，在设置尺寸的时候注意用这个工具将dp转换成px。
* show()：展示你设置的loadingDialog
* close()：关闭动画释放一些资源
* setLoadingText(String msg)：设置Loading时的文字
* setSuccessText(String msg)：设置Loading成功时文字
* setFailed(String msg)：设置Loading失败时的文字
* loadSuccess()：调用这个方法展示一个成功的反馈
* loadFailed()：调用这个方法展示一个失败的反馈
* closeSuccessAnim()：关闭成功反馈的动态绘制
* closeFailedAnim()：关闭失败反馈的动态绘制
* setInterceptBack(boolean interceptBack)：是否拦截用户back，如果设置为true（默认也为true），那么一定要调用close()，或者loadSuccess()、loadFailed()这二者中的一个，不然出现无限loading的情况我相信你不会想看到的。
* getInterceptBack()：返回dialog是否拦截的布尔值
* setLoadSpeed(Speed speed)：参数是一个枚举，一共两个值，SPEED_ONE是比较慢的，SPEED_TWO比前一个快一点，为毛不再加？处理起来比较麻烦...
* setDrawColor(int color)：可以改变绘制的颜色，圆和里面的勾啊，叉啊的颜色，不建议你用，不一定好看。
* setRepeatCount(int count)：设置动态绘制的次数，比如你设置了值为1，那么除了加载的时候绘制一次，还会再绘制一次。如果你有这个需要，可以设置他的重绘次数。
* setShowTime(long time)：设置反馈结果窗口的展示时间，默认为1秒，如果有绘制过程，则从绘制完成之后算。

目前还有一些没完善，在后续的更新中搞定吧。


# License
Copyright 2016 xiasuhuei321

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
