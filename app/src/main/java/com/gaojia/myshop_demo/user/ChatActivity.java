package com.gaojia.myshop_demo.user;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.gaojia.myshop_demo.R;
import com.gaojia.myshop_demo.base.BaseActivity;
import com.hyphenate.EMMessageListener;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.chat.EMTextMessageBody;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/3/23.
 */

public class ChatActivity extends BaseActivity {

   @BindView(R.id.tv_received_chat)
    TextView tvReceived;
    @BindView(R.id.et_receiver_chat)
    EditText etReceiver;
    @BindView(R.id.et_content_chat)
    EditText etContent;
    @BindView(R.id.btn_send_chat)
    Button btnSend;



    Handler mHandler = new Handler();

    MyMessageListener ml;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        setContentView(R.layout.activity_chat);
        init();
    }

    private void init() {
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            //发送消息功能
                //创建消息对象
                EMMessage message = EMMessage.createFileSendMessage(etContent.getText().toString(), etReceiver.getText().toString());
                //发送消息
                EMClient.getInstance().chatManager().sendMessage(message);
            }
        });
        ml = new MyMessageListener();
        EMClient.getInstance().chatManager().addMessageListener(ml);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //撤销
        EMClient.getInstance().chatManager().removeMessageListener(ml);
    }

    class MyMessageListener implements EMMessageListener{

        @Override
        public void onMessageReceived(List<EMMessage> list) {
            //获取接受到消息

            //对消息进行解析
            for (EMMessage node : list){
               //解析发送者用户名
                final String from = node.getFrom();
                //解析消息类型
                EMMessage.Type type = node.getType();
                switch (type){
                    case  TXT:
                        //如果是文本消息进行转换
                        EMTextMessageBody body = (EMTextMessageBody) node.getBody();
                        //得到文本内容
                        final String content = body.getMessage();
                        //将修改内容的工程交给主线程
                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                tvReceived.append(from + "对你说" + content);
                            }
                        });
                        break;
                    default:
                        break;
                }
            }
        }

        @Override
        public void onCmdMessageReceived(List<EMMessage> list) {

        }

        @Override
        public void onMessageRead(List<EMMessage> list) {

        }

        @Override
        public void onMessageDelivered(List<EMMessage> list) {

        }

        @Override
        public void onMessageChanged(EMMessage emMessage, Object o) {

        }
    }
}
