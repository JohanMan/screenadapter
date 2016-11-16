# screenadapter

使用sw<n>dp方式, 适配Android各种分辨率屏幕。

## 计算方式
### dp = px * 160 / densityDpi, 根据这个公式，我们可以得到测试机的最小宽度dp, 如小米4手机(分辨率1080x1920, dpi为480), 最小宽度=1080x160/480=360, 所以是属于sw360dp

## 使用
### 源码是我用eclipse平台写的Java工具类, 用于生成各种不同分辨率dp的demins.xml文件，仅供参考
