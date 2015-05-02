package com.parcool.myshop.application;

import java.io.File;

import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.utils.StorageUtils;
import com.parcool.myshop.R;
import com.umeng.socialize.utils.Log;

import android.app.Application;
import android.os.Environment;

public class UILApplication extends Application {

	private static final int DISK_CACHE_SIZE_BYTES = 50 * 1024 * 1024;
	private static final int MEMORY_CACHE_SIZE_BYTES = 2 * 1024 * 1024;

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		initUniversalImageLoader();
	}

	@SuppressWarnings("deprecation")
	private void initUniversalImageLoader() {
		File cacheDir = StorageUtils.getOwnCacheDirectory(this, Environment.getExternalStorageDirectory().getPath() + "/myShop/imageloader/Cache");
		DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder().cacheInMemory(true)// 设置下载的图片是否缓存在内存
				.cacheOnDisk(true)// 设置下载的图片是否缓存在SD卡中
				.displayer(new FadeInBitmapDisplayer(500))
				.showImageOnLoading(R.drawable.img_loading)// 设置下载期间显示的图
				.showImageForEmptyUri(R.drawable.img_missed)// 设置图片URI为空或是错误的时候显示的图片
				.showImageOnFail(R.drawable.img_loading)// 设置图片加载/解码过程中错误时候显示的图片
				.imageScaleType(ImageScaleType.EXACTLY)// 设置图片以如何的编码方式显示
				.bitmapConfig(android.graphics.Bitmap.Config.RGB_565)// 设置图片的解码类型
				.build();

		// ImageLoaderConfiguration config = new
		// ImageLoaderConfiguration.Builder(
		// this)
		// .defaultDisplayImageOptions(defaultOptions)
		// //
		// .diskCacheSize(DISK_CACHE_SIZE_BYTES)
		// .memoryCacheSize(MEMORY_CACHE_SIZE_BYTES)
		// .diskCache(new UnlimitedDiscCache(cacheDir))
		//
		// .memoryCache(new WeakMemoryCache()).threadPoolSize(3).build();

		ImageLoaderConfiguration.Builder builder = new ImageLoaderConfiguration.Builder(this);
		builder.defaultDisplayImageOptions(defaultOptions);
		// 设置线程数量
		builder.threadPoolSize(3);
		// 设定线程等级比普通低一点
		builder.threadPriority(Thread.NORM_PRIORITY);
		// 设定内存缓存为弱缓存
		builder.memoryCache(new WeakMemoryCache());
		// 设定内存图片缓存大小限制，不设置默认为屏幕的宽高
		builder.memoryCacheExtraOptions(480, 800);
		// 设定只保存同一尺寸的图片在内存
		builder.denyCacheImageMultipleSizesInMemory();
		// 设定缓存的SDcard目录，UnlimitDiscCache速度最快
		builder.discCache(new UnlimitedDiscCache(cacheDir));
		builder.diskCacheSize(DISK_CACHE_SIZE_BYTES);
		builder.memoryCacheSize(MEMORY_CACHE_SIZE_BYTES);
		ImageLoader tmpIL = ImageLoader.getInstance();
		tmpIL.init(builder.build());// 全局初始化此配置
	}

	@Override
	public void onLowMemory() {
		super.onLowMemory();
		ImageLoader.getInstance().clearMemoryCache();
		Log.d("tag", "哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈");
	}
}
