public class BitmapCompressUtil{
	/**
	 *@param bitmap 要进行质量压缩的bitmap对象. 
	 *@param size  压缩后的最大体积
	 *
	 *@return 压缩后的Bitmap对象.
	 */
	public static Bitmap CompressImageQuality(Bitmap bitmap,int size){
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		bitmap.compress(Bitmap.CompressFormat.JPEG,100,bos);
		int options=100;
		while(bos.toByteArray().length/1024 > size){
			bos.reset();
			bitmap.compress(Bitmap.CompressFormat.JPEG,options,bos);
			options -= 10;
		}
		ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
		Bitmap compressBitmap = BitmapFactory.decodeStrem(bis,null,null);
		return compressBitmap;
	}
	/**
	 * 尺寸压缩 使用时要对该方法的参数修改，添加一个被压缩的图片，返回一个压缩后的图片
	 */
	public void scalePic(int reqWidth,int reqHeight) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(getResources(), R.mipmap.demo, options);
        options.inSampleSize = PhotoUtil.calculateInSampleSize(options, reqWidth,reqHeight);
        options.inJustDecodeBounds = false;
        bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.demo, options);

        postInvalidate();
    }
	
	public static int calculateInSampleSize(BitmapFactory.Options options,int reqWidth, int reqHeight) {
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;
        if (height > reqHeight || width > reqWidth) {
            final int heightRatio = Math.round((float) height / (float) reqHeight);
            final int widthRatio = Math.round((float) width / (float) reqWidth);
            inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
        }
        return inSampleSize;
    }
}
