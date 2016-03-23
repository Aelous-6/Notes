/**
  * ȫ���ṩһ��Toastʵ��.
  */
public class ToastUtil {  
  
    private static Context context = BaseApplication.getInstance();// App����������ΨһContext��BaseApplication�̳�Application  
    private static LayoutInflater inflater = LayoutInflater.from(context);// ���ּ���  
    private static View myToastView = inflater.inflate(R.layout.layout_top_toast, null);  
    private static TextView msgView = (TextView) myToastView.findViewById(R.id.tv_msg_text);  
  
    private static final int TYPE_CODE_SUCCESS = 0x01;  
    private static final int TYPE_CODE_ERROR = 0x02;  
    private static final int COLOR_SUCCESS = context.getResources().getColor(R.color.msg_status_success);  
    private static final int COLOR_ERROR = context.getResources().getColor(R.color.msg_status_warn);  
    private static final int DEFAULT_TIME_DELAY = 50;// ��λ������  
  
    private static Toast toast;// ϵͳ��ʾ��  
    private static Handler handler;  
  
    public static void showSuccessMsg(int msgResId) {  
        try {  
            showSuccessMsg(context.getString(msgResId));  
        } catch (Resources.NotFoundException e) {  
            e.printStackTrace();  
        }  
    }  
  
    public static void showErrorMsg(int msgResId) {  
        try {  
            showErrorMsg(context.getString(msgResId));  
        } catch (Resources.NotFoundException e) {  
            e.printStackTrace();  
        }  
    }  
  
    public static void showSuccessMsg(String msg) {  
        showMsg(TYPE_CODE_SUCCESS, msg);  
    }  
  
    public static void showErrorMsg(String msg) {  
        showMsg(TYPE_CODE_ERROR, msg);  
    }  
  
    private static void showMsg(final int typeCode, final String msg) {  
        if (context == null//  
                || !ApplicationUtil.isRunningForeground(context)// ���APP�ص���̨������ʾ  
                || msg == null) {  
            return;  
        }  
  
        if (toast == null) {// ��ֹ�ظ���ʾ����ΪNull����ȫ��ʹ��ͬһ��Toastʵ��  
            toast = new Toast(context);  
        }  
  
        if (handler == null) {  
            handler = new Handler();  
        }  
  
        handler.postDelayed(new Runnable() {  
            @Override  
            public void run() {  
                int msgViewBagColor = 0;  
                switch (typeCode) {  
                    case TYPE_CODE_SUCCESS:  
                        msgViewBagColor = COLOR_SUCCESS;  
                        break;  
                    case TYPE_CODE_ERROR:  
                        msgViewBagColor = COLOR_ERROR;  
                        break;  
                    default:  
                        msgViewBagColor = COLOR_SUCCESS;  
                        break;  
                }  
                msgView.setBackgroundColor(msgViewBagColor);  
                msgView.setText(msg);  
                toast.setView(myToastView);  
                toast.setGravity(Gravity.TOP | Gravity.FILL_HORIZONTAL, 0, 0);// ��������  
                toast.setDuration(Toast.LENGTH_SHORT);  
                toast.show();  
  
            }  
        }, DEFAULT_TIME_DELAY);  
    }  
  
    // �ݲ������ṩ����Ҫ�����Ҫ��ĳ��ʱ��ȡ����ʾ  
    private static void cancelToast() {  
        if (toast != null) {  
            toast.cancel();  
            toast = null;  
        }  
    }  
}  
