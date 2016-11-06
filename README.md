# LoadingDialog

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
	        compile 'com.github.ForgetAll:LoadingDialog:v1.0.0'
	}

### 使用方法与效果图
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

![
![loadSuccess.gif](http://upload-images.jianshu.io/upload_images/1976147-3af85bae65a0b51d.gif?imageMogr2/auto-orient/strip)
](http://upload-images.jianshu.io/upload_images/1976147-efca9a1ec133b457.gif?imageMogr2/auto-orient/strip)

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
![fail_no_anim.gif](http://upload-images.jianshu.io/upload_images/1976147-ea1a4a0b33393d5d.gif?imageMogr2/auto-orient/strip)
")

提供你使用的一些方法：
* setSize(int size)：可以通过这个来设置弹框的尺寸
* show()：展示你设置的loadingDialog
* close()：关闭动画释放一些资源
* setLoadingText(String msg)：设置Loading时的文字
* setSuccessText(String msg)：设置Loading成功时文字
* setFailed(String msg)：设置Loading失败时的文字
* loadSuccess()：调用这个方法展示一个成功的反馈
* loadFailed()：调用这个方法展示一个失败的反馈
* closeSuccessAnim()：关闭成功反馈的动态绘制
* closeFailedAnim()：关闭失败反馈的动态绘制
* setInterceptBack(boolean interceptBack)：是否拦截用户back，如果设置为true，那么一定要调用close()，不然用户只能把你的程序干掉才能退出了，在我的例子中有一个解决的思路你可以参考一下。
* getInterceptBack()：返回dialog是否拦截的布尔值
* setLoadSpeed(Speed speed)：参数是一个枚举，一共两个值，SPEED_ONE是比较慢的，SPEED_TWO比前一个快一点，为毛不再加？处理起来比较麻烦...
* setDrawColor(int color)：可以改变绘制的颜色，圆和里面的勾啊，叉啊的颜色，不建议你用，不一定好看。
* setRepeatCount(int count)：设置动态绘制的次数，比如你设置了值为1，那么除了加载的时候绘制一次，还会再绘制一次。如果你有这个需要，可以设置他的重绘次数。

目前还有一些没完善，在后续的更新中搞定吧。

